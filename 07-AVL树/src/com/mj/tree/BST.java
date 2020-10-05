package com.mj.tree;

import java.util.*;

public class BST<E> extends BinaryTree<E>{
    private Comparator<E> comparator;

    public BST() {
        this(null);
    }

    public BST(Comparator<E> comparator) {
        this.comparator = comparator;
    }


    public void add(E element) {
        elementNotNullCheck(element);

        // 添加第一个节点
        if (root == null) {
            root = new Node<>(element, null);
            size++;
            // 新添加节点之后的处理
            afterAdd(root);
            return;
        }
        // 添加的不是第一个节点
        // 找到父节点
        Node<E> node = root;
        Node<E> parent = root;
        int cmp = 0;
        while (node != null) {
            cmp = compare(element, node.element);
            parent = node;
            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            } else {
                node.element = element;
                return;
            }
        }

        // 判断插入到哪个位置
        Node<E> newNode = new Node<>(element, parent);
        if (cmp > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        size++;
        // 新添加节点之后的处理
        afterAdd(newNode);
    }

    /**
     * 添加node之后的调整
     * @param node 新添加的节点
     */
    protected void afterAdd(Node<E> node){}

    public void remove(E element) {
        remove(node(element));
    }

    private void remove(Node<E> node) {
        if (node == null) {
            return;
        }
        size--;
        if (node.hasTwoChildren()) { // 度为2
            // 找到后继节点
            Node<E> s = successor(node);
            node.element = s.element;
            node = s;
        }
        // 开始删除node
        Node<E> replacement = (node.left != null) ? node.left : node.right;
        // 度为1
        if (replacement != null) {
            replacement.parent = node.parent;
            if (node.parent == null) { // 根节点
                root = replacement;
            } else if (node == node.parent.left) {
                node.parent.left = replacement;
            } else {
                node.parent.right = replacement;
            }
        } else if (node.parent == null) { // node为叶子结点且为根节点
            root = null;
        } else { // 度为0的叶子结点
            if (node == node.parent.left) {
                node.parent.left = null;
            } else {
                node.parent.right = null;
            }
        }
    }

    private Node<E> node(E element) {
        Node<E> node = root;
        while (node != null) {
            int cmp = compare(element, node.element);
            if (cmp == 0) {
                return node;
            } else if (cmp > 0) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return null;
    }

    public boolean contains(E element) {
        return node(element) != null;
    }




    /**
     * 对比
     *
     * @param e1
     * @param e2
     * @return 0代表e1=e2, 大于0代表e1>e2, 小于0代表e1<e2
     */
    private int compare(E e1, E e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        return ((Comparable<E>) e1).compareTo(e2);
    }

    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element not be null");
        }
    }



}
