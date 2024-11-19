package leetcode.dp;

import java.util.Arrays;

/**
 * 不同路径
 *
 * @author zengxi.song
 * @date 2024/9/3
 */
public class SixTwo {

    public int uniquePaths(int m, int n) {
        // 动态规划 时间复杂度O(MN) 空间复杂度O(MN)
        // 设dp[i][j]为左上角到该位置的路径数
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    private final int[][] direct = new int[][]{{0, 1}, {1, 0}};

    public int uniquePaths1(int m, int n) {
        // dfs 记忆化搜索 时间复杂度O(MN) 空间复杂度O(MN)
        int[][] arr = new int[m][n];
        for (int[] ints : arr) {
            Arrays.fill(ints, -1);
        }
        return dfs(m, n, arr, 0, 0);
    }

    private int dfs(int m, int n, int[][] arr, int i, int j) {
        if (i >= m || j >= n) {
            return 0;
        }
        if (arr[i][j] != -1) {
            return arr[i][j];
        }
        if (i == m - 1 && j == n - 1) {
            return 1;
        }
        int count = 0;
        for (int[] ints : direct) {
            count += dfs(m, n, arr, i + ints[0], j + ints[1]);
        }
        arr[i][j] = count;
        return count;
    }
}
