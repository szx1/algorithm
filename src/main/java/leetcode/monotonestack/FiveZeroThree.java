package leetcode.monotonestack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 下一个更大元素Ⅱ
 *
 * @author zengxi.song
 * @date 2024/8/13
 */
public class FiveZeroThree {

    public int[] nextGreaterElements(int[] nums) {
        // 取模 降低空间复杂度
        // 时间复杂度(2*n) 空间复杂度O(n)
        int[] res = new int[nums.length];
        Arrays.fill(res, -1);
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < 2 * nums.length; i++) {
            while (!stack.isEmpty() && nums[i % nums.length] > nums[stack.peek()]) {
                Integer pop = stack.pop();
                res[pop] = nums[i % nums.length];
            }
            stack.push(i % nums.length);
        }
        return res;
    }

    public int[] nextGreaterElements1(int[] nums) {
        // 将循环数组展平 提高空间复杂度 但是方便编码
        // 时间复杂度(2*n) 空间复杂度O(2*n)
        int[] res = new int[nums.length];
        Arrays.fill(res, -1);
        int[] val = new int[nums.length * 2];
        for (int i = 0; i < nums.length * 2; i++) {
            val[i] = nums[i % nums.length];
        }
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < 2 * nums.length; i++) {
            while (!stack.isEmpty() && val[i] > val[stack.peek()]) {
                Integer pop = stack.pop();
                if (pop < nums.length) {
                    res[pop] = val[i];
                }
            }
            stack.push(i);
        }
        return res;
    }
}
