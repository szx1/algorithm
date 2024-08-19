package leetcode.tree.traversal;

import leetcode.auxclass.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树的层序遍历
 *
 * @author zengxi.song
 * @date 2024/6/26
 */
public class OneZeroTwo {

    public List<List<Integer>> levelOrder(TreeNode root) {
        // 利用队列进行层序遍历 时间复杂度O(n)  空间复杂度O(n)
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> subList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                subList.add(poll.val);
                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
            }
            res.add(subList);
        }
        return res;
    }
}
