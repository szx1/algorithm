package leetcode.dp.stock;

/**
 * 买卖股票的最佳时机
 *
 * @author zengxi.song
 * @date 2024/9/4
 */
public class OneTwoOne {

    public int maxProfit(int[] prices) {
        // 动态规划
        // 设dp[i][0]为当天持有股票 dp[i][1]为当天不持有股票的最大利润
        int[][] dp = new int[prices.length][2];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        int res = 0;
        for (int i = 1; i < prices.length; i++) {
            // 因为只能买一次 所以这里只能 前一天购买和当天购买进行比较
            dp[i][0] = Math.max(dp[i - 1][0], -prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
            res = Math.max(res, dp[i][1]);
        }
        return res;
    }

    public int maxProfit1(int[] prices) {
        // 贪心 其实就是找到最低点以及其之后的高点
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int price : prices) {
            if (price < minprice) {
                minprice = price;
            } else if (price - minprice > maxprofit) {
                maxprofit = price - minprice;
            }
        }
        return maxprofit;
    }

    public static void main(String[] args) {
        new OneTwoOne().maxProfit(new int[]{7, 1, 5, 3, 6, 4});
    }
}
