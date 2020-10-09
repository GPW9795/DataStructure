package com.mj.tree;

import java.util.Comparator;

public class RBTree<E> extends BBST<E> {

    private static final boolean RED = false;
    private static final boolean BLACK = true;

    public RBTree() {
        this(null);
    }

    public RBTree(Comparator<E> comparator) {
        super(comparator);
    }

    @Override
    protected void afterAdd(Node<E> node) {
        Node<E> parent = node.parent;
        // 如果添加的是根节点，直接染成黑色返回(或上溢到根节点)
        if (parent == null) {
            black(node);
            return;
        }
        // 如果父节点是黑色，不用处理直接返回
        if (isBlack(parent)) return;

        // uncle节点和祖父节点
        Node<E> uncle = parent.sibling();
        Node<E> grand = red(parent.parent);
        if (isRed(uncle)) { // uncle是红色【B树节点上溢】
            black(parent);
            black(uncle);
            // 祖父节点当作新添加的节点
            afterAdd(grand);
            return;
        }
        // uncle节点不是红色
        if (parent.isLeftChild()) { // L
            if (node.isLeftChild()) { // L
                black(parent);
            } else { // LR
                black(node);
                rotateLeft(parent);
            }
            rotateRight(grand);
        } else { // R
            if (node.isLeftChild()) { // RL
                black(node);
                rotateRight(parent);
            } else { // RR
                black(parent);
            }
            rotateLeft(grand);
        }
    }

    /**
     * 删除node之后的操作
     * @param node 被删除的节点或者被删除节点的取代节点
     */
    @Override
    protected void afterRemove(Node<E> node) {
        // 如果删除的红色直接返回不用处理或者用以取代node的子节点为红色
        if (isRed(node)) {
            black(node);
            return;
        }

        Node<E> parent = node.parent;
        // 删除的为根节点
        if (parent == null) return;

        // 删除的为黑色叶子节点
        // 判断被删除的node为左还是右
        boolean left = parent.left == null || parent.isLeftChild();
        Node<E> sibling = left ? parent.right : parent.left;
        if (left) { // 被删除的节点在左边，兄弟节点在右边
            if (isRed(sibling)) { // 兄弟节点为红色，处理之后在处理兄弟节点是黑色的情况
                black(sibling);
                red(parent);
                rotateLeft(parent);
                // 更换兄弟
                sibling = parent.right;
            }
            // 兄弟节点必然是黑色
            if (isBlack(sibling.left) && isBlack(sibling.right)) { // 兄弟节点没有一个红色子节点，父节点向下合并【下溢】
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if (parentBlack) {
                    afterRemove(parent);
                }
            } else { // 兄弟节点至少有一个红色子节点，借元素
                // 兄弟节点先左旋转
                if (isBlack(sibling.right)) {
                    rotateRight(sibling);
                    sibling = parent.right;
                }
                // 先染色再旋转
                color(sibling, colorOf(parent));
                black(sibling.right);
                black(parent);
                // 父节点旋转
                rotateLeft(parent);
            }
        } else {// 被删除的节点在右边，兄弟节点在左边
            if (isRed(sibling)) { // 兄弟节点为红色，处理之后在处理兄弟节点是黑色的情况
                black(sibling);
                red(parent);
                rotateRight(parent);
                // 更换兄弟
                sibling = parent.left;
            }
            // 兄弟节点必然是黑色
            if (isBlack(sibling.left) && isBlack(sibling.right)) { // 兄弟节点没有一个红色子节点，父节点向下合并
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if (parentBlack) {
                    afterRemove(parent);
                }
            } else { // 兄弟节点至少有一个红色子节点，借元素
                // 兄弟节点先左旋转
                if (isBlack(sibling.left)) {
                    rotateLeft(sibling);
                    sibling = parent.left;
                }
                // 先染色再旋转
                color(sibling, colorOf(parent));
                black(sibling.left);
                black(parent);
                // 父节点旋转
                rotateRight(parent);
            }
        }
    }

    private Node<E> color(Node<E> node, boolean color) {
        if (node == null) return null;
        ((RBNode<E>) node).color = color;
        return node;
    }

    private Node<E> red(Node<E> node) {
        return color(node, RED);
    }

    private Node<E> black(Node<E> node) {
        return color(node, BLACK);
    }

    private boolean colorOf(Node<E> node) {
        return node == null ? BLACK : ((RBNode<E>) node).color;
    }

    private boolean isBlack(Node<E> node) {
        return colorOf(node) == BLACK;
    }

    private boolean isRed(Node<E> node) {
        return colorOf(node) == RED;
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new RBNode<>(element, parent);
    }

    private static class RBNode<E> extends Node<E> {
        boolean color = RED;

        public RBNode(E element, Node<E> parent) {
            super(element, parent);
        }

        @Override
        public String toString() {
            String str = "";
            if (color == RED) {
                str = "R_";
            }
            return str + element.toString();
        }
    }
}
