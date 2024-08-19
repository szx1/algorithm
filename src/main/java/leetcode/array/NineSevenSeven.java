package leetcode.array;

import java.util.Arrays;

/**
 * 有序数组的平方
 *
 * @author zengxi.song
 * @date 2024/7/4
 */
public class NineSevenSeven {

    public int[] sortedSquares(int[] nums) {
        // 双指针 时间复杂度O(n) 空间复杂度O(1)
        int[] res = new int[nums.length];
        int left = 0, right = nums.length - 1;
        int index = right;
        // 每次只需要比较左右两个数的大小即可
        while (left <= right) {
            if (Math.abs(nums[left]) > Math.abs(nums[right])) {
                res[index--] = nums[left] * nums[left];
                left++;
            } else {
                res[index--] = nums[right] * nums[right];
                right--;
            }
        }
        return res;
    }

    public int[] sortedSquares1(int[] nums) {
        // 平方然后排序 时间复杂度O(NlogN) 空间复杂度O(logN) 排序用到的栈空间
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            res[i++] = nums[i] * nums[i];
        }
        Arrays.sort(res);
        return res;
    }
}
