package leetcode.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 非递减子序列
 *
 * @author zengxi.song
 * @date 2024/8/23
 */
public class FourNineOne {

    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backTracking(res, new LinkedList<>(), nums, 0);
        return res;
    }

    private void backTracking(List<List<Integer>> res, LinkedList<Integer> subList, int[] nums, int index) {
        if (subList.size() >= 2) {
            res.add(new ArrayList<>(subList));
        }
        // 同一层不能选择相同的数字 题意数字取值[-100,100]可使用数组优化
        boolean[] visited = new boolean[201];
        for (int i = index; i < nums.length; i++) {
            if (visited[nums[i] + 100]) {
                continue;
            }
            if (subList.size() > 0 && nums[i] < subList.getLast()) {
                continue;
            }
            visited[nums[i] + 100] = true;
            subList.add(nums[i]);
            backTracking(res, subList, nums, i + 1);
            subList.removeLast();
        }
    }
}
