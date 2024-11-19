package leetcode.array;

import java.util.Arrays;

/**
 * 最短无序连续子数组
 *
 * @author zengxi.song
 * @date 2024/9/12
 */
public class FiveEightOne {

    public int findUnsortedSubarray(int[] nums) {
        // 一次遍历双指针 时间复杂度O(NlogN) 空间复杂度O(1)
        int right = -1, left = -1;
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        // 从左向右记录当前max 找到最后一个小于max的即为right
        // 从右向左记录当前max 找到最后一个大于min的即为left
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            // 只有小于才更新right
            if (nums[i] < max) {
                right = i;
            } else {
                max = nums[i];
            }
            // 大于更新left
            if (nums[n - i - 1] > min) {
                left = n - i - 1;
            } else {
                min = nums[n - i - 1];
            }
        }
        return right == -1 ? 0 : right - left + 1;
    }

    public int findUnsortedSubarray2(int[] nums) {
        // 排序+双指针 时间复杂度O(NlogN) 空间复杂度O(N)
        // 优化 提前检查是否是排序数组
        if (isSorted(nums)) {
            return 0;
        }
        int[] aux = new int[nums.length];
        System.arraycopy(nums, 0, aux, 0, nums.length);
        Arrays.sort(aux);
        // 排序后使用双指针查找不同的数字即可
        int left = 0, right = nums.length - 1;
        while (nums[left] == aux[left]) {
            left++;
        }
        while (nums[right] == aux[right]) {
            right--;
        }
        return right - left + 1;
    }

    private boolean isSorted(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public int findUnsortedSubarray1(int[] nums) {
        // 排序+双指针 时间复杂度O(NlogN) 空间复杂度O(N)
        int[] aux = new int[nums.length];
        System.arraycopy(nums, 0, aux, 0, nums.length);
        Arrays.sort(aux);
        // 排序后使用双指针查找不同的数字即可
        int left = 0, right = nums.length - 1;
        while (nums[left] == aux[left]) {
            left++;
            // 说明是排序数组
            if (left >= nums.length) {
                return 0;
            }
        }
        while (nums[right] == aux[right]) {
            right--;
        }
        return right - left + 1;
    }

    public static void main(String[] args) {
        new FiveEightOne().findUnsortedSubarray1(new int[]{2, 6, 4, 8, 10, 9, 15});
    }
}
