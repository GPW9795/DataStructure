package com.mj;

import java.util.Arrays;

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
        rangeCheck(index);
        return elements[index];
    }

    public int set(int index, int element) {
        rangeCheck(index);
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

    public void add(int element) {
        add(size, element);
    }

    public void add(int index, int element) {
        rangeCheckForAdd(index);
        for (int i = size - 1; i >= index; i--) {
            elements[i + 1] = elements[i];
        }
        elements[index] = element;
        size++;
    }

    @Override
    public String toString() {
        // size = 3 , [99, 88, 77]
        StringBuilder string = new StringBuilder();
        string.append("size = ").append(size).append(", [");
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                string.append(", ");
            }
            string.append(elements[i]);
        }
        string.append("]");
        return string.toString();
    }

    public int remove(int index) {
        rangeCheck(index);
        int old = elements[index];
        for (int i = index + 1; i < size; i++) {
            elements[i - 1] = elements[i];
        }
        size--;
        return old;
    }

    private void outOfBounds(int index){
        throw new IndexOutOfBoundsException("Index " + index + ", Size " + size);
    }

    private void rangeCheck(int index){
        if (index < 0 || index >= size) {
            outOfBounds(index);
        }
    }

    private void rangeCheckForAdd(int index){
        if (index < 0 || index > size) {
            outOfBounds(index);
        }
    }
}
