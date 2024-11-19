package leetcode.bit;

/**
 * 汉明距离
 * <br> 直接看191题吧
 *
 * @author zengxi.song
 * @date 2024/9/11
 */
public class FourSixOne {

    public int hammingDistance(int x, int y) {
        return bitCount(x^y);
    }

    private int bitCount(int i) {
        int res = 0;
        while (i > 0) {
            i = i & (i - 1);
            res++;
        }
        return res;
    }

    public int hammingDistance1(int x, int y) {
        // 其实等价于求两个数异或后的1的个数
        return Integer.bitCount(x ^ y);
    }
}
