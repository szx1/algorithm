package leetcode.tree;

import leetcode.auxclass.TreeNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 二叉树的最近公共祖先
 *
 * @author zengxi.song
 * @date 2024/9/9
 */
public class TwoThreeSix {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 这种递归来自于leetCode评论区 这段代码假定p,q一定存在于树中 符合题意
        // 如果要兼容节点不存在的话 需要两个变量记录p或者q是否找到
        if (root == null || root == p || root == q) {
            return root;
        }

        // 递归查找左右子树
        TreeNode leftNode = lowestCommonAncestor(root.left, p, q);
        TreeNode rightNode = lowestCommonAncestor(root.right, p, q);

        // 如果 p 和 q 分别在左右子树中，当前节点就是最近的公共祖先
        if (leftNode != null && rightNode != null) {
            return root;
        }

        // 否则返回找到的非空子树（可能是 p 或 q，或者是它们的祖先）
        return leftNode != null ? leftNode : rightNode;
    }

    /**
     * 记忆化 避免重复计算  有点丑陋
     */
    private final Map<TreeNode, Boolean> pMap = new HashMap<>();
    private final Map<TreeNode, Boolean> qMap = new HashMap<>();

    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        TreeNode leftNode = lowestCommonAncestor3(root.left, p, q);
        if (leftNode != null) {
            return leftNode;
        }
        TreeNode rightNode = lowestCommonAncestor3(root.right, p, q);
        if (rightNode != null) {
            return rightNode;
        }
        if (canReach(root, p, true) && canReach(root, q, false)) {
            return root;
        }
        return null;
    }

    private boolean canReach(TreeNode root, TreeNode node, boolean p) {
        if (root == null) {
            return false;
        }
        Map<TreeNode, Boolean> memoMap = p ? pMap : qMap;
        if (memoMap.containsKey(root)) {
            return memoMap.get(root);
        }
        boolean res = root == node;
        if (canReach(root.left, node, p) || canReach(root.right, node, p)) {
            res = true;
        }
        memoMap.put(root, res);
        return res;
    }

    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        // 递归 存在大量的重复计算
        if (root == null) {
            return null;
        }
        TreeNode leftNode = lowestCommonAncestor2(root.left, p, q);
        if (leftNode != null) {
            return leftNode;
        }
        TreeNode rightNode = lowestCommonAncestor2(root.right, p, q);
        if (rightNode != null) {
            return rightNode;
        }
        if (canReach(root, p) && canReach(root, q)) {
            return root;
        }
        return null;
    }

    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        dfs(root, parentMap);
        Set<TreeNode> parents = new HashSet<>();
        while (p != null) {
            parents.add(p);
            p = parentMap.get(p);
        }
        while (q != null) {
            if (parents.contains(q)) {
                return q;
            }
            q = parentMap.get(q);
        }
        return root;
    }

    private void dfs(TreeNode root, Map<TreeNode, TreeNode> parentMap) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            parentMap.put(root.left, root);
            dfs(root.left, parentMap);
        }
        if (root.right != null) {
            parentMap.put(root.right, root);
            dfs(root.right, parentMap);
        }
    }

    private boolean canReach(TreeNode root, TreeNode node) {
        return root != null && (root == node || canReach(root.left, node) || canReach(root.right, node));
    }
}
