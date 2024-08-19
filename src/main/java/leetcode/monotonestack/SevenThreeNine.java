package leetcode.monotonestack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 每日温度
 *
 * @author zengxi.song
 * @date 2024/8/13
 */
public class SevenThreeNine {

    public int[] dailyTemperatures(int[] temperatures) {
        // 单调栈 时间复杂度O(N) 空间复杂度O(N)
        int[] res = new int[temperatures.length];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < temperatures.length; i++) {
            if (stack.isEmpty()) {
                stack.push(i);
            } else {
                while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                    Integer pop = stack.pop();
                    res[pop] = i - pop;
                }
                stack.push(i);
            }
        }
        return res;
    }
}
