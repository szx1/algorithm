package leetcode.monotonestack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 柱状图中最大的矩形
 *
 * @author zengxi.song
 * @date 2024/8/15
 */
public class EightFour {

    public int largestRectangleArea(int[] heights) {
        // 单调栈 时间复杂度O(n) 空间复杂度O(n)
        // 由于是由柱状图限制的矩形 所以寻找到左右两侧第一个小于的值 即可计算当前的面积
        // 栈顶到栈底递减
        Deque<Integer> stack = new ArrayDeque<>();
        // 和接雨水不一样 该题两侧是需要参与计算的
        // 另一种方式是两边添加一个最小值 例如0 这样就可以处理两边的数据了
        int[] newVal = new int[heights.length + 2];
        for (int i = 1; i <= heights.length; i++) {
            newVal[i] = heights[i - 1];
        }
        newVal[0] = newVal[heights.length + 1] = 0;
        int res = 0;
        for (int i = 0; i < newVal.length; i++) {
            if (stack.isEmpty()) {
                stack.push(i);
            } else if (newVal[i] == newVal[stack.peek()]) {
                // i和peek计算面积的结果相等 只计算右边的即可
                stack.pop();
                stack.push(i);
            } else {
                while (newVal[i] < newVal[stack.peek()]) {
                    int pop = stack.pop();
                    if (stack.isEmpty()) {
                        break;
                    }
                    res = Math.max((i - stack.peek() - 1) * newVal[pop], res);
                }
                stack.push(i);
            }
        }
        return res;
    }

    public int largestRectangleArea1(int[] heights) {
        // 单调栈 时间复杂度O(n) 空间复杂度O(n)
        // 由于是由柱状图限制的矩形 所以寻找到左右两侧第一个小于的值 即可计算当前的面积
        // 栈顶到栈底递减
        Deque<Integer> stack = new ArrayDeque<>();
        // 和接雨水不一样 该题两侧是需要参与计算的
        int res = 0;
        for (int i = 0; i < heights.length; i++) {
            if (stack.isEmpty()) {
                stack.push(i);
            } else if (heights[i] == heights[stack.peek()]) {
                // i和peek计算面积的结果相等 只计算右边的即可
                stack.pop();
                stack.push(i);
            } else {
                while (heights[i] < heights[stack.peek()]) {
                    int pop = stack.pop();
                    if (stack.isEmpty()) {
                        // 这里处理的开头没有更小的值的数据
                        res = Math.max((i) * heights[pop], res);
                        break;
                    }
                    res = Math.max((i - stack.peek() - 1) * heights[pop], res);
                }
                stack.push(i);
            }
        }
        // 处理结尾的数据
        if (!stack.isEmpty()) {
            Integer first = stack.peek();
            while (!stack.isEmpty()) {
                Integer pop = stack.pop();
                if (stack.isEmpty()) {
                    res = Math.max((first + 1) * heights[pop], res);
                    break;
                }
                res = Math.max((first - stack.peek()) * heights[pop], res);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        new EightFour().largestRectangleArea(new int[]{2, 4});
    }
}
