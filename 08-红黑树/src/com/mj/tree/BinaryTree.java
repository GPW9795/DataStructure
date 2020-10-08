package com.mj.tree;

import com.mj.printer.BinaryTreeInfo;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree<E> implements BinaryTreeInfo {
    protected int size;
    protected Node<E> root;


    protected static class Node<E> {
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

        public boolean isLeftChild() {
            return parent != null && this == parent.left;
        }

        public boolean isRightChild() {
            return parent != null && this == parent.right;
        }

        public Node<E> sibling() {
            if (isLeftChild()) {
                return parent.right;
            }
            if (isRightChild()) {
                return parent.left;
            }
            return null;
        }
    }

    protected Node<E> createNode(E element, Node<E> parent) {
        return new Node<>(element, parent);
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

    /**
     * 寻找前驱节点
     *
     * @param node
     * @return
     */
    protected Node<E> predecessor(Node<E> node) {
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

    protected Node<E> successor(Node<E> node) {
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
            if (leaf && !node.isLeaf()) return false;

            if (node.left != null) {
                queue.offer(node.left);
            } else if (node.right != null) { // 左节点为空且右节点不为空
                return false;
            }
            
            if (node.right != null) {
                queue.offer(node.right);
            } else { // 右节点为空，左节点是否为空不一定
                leaf = true;
            }

        }
        return true;
    }

    // 递归
    public int height1() {
        return height1(root);
    }

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
        return node;
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
}
