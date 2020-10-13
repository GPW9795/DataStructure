package com.mj.heap;

public interface Heap<E> {
    int size();

    boolean isEmpty();

    void clear();

    void add(E element);

    E get();

    E remove();

    E replace(E element); // 删除堆顶元素的同时插入一个新元素
}
