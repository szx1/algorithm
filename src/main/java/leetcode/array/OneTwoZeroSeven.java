package leetcode.array;

import java.util.HashMap;
import java.util.Map;

/**
 * 独一无二的出现次数
 *
 * @author zengxi.song
 * @date 2024/7/22
 */
public class OneTwoZeroSeven {

    public boolean uniqueOccurrences(int[] arr) {
        // 哈希表计数 时间复杂度O(N) 空间复杂度O(N)
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int item : arr) {
            countMap.merge(item, 1, Integer::sum);
        }
        return countMap.values().stream().distinct().count() == countMap.size();
    }
}
