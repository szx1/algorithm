package leetcode.tree;

import leetcode.auxclass.TreeNode;

/**
 * 平衡二叉树
 *
 * @author zengxi.song
 * @date 2024/6/13
 */
public class OneOneZero {


    public boolean isBalanced(TreeNode root) {
        // 官方自底向上 采用常数-1来记录中间过程
        return height(root) >= 0;
    }

    public int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        } else {
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    private boolean balance = true;

    public boolean isBalanced3(TreeNode root) {
        // 自底向上 时间复杂度O(n) 从叶子节点开始 每个节点只计算一次
        // 使用全局变量记录balance
        if (root == null) {
            return true;
        }
        balance(root);
        return balance;
    }

    private int balance(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = balance(node.left);
        int rightHeight = balance(node.right);

        balance &= Math.abs(leftHeight - rightHeight) <= 1;
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public boolean isBalanced2(TreeNode root) {
        // 自底向上 时间复杂度O(n) 从叶子节点开始 每个节点只计算一次
        if (root == null) {
            return true;
        }
        return check(root).balance;
    }

    private Aux check(TreeNode node) {
        Aux aux = new Aux();
        if (node == null) {
            aux.balance = true;
            return aux;
        }
        Aux leftCheck = check(node.left);
        Aux rightCheck = check(node.right);
        if (!leftCheck.balance || !rightCheck.balance) {
            return aux;
        }
        int leftHeight = leftCheck.height;
        int rightHeight = rightCheck.height;

        aux.balance = Math.abs(leftHeight - rightHeight) <= 1;
        aux.height = Math.max(leftHeight, rightHeight) + 1;
        return aux;
    }

    private static class Aux {
        private boolean balance;
        private int height;
    }

    public boolean isBalanced1(TreeNode root) {
        // 自顶向下 时间复杂度O(n2) 每个节点可能会计算多次高度
        if (root == null) {
            return true;
        }
        return Math.abs(getHeight(root.left) - getHeight(root.right)) <= 1 && isBalanced1(root.left) && isBalanced1(root.right);
    }

    private int getHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    }
}
