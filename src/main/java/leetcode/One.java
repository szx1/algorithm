package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和
 *
 * @author zengxi.song
 * @date 2024/8/23
 */
public class One {

    public int[] twoSum(int[] nums, int target) {
        // hash表 时间复杂度O(N) 空间复杂度O(N)
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{0, 0};
    }
}
