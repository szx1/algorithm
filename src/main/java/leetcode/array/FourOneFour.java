package leetcode.array;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * 第三大的数
 *
 * @author zengxi.song
 * @date 2024/7/11
 */
public class FourOneFour {

    public int thirdMax(int[] nums) {
        // 一次遍历 3个变量记录 时间复杂度O(N) 空间复杂度O(1)
        long first = Long.MIN_VALUE, second = Long.MIN_VALUE, third = Long.MIN_VALUE;
        for (int num : nums) {
            if (num > first) {
                third = second;
                second = first;
                first = num;
            } else if (num < first && num > second) {
                third = second;
                second = num;
            } else if (num < second && num > third) {
                third = num;
            }
        }
        return third == Long.MIN_VALUE ? (int) first : (int) third;
    }

    public int thirdMax2(int[] nums) {
        // 使用TreeSet记录3个有序数组 时间复杂度O(N) 空间复杂度O(N)
        TreeSet<Integer> set = new TreeSet<>();
        for (int num : nums) {
            set.add(num);
            if (set.size() > 3) {
                set.remove(set.first());
            }
        }
        return set.size() == 3 ? set.first() : set.last();
    }

    public int thirdMax1(int[] nums) {
        // 排序 然后遍历 时间复杂度O(N*lonN) 空间复杂度O(1)
        Arrays.sort(nums);
        int res = nums[nums.length - 1], max = res;
        boolean first = true;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] != max && first) {
                max = nums[i];
                first = false;
            }
            if (res != nums[i] && max != nums[i]) {
                return nums[i];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        new FourOneFour().thirdMax(new int[]{2, 2, 3, 1});
    }
}
