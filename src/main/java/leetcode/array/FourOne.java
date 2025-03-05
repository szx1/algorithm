package leetcode.array;

import java.util.HashSet;
import java.util.Set;

/**
 * 缺失的第一个正数
 *
 * @author zengxi.song
 * @date 2025/2/18
 */
public class FourOne {

    public int firstMissingPositive(int[] nums) {
        // 题目要求很高 时间复杂度O(N) 空间复杂度O(1)
        // 官方题解2 置换数字 将处于[1,n]的数字置于i-1的位置 然后再次遍历
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            // 如果置换的数字依然处于[1,n]范围中 要一直换下去
            // 可能死循环 终止条件就是nums[nums[i-1]]==nums[i]
            while (nums[i] >= 1 && nums[i] <= n && nums[i] != nums[nums[i] - 1]) {
                int tmp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = tmp;
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return n + 1;
    }

    public int firstMissingPositive2(int[] nums) {
        // 题目要求很高 时间复杂度O(N) 空间复杂度O(1)
        // 考虑到常数级空间复杂度 则只能在输入数组nums上做文章了
        int n = nums.length;
        // 可以确保最终答案res<=n+1 nums数组足够用了
        // 如果只有正数则这题很简单 只需要将nums[Math.abs(nums[i])]映射为负数 然后遍历找到第一个非负数下标即可
        // 该题元素非正数 所以我们需要进行特殊处理
        for (int i = 0; i < n; i++) {
            if (nums[i] <= 0) {
                nums[i] = n + 1;
            }
        }
        // 只对小于n+1的数做特殊处理
        for (int i = 0; i < n; i++) {
            int abs = Math.abs(nums[i]);
            if (abs < n + 1) {
                nums[abs - 1] = -Math.abs(nums[abs - 1]);
            }
        }
        // 然后找到第一个非负数
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return n + 1;
    }

    public int firstMissingPositive1(int[] nums) {
        // 不符合题意 时间复杂度O(N) 空间复杂度O(N)
        int max = 0;
        Set<Integer> sets = new HashSet<>();
        for (int num : nums) {
            max = Math.max(num, max);
            sets.add(num);
        }
        int res = max + 1;
        for (int i = 1; i < max; i++) {
            if (!sets.contains(i)) {
                res = i;
                break;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new FourOne().firstMissingPositive(new int[]{1, 2, 0}));
    }
}
