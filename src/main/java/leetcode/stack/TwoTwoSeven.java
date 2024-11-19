package leetcode.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 基本计算器Ⅱ
 *
 * @author zengxi.song
 * @date 2024/9/20
 */
public class TwoTwoSeven {

    public int calculate(String s) {
        // 栈
        int num = 0;
        char preOpe = '+';
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                num = num * 10 + (c - '0');
            }
            if (i == s.length() - 1 || c == '+' || c == '-' || c == '*' || c == '/') {
                // 运算符 统一计算加法 如果存在-放入负数
                if (preOpe == '+') {
                    stack.push(num);
                } else if (preOpe == '-') {
                    stack.push(num * -1);
                } else if (preOpe == '*') {
                    // 因为保证输入有效 所以stack绝对不可能empty
                    Integer pop = stack.pop();
                    stack.push(pop * num);
                } else {
                    Integer pop = stack.pop();
                    stack.push(pop / num);
                }
                num = 0;
                preOpe = c;
            }
        }
        num = 0;
        while (!stack.isEmpty()) {
            num += stack.pop();
        }
        return num;
    }
}
