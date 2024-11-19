package leetcode.stack.rpn;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * 逆波兰式求值
 *
 * @author zengxi.song
 * @date 2024/9/23
 */
public class OneFiveZero {

    private static final Set<String> operator = new HashSet<>();

    static {
        operator.add("-");
        operator.add("+");
        operator.add("/");
        operator.add("*");
    }

    public int evalRPN(String[] tokens) {
        // 栈 时间复杂度O(N) 空间复杂度O(N)
        Deque<Integer> stack = new ArrayDeque<>();
        for (String token : tokens) {
            if (!isOperator(token)) {
                stack.push(Integer.parseInt(token));
            } else {
                // 有效表达式必然存在两个
                Integer one = stack.pop();
                Integer two = stack.pop();
                switch (token) {
                    case "-":
                        stack.push(two - one);
                        break;
                    case "+":
                        stack.push(one + two);
                        break;
                    case "*":
                        stack.push(one * two);
                        break;
                    case "/":
                        stack.push(two / one);
                        break;
                }
            }
        }
        return stack.pop();
    }

    private boolean isOperator(String token) {
        return operator.contains(token);
    }
}
