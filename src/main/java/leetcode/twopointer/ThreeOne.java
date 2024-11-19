package leetcode.twopointer;

import util.ArrayUtil;

import static util.ArrayUtil.swap;

/**
 * 下一个排列
 *
 * @author zengxi.song
 * @date 2024/8/30
 */
public class ThreeOne {

    public void nextPermutation(int[] nums) {
        // 两次扫描 时间复杂度O(N) 空间复杂度O(1)
        // 首先从右向左找到以一个i<i+1的位置
        int i = nums.length - 2;
        while (i >= 0) {
            if (nums[i] < nums[i + 1]) {
                break;
            }
            i--;
        }
        // 没找到说明是降序序列
        // 找到的话则从右到左找到第一个大于nums[i]的数
        if (i >= 0) {
            int j = nums.length - 1;
            while (j > i) {
                if (nums[j] > nums[i]) {
                    break;
                }
                j--;
            }
            if (j > i) {
                ArrayUtil.swap(nums, i, j);
            }
        }
        // 然后将i+1后面的数字排序
        // 这里可以取巧 因为经过上述操作 我们可以认为i后面的数字是降序的
        reverse(nums, i + 1, nums.length - 1);
    }

    private void reverse(int[] nums, int i, int j) {
        while (i < j) {
            ArrayUtil.swap(nums, i++, j--);
        }
    }
}
