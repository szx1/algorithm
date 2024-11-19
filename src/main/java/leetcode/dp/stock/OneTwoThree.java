package leetcode.dp.stock;

/**
 * 买卖股票的最佳时机Ⅲ
 *
 * @author zengxi.song
 * @date 2024/9/10
 */
public class OneTwoThree {

    public int maxProfit(int[] prices) {
        // 动态规划 时间复杂度O(N) 空间复杂度O(N)
        // 设dp[i][0]为第i天第一次处于持有股票状态的最大利润
        // dp[i][1]为第i天第一次卖出后处于不持有股票的最大利润
        // dp[i][2]为第i天第二次处于持有股票的最大利润
        // dp[i][3]为第i天第二次卖出后处于不持有股票的最大利润
        int n = prices.length;
        int[][] dp = new int[n][4];
        dp[0][0] = -prices[0];
        dp[0][2] = -prices[0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], -prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] - prices[i]);
            dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] + prices[i]);
        }
        return Math.max(dp[n - 1][1], dp[n - 1][3]);
    }

    public static void main(String[] args) {
        new OneTwoThree().maxProfit(new int[]{7,6,4,3,1});
    }
}
