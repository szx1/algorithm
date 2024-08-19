package leetcode.dp;

/**
 * 使用最小花费爬楼梯
 *
 * @author zengxi.song
 * @date 2024/8/17
 */
public class SevenFourSix {

    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        if (n < 2) {
            return 0;
        }
        int[] dp = new int[n + 1];
        dp[1] = 0;
        dp[2] = Math.min(cost[0], cost[1]);

        for (int i = 3; i <= n; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[n];
    }
}
