package com.mj.tree;

import java.util.Comparator;

public class AVLTree<E> extends BBST<E> {

    public AVLTree() {
        this(null);
    }

    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }

    @Override
    protected void afterAdd(Node<E> node) {
        while ((node = node.parent) != null) {
            if (isBalanced(node)) {
                // 更新高度
                updateHeight(node);
            } else {
                // 恢复平衡
                reBalance(node);
                // 整棵树恢复平衡
                break;
            }
        }
    }

    @Override
    protected void afterRemove(Node<E> node, Node<E> replacement) {
        while ((node = node.parent) != null) {
            if (isBalanced(node)) {
                // 更新高度
                updateHeight(node);
            } else {
                // 恢复平衡
                reBalance(node);
            }
        }
    }

    @Override
    protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
        super.afterRotate(grand, parent, child);
        // 更新高度
        updateHeight(grand);
        updateHeight(parent);
    }

    @Override
    protected void rotate(Node<E> r, Node<E> b, Node<E> c, Node<E> d, Node<E> e, Node<E> f) {
        super.rotate(r, b, c, d, e, f);
        updateHeight(b);
        updateHeight(f);
        updateHeight(d);
    }

    // 判断是否平衡
    private boolean isBalanced(Node<E> node) {
        return Math.abs(((AVLNode<E>) node).balanceFactor()) <= 1;
    }

    // 更新高度
    private void updateHeight(Node<E> node) {
        ((AVLNode<E>) node).updateHeight();
    }

    // 恢复平衡,node为高度最低的失衡点,即node的祖父节点
    private void reBalance2(Node<E> grand) {
        Node<E> parent = ((AVLNode<E>) grand).tallerChild();
        Node<E> node = ((AVLNode<E>) parent).tallerChild();
        if (parent.isLeftChild()) { // L
            if (node.isLeftChild()) { // LL
                rotateRight(grand);
            } else { //LR
                rotateLeft(parent);
                rotateRight(grand);
            }
        } else { // R
            if (node.isLeftChild()) { // RL
                rotateRight(parent);
                rotateLeft(grand);
            } else { //RR
                rotateLeft(grand);
            }
        }
    }



    // 恢复平衡,node为高度最低的失衡点,即node的祖父节点
    private void reBalance(Node<E> grand) {
        Node<E> parent = ((AVLNode<E>) grand).tallerChild();
        Node<E> node = ((AVLNode<E>) parent).tallerChild();
        if (parent.isLeftChild()) { // L
            if (node.isLeftChild()) { // LL
                rotate(grand, node, node.right, parent, parent.right, grand);
            } else { //LR
                rotate(grand, parent, node.left, node, node.right, grand);
            }
        } else { // R
            if (node.isLeftChild()) { // RL
                rotate(grand, grand, node.left, node, node.right, parent);
            } else { //RR
                rotate(grand, grand, parent.left, parent, node.left, node);
            }
        }
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new AVLNode<>(element, parent);
    }

    // AVLNode
    private static class AVLNode<E> extends Node<E> {
        int height = 1;

        public AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }

        public int balanceFactor() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            return leftHeight - rightHeight;
        }

        public void updateHeight() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            height = 1 + Math.max(leftHeight, rightHeight);
        }

        public Node<E> tallerChild() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            if (leftHeight > rightHeight) {
                return left;
            } else if (leftHeight < rightHeight) {
                return right;
            } else {
                return isLeftChild() ? left : right;
            }
        }

        @Override
        public String toString() {
            String parentString = "null";
            if (parent != null) {
                parentString = parent.element.toString();
            }

            return element + "_p(" + parentString + ")_h(" + height + ")";
        }
    }


}
