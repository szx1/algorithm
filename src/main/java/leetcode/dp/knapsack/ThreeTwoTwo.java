package leetcode.dp.knapsack;

/**
 * 零钱兑换
 *
 * @author zengxi.song
 * @date 2024/8/17
 */
public class ThreeTwoTwo {

    public int coinChange(int[] coins, int amount) {
        // 动态规划优化
        // 我们可以看到dp数组的变化只和i-1和i有关 所以可以使用一维数组优化
        int[] dp = new int[amount + 1];
        for (int i = 0; i <= amount; i++) {
            if (i % coins[0] == 0) {
                dp[i] = i / coins[0];
            } else {
                dp[i] = amount + 1;
            }
        }
        for (int i = 1; i < coins.length; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public int coinChange1(int[] coins, int amount) {
        // 动态规划
        // 定义dp[i][j]为前i个硬币能凑够j容量的最小数目
        int[][] dp = new int[coins.length][amount + 1];
        for (int i = 0; i <= amount; i++) {
            if (i % coins[0] == 0) {
                dp[0][i] = i / coins[0];
            } else {
                dp[0][i] = amount + 1;
            }
        }
        for (int i = 1; i < coins.length; i++) {
            for (int j = 0; j <= amount; j++) {
                if (j >= coins[i]) {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - coins[i]] + 1);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[coins.length - 1][amount] > amount ? -1 : dp[coins.length - 1][amount];
    }
}
