package leetcode.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 全排列Ⅱ
 *
 * @author zengxi.song
 * @date 2024/8/23
 */
public class FourSeven {

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backTracking(res, new LinkedList<>(), nums, new boolean[nums.length]);
        return res;
    }

    private void backTracking(List<List<Integer>> res, LinkedList<Integer> subList, int[] nums, boolean[] visited) {
        if (subList.size() == nums.length) {
            res.add(new ArrayList<>(subList));
            return;
        }

        // 同一层不能选取重复元素 题意取值[-10,10]
        boolean[] unique = new boolean[21];
        for (int i = 0; i < nums.length; i++) {
            if (visited[i] || unique[nums[i] + 10]) {
                continue;
            }
            unique[nums[i] + 10] = true;
            subList.add(nums[i]);
            visited[i] = true;
            backTracking(res, subList, nums, visited);
            visited[i] = false;
            subList.removeLast();
        }
    }
}
