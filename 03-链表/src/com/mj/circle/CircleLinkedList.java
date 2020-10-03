package com.mj.circle;

import com.mj.AbstractList;

public class CircleLinkedList<E> extends AbstractList<E> {

    private Node<E> first;
    private Node<E> last;
    private Node<E> current;

    private class Node<E> {
        E element;
        Node<E> next;
        Node<E> prev;

        // 构造函数
        public Node(Node<E> prev, E element, Node<E> next) {
            this.prev = prev;
            this.element = element;
            this.next = next;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (prev != null) {
                sb.append(prev.element);
            } else {
                sb.append("null");
            }
            sb.append("<-").append(element).append("->");
            if (next != null) {
                sb.append(next.element);
            } else {
                sb.append("null");
            }
            return sb.toString();
        }
    }

    /**
     * 获取index对应节点对象
     *
     * @param index
     * @return
     */
    private Node<E> node(int index) {
        rangeCheck(index);
        if (index < (size >> 1)) {
            Node<E> node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;
        } else {
            Node<E> node = last;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
            return node;
        }
    }

    @Override
    public void clear() {
        size = 0;
        first = null;
        last = null;
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
        if (index == size) { // 往最后面添加元素
            Node<E> node = new Node<>(last, element, first);
            if (size == 0) { // 链表添加的第一个元素
                first = node;
                last = node;
                node.prev = node;
                node.next = node;
            } else {
                last.next = node;
                first.prev = node;
                last = node;
            }
        } else {
            Node<E> next = node(index);
            Node<E> prev = next.prev;
            Node<E> node = new Node<>(prev, element, next);
            next.prev = node;
            prev.next = node;
            if (next == first) { // index == 0
                first = node;
            }
        }
        size++;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        return remove(node(index));
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
        Node<E> node = first;
        StringBuilder string = new StringBuilder();
        string.append("size = ").append(size).append(", [");
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                string.append(", ");
            }
            string.append(node);
            node = node.next;
        }
        string.append("]");
        return string.toString();
    }

    public void reset() {
        current = first;
    }

    public E next() {
        if (current == null) {
            return null;
        }
        current = current.next;
        return current.element;
    }

    public E remove() {
        if (current == null) {
            return null;
        }
        Node<E> next = current.next;
        E element = remove(current);
        if (size == 0) {
            current = null;
        } else {
            current = next;
        }

        return element;
    }

    private E remove(Node<E> node) {
        if (size == 1) {
            first = null;
            last = null;
        } else {
            Node<E> prev = node.prev;
            Node<E> next = node.next;
            prev.next = next;
            next.prev = prev;
            // 删除的是第一个节点, index == 0
            if (node == first) {
                first = next;
            }
            // 删除的是最后一个节点, index == size - 1
            if (node == last) {
                last = prev;
            }
        }
        size--;
        return node.element;
    }
}

