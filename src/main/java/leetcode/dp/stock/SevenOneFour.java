package leetcode.dp.stock;

/**
 * 买卖股票的最佳时机含手续费
 *
 * @author zengxi.song
 * @date 2024/9/10
 */
public class SevenOneFour {

    public int maxProfit(int[] prices, int fee) {
        // 动态规划 时间复杂度O(N) 空间复杂度O(N)
        // 本题想对122来说 多了个手续费 我们只在卖出的时候计算手续费
        // 设dp[i][0]为第i天持有股票获取最大利润
        // dp[i][1]为第i天不持有股票获取最大利润
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = -prices[0];
        // 注意dp[0][1]不能初始化为-fee
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i] - fee);
        }
        return dp[n - 1][1];
    }

    public static void main(String[] args) {
        new SevenOneFour().maxProfit(new int[]{9, 8, 7, 1, 2}, 3);
    }
}
