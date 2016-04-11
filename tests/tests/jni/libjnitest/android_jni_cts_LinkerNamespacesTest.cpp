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

/*
 * Tests accessibility of platform native libraries
 */

#include <dirent.h>
#include <dlfcn.h>
#include <fcntl.h>
#include <jni.h>
#include <JNIHelp.h>
#include <libgen.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h>

#include <list>
#include <string>
#include <unordered_set>
#include <vector>

#include "ScopedLocalRef.h"
#include "ScopedUtfChars.h"

#if defined(__LP64__)
static const std::string kSystemLibraryPath = "/system/lib64";
static const std::string kVendorLibraryPath = "/vendor/lib64";
#else
static const std::string kSystemLibraryPath = "/system/lib";
static const std::string kVendorLibraryPath = "/vendor/lib";
#endif

static std::unordered_set<std::string> kSystemPublicLibraries = {
    "libandroid.so",
    "libcamera2ndk.so",
    "libc.so",
    "libdl.so",
    "libEGL.so",
    "libGLESv1_CM.so",
    "libGLESv2.so",
    "libGLESv3.so",
    "libicui18n.so",
    "libicuuc.so",
    "libjnigraphics.so",
    "liblog.so",
    "libmediandk.so",
    "libm.so",
    "libOpenMAXAL.so",
    "libOpenSLES.so",
    "libRS.so",
    "libstdc++.so",
    "libvulkan.so",
    "libwebviewchromium_plat_support.so",
    "libz.so"
  };

template <typename F>
static bool for_each_file(const std::string& dir, F functor, std::string* error_msg) {
  auto dir_deleter = [](DIR* handle) { closedir(handle); };
  std::unique_ptr<DIR, decltype(dir_deleter)> dirp(opendir(dir.c_str()), dir_deleter);
  if (dirp == nullptr) {
    *error_msg = strerror(errno);
    return false;
  }

  dirent* dp;
  while ((dp = readdir(dirp.get())) != nullptr) {
    // skip "." and ".."
    if (strcmp(".", dp->d_name) == 0 ||
        strcmp("..", dp->d_name) == 0) {
      continue;
    }

    if (!functor(dp->d_name, error_msg)) {
      return false;
    }
  }

  return true;
}

static bool should_be_accessible(const std::string& public_library_path,
                                 const std::unordered_set<std::string>& public_libraries,
                                 const std::string& path) {
  std::string name = basename(path.c_str());
  return (public_libraries.find(name) != public_libraries.end()) &&
         (public_library_path + "/" + name == path);
}

static bool is_directory(const std::string path) {
  struct stat sb;
  if (stat(path.c_str(), &sb) != -1) {
    return S_ISDIR(sb.st_mode);
  }

  return false;
}

static bool is_libdl(const std::string path) {
  return kSystemLibraryPath + "/libdl.so" == path;
}

static bool check_libs(const std::string& public_library_path,
                       const std::unordered_set<std::string>& public_libraries,
                       std::string* error) {
  std::list<std::string> dirs;
  dirs.push_back(public_library_path);

  while (!dirs.empty()) {
    const auto dir = dirs.front();
    dirs.pop_front();
    bool success = for_each_file(dir, [&](const char* name, std::string* error_msg) {
      std::string path = dir + "/" + name;
      if (is_directory(path)) {
        dirs.push_back(path);
        return true;
      }

      if (is_libdl(path)) {
        // TODO (dimitry): we skip check for libdl.so because
        // 1. Linker will fail to check accessibility because it imposes as libdl.so (see http://b/27106625)
        // 2. It is impractical to dlopen libdl.so because this library already depends
        //    on it in order to have dlopen()
        return true;
      }

      auto dlcloser = [](void* handle) { dlclose(handle); };
      std::unique_ptr<void, decltype(dlcloser)> handle(dlopen(path.c_str(), RTLD_NOW), dlcloser);
      if (should_be_accessible(public_library_path, public_libraries, path)) {
        if (handle.get() == nullptr) {
          *error_msg = "The library \"" + path + "\" should be accessible but isn't: " + dlerror();
          return false;
        }
      } else if (handle != nullptr) {
        *error_msg = "The library \"" + path + "\" should not be accessible";
        return false;
      } else { // (handle == nullptr && !shouldBeAccessible(path))
        // Check the error message
        std::string err = dlerror();

        if (err.find("dlopen failed: library \"" + path + "\"") != 0 ||
            err.find("is not accessible for the namespace \"classloader-namespace\"") == std::string::npos) {
          *error_msg = "unexpected dlerror: " + err;
          return false;
        }
      }
      return true;
    }, error);

    if (!success) {
      return false;
    }
  }

  return true;
}

static void load_vendor_libraries(JNIEnv* env,
                                  jobjectArray java_vendor_public_libraries,
                                  std::unordered_set<std::string>* libraries) {
  size_t size = env->GetArrayLength(java_vendor_public_libraries);
  for (size_t i = 0; i<size; ++i) {
    ScopedLocalRef<jstring> java_soname(
        env, (jstring) env->GetObjectArrayElement(java_vendor_public_libraries, i));

    ScopedUtfChars soname(env, java_soname.get());
    libraries->insert(soname.c_str());
  }
}

extern "C" JNIEXPORT jstring JNICALL
    Java_android_jni_cts_LinkerNamespacesHelper_runAccessibilityTestImpl(
        JNIEnv* env,
        jclass clazz __attribute__((unused)),
        jobjectArray java_vendor_public_libraries) {
  std::string error;

  std::unordered_set<std::string> vendor_public_libraries;
  load_vendor_libraries(env, java_vendor_public_libraries, &vendor_public_libraries);

  if (!check_libs(kSystemLibraryPath, kSystemPublicLibraries, &error) ||
      !check_libs(kVendorLibraryPath, vendor_public_libraries, &error)) {
    return env->NewStringUTF(error.c_str());
  }

  return nullptr;
}

