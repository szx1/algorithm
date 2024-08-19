package leetcode.array;

/**
 * 螺旋矩阵Ⅱ
 *
 * @author zengxi.song
 * @date 2024/7/8
 */
public class FiveNine {

    public int[][] generateMatrix(int n) {
        // 时间复杂度O(n*n) 空间复杂度O(1)
        int[][] res = new int[n][n];
        int t = 0, d = n - 1, l = 0, r = n - 1;
        int index = 1;
        while (true) {
            for (int i = l; i <= r; i++) {
                res[t][i] = index++;
            }
            if (++t > d) {
                break;
            }
            for (int i = t; i <= d; i++) {
                res[i][r] = index++;
            }
            if (--r < l) {
                break;
            }
            for (int i = r; i >= l; i--) {
                res[d][i] = index++;
            }
            if (d-- < t) {
                break;
            }
            for (int i = d; i >= t; i--) {
                res[i][l] = index++;
            }
            if (l++ > r) {
                break;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        new FiveNine().generateMatrix(3);
    }
}
