package leetcode.tree;

import leetcode.auxclass.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 路径总和
 *
 * @author zengxi.song
 * @date 2024/6/13
 */
public class OneOneTwo {

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        // 层序遍历
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> valueQueue = new LinkedList<>();
        nodeQueue.add(root);
        valueQueue.add(root.val);
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            Integer value = valueQueue.poll();
            // 叶子结点
            if (node.left == null && node.right == null) {
                if (value == targetSum) {
                    return true;
                }
                continue;
            }
            if (node.left != null) {
                nodeQueue.add(node.left);
                valueQueue.add(value + node.left.val);
            }
            if (node.right != null) {
                nodeQueue.add(node.right);
                valueQueue.add(value + node.right.val);
            }
        }
        return false;
    }

    public boolean hasPathSum2(TreeNode root, int targetSum) {
        // dfs
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return root.val == targetSum;
        }

        return hasPathSum2(root.left, targetSum - root.val) || hasPathSum2(root.right, targetSum - root.val);
    }

    public boolean hasPathSum1(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        return dfs(root, 0, targetSum);
    }

    private boolean dfs(TreeNode node, int sum, int targetNum) {
        if (node == null) {
            return false;
        }
        sum += node.val;
        if (sum == targetNum && node.left == null && node.right == null) {
            return true;
        }

        return dfs(node.left, sum, targetNum) || dfs(node.right, sum, targetNum);
    }
}
