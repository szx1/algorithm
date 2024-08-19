package leetcode.tree;

import leetcode.auxclass.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zengxi.song
 * @date 2024/7/29
 */
public class OneOneThree {

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        // 回溯 本质是深度优先搜索 时间复杂度O(N^2) 空间复杂度O(N)
        List<List<Integer>> res = new ArrayList<>();
        backTracking(res, new LinkedList<>(), root, 0, targetSum);
        return res;
    }

    private void backTracking(List<List<Integer>> res, LinkedList<Integer> subList, TreeNode node, int sum, int targetSum) {
        if (node == null) {
            return;
        }
        sum += node.val;
        subList.add(node.val);
        if (node.left == null && node.right == null) {
            if (sum == targetSum) {
                res.add(new ArrayList<>(subList));
            }
        }
        backTracking(res, subList, node.left, sum, targetSum);
        backTracking(res, subList, node.right, sum, targetSum);
        subList.removeLast();
    }
}
