package leetcode.bs;

import java.util.Arrays;

/**
 * 最长递增子序列
 *
 * @author zengxi.song
 * @date 2024/9/10
 */
public class ThreeZeroZero {

    public int lengthOfLIS(int[] nums) {
        // 进阶 二分查找 时间复杂度O(NlogN) 空间复杂度O(N)
        return 0;
    }

    public int lengthOfLIS1(int[] nums) {
        // 动态规划 时间复杂度O(N^2) 空间复杂度O(N)
        // 设dp[i]为以i位置数字为结尾的自增子序列最大长度
        int[] dp = new int[nums.length];
        // 所有的单个数字都是1
        Arrays.fill(dp, 1);
        int res = 0;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
