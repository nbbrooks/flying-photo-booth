/*
 * Copyright (C) 2012 Benedict Lau
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
package com.groundupworks.flyingphotobooth.helpers;

import android.util.Log;
import com.groundupworks.flyingphotobooth.BuildConfig;

/**
 * Wrapper for {@link Log} to log only in debug builds.
 * 
 * @author Benedict Lau
 */
public class LogsHelper {

    /**
     * Tag that appears in debug logs.
     */
    private static final String LOGS_TAG = "FPB";

    //
    // Public methods.
    //

    /**
     * Logs to logcat only in debug builds.
     * 
     * @param clazz
     *            the {@link Class}.
     * @param methodName
     *            the name of the methods.
     * @param msg
     *            the debug message.
     */
    public static void log(Class<?> clazz, String methodName, String msg) {
        if (BuildConfig.DEBUG) {
            Log.d(LOGS_TAG, clazz.getSimpleName() + "#" + methodName + "() " + msg);
        }
    }
}