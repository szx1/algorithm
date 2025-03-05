package leetcode.math;

/**
 * 二进制求和
 *
 * @author zengxi.song
 * @date 2025/2/14
 */
public class SixSeven {

    public String addBinary(String a, String b) {
        // 时间复杂度O(max(m,n)) 空间复杂度O(N)
        // 从尾部开始
        StringBuilder sb = new StringBuilder();
        int remainder = 0;
        for (int i = a.length() - 1, j = b.length() - 1; i >= 0 || j >= 0; i--, j--) {
            int numa = i >= 0 ? a.charAt(i) - '0' : 0;
            int numb = j >= 0 ? b.charAt(j) - '0' : 0;
            int sum = numa + numb + remainder;
            sb.append(sum % 2);
            remainder = sum / 2;
        }
        if (remainder != 0) {
            sb.append(remainder);
        }
        return sb.reverse().toString();
    }
}
