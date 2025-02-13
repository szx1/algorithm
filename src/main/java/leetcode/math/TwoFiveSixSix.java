package leetcode.math;

/**
 * 替换一个数字后的最大差值
 *
 * @author zengxi.song
 * @date 2025/2/12
 */
public class TwoFiveSixSix {

    public int minMaxDifference(int num) {
        // 最大值肯定是尽可能使前面的数字换为9
        // 最小值尽可能使前面的数字换为0 但因为原来的数字不包含前导0 即为第一个字符
        char[] chars = String.valueOf(num).toCharArray();
        char maxChar = '9', minChar = chars[0];
        // 只需要求修改的最大值
        for (char c : chars) {
            if (c < '9') {
                maxChar = c;
                break;
            }
        }
        long min = 0, max = 0;
        for (char c : chars) {
            min = 10 * min + (c == minChar ? 0 : c - '0');
            max = 10 * max + (c == maxChar ? 9 : c - '0');
        }
        return (int) (max - min);
    }
}
