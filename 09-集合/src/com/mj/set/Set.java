package com.mj.set;

public interface Set<E> {
    int size();

    boolean isEmpty();

    void clear();

    boolean contains(E element);

    void add(E element);

    void remove(E element);

    void traversal(Vistor<E> vistor);

    public static abstract class Vistor<E> {
        boolean stop;

        public abstract boolean visit(E element);
    }
}
