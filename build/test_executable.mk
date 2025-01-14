# Copyright (C) 2011 The Android Open Source Project
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

#
# Builds an executable:
#
# Replace "include $(BUILD_EXECUTABLE)" with "include $(BUILD_CTS_EXECUTABLE)"
#

ifeq ($(filter $(LOCAL_CXX_STL),libc++_static),)
LOCAL_CXX_STL := libc++
endif

include $(BUILD_EXECUTABLE)

