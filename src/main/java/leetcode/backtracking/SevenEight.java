package leetcode.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 子集
 *
 * @author zengxi.song
 * @date 2024/8/23
 */
public class SevenEight {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backTracking(res, new LinkedList<>(), nums, 0);
        return res;
    }

    private void backTracking(List<List<Integer>> res, LinkedList<Integer> subList, int[] nums, int index) {
        res.add(new ArrayList<>(subList));
        for (int i = index; i < nums.length; i++) {
            subList.add(nums[i]);
            backTracking(res, subList, nums, i + 1);
            subList.removeLast();
        }
    }
}
