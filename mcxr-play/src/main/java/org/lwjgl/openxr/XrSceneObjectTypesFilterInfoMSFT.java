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
import java.nio.IntBuffer;

import static org.lwjgl.system.MemoryUtil.*;

/**
 * <h3>Layout</h3>
 * 
 * <pre><code>
 * struct XrSceneObjectTypesFilterInfoMSFT {
 *     XrStructureType type;
 *     void const * next;
 *     uint32_t objectTypeCount;
 *     XrSceneObjectTypeMSFT const * objectTypes;
 * }</code></pre>
 */
public class XrSceneObjectTypesFilterInfoMSFT extends Struct implements NativeResource {

    /** The struct size in bytes. */
    public static final int SIZEOF;

    /** The struct alignment in bytes. */
    public static final int ALIGNOF;

    /** The struct member offsets. */
    public static final int
        TYPE,
        NEXT,
        OBJECTTYPECOUNT,
        OBJECTTYPES;

    static {
        Layout layout = __struct(
            __member(4),
            __member(POINTER_SIZE),
            __member(4),
            __member(POINTER_SIZE)
        );

        SIZEOF = layout.getSize();
        ALIGNOF = layout.getAlignment();

        TYPE = layout.offsetof(0);
        NEXT = layout.offsetof(1);
        OBJECTTYPECOUNT = layout.offsetof(2);
        OBJECTTYPES = layout.offsetof(3);
    }

    /**
     * Creates a {@code XrSceneObjectTypesFilterInfoMSFT} instance at the current position of the specified {@link ByteBuffer} container. Changes to the buffer's content will be
     * visible to the struct instance and vice versa.
     *
     * <p>The created instance holds a strong reference to the container object.</p>
     */
    public XrSceneObjectTypesFilterInfoMSFT(ByteBuffer container) {
        super(memAddress(container), __checkContainer(container, SIZEOF));
    }

    @Override
    public int sizeof() { return SIZEOF; }

    /** @return the value of the {@code type} field. */
    @NativeType("XrStructureType")
    public int type() { return ntype(address()); }
    /** @return the value of the {@code next} field. */
    @NativeType("void const *")
    public long next() { return nnext(address()); }
    /** @return the value of the {@code objectTypeCount} field. */
    @NativeType("uint32_t")
    public int objectTypeCount() { return nobjectTypeCount(address()); }
    /** @return a {@link IntBuffer} view of the data pointed to by the {@code objectTypes} field. */
    @Nullable
    @NativeType("XrSceneObjectTypeMSFT const *")
    public IntBuffer objectTypes() { return nobjectTypes(address()); }

    /** Sets the specified value to the {@code type} field. */
    public XrSceneObjectTypesFilterInfoMSFT type(@NativeType("XrStructureType") int value) { ntype(address(), value); return this; }
    /** Sets the {@link MSFTSceneUnderstanding#XR_TYPE_SCENE_OBJECT_TYPES_FILTER_INFO_MSFT TYPE_SCENE_OBJECT_TYPES_FILTER_INFO_MSFT} value to the {@code type} field. */
    public XrSceneObjectTypesFilterInfoMSFT type$Default() { return type(MSFTSceneUnderstanding.XR_TYPE_SCENE_OBJECT_TYPES_FILTER_INFO_MSFT); }
    /** Sets the specified value to the {@code next} field. */
    public XrSceneObjectTypesFilterInfoMSFT next(@NativeType("void const *") long value) { nnext(address(), value); return this; }
    /** Sets the specified value to the {@code objectTypeCount} field. */
    public XrSceneObjectTypesFilterInfoMSFT objectTypeCount(@NativeType("uint32_t") int value) { nobjectTypeCount(address(), value); return this; }
    /** Sets the address of the specified {@link IntBuffer} to the {@code objectTypes} field. */
    public XrSceneObjectTypesFilterInfoMSFT objectTypes(@Nullable @NativeType("XrSceneObjectTypeMSFT const *") IntBuffer value) { nobjectTypes(address(), value); return this; }

    /** Initializes this struct with the specified values. */
    public XrSceneObjectTypesFilterInfoMSFT set(
        int type,
        long next,
        int objectTypeCount,
        @Nullable IntBuffer objectTypes
    ) {
        type(type);
        next(next);
        objectTypeCount(objectTypeCount);
        objectTypes(objectTypes);

        return this;
    }

    /**
     * Copies the specified struct data to this struct.
     *
     * @param src the source struct
     *
     * @return this struct
     */
    public XrSceneObjectTypesFilterInfoMSFT set(XrSceneObjectTypesFilterInfoMSFT src) {
        memCopy(src.address(), address(), SIZEOF);
        return this;
    }

    // -----------------------------------

    /** Returns a new {@code XrSceneObjectTypesFilterInfoMSFT} instance allocated with {@link MemoryUtil#memAlloc memAlloc}. The instance must be explicitly freed. */
    public static XrSceneObjectTypesFilterInfoMSFT malloc() {
        return wrap(XrSceneObjectTypesFilterInfoMSFT.class, nmemAllocChecked(SIZEOF));
    }

    /** Returns a new {@code XrSceneObjectTypesFilterInfoMSFT} instance allocated with {@link MemoryUtil#memCalloc memCalloc}. The instance must be explicitly freed. */
    public static XrSceneObjectTypesFilterInfoMSFT calloc() {
        return wrap(XrSceneObjectTypesFilterInfoMSFT.class, nmemCallocChecked(1, SIZEOF));
    }

    /** Returns a new {@code XrSceneObjectTypesFilterInfoMSFT} instance allocated with {@link BufferUtils}. */
    public static XrSceneObjectTypesFilterInfoMSFT create() {
        ByteBuffer container = BufferUtils.createByteBuffer(SIZEOF);
        return wrap(XrSceneObjectTypesFilterInfoMSFT.class, memAddress(container), container);
    }

    /** Returns a new {@code XrSceneObjectTypesFilterInfoMSFT} instance for the specified memory address. */
    public static XrSceneObjectTypesFilterInfoMSFT create(long address) {
        return wrap(XrSceneObjectTypesFilterInfoMSFT.class, address);
    }

    /** Like {@link #create(long) create}, but returns {@code null} if {@code address} is {@code NULL}. */
    @Nullable
    public static XrSceneObjectTypesFilterInfoMSFT createSafe(long address) {
        return address == NULL ? null : wrap(XrSceneObjectTypesFilterInfoMSFT.class, address);
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
    public static XrSceneObjectTypesFilterInfoMSFT.Buffer createSafe(long address, int capacity) {
        return address == NULL ? null : wrap(Buffer.class, address, capacity);
    }


    /**
     * Returns a new {@code XrSceneObjectTypesFilterInfoMSFT} instance allocated on the specified {@link MemoryStack}.
     *
     * @param stack the stack from which to allocate
     */
    public static XrSceneObjectTypesFilterInfoMSFT malloc(MemoryStack stack) {
        return wrap(XrSceneObjectTypesFilterInfoMSFT.class, stack.nmalloc(ALIGNOF, SIZEOF));
    }

    /**
     * Returns a new {@code XrSceneObjectTypesFilterInfoMSFT} instance allocated on the specified {@link MemoryStack} and initializes all its bits to zero.
     *
     * @param stack the stack from which to allocate
     */
    public static XrSceneObjectTypesFilterInfoMSFT calloc(MemoryStack stack) {
        return wrap(XrSceneObjectTypesFilterInfoMSFT.class, stack.ncalloc(ALIGNOF, 1, SIZEOF));
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
    public static int ntype(long struct) { return UNSAFE.getInt(null, struct + XrSceneObjectTypesFilterInfoMSFT.TYPE); }
    /** Unsafe version of {@link #next}. */
    public static long nnext(long struct) { return memGetAddress(struct + XrSceneObjectTypesFilterInfoMSFT.NEXT); }
    /** Unsafe version of {@link #objectTypeCount}. */
    public static int nobjectTypeCount(long struct) { return UNSAFE.getInt(null, struct + XrSceneObjectTypesFilterInfoMSFT.OBJECTTYPECOUNT); }
    /** Unsafe version of {@link #objectTypes() objectTypes}. */
    @Nullable public static IntBuffer nobjectTypes(long struct) { return memIntBufferSafe(memGetAddress(struct + XrSceneObjectTypesFilterInfoMSFT.OBJECTTYPES), nobjectTypeCount(struct)); }

    /** Unsafe version of {@link #type(int) type}. */
    public static void ntype(long struct, int value) { UNSAFE.putInt(null, struct + XrSceneObjectTypesFilterInfoMSFT.TYPE, value); }
    /** Unsafe version of {@link #next(long) next}. */
    public static void nnext(long struct, long value) { memPutAddress(struct + XrSceneObjectTypesFilterInfoMSFT.NEXT, value); }
    /** Sets the specified value to the {@code objectTypeCount} field of the specified {@code struct}. */
    public static void nobjectTypeCount(long struct, int value) { UNSAFE.putInt(null, struct + XrSceneObjectTypesFilterInfoMSFT.OBJECTTYPECOUNT, value); }
    /** Unsafe version of {@link #objectTypes(IntBuffer) objectTypes}. */
    public static void nobjectTypes(long struct, @Nullable IntBuffer value) { memPutAddress(struct + XrSceneObjectTypesFilterInfoMSFT.OBJECTTYPES, memAddressSafe(value)); if (value != null) { nobjectTypeCount(struct, value.remaining()); } }

    // -----------------------------------

    /** An array of {@link XrSceneObjectTypesFilterInfoMSFT} structs. */
    public static class Buffer extends StructBuffer<XrSceneObjectTypesFilterInfoMSFT, Buffer> implements NativeResource {

        private static final XrSceneObjectTypesFilterInfoMSFT ELEMENT_FACTORY = XrSceneObjectTypesFilterInfoMSFT.create(-1L);

        /**
         * Creates a new {@code XrSceneObjectTypesFilterInfoMSFT.Buffer} instance backed by the specified container.
         *
         * Changes to the container's content will be visible to the struct buffer instance and vice versa. The two buffers' position, limit, and mark values
         * will be independent. The new buffer's position will be zero, its capacity and its limit will be the number of bytes remaining in this buffer divided
         * by {@link XrSceneObjectTypesFilterInfoMSFT#SIZEOF}, and its mark will be undefined.
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
        protected XrSceneObjectTypesFilterInfoMSFT getElementFactory() {
            return ELEMENT_FACTORY;
        }

        /** @return the value of the {@code type} field. */
        @NativeType("XrStructureType")
        public int type() { return XrSceneObjectTypesFilterInfoMSFT.ntype(address()); }
        /** @return the value of the {@code next} field. */
        @NativeType("void const *")
        public long next() { return XrSceneObjectTypesFilterInfoMSFT.nnext(address()); }
        /** @return the value of the {@code objectTypeCount} field. */
        @NativeType("uint32_t")
        public int objectTypeCount() { return XrSceneObjectTypesFilterInfoMSFT.nobjectTypeCount(address()); }
        /** @return a {@link IntBuffer} view of the data pointed to by the {@code objectTypes} field. */
        @Nullable
        @NativeType("XrSceneObjectTypeMSFT const *")
        public IntBuffer objectTypes() { return XrSceneObjectTypesFilterInfoMSFT.nobjectTypes(address()); }

        /** Sets the specified value to the {@code type} field. */
        public Buffer type(@NativeType("XrStructureType") int value) { XrSceneObjectTypesFilterInfoMSFT.ntype(address(), value); return this; }
        /** Sets the {@link MSFTSceneUnderstanding#XR_TYPE_SCENE_OBJECT_TYPES_FILTER_INFO_MSFT TYPE_SCENE_OBJECT_TYPES_FILTER_INFO_MSFT} value to the {@code type} field. */
        public Buffer type$Default() { return type(MSFTSceneUnderstanding.XR_TYPE_SCENE_OBJECT_TYPES_FILTER_INFO_MSFT); }
        /** Sets the specified value to the {@code next} field. */
        public Buffer next(@NativeType("void const *") long value) { XrSceneObjectTypesFilterInfoMSFT.nnext(address(), value); return this; }
        /** Sets the specified value to the {@code objectTypeCount} field. */
        public Buffer objectTypeCount(@NativeType("uint32_t") int value) { XrSceneObjectTypesFilterInfoMSFT.nobjectTypeCount(address(), value); return this; }
        /** Sets the address of the specified {@link IntBuffer} to the {@code objectTypes} field. */
        public Buffer objectTypes(@Nullable @NativeType("XrSceneObjectTypeMSFT const *") IntBuffer value) { XrSceneObjectTypesFilterInfoMSFT.nobjectTypes(address(), value); return this; }

    }

}