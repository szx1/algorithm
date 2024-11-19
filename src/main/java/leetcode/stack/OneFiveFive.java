package leetcode.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author zengxi.song
 * @date 2024/9/5
 */
public class OneFiveFive {

    /**
     * 辅助栈实现
     * 这里可以使用任何可以做到栈功能的数据结构类
     * 这里采用deque
     */
    Deque<Integer> valueDeque = new ArrayDeque<>();
    Deque<Integer> minDeque = new ArrayDeque<>();


    public void push(int val) {
        valueDeque.add(val);
        if (minDeque.isEmpty()) {
            minDeque.add(val);
        } else {
            minDeque.add(Math.min(minDeque.getLast(), val));
        }
    }

    public void pop() {
        valueDeque.removeLast();
        minDeque.removeLast();
    }

    public int top() {
        return valueDeque.getLast();
    }

    public int getMin() {
        return minDeque.getLast();
    }
}
