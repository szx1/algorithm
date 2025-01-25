package leetcode.string;

/**
 * 整数转罗马数字
 *
 * @author zengxi.song
 * @date 2025/1/10
 */
public class OneTwo {

    public String intToRoman(int num) {
        String[] str = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] nums = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            for (int i = 0; i < nums.length; i++) {
                if (num - nums[i] >= 0) {
                    sb.append(str[i]);
                    num -= nums[i];
                    break;
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new OneTwo().intToRoman(3749));
    }
}
