package leetcode.monotonestack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 下一个更大元素Ⅰ
 *
 * @author zengxi.song
 * @date 2024/8/13
 */
public class FourNineSix {

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        // 单调栈 时间复杂度O(m+n) 空间复杂度O(n)
        Map<Integer, Integer> map = new HashMap<>();
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = nums2.length - 1; i >= 0; --i) {
            int num = nums2[i];
            while (!stack.isEmpty() && num >= stack.peek()) {
                stack.pop();
            }
            map.put(num, stack.isEmpty() ? -1 : stack.peek());
            stack.push(num);
        }
        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; ++i) {
            res[i] = map.get(nums1[i]);
        }
        return res;
    }

    public int[] nextGreaterElement1(int[] nums1, int[] nums2) {
        // 单调栈 时间复杂度O(m+n) 空间复杂度O(m+n)
        int[] res = new int[nums1.length];
        Set<Integer> set = new HashSet<>();
        for (int num : nums1) {
            set.add(num);
        }
        Deque<Integer> stack = new ArrayDeque<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            while (!stack.isEmpty() && nums2[i] > nums2[stack.peek()]) {
                Integer pop = stack.pop();
                if (set.contains(nums2[pop])) {
                    map.put(nums2[pop], nums2[i]);
                }
            }
            stack.push(i);
        }
        for (int i = 0; i < nums1.length; i++) {
            res[i] = map.getOrDefault(nums1[i], -1);
        }
        return res;
    }
}
