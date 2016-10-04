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
 * limitations under the License
 */

package com.android.cts.verifier.sensors;

import com.android.cts.verifier.ConditionalTest;
import com.android.cts.verifier.sensors.base.SensorCtsTestActivity;

import android.hardware.cts.SensorBatchingTests;

/**
 * Activity to execute CTS sensor batching tests.
 * It is a wrapper for {@link SensorBatchingTests} running with AndroidJUnitRunner.
 */
@ConditionalTest(applicable_features = "android.hardware.sensor.accelerometer:android.hardware.sensor.compass:android.hardware.sensor.gyroscope:android.hardware.sensor.barometer")
public class SensorBatchingTestsActivity extends SensorCtsTestActivity {
    public SensorBatchingTestsActivity() {
        super(SensorBatchingTests.class);
    }
}