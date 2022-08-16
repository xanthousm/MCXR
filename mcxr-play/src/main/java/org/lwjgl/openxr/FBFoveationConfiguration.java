/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 * MACHINE GENERATED FILE, DO NOT EDIT
 */
package org.lwjgl.openxr;

import org.jetbrains.annotations.Nullable;
/** The FB_foveation_configuration extension. */
public final class FBFoveationConfiguration {

    /** The extension specification version. */
    public static final int XR_FB_foveation_configuration_SPEC_VERSION = 1;

    /** The extension name. */
    public static final String XR_FB_FOVEATION_CONFIGURATION_EXTENSION_NAME = "XR_FB_foveation_configuration";

    /** Extends {@code XrStructureType}. */
    public static final int XR_TYPE_FOVEATION_LEVEL_PROFILE_CREATE_INFO_FB = 1000115000;

    /**
     * XrFoveationLevelFB
     * 
     * <h5>Enum values:</h5>
     * 
     * <ul>
     * <li>{@link #XR_FOVEATION_LEVEL_NONE_FB FOVEATION_LEVEL_NONE_FB}</li>
     * <li>{@link #XR_FOVEATION_LEVEL_LOW_FB FOVEATION_LEVEL_LOW_FB}</li>
     * <li>{@link #XR_FOVEATION_LEVEL_MEDIUM_FB FOVEATION_LEVEL_MEDIUM_FB}</li>
     * <li>{@link #XR_FOVEATION_LEVEL_HIGH_FB FOVEATION_LEVEL_HIGH_FB}</li>
     * </ul>
     */
    public static final int
        XR_FOVEATION_LEVEL_NONE_FB   = 0,
        XR_FOVEATION_LEVEL_LOW_FB    = 1,
        XR_FOVEATION_LEVEL_MEDIUM_FB = 2,
        XR_FOVEATION_LEVEL_HIGH_FB   = 3;

    /**
     * XrFoveationDynamicFB
     * 
     * <h5>Enum values:</h5>
     * 
     * <ul>
     * <li>{@link #XR_FOVEATION_DYNAMIC_DISABLED_FB FOVEATION_DYNAMIC_DISABLED_FB}</li>
     * <li>{@link #XR_FOVEATION_DYNAMIC_LEVEL_ENABLED_FB FOVEATION_DYNAMIC_LEVEL_ENABLED_FB}</li>
     * </ul>
     */
    public static final int
        XR_FOVEATION_DYNAMIC_DISABLED_FB      = 0,
        XR_FOVEATION_DYNAMIC_LEVEL_ENABLED_FB = 1;

    private FBFoveationConfiguration() {}

}