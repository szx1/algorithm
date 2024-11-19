package leetcode.array.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 合并区间
 *
 * @author zengxi.song
 * @date 2024/9/3
 */
public class FiveSix {

    public int[][] merge(int[][] intervals) {
        // 排序 时间复杂度O(NlogN) 空间复杂度O(logN)
        if (intervals.length <= 1) {
            return intervals;
        }
        List<int[]> res = new ArrayList<>();
        // 按照start进行排序
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));

        for (int[] interval : intervals) {
            if (res.isEmpty()) {
                res.add(new int[]{interval[0], interval[1]});
            } else {
                int[] arr = res.get(res.size() - 1);
                if (interval[0] <= arr[1]) {
                    arr[1] = Math.max(interval[1], arr[1]);
                } else {
                    res.add(new int[]{interval[0], interval[1]});
                }
            }
        }
        return res.toArray(new int[res.size()][]);
    }
}
