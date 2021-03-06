package com.mj;

import com.mj.printer.BinaryTreeInfo;

import java.util.*;

public class BinarySearchTree<E> implements BinaryTreeInfo {
    private int size;
    private Node<E> root;
    private Comparator<E> comparator;

    public BinarySearchTree() {
        this(null);
    }

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    private static class Node<E> {
        E element;
        Node<E> left;
        Node<E> right;
        Node<E> parent;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }

        // 判断是否为叶子结点
        public boolean isLeaf() {
            return left == null && right == null;
        }

        public boolean hasTwoChildren() {
            return left != null && right != null;
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    public void add(E element) {
        elementNotNullCheck(element);

        // 添加第一个节点
        if (root == null) {
            root = new Node<>(element, null);
            size++;
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
    }

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
     * 前序遍历
     */
    public void preorderTraversal() {
        preorderTraversal(root);
        System.out.println();

    }

    private void preorderTraversal(Node<E> node) {
        if (node == null) {
            return;
        }
        System.out.print(node.element + " ");
        preorderTraversal(node.left);
        preorderTraversal(node.right);
    }

    /**
     * 中序遍历
     */
    public void inorderTraversal() {
        inorderTraversal(root);
        System.out.println();
    }

    private void inorderTraversal(Node<E> node) {
        if (node == null) {
            return;
        }
        inorderTraversal(node.left);
        System.out.print(node.element + " ");
        inorderTraversal(node.right);
    }

    /**
     * 后序遍历
     */
    public void postorderTraversal() {
        postorderTraversal(root);
        System.out.println();
    }

    private void postorderTraversal(Node<E> node) {
        if (node == null) {
            return;
        }
        postorderTraversal(node.left);
        postorderTraversal(node.right);
        System.out.print(node.element + " ");
    }

    /**
     * 层序遍历
     */
    public void levelOrderTraversal() {
        if (root == null) {
            return;
        }
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            System.out.print(node.element + " ");
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        System.out.println();
    }

    /**
     * 遍历自定义操作
     */
    public static interface Visitor<E> {
        void visit(E element);
    }

    // 前序遍历
    public void preorder(Visitor<E> visitor) {
        preorder(root, visitor);
        System.out.println();

    }

    private void preorder(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor == null) {
            return;
        }
        visitor.visit(node.element);
        preorder(node.left, visitor);
        preorder(node.right, visitor);
    }

    // 中序遍历
    public void inorder(Visitor<E> visitor) {
        inorder(root, visitor);
        System.out.println();

    }

    private void inorder(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor == null) {
            return;
        }
        inorder(node.left, visitor);
        visitor.visit(node.element);
        inorder(node.right, visitor);
    }

    // 后序遍历
    public void postorder(Visitor<E> visitor) {
        postorder(root, visitor);
        System.out.println();

    }

    private void postorder(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor == null) {
            return;
        }
        postorder(node.left, visitor);
        postorder(node.right, visitor);
        visitor.visit(node.element);
    }

    // 层序遍历
    public void levelOrder(Visitor<E> visitor) {
        if (root == null || visitor == null) {
            return;
        }
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            visitor.visit(node.element);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        System.out.println();
    }

    public int height1() {
        return height1(root);
    }

    // 递归
    private int height1(Node<E> node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(height1(node.left), height1(node.right));
    }

    // 迭代，使用层序遍历
    public int height() {
        if (root == null) {
            return 0;
        }
        // 树的高度
        int height = 0;
        // 存储每一层的元素数量
        int levelSize = 1;

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            // 减为0表示此层结束
            levelSize--;
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
            if (levelSize == 0) { // 意味着即将访问下一层
                levelSize = queue.size();
                height++;
            }
        }
        return height;
    }

    // 判断是否为完全二叉树
    public boolean isComplete() {
        if (root == null) {
            return false;
        }
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        boolean leaf = false;
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            if (leaf && !node.isLeaf()) {
                return false;
            }

            if (node.hasTwoChildren()) {
                queue.offer(node.left);
                queue.offer(node.right);
            } else if (node.left == null && node.right != null) {
                return false;
            } else {
                leaf = true;
            }
        }
        return true;
    }

    /**
     * 寻找前驱节点
     *
     * @param node
     * @return
     */
    private Node<E> predecessor(Node<E> node) {
        if (node == null) {
            return null;
        }
        // 前驱节点存在在左子树中
        Node<E> p = node.left;
        if (p != null) {
            while (p.right != null) {
                p = p.right;
            }
            return p;
        }
        // 前驱节点存在在父节点及祖父节点中
        while (node.parent != null && node == node.parent.left) {
            node = node.parent;
        }
        return node.parent;
    }

    private Node<E> successor(Node<E> node) {
        if (node == null) {
            return null;
        }
        Node<E> s = node.right;
        if (s != null) {
            while (s.left != null) {
                s = s.left;
            }
            return s;
        }
        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }
        return node.parent;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toString(root, sb, "");
        return sb.toString();
    }

    private void toString(Node<E> node, StringBuilder sb, String prefix) {
        if (node == null) {
            return;
        }
        sb.append(prefix).append(node.element).append("\n");
        toString(node.left, sb, prefix + "[L]");
        toString(node.right, sb, prefix + "[R]");
    }

    // 打印二叉搜索树需要用到的方法
    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>) node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>) node).right;
    }

    @Override
    public Object string(Object node) {
        Node<E> myNode = (Node<E>) node;
        String parentString = "null";
        if (myNode.parent != null) {
            parentString = myNode.parent.element.toString();
        }

        return myNode.element + "_p(" + parentString + ")";
    }
}
