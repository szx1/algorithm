package leetcode.presum;

import java.util.HashMap;
import java.util.Map;

/**
 * 和为K的子数组
 *
 * @author zengxi.song
 * @date 2024/9/11
 */
public class FiveSixZero {

    public int subarraySum(int[] nums, int k) {
        // 连续的子数组求和 可以联想到前缀和
        // 时间复杂度O(N) 空间复杂度O(N)
        // 一次遍历 直接借助countMap来计算
        Map<Integer, Integer> countMap = new HashMap<>();
        int res = 0;
        int sum = 0;
        for (int num : nums) {
            sum += num;
            // 这里是兼容从第一个数开始加的 如果在循环外面加上countMap.put(0,1)可以代替这段代码
            if (sum == k) {
                res++;
            }
            res += countMap.getOrDefault(sum - k, 0);
            countMap.merge(sum, 1, Integer::sum);
        }
        return res;
    }

    public int subarraySum1(int[] nums, int k) {
        // 连续的子数组求和 可以联想到前缀和
        // 时间复杂度O(N) 空间复杂度O(N)
        int[] preSum = new int[nums.length + 1];
        for (int i = 1; i <= nums.length; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }
        // 因为可能存在[1,-1,0] target=0这样的用例
        // 所以需要使用map记录次数 而不是set就可以
        Map<Integer, Integer> countMap = new HashMap<>();
        int res = 0;
        for (int i = 0; i < preSum.length; i++) {
            res += countMap.getOrDefault(preSum[i] - k, 0);
            countMap.merge(preSum[i], 1, Integer::sum);
        }
        return res;
    }

    public static void main(String[] args) {
        new FiveSixZero().subarraySum(new int[]{1, -1, 0}, 0);
    }
}
