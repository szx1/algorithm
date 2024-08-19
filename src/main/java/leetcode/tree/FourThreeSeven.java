package leetcode.tree;

import leetcode.auxclass.TreeNode;

/**
 * @author zengxi.song
 * @date 2024/7/29
 */
public class FourThreeSeven {

    public int pathSum(TreeNode root, int targetSum) {
        // 暴力dfs 穷举所有的节点进行dfs 时间复杂度O(N^2) 空间复杂度O(N)
        if (root == null) {
            return 0;
        }
        return dfs(root, targetSum) + pathSum(root.right, targetSum) + pathSum(root.left, targetSum);
    }

    private int dfs(TreeNode node, long targetSum) {
        if (node == null) {
            return 0;
        }
        int res = 0;
        if (node.val == targetSum) {
            res++;
        }
        return res + dfs(node.left, targetSum - node.val) + dfs(node.right, targetSum - node.val);
    }


}
