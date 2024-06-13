package leetcode.tree;

import leetcode.auxclass.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 完全二叉树的节点个数
 *
 * @author zengxi.song
 * @date 2024/6/12
 */
public class TwoTwoTwo {

    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 可利用完全二叉树的性质
        // 左右深度相同即为满二叉树
        int leftDepth = 1, rightDepth = 1;
        TreeNode left = root.left;
        TreeNode right = root.right;
        while (left != null) {
            left = left.left;
            leftDepth++;
        }
        while (right != null) {
            right = right.right;
            rightDepth++;
        }
        // 满二叉树直接套公式 1左移几位即为2的几次方
        if (leftDepth == rightDepth) {
            return (1 << leftDepth) - 1;
        }
        // 否则左子树节点个数加上右子树节点个数
        return countNodes(root.left) + countNodes(root.right) + 1;
    }

    public int countNodes1(TreeNode root) {
        // 各种遍历方法的时间复杂度都是O(n) 虽然简单 但是达不到题目要求
        // 层序遍历
        if (root == null) {
            return 0;
        }
        int count = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            count++;
            if (poll.left != null) {
                queue.add(poll.left);
            }
            if (poll.right != null) {
                queue.add(poll.right);
            }
        }
        return count;
    }
}
