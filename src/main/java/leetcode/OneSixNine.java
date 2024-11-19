package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 多数元素
 * 主要是学习摩尔投票法 其他方法都很简单
 *
 * @author zengxi.song
 * @date 2024/4/17
 */
public class OneSixNine {

    public int majorityElement(int[] nums) {
        // 摩尔投票法  时间复杂度O(n)  空间复杂度O(1)
        int res = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                res = nums[i];
            }
            count += (nums[i] == res) ? 1 : -1;
        }
        return res;
    }

    public int majorityElement2(int[] nums) {
        // 哈希表  时间复杂度O(n)  空间复杂度O(n)
        int target = nums.length / 2;
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            if (countMap.merge(num, 1, (oldV, newV) -> oldV + 1) > target) {
                return num;
            }
        }
        return nums[0];
    }

    public int majorityElement1(int[] nums) {
        // 排序 多数元素个数因为大于n/2 所以中间即为多数元素  时间复杂度O(nlogn) 空间复杂度O(1)
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    public static void main(String[] args) {
        OneSixNine oneSixNine = new OneSixNine();
        System.out.println(oneSixNine.majorityElement(new int[]{3, 4, 3, 3, 1}));
    }
}
