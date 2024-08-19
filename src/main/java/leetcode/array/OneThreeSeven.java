package leetcode.array;

/**
 * 只出现一次的数字Ⅱ
 *
 * @author zengxi.song
 * @date 2024/6/26
 */
public class OneThreeSeven {

    public int singleNumber(int[] nums) {
        // 其余数字出现了3次 对于二进制的每一位来说 其和除以3的余数一定是目标数该位的值 我们需要遍历32位
        // 时间复杂度O(32n) 空间复杂度O(1)
        int res = 0;
        for (int i = 0; i < 32; i++) {
            int total = 0;
            for (int num : nums) {
                total += ((num >> i) & 1);
            }
            res |= ((total % 3) << i);
        }
        return res;
    }
}
