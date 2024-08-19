package leetcode.tree;

import leetcode.auxclass.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 二叉树中的最大路径和
 *
 * @author zengxi.song
 * @date 2024/8/2
 */
public class OneTwoFour {

    private int res = -1001;

    public int maxPathSum(TreeNode root) {
        // 之前的算法使用了多个map存储 其实没有必要 完全可以在递归的时候就做到
        // 时间复杂度O(N) 空间复杂度O(N)
        dfs(root);
        return res;
    }

    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        // 这里和0比较的原因是因为小于0 则不需要参与计算 即计算res的时候只考虑node.val即可
        int left = Math.max(dfs(node.left), 0);
        int right = Math.max(dfs(node.right), 0);
        res = Math.max(res, left + right + node.val);
        return Math.max(left, right) + node.val;
    }

    private int floor = -1001;

    public int maxPathSum1(TreeNode root) {
        // 自底向上 动态规划
        int res = 0;
        if (root == null) {
            return res;
        }
        Map<TreeNode, Integer> dpMap = new HashMap<>();
        // 后序遍历
        dfs(root, dpMap, new HashMap<>(), new HashMap<>());
        return dpMap.getOrDefault(root, 0);
    }

    private void dfs(TreeNode node, Map<TreeNode, Integer> dpMap, Map<TreeNode, Integer> rootLeftMap, Map<TreeNode, Integer> rootRightMap) {
        if (node == null) {
            return;
        }
        dfs(node.left, dpMap, rootLeftMap, rootRightMap);
        dfs(node.right, dpMap, rootLeftMap, rootRightMap);
        if (node.left == null && node.right == null) {
            dpMap.put(node, node.val);
            rootLeftMap.put(node, node.val);
            rootRightMap.put(node, node.val);
        } else {
            Integer leftMax = dpMap.getOrDefault(node.left, floor);
            Integer rightMax = dpMap.getOrDefault(node.right, floor);
            int leftRootMax = Math.max(rootLeftMap.getOrDefault(node.left, floor), rootRightMap.getOrDefault(node.left, floor));
            int rightRootMax = Math.max(rootLeftMap.getOrDefault(node.right, floor), rootRightMap.getOrDefault(node.right, floor));
            int rootLeftMax = Math.max(node.val, leftRootMax + node.val);
            int rootRightMax = Math.max(node.val, rightRootMax + node.val);
            int rootSum = Math.max(node.val + leftRootMax + rightRootMax, Math.max(rootLeftMax, rootRightMax));
            int dpSum = Math.max(rootSum, Math.max(leftMax, rightMax));
            dpMap.put(node, dpSum);
            rootLeftMap.put(node, rootLeftMax);
            rootRightMap.put(node, rootRightMax);
        }
    }

    public static void main(String[] args) {
        new OneTwoFour().maxPathSum(new TreeNode(-1,
                new TreeNode(5, new TreeNode(4, null, new TreeNode(2, new TreeNode(-4), null)), null),
                null));
    }
}
