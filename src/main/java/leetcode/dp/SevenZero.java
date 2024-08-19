package leetcode.dp;

/**
 * 爬楼梯
 * 斐波那契数的变形
 *
 * @author zengxi.song
 * @date 2024/8/17
 */
public class SevenZero {

    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
