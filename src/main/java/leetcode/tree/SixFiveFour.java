package leetcode.tree;

import leetcode.auxclass.TreeNode;

/**
 * 最大二叉树
 *
 * @author zengxi.song
 * @date 2024/6/14
 */
public class SixFiveFour {

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        // 递归 每次循环找最大值 找最大值的时间复杂度为O(n) 总体时间复杂度为O(n^2)
        return buildTree(nums, 0, nums.length - 1);

    }

    private TreeNode buildTree(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int maxIndex = left;
        for (int i = left; i <= right; i++) {
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }

        TreeNode root = new TreeNode(nums[maxIndex]);
        root.left = buildTree(nums, left, maxIndex - 1);
        root.right = buildTree(nums, maxIndex + 1, right);
        return root;
    }
}
