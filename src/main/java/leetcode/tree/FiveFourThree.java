package leetcode.tree;

import leetcode.auxclass.TreeNode;

/**
 * 二叉树的直径
 *
 * @author zengxi.song
 * @date 2024/8/2
 */
public class FiveFourThree {

    private int res = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return res;
    }

    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = dfs(node.left);
        int right = dfs(node.right);
        res = Math.max(res, left + right);
        // 直径用边数来表示 这里的加一很巧妙 可以不用特殊处理左右为null的情况
        // 否则在判断res的时候就要分情况判断left+right加0还是加一还是加二
        return Math.max(left, right) + 1;
    }
}
