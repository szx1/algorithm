package leetcode.dp.rob;

/**
 * 打家劫舍
 * 打家劫舍有一系列题
 *
 * @author zengxi.song
 * @date 2024/9/5
 */
public class OneNineEight {

    public int rob(int[] nums) {
        // 动态规划 时间复杂度O(N) 空间复杂度O(N)
        // 设dp[i]为前i个房屋可获得的最大金钱
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; i++) {
            // 偷或者不偷当前房屋的最大值
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[n - 1];
    }

    public int rob1(int[] nums) {
        // 动态规划 时间复杂度O(N) 空间复杂度O(N)
        // 设dp[i][0]为不偷下标i的房屋 dp[i][1]为偷下标i的房屋 获得的最大金钱
        int n = nums.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = nums[0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            dp[i][1] = dp[i - 1][0] + nums[i];
        }
        return Math.max(dp[n - 1][0], dp[n - 1][1]);
    }
}
