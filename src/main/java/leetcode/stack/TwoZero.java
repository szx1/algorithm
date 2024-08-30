package leetcode.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 有效的括号
 *
 * @author zengxi.song
 * @date 2024/8/30
 */
public class TwoZero {

    public boolean isValid(String s) {
        // 栈的经典应用
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (stack.isEmpty()) {
                if (isRight(c)) {
                    return false;
                }
                // 放入左括号
                stack.push(c);
            } else {
                if (!isRight(c)) {
                    stack.push(c);
                    continue;
                }
                if (canMatch(stack.peek(), c)) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    private boolean isRight(Character c) {
        return c == ')' || c == ']' || c == '}';
    }

    private boolean canMatch(Character left, Character right) {
        switch (left) {
            case '(':
                return right == ')';
            case '[':
                return right == ']';
            case '{':
                return right == '}';
            default:
                return false;
        }
    }
}
