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
        // 动态规划 空间优化版 时间复杂度O(N) 空间复杂度O(1)
        if (n <= 2) {
            return n;
        }
        int x = 1, y = 2;
        for (int i = 3; i <= n; i++) {
            int temp = x + y;
            x = y;
            y = temp;
        }
        return y;
    }

    public int climbStairs3(int n) {
        // 动态规划 时间复杂度O(N) 空间复杂度O(N)
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

    public int climbStairs2(int n) {
        // 记忆化搜索 递归的优化 时间复杂度O(N) 空间复杂度O(N)
        if (n <= 2) {
            return n;
        }
        Integer[] memo = new Integer[n];
        return memoSearch(n - 1, memo) + memoSearch(n - 2, memo);
    }

    public int memoSearch(int n, Integer[] memo) {
        if (memo[n] != null) {
            return memo[n];
        }
        if (n <= 2) {
            memo[n] = n;
            return n;
        }
        int res = memoSearch(n - 1, memo) + memoSearch(n - 2, memo);
        memo[n] = res;
        return res;
    }

    public int climbStairs1(int n) {
        // 递归 会超时
        if (n <= 2) {
            return n;
        }
        return climbStairs1(n - 1) + climbStairs1(n - 2);
    }
}
