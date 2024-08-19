package leetcode.array;

/**
 * 只出现一次的数字
 *
 * @author zengxi.song
 * @date 2024/6/26
 */
public class OneThreeSix {

    public int singleNumber(int[] nums) {
        // 因为其他数字都出现了两次 所以异或运算为0
        // 时间复杂度O(n) 空间复杂度O(1)
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            res ^= nums[i];
        }
        return res;
    }
}
