package leetcode.dp.knapsack.complete;

/**
 * 完全平方数
 *
 * @author zengxi.song
 * @date 2024/9/9
 */
public class TwoSevenNine {

    public int numSquares(int n) {
        // 可看做完全背包问题 时间复杂度O(N根号N) 空间复杂度O(N)
        // 将n看做背包容量 将完全平方数看做物品
        int[] dp = new int[n + 1];
        // 初始化为1填满
        for (int i = 0; i <= n; i++) {
            dp[i] = i;
        }
        for (int i = 2; i * i <= n; i++) {
            int target = i * i;
            for (int j = target; j <= n; j++) {
                dp[j] = Math.min(dp[j], dp[j - target] + 1);
            }
        }
        return dp[n];
    }
}
