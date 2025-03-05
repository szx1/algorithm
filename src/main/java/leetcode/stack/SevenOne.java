package leetcode.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 简化路径
 *
 * @author zengxi.song
 * @date 2025/2/14
 */
public class SevenOne {

    public String simplifyPath(String path) {
        // 栈 时间复杂度O(N) 空间复杂度O(N)
        // 方法1太乱了 其实栈中可以只保存目录 最后再加/
        Deque<String> stack = new ArrayDeque<>();
        StringBuilder tmp = new StringBuilder();
        char[] chars = path.toCharArray();
        for (int i = 0; i <= path.length(); i++) {
            if (i == path.length() || chars[i] == '/') {
                if (tmp.length() == 0) {
                    continue;
                }
                String s = tmp.toString();
                if ("..".equals(s)) {
                    // 删除上层目录
                    if (!stack.isEmpty()) {
                        stack.pop();
                    }
                } else if (".".equals(s)) {
                    // 什么都不做
                } else {
                    stack.push(s);
                }
                tmp.setLength(0);
            } else {
                tmp.append(chars[i]);
            }
        }

        StringBuilder sb = new StringBuilder();
        if (stack.isEmpty()) {
            sb.append('/');
        } else {
            while (!stack.isEmpty()) {
                sb.append('/');
                sb.append(stack.pollLast());
            }
        }
        return sb.toString();
    }

    public String simplifyPath1(String path) {
        // 栈 时间复杂度O(N) 空间复杂度O(N)
        Deque<Character> stack = new ArrayDeque<>();
        StringBuilder tmp = new StringBuilder();
        for (int i = 0; i < path.length(); i++) {
            char c = path.charAt(i);
            // 因为输入恒有效 为空直接push
            if (stack.isEmpty()) {
                stack.push(c);
                continue;
            }
            Character peek = stack.peek();
            if (c == '/' && peek == '/') {
                // 重复的斜线跳过
                continue;
            }
            if (stack.peek() == '/' && c == '.') {
                int j = i;
                for (; j < path.length(); j++) {
                    char inner = path.charAt(j);
                    if (inner == '/') {
                        break;
                    }
                    tmp.append(inner);
                }
                String s = tmp.toString();
                if ("..".equals(s)) {
                    if (stack.size() > 1) {
                        // 把上层删掉
                        stack.pop();
                        while (!stack.isEmpty() && stack.peek() != '/') {
                            stack.pop();
                        }
                    }
                } else if (!".".equals(s)) {
                    for (int k = 0; k < s.length(); k++) {
                        stack.push(s.charAt(k));
                    }
                    stack.push('/');
                }
                // 直接跳过本层
                i = j;
                tmp.setLength(0);
                continue;
            }
            stack.push(c);
        }
        // 将最后多余的/删除
        while (stack.size() > 1 && stack.peek() == '/') {
            stack.pop();
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pollLast());
        }
        return sb.toString();
    }
}
