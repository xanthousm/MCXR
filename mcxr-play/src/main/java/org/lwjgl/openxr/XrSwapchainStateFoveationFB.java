/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 * MACHINE GENERATED FILE, DO NOT EDIT
 */
package org.lwjgl.openxr;

import org.jetbrains.annotations.Nullable;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.*;

import java.nio.ByteBuffer;

import static org.lwjgl.system.Checks.check;
import static org.lwjgl.system.MemoryUtil.*;

/**
 * <h3>Layout</h3>
 * 
 * <pre><code>
 * struct XrSwapchainStateFoveationFB {
 *     XrStructureType type;
 *     void * next;
 *     XrSwapchainStateFoveationFlagsFB flags;
 *     XrFoveationProfileFB profile;
 * }</code></pre>
 */
public class XrSwapchainStateFoveationFB extends Struct implements NativeResource {

    /** The struct size in bytes. */
    public static final int SIZEOF;

    /** The struct alignment in bytes. */
    public static final int ALIGNOF;

    /** The struct member offsets. */
    public static final int
        TYPE,
        NEXT,
        FLAGS,
        PROFILE;

    static {
        Layout layout = __struct(
            __member(4),
            __member(POINTER_SIZE),
            __member(8),
            __member(POINTER_SIZE)
        );

        SIZEOF = layout.getSize();
        ALIGNOF = layout.getAlignment();

        TYPE = layout.offsetof(0);
        NEXT = layout.offsetof(1);
        FLAGS = layout.offsetof(2);
        PROFILE = layout.offsetof(3);
    }

    /**
     * Creates a {@code XrSwapchainStateFoveationFB} instance at the current position of the specified {@link ByteBuffer} container. Changes to the buffer's content will be
     * visible to the struct instance and vice versa.
     *
     * <p>The created instance holds a strong reference to the container object.</p>
     */
    public XrSwapchainStateFoveationFB(ByteBuffer container) {
        super(memAddress(container), __checkContainer(container, SIZEOF));
    }

    @Override
    public int sizeof() { return SIZEOF; }

    /** @return the value of the {@code type} field. */
    @NativeType("XrStructureType")
    public int type() { return ntype(address()); }
    /** @return the value of the {@code next} field. */
    @NativeType("void *")
    public long next() { return nnext(address()); }
    /** @return the value of the {@code flags} field. */
    @NativeType("XrSwapchainStateFoveationFlagsFB")
    public long flags() { return nflags(address()); }
    /** @return the value of the {@code profile} field. */
    @NativeType("XrFoveationProfileFB")
    public long profile() { return nprofile(address()); }

    /** Sets the specified value to the {@code type} field. */
    public XrSwapchainStateFoveationFB type(@NativeType("XrStructureType") int value) { ntype(address(), value); return this; }
    /** Sets the {@link FBFoveation#XR_TYPE_SWAPCHAIN_STATE_FOVEATION_FB TYPE_SWAPCHAIN_STATE_FOVEATION_FB} value to the {@code type} field. */
    public XrSwapchainStateFoveationFB type$Default() { return type(FBFoveation.XR_TYPE_SWAPCHAIN_STATE_FOVEATION_FB); }
    /** Sets the specified value to the {@code next} field. */
    public XrSwapchainStateFoveationFB next(@NativeType("void *") long value) { nnext(address(), value); return this; }
    /** Sets the specified value to the {@code flags} field. */
    public XrSwapchainStateFoveationFB flags(@NativeType("XrSwapchainStateFoveationFlagsFB") long value) { nflags(address(), value); return this; }
    /** Sets the specified value to the {@code profile} field. */
    public XrSwapchainStateFoveationFB profile(XrFoveationProfileFB value) { nprofile(address(), value); return this; }

    /** Initializes this struct with the specified values. */
    public XrSwapchainStateFoveationFB set(
        int type,
        long next,
        long flags,
        XrFoveationProfileFB profile
    ) {
        type(type);
        next(next);
        flags(flags);
        profile(profile);

        return this;
    }

    /**
     * Copies the specified struct data to this struct.
     *
     * @param src the source struct
     *
     * @return this struct
     */
    public XrSwapchainStateFoveationFB set(XrSwapchainStateFoveationFB src) {
        memCopy(src.address(), address(), SIZEOF);
        return this;
    }

    // -----------------------------------

    /** Returns a new {@code XrSwapchainStateFoveationFB} instance allocated with {@link MemoryUtil#memAlloc memAlloc}. The instance must be explicitly freed. */
    public static XrSwapchainStateFoveationFB malloc() {
        return wrap(XrSwapchainStateFoveationFB.class, nmemAllocChecked(SIZEOF));
    }

    /** Returns a new {@code XrSwapchainStateFoveationFB} instance allocated with {@link MemoryUtil#memCalloc memCalloc}. The instance must be explicitly freed. */
    public static XrSwapchainStateFoveationFB calloc() {
        return wrap(XrSwapchainStateFoveationFB.class, nmemCallocChecked(1, SIZEOF));
    }

    /** Returns a new {@code XrSwapchainStateFoveationFB} instance allocated with {@link BufferUtils}. */
    public static XrSwapchainStateFoveationFB create() {
        ByteBuffer container = BufferUtils.createByteBuffer(SIZEOF);
        return wrap(XrSwapchainStateFoveationFB.class, memAddress(container), container);
    }

    /** Returns a new {@code XrSwapchainStateFoveationFB} instance for the specified memory address. */
    public static XrSwapchainStateFoveationFB create(long address) {
        return wrap(XrSwapchainStateFoveationFB.class, address);
    }

    /** Like {@link #create(long) create}, but returns {@code null} if {@code address} is {@code NULL}. */
    @Nullable
    public static XrSwapchainStateFoveationFB createSafe(long address) {
        return address == NULL ? null : wrap(XrSwapchainStateFoveationFB.class, address);
    }

    /**
     * Returns a new {@link Buffer} instance allocated with {@link MemoryUtil#memAlloc memAlloc}. The instance must be explicitly freed.
     *
     * @param capacity the buffer capacity
     */
    public static Buffer malloc(int capacity) {
        return wrap(Buffer.class, nmemAllocChecked(__checkMalloc(capacity, SIZEOF)), capacity);
    }

    /**
     * Returns a new {@link Buffer} instance allocated with {@link MemoryUtil#memCalloc memCalloc}. The instance must be explicitly freed.
     *
     * @param capacity the buffer capacity
     */
    public static Buffer calloc(int capacity) {
        return wrap(Buffer.class, nmemCallocChecked(capacity, SIZEOF), capacity);
    }

    /**
     * Returns a new {@link Buffer} instance allocated with {@link BufferUtils}.
     *
     * @param capacity the buffer capacity
     */
    public static Buffer create(int capacity) {
        ByteBuffer container = __create(capacity, SIZEOF);
        return wrap(Buffer.class, memAddress(container), capacity, container);
    }

    /**
     * Create a {@link Buffer} instance at the specified memory.
     *
     * @param address  the memory address
     * @param capacity the buffer capacity
     */
    public static Buffer create(long address, int capacity) {
        return wrap(Buffer.class, address, capacity);
    }

    /** Like {@link #create(long, int) create}, but returns {@code null} if {@code address} is {@code NULL}. */
    @Nullable
    public static XrSwapchainStateFoveationFB.Buffer createSafe(long address, int capacity) {
        return address == NULL ? null : wrap(Buffer.class, address, capacity);
    }


    /**
     * Returns a new {@code XrSwapchainStateFoveationFB} instance allocated on the specified {@link MemoryStack}.
     *
     * @param stack the stack from which to allocate
     */
    public static XrSwapchainStateFoveationFB malloc(MemoryStack stack) {
        return wrap(XrSwapchainStateFoveationFB.class, stack.nmalloc(ALIGNOF, SIZEOF));
    }

    /**
     * Returns a new {@code XrSwapchainStateFoveationFB} instance allocated on the specified {@link MemoryStack} and initializes all its bits to zero.
     *
     * @param stack the stack from which to allocate
     */
    public static XrSwapchainStateFoveationFB calloc(MemoryStack stack) {
        return wrap(XrSwapchainStateFoveationFB.class, stack.ncalloc(ALIGNOF, 1, SIZEOF));
    }

    /**
     * Returns a new {@link Buffer} instance allocated on the specified {@link MemoryStack}.
     *
     * @param stack    the stack from which to allocate
     * @param capacity the buffer capacity
     */
    public static Buffer malloc(int capacity, MemoryStack stack) {
        return wrap(Buffer.class, stack.nmalloc(ALIGNOF, capacity * SIZEOF), capacity);
    }

    /**
     * Returns a new {@link Buffer} instance allocated on the specified {@link MemoryStack} and initializes all its bits to zero.
     *
     * @param stack    the stack from which to allocate
     * @param capacity the buffer capacity
     */
    public static Buffer calloc(int capacity, MemoryStack stack) {
        return wrap(Buffer.class, stack.ncalloc(ALIGNOF, capacity, SIZEOF), capacity);
    }

    // -----------------------------------

    /** Unsafe version of {@link #type}. */
    public static int ntype(long struct) { return UNSAFE.getInt(null, struct + XrSwapchainStateFoveationFB.TYPE); }
    /** Unsafe version of {@link #next}. */
    public static long nnext(long struct) { return memGetAddress(struct + XrSwapchainStateFoveationFB.NEXT); }
    /** Unsafe version of {@link #flags}. */
    public static long nflags(long struct) { return UNSAFE.getLong(null, struct + XrSwapchainStateFoveationFB.FLAGS); }
    /** Unsafe version of {@link #profile}. */
    public static long nprofile(long struct) { return memGetAddress(struct + XrSwapchainStateFoveationFB.PROFILE); }

    /** Unsafe version of {@link #type(int) type}. */
    public static void ntype(long struct, int value) { UNSAFE.putInt(null, struct + XrSwapchainStateFoveationFB.TYPE, value); }
    /** Unsafe version of {@link #next(long) next}. */
    public static void nnext(long struct, long value) { memPutAddress(struct + XrSwapchainStateFoveationFB.NEXT, value); }
    /** Unsafe version of {@link #flags(long) flags}. */
    public static void nflags(long struct, long value) { UNSAFE.putLong(null, struct + XrSwapchainStateFoveationFB.FLAGS, value); }
    /** Unsafe version of {@link #profile(XrFoveationProfileFB) profile}. */
    public static void nprofile(long struct, XrFoveationProfileFB value) { memPutAddress(struct + XrSwapchainStateFoveationFB.PROFILE, value.address()); }

    /**
     * Validates pointer members that should not be {@code NULL}.
     *
     * @param struct the struct to validate
     */
    public static void validate(long struct) {
        check(memGetAddress(struct + XrSwapchainStateFoveationFB.PROFILE));
    }

    /**
     * Calls {@link #validate(long)} for each struct contained in the specified struct array.
     *
     * @param array the struct array to validate
     * @param count the number of structs in {@code array}
     */
    public static void validate(long array, int count) {
        for (int i = 0; i < count; i++) {
            validate(array + Integer.toUnsignedLong(i) * SIZEOF);
        }
    }

    // -----------------------------------

    /** An array of {@link XrSwapchainStateFoveationFB} structs. */
    public static class Buffer extends StructBuffer<XrSwapchainStateFoveationFB, Buffer> implements NativeResource {

        private static final XrSwapchainStateFoveationFB ELEMENT_FACTORY = XrSwapchainStateFoveationFB.create(-1L);

        /**
         * Creates a new {@code XrSwapchainStateFoveationFB.Buffer} instance backed by the specified container.
         *
         * Changes to the container's content will be visible to the struct buffer instance and vice versa. The two buffers' position, limit, and mark values
         * will be independent. The new buffer's position will be zero, its capacity and its limit will be the number of bytes remaining in this buffer divided
         * by {@link XrSwapchainStateFoveationFB#SIZEOF}, and its mark will be undefined.
         *
         * <p>The created buffer instance holds a strong reference to the container object.</p>
         */
        public Buffer(ByteBuffer container) {
            super(container, container.remaining() / SIZEOF);
        }

        public Buffer(long address, int cap) {
            super(address, null, -1, 0, cap, cap);
        }

        Buffer(long address, @Nullable ByteBuffer container, int mark, int pos, int lim, int cap) {
            super(address, container, mark, pos, lim, cap);
        }

        @Override
        protected Buffer self() {
            return this;
        }

        @Override
        protected XrSwapchainStateFoveationFB getElementFactory() {
            return ELEMENT_FACTORY;
        }

        /** @return the value of the {@code type} field. */
        @NativeType("XrStructureType")
        public int type() { return XrSwapchainStateFoveationFB.ntype(address()); }
        /** @return the value of the {@code next} field. */
        @NativeType("void *")
        public long next() { return XrSwapchainStateFoveationFB.nnext(address()); }
        /** @return the value of the {@code flags} field. */
        @NativeType("XrSwapchainStateFoveationFlagsFB")
        public long flags() { return XrSwapchainStateFoveationFB.nflags(address()); }
        /** @return the value of the {@code profile} field. */
        @NativeType("XrFoveationProfileFB")
        public long profile() { return XrSwapchainStateFoveationFB.nprofile(address()); }

        /** Sets the specified value to the {@code type} field. */
        public Buffer type(@NativeType("XrStructureType") int value) { XrSwapchainStateFoveationFB.ntype(address(), value); return this; }
        /** Sets the {@link FBFoveation#XR_TYPE_SWAPCHAIN_STATE_FOVEATION_FB TYPE_SWAPCHAIN_STATE_FOVEATION_FB} value to the {@code type} field. */
        public Buffer type$Default() { return type(FBFoveation.XR_TYPE_SWAPCHAIN_STATE_FOVEATION_FB); }
        /** Sets the specified value to the {@code next} field. */
        public Buffer next(@NativeType("void *") long value) { XrSwapchainStateFoveationFB.nnext(address(), value); return this; }
        /** Sets the specified value to the {@code flags} field. */
        public Buffer flags(@NativeType("XrSwapchainStateFoveationFlagsFB") long value) { XrSwapchainStateFoveationFB.nflags(address(), value); return this; }
        /** Sets the specified value to the {@code profile} field. */
        public Buffer profile(XrFoveationProfileFB value) { XrSwapchainStateFoveationFB.nprofile(address(), value); return this; }

    }

}