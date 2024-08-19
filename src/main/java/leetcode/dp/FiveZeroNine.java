package leetcode.dp;

/**
 * 斐波那契数
 * 该题为动态规划的入门题目 较简单
 *
 * @author zengxi.song
 * @date 2024/8/17
 */
public class FiveZeroNine {

    public int fib(int n) {
        if (n <= 1) {
            return n;
        }
        // 动态规划优化 时间复杂度O(N) 空间复杂度O(1)
        // 可以推得dp[i]只取决于dp[i-1]和dp[i-2]，我们可以单纯使用两个变量即可
        int x = 0, y = 1;
        int res = 0;
        for (int i = 2; i <= n; i++) {
            res = x + y;
            x = y;
            y = res;
        }
        return res;
    }

    public int fib2(int n) {
        if (n <= 1) {
            return n;
        }
        // 动态规划 时间复杂度O(N) 空间复杂度O(N)
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public int fib1(int n) {
        // 递归
        if (n <= 1) {
            return n;
        }
        return fib1(n - 1) + fib1(n - 2);
    }
}
