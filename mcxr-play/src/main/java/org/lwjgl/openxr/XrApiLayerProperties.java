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

import static org.lwjgl.openxr.XR10.XR_MAX_API_LAYER_DESCRIPTION_SIZE;
import static org.lwjgl.openxr.XR10.XR_MAX_API_LAYER_NAME_SIZE;
import static org.lwjgl.system.MemoryUtil.*;

/**
 * <h3>Layout</h3>
 * 
 * <pre><code>
 * struct XrApiLayerProperties {
 *     XrStructureType type;
 *     void * next;
 *     char layerName[XR_MAX_API_LAYER_NAME_SIZE];
 *     XrVersion specVersion;
 *     uint32_t layerVersion;
 *     char description[XR_MAX_API_LAYER_DESCRIPTION_SIZE];
 * }</code></pre>
 */
public class XrApiLayerProperties extends Struct implements NativeResource {

    /** The struct size in bytes. */
    public static final int SIZEOF;

    /** The struct alignment in bytes. */
    public static final int ALIGNOF;

    /** The struct member offsets. */
    public static final int
        TYPE,
        NEXT,
        LAYERNAME,
        SPECVERSION,
        LAYERVERSION,
        DESCRIPTION;

    static {
        Layout layout = __struct(
            __member(4),
            __member(POINTER_SIZE),
            __array(1, XR_MAX_API_LAYER_NAME_SIZE),
            __member(8),
            __member(4),
            __array(1, XR_MAX_API_LAYER_DESCRIPTION_SIZE)
        );

        SIZEOF = layout.getSize();
        ALIGNOF = layout.getAlignment();

        TYPE = layout.offsetof(0);
        NEXT = layout.offsetof(1);
        LAYERNAME = layout.offsetof(2);
        SPECVERSION = layout.offsetof(3);
        LAYERVERSION = layout.offsetof(4);
        DESCRIPTION = layout.offsetof(5);
    }

    /**
     * Creates a {@code XrApiLayerProperties} instance at the current position of the specified {@link ByteBuffer} container. Changes to the buffer's content will be
     * visible to the struct instance and vice versa.
     *
     * <p>The created instance holds a strong reference to the container object.</p>
     */
    public XrApiLayerProperties(ByteBuffer container) {
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
    /** @return a {@link ByteBuffer} view of the {@code layerName} field. */
    @NativeType("char[XR_MAX_API_LAYER_NAME_SIZE]")
    public ByteBuffer layerName() { return nlayerName(address()); }
    /** @return the null-terminated string stored in the {@code layerName} field. */
    @NativeType("char[XR_MAX_API_LAYER_NAME_SIZE]")
    public String layerNameString() { return nlayerNameString(address()); }
    /** @return the value of the {@code specVersion} field. */
    @NativeType("XrVersion")
    public long specVersion() { return nspecVersion(address()); }
    /** @return the value of the {@code layerVersion} field. */
    @NativeType("uint32_t")
    public int layerVersion() { return nlayerVersion(address()); }
    /** @return a {@link ByteBuffer} view of the {@code description} field. */
    @NativeType("char[XR_MAX_API_LAYER_DESCRIPTION_SIZE]")
    public ByteBuffer description() { return ndescription(address()); }
    /** @return the null-terminated string stored in the {@code description} field. */
    @NativeType("char[XR_MAX_API_LAYER_DESCRIPTION_SIZE]")
    public String descriptionString() { return ndescriptionString(address()); }

    /** Sets the specified value to the {@code type} field. */
    public XrApiLayerProperties type(@NativeType("XrStructureType") int value) { ntype(address(), value); return this; }
    /** Sets the {@link XR10#XR_TYPE_API_LAYER_PROPERTIES TYPE_API_LAYER_PROPERTIES} value to the {@code type} field. */
    public XrApiLayerProperties type$Default() { return type(XR10.XR_TYPE_API_LAYER_PROPERTIES); }
    /** Sets the specified value to the {@code next} field. */
    public XrApiLayerProperties next(@NativeType("void *") long value) { nnext(address(), value); return this; }

    /** Initializes this struct with the specified values. */
    public XrApiLayerProperties set(
        int type,
        long next
    ) {
        type(type);
        next(next);

        return this;
    }

    /**
     * Copies the specified struct data to this struct.
     *
     * @param src the source struct
     *
     * @return this struct
     */
    public XrApiLayerProperties set(XrApiLayerProperties src) {
        memCopy(src.address(), address(), SIZEOF);
        return this;
    }

    // -----------------------------------

    /** Returns a new {@code XrApiLayerProperties} instance allocated with {@link MemoryUtil#memAlloc memAlloc}. The instance must be explicitly freed. */
    public static XrApiLayerProperties malloc() {
        return wrap(XrApiLayerProperties.class, nmemAllocChecked(SIZEOF));
    }

    /** Returns a new {@code XrApiLayerProperties} instance allocated with {@link MemoryUtil#memCalloc memCalloc}. The instance must be explicitly freed. */
    public static XrApiLayerProperties calloc() {
        return wrap(XrApiLayerProperties.class, nmemCallocChecked(1, SIZEOF));
    }

    /** Returns a new {@code XrApiLayerProperties} instance allocated with {@link BufferUtils}. */
    public static XrApiLayerProperties create() {
        ByteBuffer container = BufferUtils.createByteBuffer(SIZEOF);
        return wrap(XrApiLayerProperties.class, memAddress(container), container);
    }

    /** Returns a new {@code XrApiLayerProperties} instance for the specified memory address. */
    public static XrApiLayerProperties create(long address) {
        return wrap(XrApiLayerProperties.class, address);
    }

    /** Like {@link #create(long) create}, but returns {@code null} if {@code address} is {@code NULL}. */
    @Nullable
    public static XrApiLayerProperties createSafe(long address) {
        return address == NULL ? null : wrap(XrApiLayerProperties.class, address);
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
    public static XrApiLayerProperties.Buffer createSafe(long address, int capacity) {
        return address == NULL ? null : wrap(Buffer.class, address, capacity);
    }


    /**
     * Returns a new {@code XrApiLayerProperties} instance allocated on the specified {@link MemoryStack}.
     *
     * @param stack the stack from which to allocate
     */
    public static XrApiLayerProperties malloc(MemoryStack stack) {
        return wrap(XrApiLayerProperties.class, stack.nmalloc(ALIGNOF, SIZEOF));
    }

    /**
     * Returns a new {@code XrApiLayerProperties} instance allocated on the specified {@link MemoryStack} and initializes all its bits to zero.
     *
     * @param stack the stack from which to allocate
     */
    public static XrApiLayerProperties calloc(MemoryStack stack) {
        return wrap(XrApiLayerProperties.class, stack.ncalloc(ALIGNOF, 1, SIZEOF));
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
    public static int ntype(long struct) { return UNSAFE.getInt(null, struct + XrApiLayerProperties.TYPE); }
    /** Unsafe version of {@link #next}. */
    public static long nnext(long struct) { return memGetAddress(struct + XrApiLayerProperties.NEXT); }
    /** Unsafe version of {@link #layerName}. */
    public static ByteBuffer nlayerName(long struct) { return memByteBuffer(struct + XrApiLayerProperties.LAYERNAME, XR_MAX_API_LAYER_NAME_SIZE); }
    /** Unsafe version of {@link #layerNameString}. */
    public static String nlayerNameString(long struct) { return memUTF8(struct + XrApiLayerProperties.LAYERNAME); }
    /** Unsafe version of {@link #specVersion}. */
    public static long nspecVersion(long struct) { return UNSAFE.getLong(null, struct + XrApiLayerProperties.SPECVERSION); }
    /** Unsafe version of {@link #layerVersion}. */
    public static int nlayerVersion(long struct) { return UNSAFE.getInt(null, struct + XrApiLayerProperties.LAYERVERSION); }
    /** Unsafe version of {@link #description}. */
    public static ByteBuffer ndescription(long struct) { return memByteBuffer(struct + XrApiLayerProperties.DESCRIPTION, XR_MAX_API_LAYER_DESCRIPTION_SIZE); }
    /** Unsafe version of {@link #descriptionString}. */
    public static String ndescriptionString(long struct) { return memUTF8(struct + XrApiLayerProperties.DESCRIPTION); }

    /** Unsafe version of {@link #type(int) type}. */
    public static void ntype(long struct, int value) { UNSAFE.putInt(null, struct + XrApiLayerProperties.TYPE, value); }
    /** Unsafe version of {@link #next(long) next}. */
    public static void nnext(long struct, long value) { memPutAddress(struct + XrApiLayerProperties.NEXT, value); }

    // -----------------------------------

    /** An array of {@link XrApiLayerProperties} structs. */
    public static class Buffer extends StructBuffer<XrApiLayerProperties, Buffer> implements NativeResource {

        private static final XrApiLayerProperties ELEMENT_FACTORY = XrApiLayerProperties.create(-1L);

        /**
         * Creates a new {@code XrApiLayerProperties.Buffer} instance backed by the specified container.
         *
         * Changes to the container's content will be visible to the struct buffer instance and vice versa. The two buffers' position, limit, and mark values
         * will be independent. The new buffer's position will be zero, its capacity and its limit will be the number of bytes remaining in this buffer divided
         * by {@link XrApiLayerProperties#SIZEOF}, and its mark will be undefined.
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
        protected XrApiLayerProperties getElementFactory() {
            return ELEMENT_FACTORY;
        }

        /** @return the value of the {@code type} field. */
        @NativeType("XrStructureType")
        public int type() { return XrApiLayerProperties.ntype(address()); }
        /** @return the value of the {@code next} field. */
        @NativeType("void *")
        public long next() { return XrApiLayerProperties.nnext(address()); }
        /** @return a {@link ByteBuffer} view of the {@code layerName} field. */
        @NativeType("char[XR_MAX_API_LAYER_NAME_SIZE]")
        public ByteBuffer layerName() { return XrApiLayerProperties.nlayerName(address()); }
        /** @return the null-terminated string stored in the {@code layerName} field. */
        @NativeType("char[XR_MAX_API_LAYER_NAME_SIZE]")
        public String layerNameString() { return XrApiLayerProperties.nlayerNameString(address()); }
        /** @return the value of the {@code specVersion} field. */
        @NativeType("XrVersion")
        public long specVersion() { return XrApiLayerProperties.nspecVersion(address()); }
        /** @return the value of the {@code layerVersion} field. */
        @NativeType("uint32_t")
        public int layerVersion() { return XrApiLayerProperties.nlayerVersion(address()); }
        /** @return a {@link ByteBuffer} view of the {@code description} field. */
        @NativeType("char[XR_MAX_API_LAYER_DESCRIPTION_SIZE]")
        public ByteBuffer description() { return XrApiLayerProperties.ndescription(address()); }
        /** @return the null-terminated string stored in the {@code description} field. */
        @NativeType("char[XR_MAX_API_LAYER_DESCRIPTION_SIZE]")
        public String descriptionString() { return XrApiLayerProperties.ndescriptionString(address()); }

        /** Sets the specified value to the {@code type} field. */
        public Buffer type(@NativeType("XrStructureType") int value) { XrApiLayerProperties.ntype(address(), value); return this; }
        /** Sets the {@link XR10#XR_TYPE_API_LAYER_PROPERTIES TYPE_API_LAYER_PROPERTIES} value to the {@code type} field. */
        public Buffer type$Default() { return type(XR10.XR_TYPE_API_LAYER_PROPERTIES); }
        /** Sets the specified value to the {@code next} field. */
        public Buffer next(@NativeType("void *") long value) { XrApiLayerProperties.nnext(address(), value); return this; }

    }

}