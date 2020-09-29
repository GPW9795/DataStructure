package com.mj;

public class ArrayList {
    /**
     * 元素数量
     */
    private int size;
    /**
     * 所有元素
     */
    private int[] elements;

    private static final int DEFAULT_CAPACITY = 10;
    private static final int ELEMENT_NOT_FOUND = -1;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int capacity) {
        capacity = (capacity < DEFAULT_CAPACITY) ? DEFAULT_CAPACITY : capacity;
        elements = new int[capacity];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + ", Size " + size);
        }
        return elements[index];
    }

    public int set(int index, int element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + ", Size " + size);
        }
        int old = elements[index];
        elements[index] = element;
        return old;
    }

    public int indexOf(int element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == element) {
                return i;
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    public boolean contains(int element) {
        return indexOf(element) != ELEMENT_NOT_FOUND;
    }

    public void clear() {
        size = 0;
    }
}
