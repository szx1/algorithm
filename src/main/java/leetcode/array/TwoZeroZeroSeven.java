package leetcode.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 从双倍数组中还原原数组
 *
 * @author zengxi.song
 * @date 2025/1/3
 */
public class TwoZeroZeroSeven {

    public int[] findOriginalArray(int[] changed) {
        // 排序加hash表记录次数 时间复杂度O(NlogN) 空间复杂度O(N)
        int n = changed.length;
        if ((n & 1) == 1) {
            return new int[0];
        }
        Arrays.sort(changed);
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : changed) {
            countMap.merge(num, 1, Integer::sum);
        }
        int[] res = new int[n / 2];
        int index = 0;
        for (int num : changed) {
            // 因为从大到小排序 所以当出现num的次数为0的时候说明num是2倍的结果 已经被比他小的消耗掉了
            if (countMap.get(num) == 0) {
                continue;
            }
            // 避免2*num和num相等(即num=0) 先将num的次数减去1
            countMap.put(num, countMap.get(num) - 1);
            // 进到这里说明num的原数组中的值 如果没有2倍的次数 则失败
            Integer count = countMap.get(2 * num);
            if (count == 0) {
                return new int[0];
            }
            countMap.put(2 * num, count - 1);
            res[index++] = num;
        }
        return res;
    }
}
