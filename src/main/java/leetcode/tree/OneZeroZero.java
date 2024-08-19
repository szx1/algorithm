package leetcode.tree;

import leetcode.auxclass.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 相同的树
 *
 * @author zengxi.song
 * @date 2024/8/1
 */
public class OneZeroZero {

    public boolean isSameTree(TreeNode p, TreeNode q) {
        // 迭代 时间复杂度O(N) 空间复杂度O(N)
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();
        queue1.add(p);
        queue2.add(q);
        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            TreeNode node1 = queue1.poll();
            TreeNode node2 = queue2.poll();
            if (node1 == node2) {
                continue;
            }
            if (node1 == null || node2 == null) {
                return false;
            }
            if (node1.val != node2.val) {
                return false;
            }
            queue1.add(node1.left);
            queue1.add(node1.right);
            queue2.add(node2.left);
            queue2.add(node2.right);
        }
        return queue1.isEmpty() && queue2.isEmpty();
    }

    public boolean isSameTree1(TreeNode p, TreeNode q) {
        // 深度优先搜索 时间复杂度O(N) 空间复杂度O(N)
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
