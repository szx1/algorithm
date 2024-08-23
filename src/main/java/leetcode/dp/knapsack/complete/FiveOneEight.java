package leetcode.dp.knapsack.complete;

/**
 * 零钱兑换Ⅱ
 *
 * @author zengxi.song
 * @date 2024/8/21
 */
public class FiveOneEight {

    public int change(int amount, int[] coins) {
        // 动态规划
        // 完全背包问题
        // 定义dp[i][j]为前i个硬币能组成j的组合数
        int[][] dp = new int[coins.length + 1][amount + 1];
        // 初始化第一列
        for (int i = 0; i <= coins.length; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= coins.length; i++) {
            for (int j = 1; j <= amount; j++) {
                if (j < coins[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - coins[i - 1]];
                }
            }
        }
        return dp[coins.length][amount];
    }

    public static void main(String[] args) {
        new FiveOneEight().change(5, new int[]{1, 2, 5});
    }
}
