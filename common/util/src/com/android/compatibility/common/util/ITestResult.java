/*
 * Copyright (C) 2015 The Android Open Source Project
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
package com.android.compatibility.common.util;

/**
 * Represents a single test result.
 */
public interface ITestResult extends Comparable<ITestResult> {

    /**
     * @return The name of this test result.
     */
    String getName();

    /**
     * @return The full name of this test result, ie
     * &lt;package-name&gt;.&lt;class-name&gt;#&lt;method-name&gt;
     */
    String getFullName();

    /**
     * @return The {@link TestStatus} of this result.
     */
    TestStatus getResultStatus();

    /**
     * Sets the {@link TestStatus} of the result and updates the end time of the test.
     *
     * @param status The {@link TestStatus} of this result.
     */
    void setResultStatus(TestStatus status);

    /**
     * @return The failure message to display
     */
    String getMessage();

    /**
     * @param message The message to display which describes the failure
     */
    void setMessage(String message);

    /**
     * @return The stack trace generated by the failure
     */
    String getStackTrace();

    /**
     * @param stackTrace the stack trace generated by the failure.
     */
    void setStackTrace(String stackTrace);

    /**
     * @return the metrics report.
     */
    ReportLog getReportLog();

    /**
     * @param report the metrics report.
     */
    void setReportLog(ReportLog report);

    /**
     * @return the path of the bug report generated of the failure.
     */
    String getBugReport();

    /**
     * @param path the path of the bug report generated of the failure.
     */
    void setBugReport(String path);

    /**
     * @return the path of the log file generated of the failure.
     */
    String getLog();

    /**
     * @param path the path of the log file generated of the failure.
     */
    void setLog(String path);

    /**
     * @return the path of the screenshot file generated of the failure.
     */
    String getScreenshot();

    /**
     * @param path the path of the screenshot file generated of the failure.
     */
    void setScreenshot(String path);

    /**
     * Report the test as a failure.
     *
     * @param trace the stacktrace of the failure.
     */
    void failed(String trace);

    /**
     * Report that the test has completed.
     *
     * @param report A report generated by the test, or null.
     */
    void passed(ReportLog report);

    /**
     * Report that the test was not executed.
     *
     * This means something like a loss of connection to the hardware,
     * and indicates the run of this test was invalid and needs to be redone.
     */
    void notExecuted();

    /**
     * Report that the test was skipped.
     *
     * This means that the test is not considered appropriate for the
     * current device, and thus is never attempted.  Unlike notExecuted(),
     * this indicates the run of this test was valid and nothing further
     * needs to be done.
     */
    void skipped();

    /**
     * Resets the result.
     */
    void reset();

}