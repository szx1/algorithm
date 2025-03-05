package leetcode.tree;

import leetcode.auxclass.TreeNode;

/**
 * 二叉树的直径
 *
 * @author zengxi.song
 * @date 2024/8/2
 */
public class FiveFourThree {

    private int res = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return res;
    }

    /**
     * 该方法返回以node为根节点的二叉树的深度
     *
     * @param node
     * @return
     */
    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = dfs(node.left);
        int right = dfs(node.right);
        // 深度加起来就是经过node的边数
        res = Math.max(res, left + right);
        // 深度为较大值加1
        return Math.max(left, right) + 1;
    }
}
