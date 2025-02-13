package leetcode.math;

/**
 * Pow(x,n)
 * 快速幂算法
 *
 * @author zengxi.song
 * @date 2024/7/17
 */
public class FiveZero {

    public double myPow(double x, int n) {
        long N = n;
        return n > 0 ? quickPow(x, N) : 1 / quickPow(x, -N);
    }

    private double quickPow(double x, long n) {
        // 迭代 时间复杂度O(logN) 空间复杂度O(1)
        if (n == 0) {
            return 1;
        }
        double res = 1;
        while (n > 0) {
            // 至少调用一次
            if (n % 2 == 1) {
                res *= x;
            }
            x *= x;
            n = n / 2;
        }
        return res;
    }

    private double quickPow1(double x, long n) {
        // 递归 时间复杂度O(logN) 空间复杂度O(logN)
        if (n == 0) {
            return 1;
        }
        double i = quickPow(x, n / 2);
        return n % 2 == 1 ? i * i * x : i * i;
    }

    public static void main(String[] args) {
        System.out.println(new FiveZero().myPow(2.0, Integer.MIN_VALUE));
    }
}
