package leetcode.math;

/**
 * 字符串转换整数(atoi)
 *
 * @author zengxi.song
 * @date 2024/7/17
 */
public class Eight {

    public int myAtoi(String s) {
        // 不使用库函数 时间复杂度O(N) 空间复杂度O(1)
        if (s == null) {
            return 0;
        }
        int res = 0, sign = 1;
        int floor = Integer.MAX_VALUE / 10;
        int remainder = Integer.MAX_VALUE % 10;
        boolean flag = true;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                // 前导空格
                if (flag) {
                    continue;
                } else {
                    break;
                }
            }
            if (c >= '0' && c <= '9') {
                int i1 = c - '0';
                if ((res == floor && i1 > remainder) || res > floor) {
                    return sign > 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                }
                res = (res * 10) + i1;
            } else {
                if (flag && (c == '-' || c == '+')) {
                    sign = c == '-' ? -1 : 1;
                } else {
                    break;
                }
            }
            flag = false;
        }
        return res * sign;
    }

    public int myAtoi1(String s) {
        // 使用库函数 时间复杂度O(N) 空间复杂度O(1)
        if (s == null) {
            return 0;
        }
        String trim = s.trim();
        if (trim.length() == 0) {
            return 0;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < trim.length(); i++) {
            char c = trim.charAt(i);
            if (i == 0) {
                if (c == '-') {
                    sb.append('-');
                } else if (c == '+') {
                    continue;
                } else if (c >= '0' && c <= '9') {
                    sb.append(c);
                } else {
                    break;
                }
            } else {
                if (c >= '0' && c <= '9') {
                    sb.append(c);
                } else {
                    break;
                }
            }
        }
        if (sb.length() == 0 || (sb.length() == 1 && sb.charAt(0) == '-')) {
            return 0;
        }
        try {
            return Integer.parseInt(sb.toString());
        } catch (NumberFormatException e) {
            return sb.charAt(0) == '-' ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Eight().myAtoi("042"));
        System.out.println(new Eight().myAtoi("     -42"));
        System.out.println(new Eight().myAtoi("-123456789987456123"));
    }
}
