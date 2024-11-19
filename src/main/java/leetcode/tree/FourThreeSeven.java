package leetcode.tree;

import leetcode.auxclass.TreeNode;

/**
 * 路径总和Ⅲ
 *
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

    private int count = 0;

    public int pathSum1(TreeNode root, int targetSum) {
        // 暴力dfs 穷举所有的节点进行dfs 时间复杂度O(N^2) 空间复杂度O(N)
        if (root == null) {
            return 0;
        }
        dfs(root, targetSum, 0);
        pathSum1(root.left, targetSum);
        pathSum1(root.right, targetSum);
        return count;
    }

    private void dfs(TreeNode root, long targetSum, long sum) {
        if (root == null) {
            return;
        }
        sum += root.val;
        if (sum == targetSum) {
            count++;
        }

        dfs(root.left, targetSum, sum);
        dfs(root.right, targetSum, sum);
    }

}
