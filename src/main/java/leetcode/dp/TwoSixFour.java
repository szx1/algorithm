package leetcode.dp;

/**
 * 丑数Ⅱ
 *
 * @author zengxi.song
 * @date 2024/8/9
 */
public class TwoSixFour {

    public int nthUglyNumber(int n) {
        // 动态规划 时间复杂度O(N) 空间复杂度O(N)
        int[] dp = new int[n];
        dp[0] = 1;
        int p1 = 0, p2 = 0, p3 = 0;
        for (int i = 1; i < n; i++) {
            int num1 = 2 * dp[p1];
            int num2 = 3 * dp[p2];
            int num3 = 5 * dp[p3];
            int num = Math.min(Math.min(num1, num2), num3);
            if (num == num1) {
                p1++;
            }
            if (num == num2) {
                p2++;
            }
            if (num == num3) {
                p3++;
            }
            dp[i] = num;
        }
        return dp[n - 1];
    }
}
