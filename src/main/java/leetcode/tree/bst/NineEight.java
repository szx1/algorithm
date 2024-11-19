package leetcode.tree.bst;

import leetcode.auxclass.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 验证二叉搜索树
 *
 * @author zengxi.song
 * @date 2024/7/30
 */
public class NineEight {

    public boolean isValidBST(TreeNode root) {
        // 利用二叉搜索树的性质 中序遍历必定是递增序列 时间复杂度O(N) 空间复杂度O(N)
        Deque<TreeNode> stack = new ArrayDeque<>();
        boolean first = true;
        int pre = 0;
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            TreeNode pop = stack.pop();
            if (first) {
                first = false;
            } else if (pop.val <= pre) {
                return false;
            }
            pre = pop.val;
            root = pop.right;
        }
        return true;
    }

    public boolean isValidBST1(TreeNode root) {
        // dfs 时间复杂度O(N) 空间复杂度O(N)
        if (root == null) {
            return true;
        }
        return isValid(root.left, root.val, Long.MIN_VALUE) && isValid(root.right, Long.MAX_VALUE, root.val);
    }

    private boolean isValid(TreeNode node, long max, long min) {
        if (node == null) {
            return true;
        }
        if (node.val >= max || node.val <= min) {
            return false;
        }
        return isValid(node.left, node.val, min) && isValid(node.right, max, node.val);
    }
}
