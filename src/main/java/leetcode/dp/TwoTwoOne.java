package leetcode.dp;

/**
 * 最大正方形
 *
 * @author zengxi.song
 * @date 2024/9/6
 */
public class TwoTwoOne {

    public int maximalSquare(char[][] matrix) {
        // 动态规划 时间复杂度O(MN) 空间复杂度O(MN)
        // 设dp[i][j]为以i,j位置为右下角的正方形的最大边长
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];

        int max = 0;
        for (int i = 0; i < n; i++) {
            dp[0][i] = matrix[0][i] == '1' ? 1 : 0;
            max = Math.max(max, dp[0][i]);
        }
        for (int i = 0; i < m; i++) {
            dp[i][0] = matrix[i][0] == '1' ? 1 : 0;
            max = Math.max(max, dp[i][0]);
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == '0') {
                    continue;
                }
                // 只有为1才进行计算
                dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                max = Math.max(max, dp[i][j]);
            }
        }
        return max * max;
    }

    public static void main(String[] args) {
        new TwoTwoOne().maximalSquare(new char[][]{{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}});
    }
}
