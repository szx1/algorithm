package leetcode.bit;

/**
 * 位1的个数
 *
 * @author zengxi.song
 * @date 2024/9/11
 */
public class OneNineOne {

    public int hammingWeight(int n) {
        // 之前的算法可以优化 时间复杂度O(logN) 空间复杂度O(1)
        // 使n&n-1 每次运算可以将n的最低位的1变成0 运算次数即1的个数
        int count = 0;
        while (n != 0) {
            count++;
            n &= n - 1;
        }
        return count;
    }

    public int hammingWeight1(int n) {
        // 时间复杂度O(k) 空间复杂度O(1) 这里的k是最高1的位数
        int count = 0;
        while (n != 0) {
            if ((n & 1) == 1) {
                count++;
            }
            n >>>= 1;
        }
        return count;
    }
}
