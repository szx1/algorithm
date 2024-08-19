package leetcode.monotonestack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 接雨水
 *
 * @author zengxi.song
 * @date 2024/8/14
 */
public class FourTwo {

    public int trap(int[] height) {
        // 单调栈 时间复杂度O(n) 空间复杂度O(n)
        // 对于单调栈的解法是通过行来计算的
        // 我们要求的是下一个更大元素 所以栈顶到栈底应该是递增的
        Deque<Integer> stack = new ArrayDeque<>();
        int res = 0;
        for (int i = 0; i < height.length; i++) {
            if (stack.isEmpty()) {
                stack.push(i);
            } else if (height[i] == height[stack.peek()]) {
                // 等于的情况要以右边的下标为准
                stack.pop();
                stack.push(i);
            } else {
                while (height[i] > height[stack.peek()]) {
                    Integer pop = stack.pop();
                    if (stack.isEmpty()) {
                        stack.push(i);
                        // 没有左边界
                        break;
                    }
                    int left = stack.peek();
                    int x = Math.min(height[left], height[i]) - height[pop];
                    int y = i - left - 1;
                    res += x * y;
                }
                stack.push(i);
            }
        }
        return res;
    }

    public int trap2(int[] height) {
        // 双指针 动态规划的优化版本 时间复杂度O(n) 空间复杂度O(1)
        // 下标为i的格子能存储的水量为左侧和右侧最大值的较小值-height[i]
        int leftMax = height[0], rightMax = height[height.length - 1];
        int left = 1, right = height.length - 2;
        int res = 0;
        while (left <= right) {
            leftMax = Math.max(leftMax, height[left - 1]);
            rightMax = Math.max(rightMax, height[right + 1]);
            if (leftMax < rightMax) {
                res += leftMax > height[left] ? leftMax - height[left] : 0;
                left++;
            } else {
                res += rightMax > height[right] ? rightMax - height[right] : 0;
                right--;
            }
        }
        return res;
    }

    public int trap1(int[] height) {
        // 动态规划 时间复杂度O(n) 空间复杂度O(n)
        // 下标为i的格子能存储的水量为左侧和右侧最大值的较小值-height[i]
        int n = height.length;
        int[] leftMax = new int[n];
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(height[i], leftMax[i - 1]);
        }
        int[] rightMax = new int[n];
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(height[i], rightMax[i + 1]);
        }

        int res = 0;
        for (int i = 1; i < n - 1; i++) {
            int min = Math.min(leftMax[i - 1], rightMax[i + 1]);
            res += min > height[i] ? min - height[i] : 0;
        }
        return res;
    }
}
