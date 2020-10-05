package 二叉树;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/binary-tree-postorder-traversal/
 */
public class _145_二叉树的后序遍历 {
    /**
     * 递归实现
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        postorder(root, list);
        return list;
    }

    public void postorder(TreeNode node, List list) {
        if (node == null) {
            return;
        }
        postorder(node.left, list);
        postorder(node.right, list);
        list.add(node.val);
    }

    /**
     * 迭代实现
     */
    public List<Integer> postorderTraversal1(TreeNode root) {
        LinkedList<Integer> list = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();

        while (root!= null||!stack.isEmpty()){

        }
        return list;
    }
}
