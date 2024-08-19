package leetcode.array;

/**
 * 矩阵置零
 *
 * @author zengxi.song
 * @date 2024/7/19
 */
public class SevenThree {

    public void setZeroes(int[][] matrix) {
        // 使用变量记录 时间复杂度O(mn) 空间复杂度O(1)
        // 使用两个变量记录第一行第一列的数据 然后使用第一行第一列记录全数组
        int m = matrix.length, n = matrix[0].length;
        boolean row = false, column = false;
        for (int i = 0; i < n; i++) {
            if (matrix[0][i] == 0) {
                row = true;
                break;
            }
        }
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                column = true;
                break;
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (row) {
            for (int i = 0; i < n; i++) {
                matrix[0][i] = 0;
            }
        }
        if (column) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }

    }

    public void setZeroes1(int[][] matrix) {
        // 使用额外数组记录 时间复杂度O(mn) 空间复杂度O(m+n)
        int m = matrix.length, n = matrix[0].length;
        boolean[] row = new boolean[m];
        boolean[] column = new boolean[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = true;
                    column[j] = true;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (row[i] || column[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
}
