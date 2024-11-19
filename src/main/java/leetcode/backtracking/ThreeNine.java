package leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 组合总和
 *
 * @author zengxi.song
 * @date 2024/8/22
 */
public class ThreeNine {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        // 这里的排序是为了剪枝 不排序也不影响结果
        Arrays.sort(candidates);
        backTracking(res, new LinkedList<>(), candidates, target, 0, 0);
        return res;
    }

    private void backTracking(List<List<Integer>> res, LinkedList<Integer> subList, int[] candidates, int target, int sum, int index) {
        if (sum == target) {
            res.add(new ArrayList<>(subList));
            return;
        }
        // 不存在负数
        if (sum > target) {
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            subList.add(candidates[i]);
            backTracking(res, subList, candidates, target, sum + candidates[i], i);
            subList.removeLast();
        }
    }
}
