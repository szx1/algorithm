package leetcode.dp.stock;

/**
 * 买卖股票的最佳时机Ⅳ
 *
 * @author zengxi.song
 * @date 2024/9/10
 */
public class OneEightEight {

    public int maxProfit(int k, int[] prices) {
        // 动态规划 时间复杂度O(2kN) 空间复杂度O(N)   ps:同样可以奇数代表持有 偶数不持有 二维数组 但是第二维长度2k
        // 该题允许交易k次 我们可以仿照123题的思路来做
        // 设dp[i][j][0]为第i天第j次处于持有股票状态的最大利润
        // dp[i][j][1]为第i天第j次卖出后处于不持有股票的最大利润
        int n = prices.length;
        int[][][] dp = new int[n][k][2];
        dp[0][0][0] = -prices[0];
        for (int j = 1; j < k; j++) {
            dp[0][j][0] = -prices[0];
        }
        for (int i = 1; i < n; i++) {
            dp[i][0][0] = Math.max(dp[i - 1][0][0], -prices[i]);
            dp[i][0][1] = Math.max(dp[i - 1][0][1], dp[i - 1][0][0] + prices[i]);
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < k; j++) {
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j - 1][1] - prices[i]);
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j][0] + prices[i]);
            }
        }
        return dp[n - 1][k - 1][1];
    }

}
