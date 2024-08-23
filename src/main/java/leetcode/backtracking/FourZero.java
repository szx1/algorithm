package leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 组合总和Ⅱ
 *
 * @author zengxi.song
 * @date 2024/8/22
 */
public class FourZero {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        return res;
    }

    private void backTracking(List<List<Integer>> res, LinkedList<Integer> subList, int sum, int target, int[] candidates, int index, boolean[] visited) {
        if (sum == target) {
            res.add(new ArrayList<>(subList));
            return;
        }
        // 没有负数
        if (sum > target) {
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            if (sum + candidates[i] > target) {
                break;
            }
            if (visited[i]) {
                continue;
            }
            // 只允许出现一次
            if (i > 1 && candidates[i] == candidates[i - 1] && !visited[i - 1]) {
                continue;
            }

            subList.add(candidates[i]);
            visited[i] = true;
            backTracking(res, subList, sum + candidates[i], target, candidates, i + 1, visited);
            visited[i] = false;
            subList.removeLast();

        }
    }
}
