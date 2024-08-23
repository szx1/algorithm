package leetcode.dp.knapsack.zeroone;

/**
 * 一和零
 *
 * @author zengxi.song
 * @date 2024/8/20
 */
public class FourSevenFour {

    public int findMaxForm(String[] strs, int m, int n) {
        // 动态规划优化 时间复杂度O(n^3) 空间复杂度O(n^2)
        // 最多有m个0和n个1 可以当做0-1背包问题处理
        // 有两个容量 定义dp[j][k]在j个0，k个1的情况下的最大数量
        int[][] dp = new int[m + 1][n + 1];
        for (String str : strs) {
            int[] zeroAndOne = findZeroAndOne(str);
            for (int j = m; j >= zeroAndOne[0]; j--) {
                for (int k = n; k >= zeroAndOne[1]; k--) {
                    dp[j][k] = Math.max(dp[j][k], dp[j - zeroAndOne[0]][k - zeroAndOne[1]] + 1);
                }
            }
        }
        return dp[m][n];
    }

    public int findMaxForm1(String[] strs, int m, int n) {
        // 动态规划 时间复杂度O(n^3) 空间复杂度O(n^3)
        // 最多有m个0和n个1 可以当做0-1背包问题处理
        // 有两个容量 定义dp[i][j][k]为前k个字符串在j个0，k个1的情况下的最大数量
        int[][][] dp = new int[strs.length + 1][m + 1][n + 1];
        for (int i = 1; i <= strs.length; i++) {
            int[] zeroAndOne = findZeroAndOne(strs[i - 1]);
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    dp[i][j][k] = dp[i - 1][j][k];
                    if (j >= zeroAndOne[0] && k >= zeroAndOne[1]) {
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j - zeroAndOne[0]][k - zeroAndOne[1]] + 1);
                    }
                }
            }
        }
        return dp[strs.length][m][n];
    }

    private int[] findZeroAndOne(String str) {
        int m = 0, n = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '0') {
                m++;
            } else {
                n++;
            }
        }
        return new int[]{m, n};
    }
}
