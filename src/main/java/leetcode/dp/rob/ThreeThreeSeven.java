package leetcode.dp.rob;

import leetcode.auxclass.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 打家劫舍Ⅲ
 *
 * @author zengxi.song
 * @date 2024/9/10
 */
public class ThreeThreeSeven {

    public int rob(TreeNode root) {
        // 树形动态规划 时间复杂度O(N) 空间复杂度O(N)
        // 相连的才会报警 从底向上递归(后序遍历) 使用map记录
        if (root == null) {
            return 0;
        }
        Map<TreeNode, Integer> selectMap = new HashMap<>();
        Map<TreeNode, Integer> nonSelectMap = new HashMap<>();
        dfs(root, selectMap, nonSelectMap);
        return Math.max(selectMap.get(root), nonSelectMap.get(root));
    }

    private void dfs(TreeNode root, Map<TreeNode, Integer> selectMap, Map<TreeNode, Integer> nonSelectMap) {
        if (root == null) {
            return;
        }
        dfs(root.left, selectMap, nonSelectMap);
        dfs(root.right, selectMap, nonSelectMap);
        selectMap.put(root, root.val + nonSelectMap.getOrDefault(root.left, 0) + nonSelectMap.getOrDefault(root.right, 0));
        nonSelectMap.put(root, Math.max(selectMap.getOrDefault(root.left, 0), nonSelectMap.getOrDefault(root.left, 0))
                + Math.max(selectMap.getOrDefault(root.right, 0), nonSelectMap.getOrDefault(root.right, 0)));
    }
}
