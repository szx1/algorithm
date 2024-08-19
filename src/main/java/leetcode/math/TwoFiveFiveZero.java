package leetcode.math;

/**
 * 猴子碰撞的方法数
 *
 * @author zengxi.song
 * @date 2024/7/17
 */
public class TwoFiveFiveZero {

    public int monkeyMove(int n) {
        // 快速幂 时间复杂度O(logN) 空间复杂度O(1)
        // 每只猴子都有顺时针和逆时针两种方向 只有两种不会碰撞
        // 猴子碰撞的次数即为 2的n次方-2
        long mod = (long) Math.pow(10, 9) + 7;
        long res = 1;
        long num = 2;
        while (n > 0) {
            if ((n & 1) == 1) {
                res = res * num % mod;
            }
            num = num * num % mod;
            n >>= 1;
        }
        return (int) ((res + mod - 2) % mod);
    }
}
