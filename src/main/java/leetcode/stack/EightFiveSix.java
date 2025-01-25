package leetcode.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 括号的分数
 *
 * @author zengxi.song
 * @date 2025/1/24
 */
public class EightFiveSix {

    public int scoreOfParentheses(String s) {
        // 使用栈 时间复杂度O(N) 空间复杂度O(N)
        // 将分数压入栈 每当遇见(将分数0进栈
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            // 左括号直接入栈, 使用0表示
            if (s.charAt(i) == '(') {
                stack.push(0);
                continue;
            }
            // 右括号则一直去找栈中第一个左括号为止, 得到大于0的则一直累加, 即AB 得 A + B 分
            int base = 0;
            while (!stack.isEmpty() && stack.peek() != 0) {
                base += stack.pop();
            }
            // 去掉左括号
            stack.pop();
            // base=0表示直接找到左括号, ()直接入栈1, 否则得到(A)得2 * A分
            stack.push(base == 0 ? 1 : 2 * base);
        }
        // 累加多分, 这里是为了兼容()()这类情况
        int ans = 0;
        while (!stack.isEmpty()) {
            ans += stack.pop();
        }
        return ans;
    }
}
