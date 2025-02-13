package leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 插入区间
 *
 * @author zengxi.song
 * @date 2025/2/12
 */
public class FiveSeven {

    public int[][] insert(int[][] intervals, int[] newInterval) {
        // 本题可借鉴56题[合并区间] 题意中intervals无重叠且已经排好序了
        // 不需原地修改 时间复杂度O(N) 空间复杂度O(N)
        // 方法1借助一个多余的list进行存储 这里的空间可以优化 虽然也是O(N)
        List<int[]> res = new ArrayList<>();
        boolean insert = false;
        int left = newInterval[0];
        int right = newInterval[1];
        for (int[] interval : intervals) {
            if (interval[0] > right) {
                // 只有此时才可以add二者
                if (!insert) {
                    res.add(new int[]{left, right});
                    insert = true;
                }
                res.add(interval);
            } else if (interval[1] < left) {
                // 此时只add当前区间
                res.add(interval);
            } else {
                // 重合 先合并 然后保留不急着add
                left = Math.min(interval[0], left);
                right = Math.max(interval[1], right);
            }
        }
        if (!insert) {
            res.add(new int[]{left, right});
        }
        return res.toArray(new int[res.size()][]);
    }

    public int[][] insert1(int[][] intervals, int[] newInterval) {
        // 本题可借鉴56题[合并区间] 题意中intervals无重叠且已经排好序了
        // 不需原地修改 时间复杂度O(N) 空间复杂度O(N)
        // 先将newInterval插入list,接下来就是56题操作
        List<int[]> intervalList = new ArrayList<>();
        boolean insert = false;
        for (int[] interval : intervals) {
            if (insert) {
                intervalList.add(interval);
            } else {
                if (newInterval[0] < interval[0]) {
                    intervalList.add(newInterval);
                    intervalList.add(interval);
                    insert = true;
                } else {
                    intervalList.add(interval);
                }
            }
        }
        if (!insert) {
            intervalList.add(newInterval);
        }
        List<int[]> res = new ArrayList<>();
        for (int[] interval : intervalList) {
            if (res.isEmpty()) {
                res.add(interval);
                continue;
            }
            int[] arr = res.get(res.size() - 1);
            // 重合
            if (interval[0] <= arr[1]) {
                arr[1] = Math.max(interval[1], arr[1]);
            } else {
                res.add(interval);
            }
        }
        return res.toArray(new int[res.size()][]);
    }
}
