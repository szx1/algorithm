package leetcode.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 基本计算器
 *
 * @author zengxi.song
 * @date 2024/9/20
 */
public class TwoTwoFour {

    public int calculate(String s) {
        // 使用栈记录符号 时间复杂度O(N) 空间复杂度O(N)
        int res = 0, num = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(1);
        int sign = 1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case ' ':
                    break;
                case '+':
                    sign = stack.peek();
                    break;
                case '-':
                    sign = -stack.peek();
                    break;
                case '(':
                    stack.push(sign);
                    break;
                case ')':
                    stack.pop();
                    break;
                default:
                    while (i < s.length()) {
                        char c1 = s.charAt(i);
                        if (!(c1 >= '0' && c1 <= '9')) {
                            break;
                        }
                        num = num * 10 + (c1 - '0');
                        i++;
                    }
                    res += sign * num;
                    num = 0;
                    // 回退 因为最外面还有i++
                    i--;
            }
        }
        return res;
    }

    public int calculate1(String s) {
        // 递归 这个版本会扫描多遍字符串
        int res = 0, num = 0;
        char preOpe = '+';
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 不关心' '和')'
            if (c >= '0' && c <= '9') {
                num = num * 10 + (c - '0');
            }
            if (i == s.length() - 1 || c == '-' || c == '+') {
                if (preOpe == '+') {
                    res += num;
                } else {
                    res -= num;
                }
                num = 0;
                preOpe = c;
            }


            if (c == '(') {
                int leftCount = 1;
                int j = i + 1;
                // 这一段时间复杂度较高 先循环再递归还会扫描一遍
                for (; j < s.length(); j++) {
                    if (s.charAt(j) == '(') {
                        leftCount++;
                    } else if (s.charAt(j) == ')') {
                        leftCount--;
                        if (leftCount == 0) {
                            break;
                        }
                    }
                }
                if (preOpe == '+') {
                    res += calculate(s.substring(i + 1, j));
                } else {
                    res -= calculate(s.substring(i + 1, j));
                }
                i = j;
            }
        }
        return res;
    }
}
