package leetcode.array;

/**
 * 加一
 *
 * @author zengxi.song
 * @date 2024/7/11
 */
public class SixSix {

    public int[] plusOne(int[] digits) {
        // 使用源数组 时间复杂度O(N) 空间复杂度O(1)
        for (int i = digits.length - 1; i >= 0; i--) {
            // 最多一个进位所以直接++即可
            digits[i]++;
            digits[i] = digits[i] % 10;
            if (digits[i] != 0) {
                // 没有进位 直接返回
                return digits;
            }
        }
        if (digits[0] == 0) {
            digits = new int[digits.length + 1];
            // 否则后续都是0,只有第一位是1 很巧妙
            digits[0] = 1;
        }
        return digits;
    }

    public int[] plusOne1(int[] digits) {
        // 使用辅助数组 时间复杂度O(N) 空间复杂度O(N)
        int[] temp = new int[digits.length];
        int remainder = 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            int sum = digits[i] + remainder;
            temp[i] = sum % 10;
            remainder = sum / 10;
        }
        if (remainder == 0) {
            int[] res = new int[digits.length];
            System.arraycopy(temp, 0, res, 0, digits.length);
            return res;
        }
        int[] res = new int[digits.length + 1];
        System.arraycopy(temp, 0, res, 1, digits.length);
        res[0] = remainder;
        return res;
    }

    public static void main(String[] args) {
        new SixSix().plusOne(new int[]{1, 2, 3});
    }
}
