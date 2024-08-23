package leetcode.dp.knapsack.complete;

/**
 * 组合总和Ⅳ
 *
 * @author zengxi.song
 * @date 2024/8/21
 */
public class ThreeSevenSeven {

    public int combinationSum4(int[] nums, int target) {
        // 完全背包排列问题 虽然题目为组合 但是实际求得是排列
        // 动态规划一维数组
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int j = 0; j <= target; j++) {
            for (int num : nums) {
                if (j >= num) {
                    dp[j] += dp[j - num];
                }
            }
        }
        return dp[target];
    }

    public int combinationSum41(int[] nums, int target) {
        // 完全背包排列问题 虽然题目为组合 但是实际求得是排列
        // 定义dp[i][j]为以第i个元素为最后一个元素的和为j的组合数
        int[][] dp = new int[nums.length + 1][target + 1];
        // 题目中说明nums[i]大于0
        for (int i = 0; i <= nums.length; i++) {
            dp[i][0] = 1;
        }

        for (int j = 0; j <= target; j++) {
            for (int i = 1; i <= nums.length; i++) {
                if (j >= nums[i - 1]) {
                    dp[i][j] = dp[i - 1][j] + dp[nums.length][j - nums[i - 1]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[nums.length][target];
    }
}
