package leetcode.dp.stock;

/**
 * 买卖股票的最佳时机Ⅱ
 *
 * @author zengxi.song
 * @date 2024/9/10
 */
public class OneTwoTwo {

    public int maxProfit(int[] prices) {
        // 动态规划 时间复杂度O(N) 空间复杂度O(N)
        // 设dp[i][0]为第i天处于持有股票状态的最大利润
        // dp[i][1]为第i天处于不持有股票的最大利润
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = -prices[0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
        }
        return dp[n - 1][1];
    }

    public int maxProfit1(int[] prices) {
        // 贪心 时间复杂度O(N) 空间复杂度O(1)
        int res = 0;
        int n = prices.length;
        for (int i = 1; i < n; ++i) {
            // 每个分段都取与0比较的较大值 总体就是能获得的总利润
            res += Math.max(0, prices[i] - prices[i - 1]);
        }
        return res;
    }
}
