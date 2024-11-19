/**
 * 逆波兰式
 *
 * @author zengxi.song
 * @date 2024/9/23
 */
package leetcode.stack.rpn;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

class ReversePolishNotation {

    private final Map<Character, Integer> priorityMap = new HashMap<>();

    {
        priorityMap.put('-', 1);
        priorityMap.put('+', 1);
        priorityMap.put('/', 2);
        priorityMap.put('*', 2);
    }

    /**
     * 将字符串转换为逆波兰式
     *
     * @param s
     * @return
     */
    public String convert2Rpn(String s) {
        // 最终输出 以空格为分界
        StringBuilder output = new StringBuilder();
        // 栈用来记录操作符
        Deque<Character> stack = new ArrayDeque<>();
        // 记录多位数
        StringBuilder num = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                continue;
            }
            if (c >= '0' && c <= '9') {
                num.append(c);
            } else {
                // 先写入数字
                if (num.length() > 0) {
                    output.append(num).append(' ');
                    num.setLength(0);
                }
                if (c == '(') {
                    // 左括号进栈
                    stack.push(c);
                } else if (c == ')') {
                    // 右括号要弹出所有的操作符 直到左括号出现
                    while (!stack.isEmpty() && stack.peek() != '(') {
                        output.append(stack.pop()).append(' ');
                    }
                    // 弹出左括号
                    stack.pop();
                } else if (isOperator(c)) {
                    // 弹出所有优先级大于等于当前运算符的运算符
                    while (!stack.isEmpty() && morePriority(stack.peek(), c)) {
                        output.append(stack.pop()).append(' ');
                    }
                    // 将当前运算符入栈
                    stack.push(c);
                }
            }
        }

        // 处理表达式最后部分的数字
        if (num.length() > 0) {
            output.append(num).append(' ');
        }

        // 把栈中剩下的所有操作符加入输出
        while (!stack.isEmpty()) {
            output.append(stack.pop()).append(' ');
        }
        return output.toString();
    }

    public int evalRPN(String[] tokens) {
        // 栈 时间复杂度O(N) 空间复杂度O(N)
        Deque<Integer> stack = new ArrayDeque<>();
        for (String token : tokens) {
            token = token.trim();
            switch (token) {
                case "-": {
                    // 有效表达式必然存在两个
                    Integer one = stack.pop();
                    Integer two = stack.pop();
                    stack.push(two - one);
                    break;
                }
                case "+": {
                    Integer one = stack.pop();
                    Integer two = stack.pop();
                    stack.push(two + one);
                    break;
                }
                case "*": {
                    Integer one = stack.pop();
                    Integer two = stack.pop();
                    stack.push(two * one);
                    break;
                }
                case "/": {
                    Integer one = stack.pop();
                    Integer two = stack.pop();
                    stack.push(two / one);
                    break;
                }
                default:
                    stack.push(Integer.parseInt(token));
                    break;
            }
        }
        return stack.pop();
    }

    private boolean isOperator(char c) {
        return c == '-' || c == '+' || c == '/' || c == '*';
    }

    private boolean morePriority(char one, char two) {
        return priorityMap.getOrDefault(one, 0) >= priorityMap.getOrDefault(two, 0);
    }


    public static void main(String[] args) {
        ReversePolishNotation rpn = new ReversePolishNotation();
        System.out.println(rpn.convert2Rpn("((10 * (6 / ((9 + 3) * 11))) + 17) + 5"));
        System.out.println(rpn.evalRPN(rpn.convert2Rpn("((10 * (6 / ((9 + 3) * 11))) + 17) + 5").split(" ")));
    }
}