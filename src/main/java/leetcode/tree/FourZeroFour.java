package leetcode.tree;

import leetcode.auxclass.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 左叶子之和
 *
 * @author zengxi.song
 * @date 2024/6/13
 */
public class FourZeroFour {

    public int sumOfLeftLeaves(TreeNode root) {
        // 层序遍历
        if (root == null || isLeaf(root)) {
            return 0;
        }
        int res = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            if (poll.left == null && poll.right == null) {
                res += poll.val;
                continue;
            }
            // 左子树添加 右子树只有在有子树的情况下再添加
            if (poll.left != null) {
                queue.add(poll.left);
            }
            if (poll.right != null && !isLeaf(poll.right)) {
                queue.add(poll.right);
            }
        }
        return res;
    }

    private boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null;
    }

    public int sumOfLeftLeaves2(TreeNode root) {
        // dfs 不使用全局变量
        if (root == null) {
            return 0;
        }
        return dfs1(root, false);
    }

    private int dfs1(TreeNode node, boolean left) {
        int res = 0;
        if (node == null) {
            return 0;
        }
        if (left && node.left == null && node.right == null) {
            res += node.val;
        }
        res += dfs1(node.left, true);
        res += dfs1(node.right, false);
        return res;
    }

    private int res;

    public int sumOfLeftLeaves1(TreeNode root) {
        // dfs 使用全局变量
        if (root == null) {
            return 0;
        }
        dfs(root, false);
        return res;
    }

    private void dfs(TreeNode node, boolean left) {
        if (node == null) {
            return;
        }
        if (left && node.left == null && node.right == null) {
            res += node.val;
            return;
        }
        dfs(node.left, true);
        dfs(node.right, false);
    }
}
