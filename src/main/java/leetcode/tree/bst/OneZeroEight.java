package leetcode.tree.bst;

import leetcode.auxclass.TreeNode;

/**
 * 将有序数组转换为二叉搜索树
 *
 * @author zengxi.song
 * @date 2024/7/4
 */
public class OneZeroEight {

    public TreeNode sortedArrayToBST(int[] nums) {
        // 时间复杂度O(n) 空间复杂度O(logN)
        return searchMidNode(nums, 0, nums.length - 1);
    }

    private TreeNode searchMidNode(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        // 每次都选取中间的数字(左右没关系,这里使用左边的值) 作为根节点
        int mid = start + (end - start) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = searchMidNode(nums, start, mid - 1);
        root.right = searchMidNode(nums, mid + 1, end);
        return root;
    }
}
