package leetcode.tree;

import leetcode.auxclass.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树的右视图
 *
 * @author zengxi.song
 * @date 2024/8/2
 */
public class OneNineNine {

    public List<Integer> rightSideView(TreeNode root) {
        // dfs 先遍历右侧节点 时间复杂度O(N) 空间复杂度O(N)
        List<Integer> res = new ArrayList<>();
        this.dfs(root, res, 0);
        return res;
    }

    private void dfs(TreeNode node, List<Integer> res, int depth) {
        if (node == null) {
            return;
        }
        if (res.size() <= depth) {
            res.add(node.val);
        }
        // right放在前面 不需要要覆盖
        dfs(node.right, res, depth + 1);
        dfs(node.left, res, depth + 1);
    }

    public List<Integer> rightSideView2(TreeNode root) {
        // dfs 右侧的节点覆盖左侧节点 时间复杂度O(N) 空间复杂度O(N)
        List<Integer> res = new ArrayList<>();
        this.dfs1(root, res, 0);
        return res;
    }

    private void dfs1(TreeNode node, List<Integer> res, int depth) {
        if (node == null) {
            return;
        }
        if (res.size() <= depth) {
            res.add(node.val);
        } else {
            res.set(depth, node.val);
        }
        dfs1(node.left, res, depth + 1);
        // right要放在后面 用于覆盖res
        dfs1(node.right, res, depth + 1);
    }

    public List<Integer> rightSideView1(TreeNode root) {
        // 层序遍历 bfs 只收集最右侧的节点 时间复杂度O(N) 空间复杂度O(N)
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            for (int i = queue.size() - 1; i >= 0; i--) {
                TreeNode poll = queue.poll();
                if (i == 0) {
                    res.add(poll.val);
                }
                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        new OneNineNine().rightSideView(new TreeNode(1,
                new TreeNode(2),
                new TreeNode(3, new TreeNode(4), null)));
    }
}
