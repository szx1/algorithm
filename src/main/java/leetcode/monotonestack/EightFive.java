package leetcode.monotonestack;

/**
 * 最大矩形
 *
 * @author zengxi.song
 * @date 2024/8/16
 */
public class EightFive {

    public int maximalRectangle(char[][] matrix) {
        // 对每一行做84题的操作
        // 时间复杂度O(mn) 空间复杂度O(mn)
        int m = matrix.length, n = matrix[0].length;
        int[][] top = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0) {
                        top[i][j] = 1;
                    } else {
                        top[i][j] = top[i - 1][j] + 1;
                    }
                }
            }
        }
        int res = 0;
        // 此时就可以循环每一行了
        for (int i = 0; i < m; i++) {
            res = Math.max(res, new EightFour().largestRectangleArea(top[i]));
        }
        return res;
    }
}
