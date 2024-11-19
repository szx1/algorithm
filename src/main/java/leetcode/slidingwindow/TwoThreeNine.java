package leetcode.slidingwindow;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 滑动窗口最大值
 *
 * @author zengxi.song
 * @date 2024/7/12
 */
public class TwoThreeNine {

    public int[] maxSlidingWindow(int[] nums, int k) {
        // 分组计算最大值 总体时间复杂度O(N)每个下标最多只会放入一次 空间复杂度O(k)
        int n = nums.length;
        // 将数组从左到右k个一组进行分组
        // prefix代表当前元素在分组的前缀最大值(即分组的左边界到当前元素的最大值) 从左到右
        // 比如 k=2的情况下
        // 下标0->1为一个分组,prefixMax[0]=nums[0],preMax[1]=Math.max(preMax[0],nums[1]),prefixMax[2]=nums[2]
        // 即i如果是k的倍数 则重新赋值prefixMax为当前值
        int[] prefixMax = new int[n];
        // suffixMax代表当前元素在分组的后缀最大值 同prefixMax 但是顺序是从右到左
        int[] suffixMax = new int[n];
        for (int i = 0; i < n; ++i) {
            if (i % k == 0) {
                prefixMax[i] = nums[i];
            } else {
                prefixMax[i] = Math.max(prefixMax[i - 1], nums[i]);
            }
        }
        for (int i = n - 1; i >= 0; --i) {
            // suffix有一个例外 n-1肯定为nums[i]
            if (i == n - 1 || (i + 1) % k == 0) {
                suffixMax[i] = nums[i];
            } else {
                suffixMax[i] = Math.max(suffixMax[i + 1], nums[i]);
            }
        }
        int[] ans = new int[n - k + 1];
        // 从第一个分组开始遍历 i表示分组的左边界
        // 假设此分组跨越两个第一步分组(以下统称one分组) 分组内下标j为两个one分组的分界线
        // 则该分组内的最大值为i到j以及j到i+k-1的最大值  其中i到j即为suffixMax[i] j到i+k-1即为prefixMax[i+k-1]
        for (int i = 0; i <= n - k; i++) {
            ans[i] = Math.max(suffixMax[i], prefixMax[i + k - 1]);
        }
        // 这个是以下标k-1开始计算 即i表示的是分组的右边界
//        for (int i = k - 1; i < n; i++) {
//            ans[i - k + 1] = Math.max(suffixMax[i - k + 1], prefixMax[i]);
//        }
        return ans;
    }

    public int[] maxSlidingWindow2(int[] nums, int k) {
        // 单调队列(最大维护K个元素) 总体时间复杂度O(N)每个下标最多只会放入一次 空间复杂度O(k)
        Deque<Integer> deque = new LinkedList<>();
        // index即右边界
        int index = 0;
        // 队列只记录下标
        List<Integer> res = new ArrayList<>();
        while (index < nums.length) {
            // 从队头到队尾递减
            while (!deque.isEmpty() && nums[index] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(index);
            index++;
            if (index < k) {
                // 此时还没有构建成功第一个窗口
                continue;
            }
            // 队头为最大值 最大值不处于窗口范围则poll
            // index-k窗口左边界
            while (deque.peekFirst() < index - k) {
                deque.pollFirst();
            }
            res.add(nums[deque.peekFirst()]);
        }
        return res.stream().mapToInt(Integer::intValue).toArray();
    }

    public int[] maxSlidingWindow1(int[] nums, int k) {
        // 优先队列(最大维护N个元素,如果单调递增) 总体时间复杂度O(NlogN) 空间复杂度O(N)
        int right = 0;
        List<Integer> res = new ArrayList<>();
        PriorityQueue<Entry> queue = new PriorityQueue<>((o1, o2) -> {
            // 对于相同的值 索引大的在队头 减少poll操作
            if (o1.val == o2.val) {
                return o2.index - o1.index;
            }
            return o2.val - o1.val;
        });
        while (right < nums.length) {
            queue.add(new Entry(right, nums[right++]));
            // 只删除当前最大且不处于窗口范围的数字
            while (queue.size() > k && queue.peek().index < right - k) {
                queue.poll();
            }
            if (queue.size() >= k) {
                res.add(queue.peek().val);
            }
        }
        return res.stream().mapToInt(Integer::intValue).toArray();
    }

    static class Entry {
        int index;
        int val;

        public Entry(int index, int val) {
            this.index = index;
            this.val = val;
        }
    }

    public static void main(String[] args) {
        new TwoThreeNine().maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3);
    }
}
