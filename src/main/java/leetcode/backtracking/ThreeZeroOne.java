package leetcode.backtracking;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * 删除无效的括号
 *
 * @author zengxi.song
 * @date 2024/9/14
 */
public class ThreeZeroOne {

    public List<String> removeInvalidParentheses(String s) {
        // bfs 时间复杂度O(N*2^N) 空间复杂度看题解吧 总体应该和迭代的轮数以及每轮能生成的字符串个数有关 不知道咋算的
        // 每次都删除一个括号 然后校验删除括号后的所有字符串是否是有效的字符串 不是则继续删除 是则停止 因为要找到删除最少的括号
        Queue<String> queue = new LinkedList<>();
        // 可能会出现重复字符串
        Set<String> visited = new HashSet<>();
        queue.add(s);
        List<String> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            // 每次都删除括号 一次循环可以清空一次
            visited.clear();
            for (int i = queue.size() - 1; i >= 0; i--) {
                String poll = queue.poll();
                if (isValid(poll)) {
                    res.add(poll);
                } else {
                    // 继续删除
                    fillQueue(queue, poll, visited);
                }
            }
            // 已经是最短的了,直接返回
            if (!res.isEmpty()) {
                return res;
            }
        }
        return res;
    }

    private void fillQueue(Queue<String> queue, String s, Set<String> visited) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == ')') {
                String str = s.substring(0, i) + s.substring(i + 1);
                if (visited.add(str)) {
                    queue.add(str);
                }
            }
        }
    }

    /**
     * 判断是否为有效括号
     *
     * @param s
     * @return
     */
    private boolean isValid(String s) {
        int left = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                left++;
            } else if (c == ')') {
                if (left == 0) {
                    return false;
                } else {
                    left--;
                }
            }
        }
        return left == 0;
    }

    public List<String> removeInvalidParentheses1(String s) {
        // 回溯 时间复杂度O(N*2^N) 空间复杂度O(N^2)
        // 先计算出需要删除的左括号和右括号数量
        int leftDel = 0, rightDel = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                leftDel++;
            } else if (c == ')') {
                if (leftDel == 0) {
                    rightDel++;
                } else {
                    leftDel--;
                }
            }
        }

        // 然后进行回溯
        List<String> res = new ArrayList<>();
        backTracking(res, new StringBuilder(s), leftDel, rightDel, 0);
        return res;
    }

    private void backTracking(List<String> res, StringBuilder sb, int leftDel, int rightDel, int index) {
        if (leftDel == 0 && rightDel == 0) {
            if (isValid(sb)) {
                res.add(sb.toString());
            }
            return;
        }
        for (int i = index; i < sb.length(); i++) {
            // 剪枝
            char c = sb.charAt(i);
            if (i > index && c == sb.charAt(i - 1)) {
                continue;
            }
            if (c != '(' && c != ')') {
                continue;
            }
            // 长度不够删除 直接break
            if (leftDel + rightDel > sb.length() - i) {
                break;
            }

            // 先尝试删除左括号
            if (leftDel > 0 && c == '(') {
                sb.deleteCharAt(i);
                // 下一层用i即可 不能用i+1 因为已经sb会动态变化的
                backTracking(res, sb, leftDel - 1, rightDel, i);
                sb.insert(i, '(');
            }
            if (rightDel > 0 && c == ')') {
                sb.deleteCharAt(i);
                backTracking(res, sb, leftDel, rightDel - 1, i);
                sb.insert(i, ')');
            }
        }
    }

    /**
     * 判断是否为有效括号
     *
     * @param sb
     * @return
     */
    private boolean isValid(StringBuilder sb) {
        int left = 0;
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            if (c == '(') {
                left++;
            } else if (c == ')') {
                if (left == 0) {
                    return false;
                } else {
                    left--;
                }
            }
        }
        return left == 0;
    }

    public static void main(String[] args) {
        new ThreeZeroOne().removeInvalidParentheses("()())()");
        new ThreeZeroOne().removeInvalidParentheses("(a)())()");
        new ThreeZeroOne().removeInvalidParentheses(")(");
    }
}
