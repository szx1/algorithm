package leetcode.dp;

/**
 * 统计全为1的正方形子矩阵
 *
 * @author zengxi.song
 * @date 2024/9/9
 */
public class OneTwoSevenSeven {

    public int countSquares(int[][] matrix) {
        // 动态规划 时间复杂度O(MN) 空间复杂度O(MN)
        // 设dp[i][j]为以i,j位置为右下角的正方形的最大边长
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    continue;
                }
                if (i == 0 || j == 0) {
                    dp[i][j] = matrix[i][j] == 1 ? 1 : 0;
                } else {
                    // 只有为1才进行计算
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
                // 边长为几 代表能组成几个正方形 比如边长为3表示3,2,1三个
                res += dp[i][j];
            }
        }
        return res;
    }
}
