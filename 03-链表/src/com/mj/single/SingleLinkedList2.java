package com.mj.single;

import com.mj.ArrayList;

/**
 * 增加一个虚拟头节点
 *
 * @param <E>
 */
public class SingleLinkedList2<E> extends ArrayList<E> {
    private Node<E> first;

    public SingleLinkedList2() {
        first = new Node<>(null, null);
    }

    @Override
    public void clear() {
        first = null;
        size = 0;
    }

    @Override
    public E get(int index) {
        return node(index).element;
    }

    @Override
    public E set(int index, E element) {
        Node<E> node = node(index);
        E old = node.element;
        node.element = element;
        return old;
    }

    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);

        Node<E> prev = index == 0 ? first : node(index - 1);
        prev.next = new Node<>(element, prev.next);

        size++;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);

        Node<E> prev = index == 0 ? first : node(index - 1);
        Node<E> old = prev.next;
        prev.next = old.next;

        size--;
        return old.element;
    }

    @Override
    public int indexOf(E element) {
        Node<E> node = first;
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (node.element == null) {
                    return i;
                }
                node = node.next;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(node.element)) {
                    return i;
                }
                node = node.next;
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    @Override
    public String toString() {
        // size = 3 , [99, 88, 77]
        Node<E> node = first.next;
        StringBuilder string = new StringBuilder();
        string.append("size = ").append(size).append(", [");
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                string.append(", ");
            }
            string.append(node.element);
            node = node.next;
        }
        string.append("]");
        return string.toString();
    }

    /**
     * 获取index对应节点对象
     *
     * @param index
     * @return
     */
    private Node<E> node(int index) {
        rangeCheck(index);
        Node<E> node = first.next;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    private class Node<E> {
        E element;
        Node<E> next;

        // 构造函数
        public Node(E elementE, Node<E> next) {
            this.element = elementE;
            this.next = next;
        }
    }
}
