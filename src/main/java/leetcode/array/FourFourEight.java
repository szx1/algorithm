package leetcode.array;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 找到所有数组中消失的数字
 *
 * @author zengxi.song
 * @date 2024/9/11
 */
public class FourFourEight {

    public List<Integer> findDisappearedNumbers(int[] nums) {
        // 进阶 要求不适用额外空间 时间复杂度O(N) 空间复杂度O(1)
        // 结果list不算
        // 可以将数字对应下标转换为负数 对于没有变成负数的下标即为缺失的数字
        // 官方题解使用的是+n 原理一样 +n的话会溢出数字返回 要注意取模 和本题要注意abs一个道理
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            // 要兼容已经被其他搞成负数了
            int abs = Math.abs(nums[i]);
            if (nums[abs - 1] > 0) {
                nums[abs - 1] = -nums[abs - 1];
            }
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                res.add(i + 1);
            } else {
                // 回退
                nums[i] = -nums[i];
            }
        }
        return res;
    }

    public List<Integer> findDisappearedNumbers1(int[] nums) {
        // hash表 时间复杂度O(N) 空间复杂度O(N)
        int n = nums.length;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (!set.contains(i)) {
                res.add(i);
            }
        }
        return res;
    }
}
