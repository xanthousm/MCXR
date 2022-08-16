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
 * struct XrTriangleMeshCreateInfoFB {
 *     XrStructureType type;
 *     void const * next;
 *     XrTriangleMeshFlagsFB flags;
 *     XrWindingOrderFB windingOrder;
 *     uint32_t vertexCount;
 *     {@link XrVector3f XrVector3f} const * vertexBuffer;
 *     uint32_t triangleCount;
 *     uint32_t const * indexBuffer;
 * }</code></pre>
 */
public class XrTriangleMeshCreateInfoFB extends Struct implements NativeResource {

    /** The struct size in bytes. */
    public static final int SIZEOF;

    /** The struct alignment in bytes. */
    public static final int ALIGNOF;

    /** The struct member offsets. */
    public static final int
        TYPE,
        NEXT,
        FLAGS,
        WINDINGORDER,
        VERTEXCOUNT,
        VERTEXBUFFER,
        TRIANGLECOUNT,
        INDEXBUFFER;

    static {
        Layout layout = __struct(
            __member(4),
            __member(POINTER_SIZE),
            __member(8),
            __member(4),
            __member(4),
            __member(POINTER_SIZE),
            __member(4),
            __member(POINTER_SIZE)
        );

        SIZEOF = layout.getSize();
        ALIGNOF = layout.getAlignment();

        TYPE = layout.offsetof(0);
        NEXT = layout.offsetof(1);
        FLAGS = layout.offsetof(2);
        WINDINGORDER = layout.offsetof(3);
        VERTEXCOUNT = layout.offsetof(4);
        VERTEXBUFFER = layout.offsetof(5);
        TRIANGLECOUNT = layout.offsetof(6);
        INDEXBUFFER = layout.offsetof(7);
    }

    /**
     * Creates a {@code XrTriangleMeshCreateInfoFB} instance at the current position of the specified {@link ByteBuffer} container. Changes to the buffer's content will be
     * visible to the struct instance and vice versa.
     *
     * <p>The created instance holds a strong reference to the container object.</p>
     */
    public XrTriangleMeshCreateInfoFB(ByteBuffer container) {
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
    /** @return the value of the {@code flags} field. */
    @NativeType("XrTriangleMeshFlagsFB")
    public long flags() { return nflags(address()); }
    /** @return the value of the {@code windingOrder} field. */
    @NativeType("XrWindingOrderFB")
    public int windingOrder() { return nwindingOrder(address()); }
    /** @return the value of the {@code vertexCount} field. */
    @NativeType("uint32_t")
    public int vertexCount() { return nvertexCount(address()); }
    /** @return a {@link XrVector3f} view of the struct pointed to by the {@code vertexBuffer} field. */
    @Nullable
    @NativeType("XrVector3f const *")
    public XrVector3f vertexBuffer() { return nvertexBuffer(address()); }
    /** @return the value of the {@code triangleCount} field. */
    @NativeType("uint32_t")
    public int triangleCount() { return ntriangleCount(address()); }
    /**
     * @return a {@link IntBuffer} view of the data pointed to by the {@code indexBuffer} field.
     *
     * @param capacity the number of elements in the returned buffer
     */
    @Nullable
    @NativeType("uint32_t const *")
    public IntBuffer indexBuffer(int capacity) { return nindexBuffer(address(), capacity); }

    /** Sets the specified value to the {@code type} field. */
    public XrTriangleMeshCreateInfoFB type(@NativeType("XrStructureType") int value) { ntype(address(), value); return this; }
    /** Sets the {@link FBTriangleMesh#XR_TYPE_TRIANGLE_MESH_CREATE_INFO_FB TYPE_TRIANGLE_MESH_CREATE_INFO_FB} value to the {@code type} field. */
    public XrTriangleMeshCreateInfoFB type$Default() { return type(FBTriangleMesh.XR_TYPE_TRIANGLE_MESH_CREATE_INFO_FB); }
    /** Sets the specified value to the {@code next} field. */
    public XrTriangleMeshCreateInfoFB next(@NativeType("void const *") long value) { nnext(address(), value); return this; }
    /** Sets the specified value to the {@code flags} field. */
    public XrTriangleMeshCreateInfoFB flags(@NativeType("XrTriangleMeshFlagsFB") long value) { nflags(address(), value); return this; }
    /** Sets the specified value to the {@code windingOrder} field. */
    public XrTriangleMeshCreateInfoFB windingOrder(@NativeType("XrWindingOrderFB") int value) { nwindingOrder(address(), value); return this; }
    /** Sets the specified value to the {@code vertexCount} field. */
    public XrTriangleMeshCreateInfoFB vertexCount(@NativeType("uint32_t") int value) { nvertexCount(address(), value); return this; }
    /** Sets the address of the specified {@link XrVector3f} to the {@code vertexBuffer} field. */
    public XrTriangleMeshCreateInfoFB vertexBuffer(@Nullable @NativeType("XrVector3f const *") XrVector3f value) { nvertexBuffer(address(), value); return this; }
    /** Sets the specified value to the {@code triangleCount} field. */
    public XrTriangleMeshCreateInfoFB triangleCount(@NativeType("uint32_t") int value) { ntriangleCount(address(), value); return this; }
    /** Sets the address of the specified {@link IntBuffer} to the {@code indexBuffer} field. */
    public XrTriangleMeshCreateInfoFB indexBuffer(@Nullable @NativeType("uint32_t const *") IntBuffer value) { nindexBuffer(address(), value); return this; }

    /** Initializes this struct with the specified values. */
    public XrTriangleMeshCreateInfoFB set(
        int type,
        long next,
        long flags,
        int windingOrder,
        int vertexCount,
        @Nullable XrVector3f vertexBuffer,
        int triangleCount,
        @Nullable IntBuffer indexBuffer
    ) {
        type(type);
        next(next);
        flags(flags);
        windingOrder(windingOrder);
        vertexCount(vertexCount);
        vertexBuffer(vertexBuffer);
        triangleCount(triangleCount);
        indexBuffer(indexBuffer);

        return this;
    }

    /**
     * Copies the specified struct data to this struct.
     *
     * @param src the source struct
     *
     * @return this struct
     */
    public XrTriangleMeshCreateInfoFB set(XrTriangleMeshCreateInfoFB src) {
        memCopy(src.address(), address(), SIZEOF);
        return this;
    }

    // -----------------------------------

    /** Returns a new {@code XrTriangleMeshCreateInfoFB} instance allocated with {@link MemoryUtil#memAlloc memAlloc}. The instance must be explicitly freed. */
    public static XrTriangleMeshCreateInfoFB malloc() {
        return wrap(XrTriangleMeshCreateInfoFB.class, nmemAllocChecked(SIZEOF));
    }

    /** Returns a new {@code XrTriangleMeshCreateInfoFB} instance allocated with {@link MemoryUtil#memCalloc memCalloc}. The instance must be explicitly freed. */
    public static XrTriangleMeshCreateInfoFB calloc() {
        return wrap(XrTriangleMeshCreateInfoFB.class, nmemCallocChecked(1, SIZEOF));
    }

    /** Returns a new {@code XrTriangleMeshCreateInfoFB} instance allocated with {@link BufferUtils}. */
    public static XrTriangleMeshCreateInfoFB create() {
        ByteBuffer container = BufferUtils.createByteBuffer(SIZEOF);
        return wrap(XrTriangleMeshCreateInfoFB.class, memAddress(container), container);
    }

    /** Returns a new {@code XrTriangleMeshCreateInfoFB} instance for the specified memory address. */
    public static XrTriangleMeshCreateInfoFB create(long address) {
        return wrap(XrTriangleMeshCreateInfoFB.class, address);
    }

    /** Like {@link #create(long) create}, but returns {@code null} if {@code address} is {@code NULL}. */
    @Nullable
    public static XrTriangleMeshCreateInfoFB createSafe(long address) {
        return address == NULL ? null : wrap(XrTriangleMeshCreateInfoFB.class, address);
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
    public static XrTriangleMeshCreateInfoFB.Buffer createSafe(long address, int capacity) {
        return address == NULL ? null : wrap(Buffer.class, address, capacity);
    }


    /**
     * Returns a new {@code XrTriangleMeshCreateInfoFB} instance allocated on the specified {@link MemoryStack}.
     *
     * @param stack the stack from which to allocate
     */
    public static XrTriangleMeshCreateInfoFB malloc(MemoryStack stack) {
        return wrap(XrTriangleMeshCreateInfoFB.class, stack.nmalloc(ALIGNOF, SIZEOF));
    }

    /**
     * Returns a new {@code XrTriangleMeshCreateInfoFB} instance allocated on the specified {@link MemoryStack} and initializes all its bits to zero.
     *
     * @param stack the stack from which to allocate
     */
    public static XrTriangleMeshCreateInfoFB calloc(MemoryStack stack) {
        return wrap(XrTriangleMeshCreateInfoFB.class, stack.ncalloc(ALIGNOF, 1, SIZEOF));
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
    public static int ntype(long struct) { return UNSAFE.getInt(null, struct + XrTriangleMeshCreateInfoFB.TYPE); }
    /** Unsafe version of {@link #next}. */
    public static long nnext(long struct) { return memGetAddress(struct + XrTriangleMeshCreateInfoFB.NEXT); }
    /** Unsafe version of {@link #flags}. */
    public static long nflags(long struct) { return UNSAFE.getLong(null, struct + XrTriangleMeshCreateInfoFB.FLAGS); }
    /** Unsafe version of {@link #windingOrder}. */
    public static int nwindingOrder(long struct) { return UNSAFE.getInt(null, struct + XrTriangleMeshCreateInfoFB.WINDINGORDER); }
    /** Unsafe version of {@link #vertexCount}. */
    public static int nvertexCount(long struct) { return UNSAFE.getInt(null, struct + XrTriangleMeshCreateInfoFB.VERTEXCOUNT); }
    /** Unsafe version of {@link #vertexBuffer}. */
    @Nullable public static XrVector3f nvertexBuffer(long struct) { return XrVector3f.createSafe(memGetAddress(struct + XrTriangleMeshCreateInfoFB.VERTEXBUFFER)); }
    /** Unsafe version of {@link #triangleCount}. */
    public static int ntriangleCount(long struct) { return UNSAFE.getInt(null, struct + XrTriangleMeshCreateInfoFB.TRIANGLECOUNT); }
    /** Unsafe version of {@link #indexBuffer(int) indexBuffer}. */
    @Nullable public static IntBuffer nindexBuffer(long struct, int capacity) { return memIntBufferSafe(memGetAddress(struct + XrTriangleMeshCreateInfoFB.INDEXBUFFER), capacity); }

    /** Unsafe version of {@link #type(int) type}. */
    public static void ntype(long struct, int value) { UNSAFE.putInt(null, struct + XrTriangleMeshCreateInfoFB.TYPE, value); }
    /** Unsafe version of {@link #next(long) next}. */
    public static void nnext(long struct, long value) { memPutAddress(struct + XrTriangleMeshCreateInfoFB.NEXT, value); }
    /** Unsafe version of {@link #flags(long) flags}. */
    public static void nflags(long struct, long value) { UNSAFE.putLong(null, struct + XrTriangleMeshCreateInfoFB.FLAGS, value); }
    /** Unsafe version of {@link #windingOrder(int) windingOrder}. */
    public static void nwindingOrder(long struct, int value) { UNSAFE.putInt(null, struct + XrTriangleMeshCreateInfoFB.WINDINGORDER, value); }
    /** Unsafe version of {@link #vertexCount(int) vertexCount}. */
    public static void nvertexCount(long struct, int value) { UNSAFE.putInt(null, struct + XrTriangleMeshCreateInfoFB.VERTEXCOUNT, value); }
    /** Unsafe version of {@link #vertexBuffer(XrVector3f) vertexBuffer}. */
    public static void nvertexBuffer(long struct, @Nullable XrVector3f value) { memPutAddress(struct + XrTriangleMeshCreateInfoFB.VERTEXBUFFER, memAddressSafe(value)); }
    /** Unsafe version of {@link #triangleCount(int) triangleCount}. */
    public static void ntriangleCount(long struct, int value) { UNSAFE.putInt(null, struct + XrTriangleMeshCreateInfoFB.TRIANGLECOUNT, value); }
    /** Unsafe version of {@link #indexBuffer(IntBuffer) indexBuffer}. */
    public static void nindexBuffer(long struct, @Nullable IntBuffer value) { memPutAddress(struct + XrTriangleMeshCreateInfoFB.INDEXBUFFER, memAddressSafe(value)); }

    // -----------------------------------

    /** An array of {@link XrTriangleMeshCreateInfoFB} structs. */
    public static class Buffer extends StructBuffer<XrTriangleMeshCreateInfoFB, Buffer> implements NativeResource {

        private static final XrTriangleMeshCreateInfoFB ELEMENT_FACTORY = XrTriangleMeshCreateInfoFB.create(-1L);

        /**
         * Creates a new {@code XrTriangleMeshCreateInfoFB.Buffer} instance backed by the specified container.
         *
         * Changes to the container's content will be visible to the struct buffer instance and vice versa. The two buffers' position, limit, and mark values
         * will be independent. The new buffer's position will be zero, its capacity and its limit will be the number of bytes remaining in this buffer divided
         * by {@link XrTriangleMeshCreateInfoFB#SIZEOF}, and its mark will be undefined.
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
        protected XrTriangleMeshCreateInfoFB getElementFactory() {
            return ELEMENT_FACTORY;
        }

        /** @return the value of the {@code type} field. */
        @NativeType("XrStructureType")
        public int type() { return XrTriangleMeshCreateInfoFB.ntype(address()); }
        /** @return the value of the {@code next} field. */
        @NativeType("void const *")
        public long next() { return XrTriangleMeshCreateInfoFB.nnext(address()); }
        /** @return the value of the {@code flags} field. */
        @NativeType("XrTriangleMeshFlagsFB")
        public long flags() { return XrTriangleMeshCreateInfoFB.nflags(address()); }
        /** @return the value of the {@code windingOrder} field. */
        @NativeType("XrWindingOrderFB")
        public int windingOrder() { return XrTriangleMeshCreateInfoFB.nwindingOrder(address()); }
        /** @return the value of the {@code vertexCount} field. */
        @NativeType("uint32_t")
        public int vertexCount() { return XrTriangleMeshCreateInfoFB.nvertexCount(address()); }
        /** @return a {@link XrVector3f} view of the struct pointed to by the {@code vertexBuffer} field. */
        @Nullable
        @NativeType("XrVector3f const *")
        public XrVector3f vertexBuffer() { return XrTriangleMeshCreateInfoFB.nvertexBuffer(address()); }
        /** @return the value of the {@code triangleCount} field. */
        @NativeType("uint32_t")
        public int triangleCount() { return XrTriangleMeshCreateInfoFB.ntriangleCount(address()); }
        /**
         * @return a {@link IntBuffer} view of the data pointed to by the {@code indexBuffer} field.
         *
         * @param capacity the number of elements in the returned buffer
         */
        @Nullable
        @NativeType("uint32_t const *")
        public IntBuffer indexBuffer(int capacity) { return XrTriangleMeshCreateInfoFB.nindexBuffer(address(), capacity); }

        /** Sets the specified value to the {@code type} field. */
        public Buffer type(@NativeType("XrStructureType") int value) { XrTriangleMeshCreateInfoFB.ntype(address(), value); return this; }
        /** Sets the {@link FBTriangleMesh#XR_TYPE_TRIANGLE_MESH_CREATE_INFO_FB TYPE_TRIANGLE_MESH_CREATE_INFO_FB} value to the {@code type} field. */
        public Buffer type$Default() { return type(FBTriangleMesh.XR_TYPE_TRIANGLE_MESH_CREATE_INFO_FB); }
        /** Sets the specified value to the {@code next} field. */
        public Buffer next(@NativeType("void const *") long value) { XrTriangleMeshCreateInfoFB.nnext(address(), value); return this; }
        /** Sets the specified value to the {@code flags} field. */
        public Buffer flags(@NativeType("XrTriangleMeshFlagsFB") long value) { XrTriangleMeshCreateInfoFB.nflags(address(), value); return this; }
        /** Sets the specified value to the {@code windingOrder} field. */
        public Buffer windingOrder(@NativeType("XrWindingOrderFB") int value) { XrTriangleMeshCreateInfoFB.nwindingOrder(address(), value); return this; }
        /** Sets the specified value to the {@code vertexCount} field. */
        public Buffer vertexCount(@NativeType("uint32_t") int value) { XrTriangleMeshCreateInfoFB.nvertexCount(address(), value); return this; }
        /** Sets the address of the specified {@link XrVector3f} to the {@code vertexBuffer} field. */
        public Buffer vertexBuffer(@Nullable @NativeType("XrVector3f const *") XrVector3f value) { XrTriangleMeshCreateInfoFB.nvertexBuffer(address(), value); return this; }
        /** Sets the specified value to the {@code triangleCount} field. */
        public Buffer triangleCount(@NativeType("uint32_t") int value) { XrTriangleMeshCreateInfoFB.ntriangleCount(address(), value); return this; }
        /** Sets the address of the specified {@link IntBuffer} to the {@code indexBuffer} field. */
        public Buffer indexBuffer(@Nullable @NativeType("uint32_t const *") IntBuffer value) { XrTriangleMeshCreateInfoFB.nindexBuffer(address(), value); return this; }

    }

}