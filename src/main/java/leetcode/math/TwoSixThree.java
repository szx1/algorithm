package leetcode.math;

/**
 * 丑数
 *
 * @author zengxi.song
 * @date 2024/8/9
 */
public class TwoSixThree {

    public boolean isUgly(int n) {
        // 时间复杂度O(logN) 空间复杂度O(1)
        if (n <= 0) {
            return false;
        }
        int[] factors = {2, 3, 5};
        for (int factor : factors) {
            while (n % factor == 0) {
                n /= factor;
            }
        }
        return n == 1;
    }
}
