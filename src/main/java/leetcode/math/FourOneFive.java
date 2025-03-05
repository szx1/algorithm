package leetcode.math;

/**
 * 字符串相加
 *
 * @author zengxi.song
 * @date 2025/2/13
 */
public class FourOneFive {

    public String addStrings(String num1, String num2) {
        // 时间复杂度O(max(m,n)) 空间复杂度O(N)
        int i = num1.length() - 1, j = num2.length() - 1;
        int remainder = 0;
        StringBuilder res = new StringBuilder();
        for (; i >= 0 && j >= 0; i--, j--) {
            int sum = num1.charAt(i) - '0' + num2.charAt(j) - '0' + remainder;
            res.append(sum % 10);
            remainder = sum / 10;
        }
        while (i >= 0) {
            int sum = num1.charAt(i--) - '0' + remainder;
            res.append(sum % 10);
            remainder = sum / 10;
        }
        while (j >= 0) {
            int sum = num2.charAt(j--) - '0' + remainder;
            res.append(sum % 10);
            remainder = sum / 10;
        }

        if (remainder != 0) {
            res.append(remainder);
        }

        return res.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(new FourOneFive().addStrings("11", "123"));
    }
}
