package leetcode.tree.traversal;

import leetcode.auxclass.TreeNode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * 二叉树的垂序遍历
 *
 * @author zengxi.song
 * @date 2024/7/4
 */
public class NineEightSeven {

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        // 使用map记录每一列的数据 时间复杂度O(NlogN) 空间复杂度O(N)
        Map<Integer, List<int[]>> resMap = new TreeMap<>();
        dfs(resMap, root, 0, 0);
        return resMap.values().stream().map(list -> {
            list.sort((o1, o2) -> o1[1] == o2[1] ? o1[0] - o2[0] : o1[1] - o2[1]);
            return list.stream().map(arr-> arr[0]).collect(Collectors.toList());
        }).collect(Collectors.toList());
    }

    private void dfs(Map<Integer, List<int[]>> resMap, TreeNode node, int column, int row) {
        if (node == null) {
            return;
        }
        resMap.computeIfAbsent(column, k -> new ArrayList<>()).add(new int[]{node.val, row});
        dfs(resMap, node.left, column - 1, row + 1);
        dfs(resMap, node.right, column + 1, row + 1);
    }
}
