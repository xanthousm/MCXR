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
 * struct XrSceneComponentsMSFT {
 *     XrStructureType type;
 *     void * next;
 *     uint32_t componentCapacityInput;
 *     uint32_t componentCountOutput;
 *     {@link XrSceneComponentMSFT XrSceneComponentMSFT} * components;
 * }</code></pre>
 */
public class XrSceneComponentsMSFT extends Struct implements NativeResource {

    /** The struct size in bytes. */
    public static final int SIZEOF;

    /** The struct alignment in bytes. */
    public static final int ALIGNOF;

    /** The struct member offsets. */
    public static final int
        TYPE,
        NEXT,
        COMPONENTCAPACITYINPUT,
        COMPONENTCOUNTOUTPUT,
        COMPONENTS;

    static {
        Layout layout = __struct(
            __member(4),
            __member(POINTER_SIZE),
            __member(4),
            __member(4),
            __member(POINTER_SIZE)
        );

        SIZEOF = layout.getSize();
        ALIGNOF = layout.getAlignment();

        TYPE = layout.offsetof(0);
        NEXT = layout.offsetof(1);
        COMPONENTCAPACITYINPUT = layout.offsetof(2);
        COMPONENTCOUNTOUTPUT = layout.offsetof(3);
        COMPONENTS = layout.offsetof(4);
    }

    /**
     * Creates a {@code XrSceneComponentsMSFT} instance at the current position of the specified {@link ByteBuffer} container. Changes to the buffer's content will be
     * visible to the struct instance and vice versa.
     *
     * <p>The created instance holds a strong reference to the container object.</p>
     */
    public XrSceneComponentsMSFT(ByteBuffer container) {
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
    /** @return the value of the {@code componentCapacityInput} field. */
    @NativeType("uint32_t")
    public int componentCapacityInput() { return ncomponentCapacityInput(address()); }
    /** @return the value of the {@code componentCountOutput} field. */
    @NativeType("uint32_t")
    public int componentCountOutput() { return ncomponentCountOutput(address()); }
    /** @return a {@link XrSceneComponentMSFT.Buffer} view of the struct array pointed to by the {@code components} field. */
    @Nullable
    @NativeType("XrSceneComponentMSFT *")
    public XrSceneComponentMSFT.Buffer components() { return ncomponents(address()); }

    /** Sets the specified value to the {@code type} field. */
    public XrSceneComponentsMSFT type(@NativeType("XrStructureType") int value) { ntype(address(), value); return this; }
    /** Sets the {@link MSFTSceneUnderstanding#XR_TYPE_SCENE_COMPONENTS_MSFT TYPE_SCENE_COMPONENTS_MSFT} value to the {@code type} field. */
    public XrSceneComponentsMSFT type$Default() { return type(MSFTSceneUnderstanding.XR_TYPE_SCENE_COMPONENTS_MSFT); }
    /** Sets the specified value to the {@code next} field. */
    public XrSceneComponentsMSFT next(@NativeType("void *") long value) { nnext(address(), value); return this; }
    /** Sets the specified value to the {@code componentCapacityInput} field. */
    public XrSceneComponentsMSFT componentCapacityInput(@NativeType("uint32_t") int value) { ncomponentCapacityInput(address(), value); return this; }
    /** Sets the specified value to the {@code componentCountOutput} field. */
    public XrSceneComponentsMSFT componentCountOutput(@NativeType("uint32_t") int value) { ncomponentCountOutput(address(), value); return this; }
    /** Sets the address of the specified {@link XrSceneComponentMSFT.Buffer} to the {@code components} field. */
    public XrSceneComponentsMSFT components(@Nullable @NativeType("XrSceneComponentMSFT *") XrSceneComponentMSFT.Buffer value) { ncomponents(address(), value); return this; }

    /** Initializes this struct with the specified values. */
    public XrSceneComponentsMSFT set(
        int type,
        long next,
        int componentCapacityInput,
        int componentCountOutput,
        @Nullable XrSceneComponentMSFT.Buffer components
    ) {
        type(type);
        next(next);
        componentCapacityInput(componentCapacityInput);
        componentCountOutput(componentCountOutput);
        components(components);

        return this;
    }

    /**
     * Copies the specified struct data to this struct.
     *
     * @param src the source struct
     *
     * @return this struct
     */
    public XrSceneComponentsMSFT set(XrSceneComponentsMSFT src) {
        memCopy(src.address(), address(), SIZEOF);
        return this;
    }

    // -----------------------------------

    /** Returns a new {@code XrSceneComponentsMSFT} instance allocated with {@link MemoryUtil#memAlloc memAlloc}. The instance must be explicitly freed. */
    public static XrSceneComponentsMSFT malloc() {
        return wrap(XrSceneComponentsMSFT.class, nmemAllocChecked(SIZEOF));
    }

    /** Returns a new {@code XrSceneComponentsMSFT} instance allocated with {@link MemoryUtil#memCalloc memCalloc}. The instance must be explicitly freed. */
    public static XrSceneComponentsMSFT calloc() {
        return wrap(XrSceneComponentsMSFT.class, nmemCallocChecked(1, SIZEOF));
    }

    /** Returns a new {@code XrSceneComponentsMSFT} instance allocated with {@link BufferUtils}. */
    public static XrSceneComponentsMSFT create() {
        ByteBuffer container = BufferUtils.createByteBuffer(SIZEOF);
        return wrap(XrSceneComponentsMSFT.class, memAddress(container), container);
    }

    /** Returns a new {@code XrSceneComponentsMSFT} instance for the specified memory address. */
    public static XrSceneComponentsMSFT create(long address) {
        return wrap(XrSceneComponentsMSFT.class, address);
    }

    /** Like {@link #create(long) create}, but returns {@code null} if {@code address} is {@code NULL}. */
    @Nullable
    public static XrSceneComponentsMSFT createSafe(long address) {
        return address == NULL ? null : wrap(XrSceneComponentsMSFT.class, address);
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
    public static XrSceneComponentsMSFT.Buffer createSafe(long address, int capacity) {
        return address == NULL ? null : wrap(Buffer.class, address, capacity);
    }


    /**
     * Returns a new {@code XrSceneComponentsMSFT} instance allocated on the specified {@link MemoryStack}.
     *
     * @param stack the stack from which to allocate
     */
    public static XrSceneComponentsMSFT malloc(MemoryStack stack) {
        return wrap(XrSceneComponentsMSFT.class, stack.nmalloc(ALIGNOF, SIZEOF));
    }

    /**
     * Returns a new {@code XrSceneComponentsMSFT} instance allocated on the specified {@link MemoryStack} and initializes all its bits to zero.
     *
     * @param stack the stack from which to allocate
     */
    public static XrSceneComponentsMSFT calloc(MemoryStack stack) {
        return wrap(XrSceneComponentsMSFT.class, stack.ncalloc(ALIGNOF, 1, SIZEOF));
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
    public static int ntype(long struct) { return UNSAFE.getInt(null, struct + XrSceneComponentsMSFT.TYPE); }
    /** Unsafe version of {@link #next}. */
    public static long nnext(long struct) { return memGetAddress(struct + XrSceneComponentsMSFT.NEXT); }
    /** Unsafe version of {@link #componentCapacityInput}. */
    public static int ncomponentCapacityInput(long struct) { return UNSAFE.getInt(null, struct + XrSceneComponentsMSFT.COMPONENTCAPACITYINPUT); }
    /** Unsafe version of {@link #componentCountOutput}. */
    public static int ncomponentCountOutput(long struct) { return UNSAFE.getInt(null, struct + XrSceneComponentsMSFT.COMPONENTCOUNTOUTPUT); }
    /** Unsafe version of {@link #components}. */
    @Nullable public static XrSceneComponentMSFT.Buffer ncomponents(long struct) { return XrSceneComponentMSFT.createSafe(memGetAddress(struct + XrSceneComponentsMSFT.COMPONENTS), ncomponentCapacityInput(struct)); }

    /** Unsafe version of {@link #type(int) type}. */
    public static void ntype(long struct, int value) { UNSAFE.putInt(null, struct + XrSceneComponentsMSFT.TYPE, value); }
    /** Unsafe version of {@link #next(long) next}. */
    public static void nnext(long struct, long value) { memPutAddress(struct + XrSceneComponentsMSFT.NEXT, value); }
    /** Sets the specified value to the {@code componentCapacityInput} field of the specified {@code struct}. */
    public static void ncomponentCapacityInput(long struct, int value) { UNSAFE.putInt(null, struct + XrSceneComponentsMSFT.COMPONENTCAPACITYINPUT, value); }
    /** Unsafe version of {@link #componentCountOutput(int) componentCountOutput}. */
    public static void ncomponentCountOutput(long struct, int value) { UNSAFE.putInt(null, struct + XrSceneComponentsMSFT.COMPONENTCOUNTOUTPUT, value); }
    /** Unsafe version of {@link #components(XrSceneComponentMSFT.Buffer) components}. */
    public static void ncomponents(long struct, @Nullable XrSceneComponentMSFT.Buffer value) { memPutAddress(struct + XrSceneComponentsMSFT.COMPONENTS, memAddressSafe(value)); if (value != null) { ncomponentCapacityInput(struct, value.remaining()); } }

    // -----------------------------------

    /** An array of {@link XrSceneComponentsMSFT} structs. */
    public static class Buffer extends StructBuffer<XrSceneComponentsMSFT, Buffer> implements NativeResource {

        private static final XrSceneComponentsMSFT ELEMENT_FACTORY = XrSceneComponentsMSFT.create(-1L);

        /**
         * Creates a new {@code XrSceneComponentsMSFT.Buffer} instance backed by the specified container.
         *
         * Changes to the container's content will be visible to the struct buffer instance and vice versa. The two buffers' position, limit, and mark values
         * will be independent. The new buffer's position will be zero, its capacity and its limit will be the number of bytes remaining in this buffer divided
         * by {@link XrSceneComponentsMSFT#SIZEOF}, and its mark will be undefined.
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
        protected XrSceneComponentsMSFT getElementFactory() {
            return ELEMENT_FACTORY;
        }

        /** @return the value of the {@code type} field. */
        @NativeType("XrStructureType")
        public int type() { return XrSceneComponentsMSFT.ntype(address()); }
        /** @return the value of the {@code next} field. */
        @NativeType("void *")
        public long next() { return XrSceneComponentsMSFT.nnext(address()); }
        /** @return the value of the {@code componentCapacityInput} field. */
        @NativeType("uint32_t")
        public int componentCapacityInput() { return XrSceneComponentsMSFT.ncomponentCapacityInput(address()); }
        /** @return the value of the {@code componentCountOutput} field. */
        @NativeType("uint32_t")
        public int componentCountOutput() { return XrSceneComponentsMSFT.ncomponentCountOutput(address()); }
        /** @return a {@link XrSceneComponentMSFT.Buffer} view of the struct array pointed to by the {@code components} field. */
        @Nullable
        @NativeType("XrSceneComponentMSFT *")
        public XrSceneComponentMSFT.Buffer components() { return XrSceneComponentsMSFT.ncomponents(address()); }

        /** Sets the specified value to the {@code type} field. */
        public Buffer type(@NativeType("XrStructureType") int value) { XrSceneComponentsMSFT.ntype(address(), value); return this; }
        /** Sets the {@link MSFTSceneUnderstanding#XR_TYPE_SCENE_COMPONENTS_MSFT TYPE_SCENE_COMPONENTS_MSFT} value to the {@code type} field. */
        public Buffer type$Default() { return type(MSFTSceneUnderstanding.XR_TYPE_SCENE_COMPONENTS_MSFT); }
        /** Sets the specified value to the {@code next} field. */
        public Buffer next(@NativeType("void *") long value) { XrSceneComponentsMSFT.nnext(address(), value); return this; }
        /** Sets the specified value to the {@code componentCapacityInput} field. */
        public Buffer componentCapacityInput(@NativeType("uint32_t") int value) { XrSceneComponentsMSFT.ncomponentCapacityInput(address(), value); return this; }
        /** Sets the specified value to the {@code componentCountOutput} field. */
        public Buffer componentCountOutput(@NativeType("uint32_t") int value) { XrSceneComponentsMSFT.ncomponentCountOutput(address(), value); return this; }
        /** Sets the address of the specified {@link XrSceneComponentMSFT.Buffer} to the {@code components} field. */
        public Buffer components(@Nullable @NativeType("XrSceneComponentMSFT *") XrSceneComponentMSFT.Buffer value) { XrSceneComponentsMSFT.ncomponents(address(), value); return this; }

    }

}