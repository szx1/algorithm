package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 任务调度器
 *
 * @author zengxi.song
 * @date 2024/4/24
 */
public class SixTwoOne {

    public int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> countMap = new HashMap<>();
        // 统计所有任务的个数
        for (char ch : tasks) {
            countMap.merge(ch, 1, Integer::sum);
        }

        List<Character> list = new ArrayList<>(countMap.keySet());
        list.sort(Comparator.comparingInt(countMap::get));
        return 1;

    }

    public int leastInterval1(char[] tasks, int n) {
        Map<Character, Integer> countMap = new HashMap<>();
        // 统计所有任务的个数
        for (char ch : tasks) {
            countMap.merge(ch, 1, Integer::sum);
        }
        // 任务种类数
        int m = countMap.size();
        List<Integer> nextValid = new ArrayList<>();
        List<Integer> rest = new ArrayList<>();
        for (Map.Entry<Character, Integer> entry : countMap.entrySet()) {
            nextValid.add(1);
            rest.add(entry.getValue());
        }
        int time = 0;
        for (int i = 0; i < tasks.length; ++i) {
            ++time;
            int minNextValid = Integer.MAX_VALUE;
            for (int j = 0; j < m; ++j) {
                if (rest.get(j) != 0) {
                    minNextValid = Math.min(minNextValid, nextValid.get(j));
                }
            }
            time = Math.max(time, minNextValid);
            int best = -1;
            for (int j = 0; j < m; ++j) {
                if (rest.get(j) != 0 && nextValid.get(j) <= time) {
                    if (best == -1 || rest.get(j) > rest.get(best)) {
                        best = j;
                    }
                }
            }
            nextValid.set(best, time + n + 1);
            rest.set(best, rest.get(best) - 1);
        }
        return time;
    }
}
