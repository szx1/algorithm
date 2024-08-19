package leetcode.math;

/**
 * 找出数组的最大公约数
 *
 * @author zengxi.song
 * @date 2024/8/6
 */
public class OneNineSevenNine {

    public int findGCD(int[] nums) {
        // 时间复杂度O(N) 空间复杂度O(1)
        int min = 1001, max = -1;
        for (int num : nums) {
            if (num > max) {
                max = num;
            }
            if (num < min) {
                min = num;
            }
        }
        // 辗转相除法
        return calGCD(max, min);
    }

    private int calGCD(int max, int min) {
        int remainder = max % min;
        while (remainder != 0) {
            int temp = remainder;
            remainder = min % remainder;
            min = temp;
        }
        return min;
    }

    public static void main(String[] args) {
        new OneNineSevenNine().findGCD(new int[]{2, 2, 7});
    }
}
