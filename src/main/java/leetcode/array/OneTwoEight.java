package leetcode.array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 最长连续序列
 *
 * @author zengxi.song
 * @date 2024/9/4
 */
public class OneTwoEight {

    public int longestConsecutive(int[] nums) {
        // 使用set记录 时间复杂度O(N) 空间复杂度O(N)
        // 去掉了额外的map记录
        Set<Integer> set = new HashSet<>(nums.length);
        for (int num : nums) {
            set.add(num);
        }
        int res = 0;
        // 注意迭代set 因为原来的nums可能会有很多重复数字 导致超时
        for (Integer num : set) {
            // 其实我们不需要额外的map记录
            // 我们只需要关注每次从最小的开始就可以
            // 如果这里使用num+1 下面while循环里要换成num--
            if (set.contains(num - 1)) {
                continue;
            }
            int count = 0;
            while (set.contains(num)) {
                count++;
                num++;
            }
            res = Math.max(res, count);
        }
        return res;
    }

    public int longestConsecutive1(int[] nums) {
        // 使用set记录 时间复杂度O(N) 空间复杂度O(N)
        Set<Integer> set = new HashSet<>(nums.length);
        for (int num : nums) {
            set.add(num);
        }
        Map<Integer, Boolean> visited = new HashMap<>(nums.length);
        int res = 0;
        // 注意迭代set 因为原来的nums可能会有很多重复数字 导致超时
        for (Integer num : set) {
            if (visited.getOrDefault(num, false)) {
                continue;
            }
            int count = 0;
            while (set.contains(num)) {
                count++;
                visited.put(num, true);
                num++;
            }
            res = Math.max(res, count);
        }

        return res;
    }
}
