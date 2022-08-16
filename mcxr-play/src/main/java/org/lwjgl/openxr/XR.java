/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 */
package org.lwjgl.openxr;

import net.sorenon.mcxr.play.MCXRNativeLoad;
import net.sorenon.mcxr.play.MCXRPlatform;
import net.sorenon.mcxr.play.MCXRPlayClient;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.*;

import java.util.HashSet;
import java.util.Set;

import static java.lang.Math.min;
import static org.lwjgl.system.JNI.callPI;
import static org.lwjgl.system.JNI.callPPPI;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.*;

public class XR {

    @Nullable
    private static FunctionProvider functionProvider;

    @Nullable
    private static GlobalCommands globalCommands;

//    /**
//     * Loads the OpenXR loader using the bundled library.
//     *
//     * @see #create(String)
//     */
//    public static void create() {
//        SharedLibrary defaultOpenXRLoader = Library.loadNative(XR.class, "org.lwjgl.org.lwjgl.openxr", Configuration.OPENXR_LIBRARY_NAME.get(Platform.mapLibraryNameBundled("openxr_loader")), true);
//        XR.create(defaultOpenXRLoader);
//    }

    /**
     * Loads the OpenXR loader, using the specified library name.
     *
     * <p>The {@link FunctionProvider} instance created by this method can only be used to retrieve global commands and commands exposed statically by the loader.</p>
     *
     * @param libName the shared library name (or absolute path)
     * @see #create(FunctionProvider)
     */
    public static void create(String libName) {
        SharedLibrary defaultOpenXRLoader = MCXRPlayClient.PLATFORM.getOpenXRLib();
        XR.create(defaultOpenXRLoader);
    }

    /**
     * Initializes OpenXR with the specified {@link FunctionProvider}. This method can be used to implement custom OpenXR library loading.
     *
     * @param functionProvider the provider of OpenXR function addresses
     */
    public static void create(FunctionProvider functionProvider) {
        if (XR.functionProvider != null) {
            throw new IllegalStateException("OpenXR has already been created.");
        }

        XR.functionProvider = functionProvider;
        globalCommands = new GlobalCommands(functionProvider);
    }

    public static boolean loaded() {
        return functionProvider != null;
    }

    private static <T> T check(@Nullable T t) {
        if (t == null) {
            throw new IllegalStateException("OpenXR library has not been loaded.");
        }
        return t;
    }

    static class GlobalCommands {

        final long xrGetInstanceProcAddr;

        final long xrCreateInstance;
        final long xrEnumerateInstanceExtensionProperties;
        final long xrEnumerateApiLayerProperties;
        final long xrInitializeLoaderKHR;

        GlobalCommands(FunctionProvider library) {
            xrGetInstanceProcAddr = library.getFunctionAddress("xrGetInstanceProcAddr");
            if (xrGetInstanceProcAddr == NULL) {
                throw new IllegalArgumentException("A critical function is missing. Make sure that OpenXR is available.");
            }

            xrInitializeLoaderKHR = library.getFunctionAddress("xrInitializeLoaderKHR");
            if (xrInitializeLoaderKHR != NULL) {
                if (MCXRPlayClient.PLATFORM.getPlatform() == MCXRPlatform.PlatformType.Quest) {
                    try (MemoryStack stack = stackPush()) {
                        long context = memGetAddress(MCXRNativeLoad.getApplicationActivityPtr());
                        long jvm = memGetAddress(MCXRNativeLoad.getJVMPtr());

                        var createInfo = XrLoaderInitInfoAndroidKHR
                                .calloc(stack)
                                .type$Default()
                                .next(NULL)
                                .applicationVM(jvm)
                                .applicationContext(context);

                        System.out.println("XrResult:" + callPI(createInfo.address(), xrInitializeLoaderKHR));
                    }
                }
            }

            xrCreateInstance = library.getFunctionAddress("xrCreateInstance");
            xrEnumerateInstanceExtensionProperties = getFunctionAddress("xrEnumerateInstanceExtensionProperties");
            xrEnumerateApiLayerProperties = getFunctionAddress("xrEnumerateApiLayerProperties");
        }

        private long getFunctionAddress(String name) {
            return getFunctionAddress(name, true);
        }

        private long getFunctionAddress(String name, boolean required) {
            try (MemoryStack stack = stackPush()) {
                PointerBuffer pp = stack.mallocPointer(1);
                callPPPI(NULL, memAddress(stack.ASCII(name)), memAddress(pp), xrGetInstanceProcAddr);
                long address = pp.get();
                if (address == NULL && required) {
                    throw new IllegalArgumentException("A critical function is missing. Make sure that OpenXR is available.");
                }
                return address;
            }
        }
    }

    static GlobalCommands getGlobalCommands() {
        return check(globalCommands);
    }

    static Set<String> getEnabledExtensionSet(long apiVersion, @Nullable PointerBuffer extensionNames) {
        Set<String> enabledExtensions = new HashSet<>(16);

        long majorVersion = XR10.XR_VERSION_MAJOR(apiVersion);
        long minorVersion = XR10.XR_VERSION_MINOR(apiVersion);

        int[] XR_VERSIONS = {
                1
        };

        long maxMajor = min(majorVersion, XR_VERSIONS.length);
        for (int M = 1; M <= maxMajor; M++) {
            long maxMinor = XR_VERSIONS[M - 1];
            if (M == majorVersion) {
                maxMinor = min(minorVersion, maxMinor);
            }
            for (int m = 0; m <= maxMinor; m++) {
                enabledExtensions.add(String.format("OpenXR%d%d", M, m));
            }
        }

        if (extensionNames != null) {
            for (int i = extensionNames.position(); i < extensionNames.limit(); i++) {
                enabledExtensions.add(extensionNames.getStringUTF8(i));
            }
        }

        return enabledExtensions;
    }
}
