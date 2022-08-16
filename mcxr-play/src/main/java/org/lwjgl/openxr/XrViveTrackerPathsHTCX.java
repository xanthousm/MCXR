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

import static org.lwjgl.system.MemoryUtil.*;

/**
 * <h3>Layout</h3>
 * 
 * <pre><code>
 * struct XrViveTrackerPathsHTCX {
 *     XrStructureType type;
 *     void * next;
 *     XrPath persistentPath;
 *     XrPath rolePath;
 * }</code></pre>
 */
public class XrViveTrackerPathsHTCX extends Struct implements NativeResource {

    /** The struct size in bytes. */
    public static final int SIZEOF;

    /** The struct alignment in bytes. */
    public static final int ALIGNOF;

    /** The struct member offsets. */
    public static final int
        TYPE,
        NEXT,
        PERSISTENTPATH,
        ROLEPATH;

    static {
        Layout layout = __struct(
            __member(4),
            __member(POINTER_SIZE),
            __member(8),
            __member(8)
        );

        SIZEOF = layout.getSize();
        ALIGNOF = layout.getAlignment();

        TYPE = layout.offsetof(0);
        NEXT = layout.offsetof(1);
        PERSISTENTPATH = layout.offsetof(2);
        ROLEPATH = layout.offsetof(3);
    }

    /**
     * Creates a {@code XrViveTrackerPathsHTCX} instance at the current position of the specified {@link ByteBuffer} container. Changes to the buffer's content will be
     * visible to the struct instance and vice versa.
     *
     * <p>The created instance holds a strong reference to the container object.</p>
     */
    public XrViveTrackerPathsHTCX(ByteBuffer container) {
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
    /** @return the value of the {@code persistentPath} field. */
    @NativeType("XrPath")
    public long persistentPath() { return npersistentPath(address()); }
    /** @return the value of the {@code rolePath} field. */
    @NativeType("XrPath")
    public long rolePath() { return nrolePath(address()); }

    /** Sets the specified value to the {@code type} field. */
    public XrViveTrackerPathsHTCX type(@NativeType("XrStructureType") int value) { ntype(address(), value); return this; }
    /** Sets the {@link HTCXViveTrackerInteraction#XR_TYPE_VIVE_TRACKER_PATHS_HTCX TYPE_VIVE_TRACKER_PATHS_HTCX} value to the {@code type} field. */
    public XrViveTrackerPathsHTCX type$Default() { return type(HTCXViveTrackerInteraction.XR_TYPE_VIVE_TRACKER_PATHS_HTCX); }
    /** Sets the specified value to the {@code next} field. */
    public XrViveTrackerPathsHTCX next(@NativeType("void *") long value) { nnext(address(), value); return this; }

    /** Initializes this struct with the specified values. */
    public XrViveTrackerPathsHTCX set(
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
    public XrViveTrackerPathsHTCX set(XrViveTrackerPathsHTCX src) {
        memCopy(src.address(), address(), SIZEOF);
        return this;
    }

    // -----------------------------------

    /** Returns a new {@code XrViveTrackerPathsHTCX} instance allocated with {@link MemoryUtil#memAlloc memAlloc}. The instance must be explicitly freed. */
    public static XrViveTrackerPathsHTCX malloc() {
        return wrap(XrViveTrackerPathsHTCX.class, nmemAllocChecked(SIZEOF));
    }

    /** Returns a new {@code XrViveTrackerPathsHTCX} instance allocated with {@link MemoryUtil#memCalloc memCalloc}. The instance must be explicitly freed. */
    public static XrViveTrackerPathsHTCX calloc() {
        return wrap(XrViveTrackerPathsHTCX.class, nmemCallocChecked(1, SIZEOF));
    }

    /** Returns a new {@code XrViveTrackerPathsHTCX} instance allocated with {@link BufferUtils}. */
    public static XrViveTrackerPathsHTCX create() {
        ByteBuffer container = BufferUtils.createByteBuffer(SIZEOF);
        return wrap(XrViveTrackerPathsHTCX.class, memAddress(container), container);
    }

    /** Returns a new {@code XrViveTrackerPathsHTCX} instance for the specified memory address. */
    public static XrViveTrackerPathsHTCX create(long address) {
        return wrap(XrViveTrackerPathsHTCX.class, address);
    }

    /** Like {@link #create(long) create}, but returns {@code null} if {@code address} is {@code NULL}. */
    @Nullable
    public static XrViveTrackerPathsHTCX createSafe(long address) {
        return address == NULL ? null : wrap(XrViveTrackerPathsHTCX.class, address);
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
    public static XrViveTrackerPathsHTCX.Buffer createSafe(long address, int capacity) {
        return address == NULL ? null : wrap(Buffer.class, address, capacity);
    }


    /**
     * Returns a new {@code XrViveTrackerPathsHTCX} instance allocated on the specified {@link MemoryStack}.
     *
     * @param stack the stack from which to allocate
     */
    public static XrViveTrackerPathsHTCX malloc(MemoryStack stack) {
        return wrap(XrViveTrackerPathsHTCX.class, stack.nmalloc(ALIGNOF, SIZEOF));
    }

    /**
     * Returns a new {@code XrViveTrackerPathsHTCX} instance allocated on the specified {@link MemoryStack} and initializes all its bits to zero.
     *
     * @param stack the stack from which to allocate
     */
    public static XrViveTrackerPathsHTCX calloc(MemoryStack stack) {
        return wrap(XrViveTrackerPathsHTCX.class, stack.ncalloc(ALIGNOF, 1, SIZEOF));
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
    public static int ntype(long struct) { return UNSAFE.getInt(null, struct + XrViveTrackerPathsHTCX.TYPE); }
    /** Unsafe version of {@link #next}. */
    public static long nnext(long struct) { return memGetAddress(struct + XrViveTrackerPathsHTCX.NEXT); }
    /** Unsafe version of {@link #persistentPath}. */
    public static long npersistentPath(long struct) { return UNSAFE.getLong(null, struct + XrViveTrackerPathsHTCX.PERSISTENTPATH); }
    /** Unsafe version of {@link #rolePath}. */
    public static long nrolePath(long struct) { return UNSAFE.getLong(null, struct + XrViveTrackerPathsHTCX.ROLEPATH); }

    /** Unsafe version of {@link #type(int) type}. */
    public static void ntype(long struct, int value) { UNSAFE.putInt(null, struct + XrViveTrackerPathsHTCX.TYPE, value); }
    /** Unsafe version of {@link #next(long) next}. */
    public static void nnext(long struct, long value) { memPutAddress(struct + XrViveTrackerPathsHTCX.NEXT, value); }

    // -----------------------------------

    /** An array of {@link XrViveTrackerPathsHTCX} structs. */
    public static class Buffer extends StructBuffer<XrViveTrackerPathsHTCX, Buffer> implements NativeResource {

        private static final XrViveTrackerPathsHTCX ELEMENT_FACTORY = XrViveTrackerPathsHTCX.create(-1L);

        /**
         * Creates a new {@code XrViveTrackerPathsHTCX.Buffer} instance backed by the specified container.
         *
         * Changes to the container's content will be visible to the struct buffer instance and vice versa. The two buffers' position, limit, and mark values
         * will be independent. The new buffer's position will be zero, its capacity and its limit will be the number of bytes remaining in this buffer divided
         * by {@link XrViveTrackerPathsHTCX#SIZEOF}, and its mark will be undefined.
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
        protected XrViveTrackerPathsHTCX getElementFactory() {
            return ELEMENT_FACTORY;
        }

        /** @return the value of the {@code type} field. */
        @NativeType("XrStructureType")
        public int type() { return XrViveTrackerPathsHTCX.ntype(address()); }
        /** @return the value of the {@code next} field. */
        @NativeType("void *")
        public long next() { return XrViveTrackerPathsHTCX.nnext(address()); }
        /** @return the value of the {@code persistentPath} field. */
        @NativeType("XrPath")
        public long persistentPath() { return XrViveTrackerPathsHTCX.npersistentPath(address()); }
        /** @return the value of the {@code rolePath} field. */
        @NativeType("XrPath")
        public long rolePath() { return XrViveTrackerPathsHTCX.nrolePath(address()); }

        /** Sets the specified value to the {@code type} field. */
        public Buffer type(@NativeType("XrStructureType") int value) { XrViveTrackerPathsHTCX.ntype(address(), value); return this; }
        /** Sets the {@link HTCXViveTrackerInteraction#XR_TYPE_VIVE_TRACKER_PATHS_HTCX TYPE_VIVE_TRACKER_PATHS_HTCX} value to the {@code type} field. */
        public Buffer type$Default() { return type(HTCXViveTrackerInteraction.XR_TYPE_VIVE_TRACKER_PATHS_HTCX); }
        /** Sets the specified value to the {@code next} field. */
        public Buffer next(@NativeType("void *") long value) { XrViveTrackerPathsHTCX.nnext(address(), value); return this; }

    }

}