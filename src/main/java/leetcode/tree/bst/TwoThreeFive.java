package leetcode.tree.bst;

import leetcode.auxclass.TreeNode;

/**
 * 二叉搜索树的最近公共祖先
 *
 * @author zengxi.song
 * @date 2025/1/10
 */
public class TwoThreeFive {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 利用二叉搜索树的性质 我们只要找到最深层的大于min 小于max的节点即为最近公共祖先
        // 可以利用二叉搜索树的性质对方法1进行剪枝
        int min = Math.min(p.val, q.val);
        int max = Math.max(p.val, q.val);
        return dfs(root, min, max);
    }

    private TreeNode dfs(TreeNode root, int min, int max) {
        if (root == null) {
            return null;
        }
        if (root.val > max) {
            return dfs(root.left, min, max);
        }
        if (root.val < min) {
            return dfs(root.right, min, max);
        }
        // root.val>min&&root.val<max
        return root;
    }


    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        // 这个方法没有利用到二叉搜索树的性质
        return dfs(root, p, q);
    }

    private TreeNode dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root == p || root == q) {
            return root;
        }
        TreeNode left = dfs(root.left, p, q);
        TreeNode right = dfs(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }
        return left == null ? right : left;
    }
}
