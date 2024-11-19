package leetcode.greedy;

/**
 * 坏了的计算器
 *
 * @author zengxi.song
 * @date 2024/9/20
 */
public class NineNineOne {

    public int brokenCalc(int startValue, int target) {
        // 贪心
        int res = 0;
        // 只有先减再乘才会让操作次数最少
        while (target > startValue) {
            // 如果出现奇数 我们就认为是偶数-1，此时res++即可
            if ((target & 1) == 1) {
                target = target + 1;
                res++;
            }
            res++;
            // 中间有可能会变成奇数
            target >>>= 1;
            if (target <= startValue) {
                break;
            }
        }
        res += startValue - target;
        return res;
    }

    public static void main(String[] args) {
        new NineNineOne().brokenCalc(1, 10);
    }
}
