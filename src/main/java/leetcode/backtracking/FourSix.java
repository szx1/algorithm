package leetcode.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 全排列
 *
 * @author zengxi.song
 * @date 2024/8/22
 */
public class FourSix {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backTracking(res, new LinkedList<>(), nums, new boolean[nums.length]);
        return res;
    }

    private void backTracking(List<List<Integer>> res, LinkedList<Integer> subList, int[] nums, boolean[] visited) {
        if (subList.size() == nums.length) {
            res.add(new ArrayList<>(subList));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                subList.add(nums[i]);
                visited[i] = true;
                backTracking(res, subList, nums, visited);
                visited[i] = false;
                subList.removeLast();
            }
        }
    }
}
