package leetcode;

import leetcode.auxclass.TreeNode;

import java.util.Stack;

/**
 * 从前序与中序遍历序列构造二叉树
 *
 * @author zengxi.song
 * @date 2024/4/16
 */
public class OneZeroFive {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 迭代
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        int inorderIndex = 0;
        for (int i = 1; i < preorder.length; i++) {
            int preorderVal = preorder[i];
            TreeNode node = stack.peek();
            if (node.val != inorder[inorderIndex]) {
                node.left = new TreeNode(preorderVal);
                stack.push(node.left);
            } else {
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                    node = stack.pop();
                    inorderIndex++;
                }
                node.right = new TreeNode(preorderVal);
                stack.push(node.right);
            }
        }
        return root;
    }

    public static void main(String[] args) {
        OneZeroFive oneZeroFive = new OneZeroFive();
        oneZeroFive.buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});
    }
}
