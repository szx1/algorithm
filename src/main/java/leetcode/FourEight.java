package leetcode;

/**
 * 旋转图像
 *
 * @author zengxi.song
 * @date 2024/4/12
 */
public class FourEight {

    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < (n + 1) / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][i];
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = temp;
            }
        }
    }

    public void rotate1(int[][] matrix) {
        int n = matrix.length;
        int[][] aux = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                aux[i][j] = matrix[n - j - 1][i];
            }
        }

        for (int i = 0; i < n; i++) {
            System.arraycopy(aux[i], 0, matrix[i], 0, n);
        }
    }
}
