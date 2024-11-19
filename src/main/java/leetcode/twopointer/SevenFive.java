package leetcode.twopointer;

import util.ArrayUtil;

/**
 * 颜色分类
 *
 * @author zengxi.song
 * @date 2024/9/3
 */
public class SevenFive {

    public void sortColors(int[] nums) {
        // 一次遍历 时间复杂度O(N) 空间复杂度O(1)
        int left = 0, right = nums.length - 1;
        for (int i = 0; i < nums.length; i++) {
            while (i < right && nums[i] == 2) {
                ArrayUtil.swap(nums, i, right);
                right--;
            }
            // 通过上面的代码可以保证此时nums[left]不可能为2
            if (nums[i] == 0) {
                ArrayUtil.swap(nums, i, left);
                left++;
            }
        }
    }

    public void sortColors1(int[] nums) {
        // 二次遍历 第一次调整2，第二次调整1 时间复杂度O(N) 空间复杂度O(1)
        int left = 0, right = nums.length - 1;
        while (left < right) {
            while (nums[right] == 2 && left < right) {
                right--;
            }
            while (nums[left] != 2 && left < right) {
                left++;
            }
            if (left < right) {
                ArrayUtil.swap(nums, left, right);
            }
        }
        left = 0;
        while (left < right) {
            while (nums[right] == 1 && left < right) {
                right--;
            }
            while (nums[left] != 1 && left < right) {
                left++;
            }
            if (left < right) {
                ArrayUtil.swap(nums, left, right);
            }
        }
    }
}
