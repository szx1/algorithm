package leetcode.math;

/**
 * 两整数之和
 *
 * @author zengxi.song
 * @date 2024/7/12
 */
public class ThreeSevenOne {

    public int getSum(int a, int b) {
        // 位运算 时间复杂度O(log(max_int)) 空间复杂度O(1)
        while (b != 0) {
            int carry = (a & b) << 1;
            a = a ^ b;
            b = carry;
        }
        return a;
    }
}
