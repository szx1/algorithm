package leetcode.dp;

import java.util.Arrays;

/**
 * 最长连续递增序列
 *
 * @author zengxi.song
 * @date 2024/10/17
 */
public class SixSevenFour {

    public int findLengthOfLCIS(int[] nums) {
        // 时间复杂度O(N) 空间复杂度O(1)
        int length = 1;
        int left = 0, right = 1;
        while (right < nums.length) {
            if (nums[right] <= nums[right - 1]) {
                length = Math.max(right - left, length);
                left = right;
            } else if (right == nums.length - 1) {
                length = Math.max(right - left + 1, length);
            }
            right++;
        }
        return length;
    }

    public int findLengthOfLCIS1(int[] nums) {
        // 动态规划思想 时间复杂度O(N) 空间复杂度O(N)
        int length = 1;
        int[] dp = new int[nums.length];
        Arrays.fill(nums, 1);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                dp[i] = dp[i - 1] + 1;
            }
            length = Math.max(length, dp[i]);
        }
        return length;
    }
}
