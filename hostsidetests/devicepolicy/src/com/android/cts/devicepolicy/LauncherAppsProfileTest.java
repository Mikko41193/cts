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

package com.android.cts.devicepolicy;

import com.android.ddmlib.Log.LogLevel;
import com.android.tradefed.device.DeviceNotAvailableException;
import com.android.tradefed.log.LogUtil.CLog;

import java.util.Collections;

/**
 * Set of tests for LauncherApps with managed profiles.
 */
public class LauncherAppsProfileTest extends BaseLauncherAppsTest {

    private static final String MANAGED_PROFILE_PKG = "com.android.cts.managedprofile";
    private static final String MANAGED_PROFILE_APK = "CtsManagedProfileApp.apk";
    private static final String ADMIN_RECEIVER_TEST_CLASS =
            MANAGED_PROFILE_PKG + ".BaseManagedProfileTest$BasicAdminReceiver";

    private int mProfileUserId;
    private int mParentUserId;
    private String mProfileSerialNumber;
    private String mMainUserSerialNumber;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mHasFeature = mHasFeature && hasDeviceFeature("android.software.managed_users");
        if (mHasFeature) {
            removeTestUsers();
            installTestApps();
            // Create a managed profile
            mParentUserId = getPrimaryUser();
            mProfileUserId = createManagedProfile(mParentUserId);
            installApp(MANAGED_PROFILE_APK);
            setProfileOwnerOrFail(MANAGED_PROFILE_PKG + "/" + ADMIN_RECEIVER_TEST_CLASS,
                    mProfileUserId);
            mProfileSerialNumber = Integer.toString(getUserSerialNumber(mProfileUserId));
            mMainUserSerialNumber = Integer.toString(getUserSerialNumber(mParentUserId));
            startUser(mProfileUserId);
        }
    }

    @Override
    protected void tearDown() throws Exception {
        if (mHasFeature) {
            removeUser(mProfileUserId);
            uninstallTestApps();
            getDevice().uninstallPackage(MANAGED_PROFILE_PKG);
        }
        super.tearDown();
    }

    public void testGetActivitiesWithProfile() throws Exception {
        if (!mHasFeature) {
            return;
        }
        // Install app for all users.
        installApp(SIMPLE_APP_APK);

        // Run tests to check SimpleApp exists in both profile and main user.
        assertTrue(runDeviceTestsAsUser(LAUNCHER_TESTS_PKG,
                LAUNCHER_TESTS_CLASS,
                "testSimpleAppInstalledForUser",
                mParentUserId, Collections.singletonMap(PARAM_TEST_USER, mProfileSerialNumber)));
        assertTrue(runDeviceTestsAsUser(LAUNCHER_TESTS_PKG,
                LAUNCHER_TESTS_PKG + ".LauncherAppsTests", "testSimpleAppInstalledForUser",
                mParentUserId, Collections.singletonMap(PARAM_TEST_USER, mMainUserSerialNumber)));
    }

    public void testLauncherCallbackPackageAddedProfile() throws Exception {
        if (!mHasFeature) {
            return;
        }
        startCallbackService();
        installApp(SIMPLE_APP_APK);
        assertTrue(runDeviceTestsAsUser(LAUNCHER_TESTS_PKG,
                LAUNCHER_TESTS_CLASS,
                "testPackageAddedCallbackForUser",
                mParentUserId, Collections.singletonMap(PARAM_TEST_USER, mProfileSerialNumber)));
    }

    public void testLauncherCallbackPackageRemovedProfile() throws Exception {
        if (!mHasFeature) {
            return;
        }
        installApp(SIMPLE_APP_APK);
        startCallbackService();
        getDevice().uninstallPackage(SIMPLE_APP_PKG);
        assertTrue(runDeviceTestsAsUser(LAUNCHER_TESTS_PKG,
                LAUNCHER_TESTS_CLASS,
                "testPackageRemovedCallbackForUser",
                mParentUserId, Collections.singletonMap(PARAM_TEST_USER, mProfileSerialNumber)));
    }

    public void testLauncherCallbackPackageChangedProfile() throws Exception {
        if (!mHasFeature) {
            return;
        }
        installApp(SIMPLE_APP_APK);
        startCallbackService();
        installApp(SIMPLE_APP_APK);
        assertTrue(runDeviceTestsAsUser(LAUNCHER_TESTS_PKG,
                LAUNCHER_TESTS_CLASS,
                "testPackageChangedCallbackForUser",
                mParentUserId, Collections.singletonMap(PARAM_TEST_USER, mProfileSerialNumber)));
    }
}
