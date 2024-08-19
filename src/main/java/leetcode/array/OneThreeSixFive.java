package leetcode.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 有多少小于当前数字的数字
 *
 * @author zengxi.song
 * @date 2024/7/22
 */
public class OneThreeSixFive {

    public int[] smallerNumbersThanCurrent(int[] nums) {
        // 计数排序 时间复杂度O(N+K) 空间复杂度O(K) K为值域的大小
        int[] cnt = new int[101];
        int n = nums.length;
        for (int num : nums) {
            cnt[num]++;
        }
        // 类似于前缀和
        for (int i = 1; i <= 100; i++) {
            cnt[i] += cnt[i - 1];
        }
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = nums[i] == 0 ? 0 : cnt[nums[i] - 1];
        }
        return res;
    }

    public int[] smallerNumbersThanCurrent1(int[] nums) {
        // 排序加哈希表 时间复杂度O(NlogN) 空间复杂度O(N)
        Map<Integer, Integer> originalMap = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            originalMap.put(i, nums[i]);
        }
        int[] res = new int[nums.length];
        Map<Integer, Integer> indexMap = new HashMap<>(nums.length);
        Arrays.sort(nums);
        for (int i = nums.length - 1; i >= 0; i--) {
            indexMap.put(nums[i], i);
        }
        for (Map.Entry<Integer, Integer> entry : originalMap.entrySet()) {
            res[entry.getKey()] = indexMap.get(entry.getValue());
        }
        return res;
    }
}
