package leetcode.math;

import java.util.ArrayList;
import java.util.List;

/**
 * 自除数
 *
 * @author zengxi.song
 * @date 2025/1/7
 */
public class SevenTwoEight {

    public List<Integer> selfDividingNumbers(int left, int right) {
        // 就很暴力 时间复杂度O(n*logright) 空间复杂度O(1) 其中n为right-left+1
        List<Integer> res = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (selfDividingNumber(i)) {
                res.add(i);
            }
        }
        return res;
    }

    private boolean selfDividingNumber(int num) {
        int originalNum = num;
        while (num > 0) {
            int remainder = num % 10;
            if (remainder == 0 || originalNum % remainder != 0) {
                return false;
            }
            num = num / 10;
        }
        return true;
    }
}
