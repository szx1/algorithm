package leetcode.array;

/**
 * 只出现一次的数字Ⅲ
 *
 * @author zengxi.song
 * @date 2024/6/26
 */
public class TwoSixZero {

    public int[] singleNumber(int[] nums) {
        // 时间复杂度O(n) 空间复杂度O(1)
        int xor = nums[0];
        for (int i = 1; i < nums.length; i++) {
            xor ^= nums[i];
        }
        // xor是两个只出现一次的数字异或后的结果 且不可能为0
        int num1 = 0, num2 = 0;
        // 通过最低位的1可以将nums分为两类，然后就可以单独找出唯一的数了
        int lowBit = Integer.lowestOneBit(xor);
        for (int num : nums) {
            if ((num & lowBit) == 0) {
                num1 ^= num;
            } else {
                num2 ^= num;
            }
        }
        return new int[]{num1, num2};
    }
}
