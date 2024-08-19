/**
 * 该目录下记录单调栈的算法题目 <br>
 * 关于求当前的下一个较大/较小值的问题都可以使用单调栈 对应的栈顶到栈底的关系为 递增/递减 <br>
 * 时间复杂度O(n) 空间复杂度O(n)
 *
 * @author zengxi.song
 * @date 2024/8/16
 */
package leetcode.monotonestack;

import java.util.ArrayDeque;
import java.util.Deque;

class Template {
    public static void main(String[] args) {
        // 关于单调栈的模板
        int[] nums = new int[10];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            if (stack.isEmpty()) {
                stack.push(i);
            } else if (nums[i] == nums[stack.peek()]) {
                // 有些问题需要对==做特殊处理
                stack.push(i);
            } else {
                while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                    Integer pop = stack.pop();
                    // 这里的nums[i]就是下标pop的下一个较大值
                }
                stack.push(i);
            }
        }
        // 如果遇到循环数组 则循环2n遍即可 注意下标的取余 另一种方式是copy 2n数组
        // 如果遇到两边的数也要参与计算的情况 有一种简单的做法 即在两端添加其他值 例如Integer.MAX/Integer.MIN 用于处理递增和递减
    }
}

