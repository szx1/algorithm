package leetcode.tree;

import leetcode.auxclass.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 找树左下角的值
 *
 * @author zengxi.song
 * @date 2024/6/13
 */
public class FiveOneThree {

    private int res;
    private int height;

    public int findBottomLeftValue(TreeNode root) {
        // dfs
        dfs(root, 1);
        return res;
    }

    private void dfs(TreeNode node, int curHeight) {
        if (node.left == null && node.right == null && curHeight > height) {
            height = curHeight;
            res = node.val;
            return;
        }
        curHeight++;
        if (node.left != null) {
            dfs(node.left, curHeight);
        }
        if (node.right != null) {
            dfs(node.right, curHeight);
        }
    }

    public int findBottomLeftValue2(TreeNode root) {
        // 层序遍历
        // 题意 root不可能为null
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int res = root.val;
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            // 先添加右再添加左 可以保证队列的最后一个就是最底层最左边的
            if (poll.right != null) {
                queue.add(poll.right);
            }
            if (poll.left != null) {
                queue.add(poll.left);
            }

            res = poll.val;

        }
        return res;
    }

    public int findBottomLeftValue1(TreeNode root) {
        // 层序遍历
        // 题意 root不可能为null
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int res = root.val;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = size; i > 0; i--) {
                TreeNode poll = queue.poll();
                // FIFO 第一个即最左边
                if (i == size) {
                    res = poll.val;
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
}
