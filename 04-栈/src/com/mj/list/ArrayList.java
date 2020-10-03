package com.mj.list;

public class ArrayList<E> extends AbstractList<E> {

    /**
     * 所有元素
     */
    private E[] elements;

    private static final int DEFAULT_CAPACITY = 10;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int capacity) {
        capacity = (capacity < DEFAULT_CAPACITY) ? DEFAULT_CAPACITY : capacity;
        elements = (E[]) new Object[capacity];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E get(int index) {
        rangeCheck(index);
        return elements[index];
    }

    public E set(int index, E element) {
        rangeCheck(index);
        E old = elements[index];
        elements[index] = element;
        return old;
    }

    public int indexOf(E element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(elements[i])) {
                    return i;
                }
            }
        }
        return ELEMENT_NOT_FOUND;
    }


    public void clear() {
        // 减少浪费空间
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
        // 适当缩容
        if (elements != null && elements.length > DEFAULT_CAPACITY) {
            elements = (E[]) new Object[DEFAULT_CAPACITY];
        }
    }


    public void add(int index, E element) {
        rangeCheckForAdd(index);

        ensureCapacity(size + 1);

        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
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

    public E remove(int index) {
        rangeCheck(index);
        E old = elements[index];
        for (int i = index + 1; i < size; i++) {
            elements[i - 1] = elements[i];
        }
        elements[--size] = null;
        return old;
    }

    public void remove(E element) {
        remove(indexOf(element));
    }

    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (oldCapacity > capacity) {
            return;
        }
        // 新容量为旧容量的1.5倍，右移以为等于除以2
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        // 旧的数组指向新的数组
        elements = newElements;
    }
}
