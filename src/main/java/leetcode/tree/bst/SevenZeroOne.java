package leetcode.tree.bst;

import leetcode.auxclass.TreeNode;

/**
 * 二叉搜索树中的插入操作
 *
 * @author zengxi.song
 * @date 2024/6/27
 */
public class SevenZeroOne {

    public TreeNode insertIntoBST(TreeNode root, int val) {
        // 时间复杂度O(n) 空间复杂度O(1)
        if (root == null) {
            return new TreeNode(val);
        }
        TreeNode point = root;
        while (point != null) {
            if (val > point.val) {
                if (point.right == null) {
                    point.right = new TreeNode(val);
                    return root;
                }
                point = point.right;
            }
            if (val < point.val) {
                if (point.left == null) {
                    point.left = new TreeNode(val);
                    return root;
                }
                point = point.left;
            }
        }

        return root;
    }
}
