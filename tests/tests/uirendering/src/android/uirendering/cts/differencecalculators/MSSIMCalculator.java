/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package android.uirendering.cts.differencecalculators;

import com.android.cts.uirendering.R;
import com.android.cts.uirendering.ScriptC_MSSIMCalculator;

import android.content.res.Resources;
import android.graphics.Color;
import android.renderscript.Allocation;
import android.renderscript.RenderScript;
import android.util.Log;

/**
 * Image comparison using Structural Similarity Index, developed by Wang, Bovik, Sheikh, and
 * Simoncelli. Details can be read in their paper :
 *
 * https://ece.uwaterloo.ca/~z70wang/publications/ssim.pdf
 */
public class MSSIMCalculator extends BaseRenderScriptCalculator{
    // These values were taken from the publication
    public static final boolean DEBUG = false;
    public static final String TAG_NAME = "MSSIMCalculator";
    public static final double CONSTANT_L = 254;
    public static final double CONSTANT_K1 = 0.00001;
    public static final double CONSTANT_K2 = 0.00003;
    public static final double CONSTANT_C1 = Math.pow(CONSTANT_L * CONSTANT_K1, 2);
    public static final double CONSTANT_C2 = Math.pow(CONSTANT_L * CONSTANT_K2, 2);
    public static final int WINDOW_SIZE = 3;

    private double mThreshold;
    private ScriptC_MSSIMCalculator mScript;

    public MSSIMCalculator(double threshold) {
        mThreshold = threshold;
    }

    @Override
    public boolean verifySame(int[] ideal, int[] given, int offset, int stride, int width,
            int height) {
        double SSIMTotal = 0;
        int windows = 0;

        for (int currentWindowY = 0 ; currentWindowY < height ; currentWindowY += WINDOW_SIZE) {
            for (int currentWindowX = 0 ; currentWindowX < width ; currentWindowX += WINDOW_SIZE) {
                int start = indexFromXAndY(currentWindowX, currentWindowY, stride, offset);
                if (isWindowWhite(ideal, start, stride) && isWindowWhite(given, start, stride)) {
                    continue;
                }
                windows++;
                double[] means = getMeans(ideal, given, start, stride);
                double meanX = means[0];
                double meanY = means[1];
                double[] variances = getVariances(ideal, given, meanX, meanY, start, stride);
                double varX = variances[0];
                double varY = variances[1];
                double stdBoth = variances[2];
                double SSIM = SSIM(meanX, meanY, varX, varY, stdBoth);
                SSIMTotal += SSIM;
            }
        }

        if (windows == 0) { //if they were both white screens then we are good
            return true;
        }

        SSIMTotal /= windows;

        if (DEBUG) {
            Log.d(TAG_NAME, "MSSIM = " + SSIMTotal);
        }

        return (SSIMTotal >= mThreshold);
    }

    @Override
    public boolean verifySameRowsRS(Resources resources, Allocation ideal,
            Allocation given, int offset, int stride, int width, int height,
            RenderScript renderScript, Allocation inputAllocation, Allocation outputAllocation) {
        if (mScript == null) {
            mScript = new ScriptC_MSSIMCalculator(renderScript, resources,
                    R.raw.mssimcalculator);
        }
        mScript.set_WIDTH(width);
        mScript.set_HEIGHT(height);

        //Set the bitmap allocations
        mScript.set_ideal(ideal);
        mScript.set_given(given);

        //Call the renderscript function on each row
        mScript.forEach_calcSSIM(inputAllocation, outputAllocation);

        float MSSIM = sum1DFloatAllocation(outputAllocation);
        MSSIM /= height;

        if (DEBUG) {
            Log.d(TAG_NAME, "RenderScript MSSIM = " + MSSIM);
        }

        return (MSSIM >= mThreshold);
    }

    private boolean isWindowWhite(int[] colors, int start, int stride) {
        for (int y = 0 ; y < WINDOW_SIZE ; y++) {
            for (int x = 0 ; x < WINDOW_SIZE ; x++) {
                if (colors[indexFromXAndY(x, y, stride, start)] != Color.WHITE) {
                    return false;
                }
            }
        }
        return true;
    }

    private double SSIM(double muX, double muY, double sigX, double sigY, double sigXY) {
        double SSIM = (((2 * muX * muY) + CONSTANT_C1) * ((2 * sigXY) + CONSTANT_C2));
        double denom = ((muX * muX) + (muY * muY) + CONSTANT_C1)
                * (sigX + sigY + CONSTANT_C2);
        SSIM /= denom;
        return SSIM;
    }


    /**
     * This method will find the mean of a window in both sets of pixels. The return is an array
     * where the first double is the mean of the first set and the second double is the mean of the
     * second set.
     */
    private double[] getMeans(int[] pixels0, int[] pixels1, int start, int stride) {
        double avg0 = 0;
        double avg1 = 0;
        for (int y = 0 ; y < WINDOW_SIZE ; y++) {
            for (int x = 0 ; x < WINDOW_SIZE ; x++) {
                int index = indexFromXAndY(x, y, stride, start);
                avg0 += getIntensity(pixels0[index]);
                avg1 += getIntensity(pixels1[index]);
            }
        }
        avg0 /= WINDOW_SIZE * WINDOW_SIZE;
        avg1 /= WINDOW_SIZE * WINDOW_SIZE;
        return new double[] {avg0, avg1};
    }

    /**
     * Finds the variance of the two sets of pixels, as well as the covariance of the windows. The
     * return value is an array of doubles, the first is the variance of the first set of pixels,
     * the second is the variance of the second set of pixels, and the third is the covariance.
     */
    private double[] getVariances(int[] pixels0, int[] pixels1, double mean0, double mean1,
            int start, int stride) {
        double var0 = 0;
        double var1 = 0;
        double varBoth = 0;
        for (int y = 0 ; y < WINDOW_SIZE ; y++) {
            for (int x = 0 ; x < WINDOW_SIZE ; x++) {
                int index = indexFromXAndY(x, y, stride, start);
                double v0 = getIntensity(pixels0[index]) - mean0;
                double v1 = getIntensity(pixels1[index]) - mean1;
                var0 += v0 * v0;
                var1 += v1 * v1;
                varBoth += v0 * v1;
            }
        }
        var0 /= (WINDOW_SIZE * WINDOW_SIZE) - 1;
        var1 /= (WINDOW_SIZE * WINDOW_SIZE) - 1;
        varBoth /= (WINDOW_SIZE * WINDOW_SIZE) - 1;
        return new double[] {var0, var1, varBoth};
    }

    /**
     * Gets the intensity of a given pixel in RGB using luminosity formula
     *
     * l = 0.21R' + 0.72G' + 0.07B'
     *
     * The prime symbols dictate a gamma correction of 1.
     */
    private double getIntensity(int pixel) {
        final double gamma = 1;
        double l = 0;
        l += (0.21f * Math.pow(Color.red(pixel) / 255f, gamma));
        l += (0.72f * Math.pow(Color.green(pixel) / 255f, gamma));
        l += (0.07f * Math.pow(Color.blue(pixel) / 255f, gamma));
        return l;
    }
}
