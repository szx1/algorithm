package leetcode.tree;

import leetcode.auxclass.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 路径总和Ⅲ
 *
 * @author zengxi.song
 * @date 2024/7/29
 */
public class FourThreeSeven {

    public int pathSum(TreeNode root, int targetSum) {
        // 借助前缀和 可参考560题 时间复杂度O(N) 空间复杂度O(N)
        // 为了不影响其他路线 有两个方式 1.每条路线定义一个countMap 2.借助回溯思想恢复countMap
        dfs(root, 0, targetSum, new HashMap<>());
        return count;
    }

    private void dfs(TreeNode node, long sum, int targetSum, Map<Long, Integer> countMap) {
        if (node == null) {
            return;
        }
        // 1.每次复制一个新的map 会大量创建新map 不建议采用
//        countMap = new HashMap<>(countMap);
        sum += node.val;
        if (sum == targetSum) {
            count++;
        }
        count += countMap.getOrDefault(sum - targetSum, 0);
        countMap.merge(sum, 1, Integer::sum);
        dfs(node.left, sum, targetSum, countMap);
        dfs(node.right, sum, targetSum, countMap);
        // 2.恢复现场 优于创建新map
        countMap.merge(sum, -1, Integer::sum);
    }

    public int pathSum2(TreeNode root, int targetSum) {
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
