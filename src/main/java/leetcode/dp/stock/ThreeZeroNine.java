package leetcode.dp.stock;

/**
 * 买卖股票的最佳时机含冷冻期
 *
 * @author zengxi.song
 * @date 2024/9/10
 */
public class ThreeZeroNine {

    public int maxProfit(int[] prices) {
        // 动态规划 空间优化版 使用变量代替数组 时间复杂度O(N) 空间复杂度O(1)
        // 设x为持有股票 y为当天卖出 z为非当天卖出的非持有状态
        int n = prices.length;
        int x = -prices[0], y = 0, z = 0;
        int tmpx = x, tmpy = y;
        for (int i = 1; i < n; i++) {
            x = Math.max(x, z - prices[i]);
            y = tmpx + prices[i];
            z = Math.max(tmpy, z);
            tmpx = x;
            tmpy = y;
        }
        // 两个卖出的状态中取较大值
        return Math.max(y, z);
    }

    public int maxProfit1(int[] prices) {
        // 动态规划 时间复杂度O(N) 空间复杂度O(N)
        // 设dp[i][0]为第i天处于买入股票状态最大利息
        // dp[i][1]为第i天卖出股票的最大利息 即明天会处于冷冻期
        // dp[i][2]为第i天处于卖出状态的最大利息 非今天卖出
        int n = prices.length;
        int[][] dp = new int[n][3];
        dp[0][0] = -prices[0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2] - prices[i]);
            dp[i][1] = dp[i - 1][0] + prices[i];
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1]);
        }
        // 两个卖出的状态中取较大值
        return Math.max(dp[n - 1][1], dp[n - 1][2]);
    }
}
