/*
 * Copyright (C) 2016 The Android Open Source Project
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

// Don't edit this file!  It is auto-generated by frameworks/rs/api/generate.sh.

package android.renderscript.cts;

import android.renderscript.Allocation;
import android.renderscript.RSRuntimeException;
import android.renderscript.Element;
import android.renderscript.cts.Target;

import java.util.Arrays;

public class TestRootn extends RSBaseCompute {

    private ScriptC_TestRootn script;
    private ScriptC_TestRootnRelaxed scriptRelaxed;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        script = new ScriptC_TestRootn(mRS);
        scriptRelaxed = new ScriptC_TestRootnRelaxed(mRS);
    }

    public class ArgumentsFloatIntFloat {
        public float inV;
        public int inN;
        public Target.Floaty out;
    }

    private void checkRootnFloatIntFloat() {
        Allocation inV = createRandomAllocation(mRS, Element.DataType.FLOAT_32, 1, 0x37d0d9514daae0ccl, false);
        Allocation inN = createRandomAllocation(mRS, Element.DataType.SIGNED_32, 1, 0x37d0d9514daae0c4l, false);
        try {
            Allocation out = Allocation.createSized(mRS, getElement(mRS, Element.DataType.FLOAT_32, 1), INPUTSIZE);
            script.set_gAllocInN(inN);
            script.forEach_testRootnFloatIntFloat(inV, out);
            verifyResultsRootnFloatIntFloat(inV, inN, out, false);
        } catch (Exception e) {
            throw new RSRuntimeException("RenderScript. Can't invoke forEach_testRootnFloatIntFloat: " + e.toString());
        }
        try {
            Allocation out = Allocation.createSized(mRS, getElement(mRS, Element.DataType.FLOAT_32, 1), INPUTSIZE);
            scriptRelaxed.set_gAllocInN(inN);
            scriptRelaxed.forEach_testRootnFloatIntFloat(inV, out);
            verifyResultsRootnFloatIntFloat(inV, inN, out, true);
        } catch (Exception e) {
            throw new RSRuntimeException("RenderScript. Can't invoke forEach_testRootnFloatIntFloat: " + e.toString());
        }
    }

    private void verifyResultsRootnFloatIntFloat(Allocation inV, Allocation inN, Allocation out, boolean relaxed) {
        float[] arrayInV = new float[INPUTSIZE * 1];
        Arrays.fill(arrayInV, (float) 42);
        inV.copyTo(arrayInV);
        int[] arrayInN = new int[INPUTSIZE * 1];
        Arrays.fill(arrayInN, (int) 42);
        inN.copyTo(arrayInN);
        float[] arrayOut = new float[INPUTSIZE * 1];
        Arrays.fill(arrayOut, (float) 42);
        out.copyTo(arrayOut);
        StringBuilder message = new StringBuilder();
        boolean errorFound = false;
        for (int i = 0; i < INPUTSIZE; i++) {
            for (int j = 0; j < 1 ; j++) {
                // Extract the inputs.
                ArgumentsFloatIntFloat args = new ArgumentsFloatIntFloat();
                args.inV = arrayInV[i];
                args.inN = arrayInN[i];
                // Figure out what the outputs should have been.
                Target target = new Target(Target.FunctionType.NORMAL, Target.ReturnType.FLOAT, relaxed);
                CoreMathVerifier.computeRootn(args, target);
                // Validate the outputs.
                boolean valid = true;
                if (!args.out.couldBe(arrayOut[i * 1 + j])) {
                    valid = false;
                }
                if (!valid) {
                    if (!errorFound) {
                        errorFound = true;
                        message.append("Input inV: ");
                        appendVariableToMessage(message, args.inV);
                        message.append("\n");
                        message.append("Input inN: ");
                        appendVariableToMessage(message, args.inN);
                        message.append("\n");
                        message.append("Expected output out: ");
                        appendVariableToMessage(message, args.out);
                        message.append("\n");
                        message.append("Actual   output out: ");
                        appendVariableToMessage(message, arrayOut[i * 1 + j]);
                        if (!args.out.couldBe(arrayOut[i * 1 + j])) {
                            message.append(" FAIL");
                        }
                        message.append("\n");
                        message.append("Errors at");
                    }
                    message.append(" [");
                    message.append(Integer.toString(i));
                    message.append(", ");
                    message.append(Integer.toString(j));
                    message.append("]");
                }
            }
        }
        assertFalse("Incorrect output for checkRootnFloatIntFloat" +
                (relaxed ? "_relaxed" : "") + ":\n" + message.toString(), errorFound);
    }

    private void checkRootnFloat2Int2Float2() {
        Allocation inV = createRandomAllocation(mRS, Element.DataType.FLOAT_32, 2, 0x2a7a849dcb32d88el, false);
        Allocation inN = createRandomAllocation(mRS, Element.DataType.SIGNED_32, 2, 0x2a7a849dcb32d886l, false);
        try {
            Allocation out = Allocation.createSized(mRS, getElement(mRS, Element.DataType.FLOAT_32, 2), INPUTSIZE);
            script.set_gAllocInN(inN);
            script.forEach_testRootnFloat2Int2Float2(inV, out);
            verifyResultsRootnFloat2Int2Float2(inV, inN, out, false);
        } catch (Exception e) {
            throw new RSRuntimeException("RenderScript. Can't invoke forEach_testRootnFloat2Int2Float2: " + e.toString());
        }
        try {
            Allocation out = Allocation.createSized(mRS, getElement(mRS, Element.DataType.FLOAT_32, 2), INPUTSIZE);
            scriptRelaxed.set_gAllocInN(inN);
            scriptRelaxed.forEach_testRootnFloat2Int2Float2(inV, out);
            verifyResultsRootnFloat2Int2Float2(inV, inN, out, true);
        } catch (Exception e) {
            throw new RSRuntimeException("RenderScript. Can't invoke forEach_testRootnFloat2Int2Float2: " + e.toString());
        }
    }

    private void verifyResultsRootnFloat2Int2Float2(Allocation inV, Allocation inN, Allocation out, boolean relaxed) {
        float[] arrayInV = new float[INPUTSIZE * 2];
        Arrays.fill(arrayInV, (float) 42);
        inV.copyTo(arrayInV);
        int[] arrayInN = new int[INPUTSIZE * 2];
        Arrays.fill(arrayInN, (int) 42);
        inN.copyTo(arrayInN);
        float[] arrayOut = new float[INPUTSIZE * 2];
        Arrays.fill(arrayOut, (float) 42);
        out.copyTo(arrayOut);
        StringBuilder message = new StringBuilder();
        boolean errorFound = false;
        for (int i = 0; i < INPUTSIZE; i++) {
            for (int j = 0; j < 2 ; j++) {
                // Extract the inputs.
                ArgumentsFloatIntFloat args = new ArgumentsFloatIntFloat();
                args.inV = arrayInV[i * 2 + j];
                args.inN = arrayInN[i * 2 + j];
                // Figure out what the outputs should have been.
                Target target = new Target(Target.FunctionType.NORMAL, Target.ReturnType.FLOAT, relaxed);
                CoreMathVerifier.computeRootn(args, target);
                // Validate the outputs.
                boolean valid = true;
                if (!args.out.couldBe(arrayOut[i * 2 + j])) {
                    valid = false;
                }
                if (!valid) {
                    if (!errorFound) {
                        errorFound = true;
                        message.append("Input inV: ");
                        appendVariableToMessage(message, args.inV);
                        message.append("\n");
                        message.append("Input inN: ");
                        appendVariableToMessage(message, args.inN);
                        message.append("\n");
                        message.append("Expected output out: ");
                        appendVariableToMessage(message, args.out);
                        message.append("\n");
                        message.append("Actual   output out: ");
                        appendVariableToMessage(message, arrayOut[i * 2 + j]);
                        if (!args.out.couldBe(arrayOut[i * 2 + j])) {
                            message.append(" FAIL");
                        }
                        message.append("\n");
                        message.append("Errors at");
                    }
                    message.append(" [");
                    message.append(Integer.toString(i));
                    message.append(", ");
                    message.append(Integer.toString(j));
                    message.append("]");
                }
            }
        }
        assertFalse("Incorrect output for checkRootnFloat2Int2Float2" +
                (relaxed ? "_relaxed" : "") + ":\n" + message.toString(), errorFound);
    }

    private void checkRootnFloat3Int3Float3() {
        Allocation inV = createRandomAllocation(mRS, Element.DataType.FLOAT_32, 3, 0x5030c300c0e54547l, false);
        Allocation inN = createRandomAllocation(mRS, Element.DataType.SIGNED_32, 3, 0x5030c300c0e5453fl, false);
        try {
            Allocation out = Allocation.createSized(mRS, getElement(mRS, Element.DataType.FLOAT_32, 3), INPUTSIZE);
            script.set_gAllocInN(inN);
            script.forEach_testRootnFloat3Int3Float3(inV, out);
            verifyResultsRootnFloat3Int3Float3(inV, inN, out, false);
        } catch (Exception e) {
            throw new RSRuntimeException("RenderScript. Can't invoke forEach_testRootnFloat3Int3Float3: " + e.toString());
        }
        try {
            Allocation out = Allocation.createSized(mRS, getElement(mRS, Element.DataType.FLOAT_32, 3), INPUTSIZE);
            scriptRelaxed.set_gAllocInN(inN);
            scriptRelaxed.forEach_testRootnFloat3Int3Float3(inV, out);
            verifyResultsRootnFloat3Int3Float3(inV, inN, out, true);
        } catch (Exception e) {
            throw new RSRuntimeException("RenderScript. Can't invoke forEach_testRootnFloat3Int3Float3: " + e.toString());
        }
    }

    private void verifyResultsRootnFloat3Int3Float3(Allocation inV, Allocation inN, Allocation out, boolean relaxed) {
        float[] arrayInV = new float[INPUTSIZE * 4];
        Arrays.fill(arrayInV, (float) 42);
        inV.copyTo(arrayInV);
        int[] arrayInN = new int[INPUTSIZE * 4];
        Arrays.fill(arrayInN, (int) 42);
        inN.copyTo(arrayInN);
        float[] arrayOut = new float[INPUTSIZE * 4];
        Arrays.fill(arrayOut, (float) 42);
        out.copyTo(arrayOut);
        StringBuilder message = new StringBuilder();
        boolean errorFound = false;
        for (int i = 0; i < INPUTSIZE; i++) {
            for (int j = 0; j < 3 ; j++) {
                // Extract the inputs.
                ArgumentsFloatIntFloat args = new ArgumentsFloatIntFloat();
                args.inV = arrayInV[i * 4 + j];
                args.inN = arrayInN[i * 4 + j];
                // Figure out what the outputs should have been.
                Target target = new Target(Target.FunctionType.NORMAL, Target.ReturnType.FLOAT, relaxed);
                CoreMathVerifier.computeRootn(args, target);
                // Validate the outputs.
                boolean valid = true;
                if (!args.out.couldBe(arrayOut[i * 4 + j])) {
                    valid = false;
                }
                if (!valid) {
                    if (!errorFound) {
                        errorFound = true;
                        message.append("Input inV: ");
                        appendVariableToMessage(message, args.inV);
                        message.append("\n");
                        message.append("Input inN: ");
                        appendVariableToMessage(message, args.inN);
                        message.append("\n");
                        message.append("Expected output out: ");
                        appendVariableToMessage(message, args.out);
                        message.append("\n");
                        message.append("Actual   output out: ");
                        appendVariableToMessage(message, arrayOut[i * 4 + j]);
                        if (!args.out.couldBe(arrayOut[i * 4 + j])) {
                            message.append(" FAIL");
                        }
                        message.append("\n");
                        message.append("Errors at");
                    }
                    message.append(" [");
                    message.append(Integer.toString(i));
                    message.append(", ");
                    message.append(Integer.toString(j));
                    message.append("]");
                }
            }
        }
        assertFalse("Incorrect output for checkRootnFloat3Int3Float3" +
                (relaxed ? "_relaxed" : "") + ":\n" + message.toString(), errorFound);
    }

    private void checkRootnFloat4Int4Float4() {
        Allocation inV = createRandomAllocation(mRS, Element.DataType.FLOAT_32, 4, 0x75e70163b697b200l, false);
        Allocation inN = createRandomAllocation(mRS, Element.DataType.SIGNED_32, 4, 0x75e70163b697b1f8l, false);
        try {
            Allocation out = Allocation.createSized(mRS, getElement(mRS, Element.DataType.FLOAT_32, 4), INPUTSIZE);
            script.set_gAllocInN(inN);
            script.forEach_testRootnFloat4Int4Float4(inV, out);
            verifyResultsRootnFloat4Int4Float4(inV, inN, out, false);
        } catch (Exception e) {
            throw new RSRuntimeException("RenderScript. Can't invoke forEach_testRootnFloat4Int4Float4: " + e.toString());
        }
        try {
            Allocation out = Allocation.createSized(mRS, getElement(mRS, Element.DataType.FLOAT_32, 4), INPUTSIZE);
            scriptRelaxed.set_gAllocInN(inN);
            scriptRelaxed.forEach_testRootnFloat4Int4Float4(inV, out);
            verifyResultsRootnFloat4Int4Float4(inV, inN, out, true);
        } catch (Exception e) {
            throw new RSRuntimeException("RenderScript. Can't invoke forEach_testRootnFloat4Int4Float4: " + e.toString());
        }
    }

    private void verifyResultsRootnFloat4Int4Float4(Allocation inV, Allocation inN, Allocation out, boolean relaxed) {
        float[] arrayInV = new float[INPUTSIZE * 4];
        Arrays.fill(arrayInV, (float) 42);
        inV.copyTo(arrayInV);
        int[] arrayInN = new int[INPUTSIZE * 4];
        Arrays.fill(arrayInN, (int) 42);
        inN.copyTo(arrayInN);
        float[] arrayOut = new float[INPUTSIZE * 4];
        Arrays.fill(arrayOut, (float) 42);
        out.copyTo(arrayOut);
        StringBuilder message = new StringBuilder();
        boolean errorFound = false;
        for (int i = 0; i < INPUTSIZE; i++) {
            for (int j = 0; j < 4 ; j++) {
                // Extract the inputs.
                ArgumentsFloatIntFloat args = new ArgumentsFloatIntFloat();
                args.inV = arrayInV[i * 4 + j];
                args.inN = arrayInN[i * 4 + j];
                // Figure out what the outputs should have been.
                Target target = new Target(Target.FunctionType.NORMAL, Target.ReturnType.FLOAT, relaxed);
                CoreMathVerifier.computeRootn(args, target);
                // Validate the outputs.
                boolean valid = true;
                if (!args.out.couldBe(arrayOut[i * 4 + j])) {
                    valid = false;
                }
                if (!valid) {
                    if (!errorFound) {
                        errorFound = true;
                        message.append("Input inV: ");
                        appendVariableToMessage(message, args.inV);
                        message.append("\n");
                        message.append("Input inN: ");
                        appendVariableToMessage(message, args.inN);
                        message.append("\n");
                        message.append("Expected output out: ");
                        appendVariableToMessage(message, args.out);
                        message.append("\n");
                        message.append("Actual   output out: ");
                        appendVariableToMessage(message, arrayOut[i * 4 + j]);
                        if (!args.out.couldBe(arrayOut[i * 4 + j])) {
                            message.append(" FAIL");
                        }
                        message.append("\n");
                        message.append("Errors at");
                    }
                    message.append(" [");
                    message.append(Integer.toString(i));
                    message.append(", ");
                    message.append(Integer.toString(j));
                    message.append("]");
                }
            }
        }
        assertFalse("Incorrect output for checkRootnFloat4Int4Float4" +
                (relaxed ? "_relaxed" : "") + ":\n" + message.toString(), errorFound);
    }

    public void testRootn() {
        checkRootnFloatIntFloat();
        checkRootnFloat2Int2Float2();
        checkRootnFloat3Int3Float3();
        checkRootnFloat4Int4Float4();
    }
}
