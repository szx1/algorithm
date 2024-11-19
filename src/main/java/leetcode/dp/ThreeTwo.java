package leetcode.dp;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 最长有效括号
 *
 * @author zengxi.song
 * @date 2024/8/19
 */
public class ThreeTwo {

    public int longestValidParentheses(String s) {
        // 双指针 时间复杂度O(N) 空间复杂度O(1)
        // 使用双指针记录(和)的个数 当二者相等时我们统计长度 特别的是)个数大于(时 我们需要将二者都置为0
        int leftCount = 0, rightCount = 0;
        char[] charArr = s.toCharArray();
        int res = 0;
        for (char c : charArr) {
            if (c == '(') {
                leftCount++;
            } else {
                rightCount++;
            }
            if (leftCount == rightCount) {
                res = Math.max(res, 2 * leftCount);
            } else if (rightCount > leftCount) {
                leftCount = 0;
                rightCount = 0;
            }
        }
        // 特别的 对于(()这种情况 因为leftCount恒大于rightCount
        // 为了兼容这种情况 我们只需要反向再遍历一次
        leftCount = 0;
        rightCount = 0;
        for (int i = charArr.length - 1; i >= 0; i--) {
            if (charArr[i] == '(') {
                leftCount++;
            } else {
                rightCount++;
            }
            if (leftCount == rightCount) {
                res = Math.max(res, 2 * leftCount);
            } else if (rightCount < leftCount) {
                leftCount = 0;
                rightCount = 0;
            }
        }
        return res;
    }

    public int longestValidParentheses2(String s) {
        // 栈 时间复杂度O(N) 空间复杂度O(N)
        // 栈中保存最后无效的) 以及所有(的下标
        // 遇到匹配的右括号时将左括号弹出
        Deque<Integer> stack = new ArrayDeque<>();
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (stack.isEmpty() || c == '(') {
                stack.push(i);
            } else {
                // 此时c为右括号
                if (s.charAt(stack.peek()) == '(') {
                    stack.pop();
                    // 有可能没有无效的右括号 则此时取长度为i+1
                    if (stack.isEmpty()) {
                        res = Math.max(res, i + 1);
                    } else {
                        // 不断更新res 直至无效或者stack为空
                        res = Math.max(res, i - stack.peek());
                    }
                } else {
                    // 代表该i处的右括号无效 存入stack
                    stack.push(i);
                }
            }
        }
        return res;
    }

    public int longestValidParentheses1(String s) {
        // 动态规划 时间复杂度O(N) 空间复杂度O(N)
        // 定义dp[i]为s的以下标i为结尾的字符的最长有效括号长度
        int[] dp = new int[s.length()];
        int res = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                continue;
            }
            // 只对')'结尾做更新
            if (s.charAt(i - 1) == '(') {
                dp[i] = i >= 2 ? dp[i - 2] + 2 : 2;
            } else {
                // i-1为) 比较复杂
                // 需要判断i减去dp[i-1]是否为(
                int left = i - dp[i - 1] - 1;
                if (left >= 0 && s.charAt(left) == '(') {
                    dp[i] = dp[i - 1] + 2;
                    // 还需要判断前面是否能续上
                    if (left - 1 >= 0) {
                        dp[i] += dp[left - 1];
                    }
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
