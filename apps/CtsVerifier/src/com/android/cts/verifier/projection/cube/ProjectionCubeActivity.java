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

package com.android.cts.verifier.projection.cube;

import com.android.cts.verifier.ConditionalTest;
import com.android.cts.verifier.R;

import android.os.Bundle;
import android.util.Log;

import com.android.cts.verifier.projection.ProjectionActivity;
import com.android.cts.verifier.projection.ProjectionPresentationType;

@ConditionalTest(required_features = "android.hardware.faketouch")
public class ProjectionCubeActivity extends ProjectionActivity {
    private static final String TAG = ProjectionCubeActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        setContentViewAndInfoResources(R.layout.pa_main, R.string.pca_test, R.string.pca_info);
        mType = ProjectionPresentationType.TUMBLING_CUBES;
    }
}
