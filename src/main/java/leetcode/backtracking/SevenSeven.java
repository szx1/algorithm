package leetcode.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 组合
 *
 * @author zengxi.song
 * @date 2024/8/22
 */
public class SevenSeven {

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        backTracking(res, new LinkedList<>(), 0, n, k);
        return res;
    }

    private void backTracking(List<List<Integer>> res, LinkedList<Integer> subList, int index, int n, int k) {
        if (subList.size() == k) {
            res.add(new ArrayList<>(subList));
            return;
        }

        subList.add(index);
        for (int i = index; i <= n; i++) {
            backTracking(res, subList, index + 1, n, k);
        }
        subList.removeLast();
    }
}
