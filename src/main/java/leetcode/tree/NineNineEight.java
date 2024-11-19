package leetcode.tree;

import leetcode.auxclass.TreeNode;

/**
 * 最大二叉树Ⅱ
 *
 * @author zengxi.song
 * @date 2024/10/13
 */
public class NineNineEight {

    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        // 迭代 时间复杂度O(1) 空间复杂度O(1)
        if (root == null) {
            return new TreeNode(val);
        }
        TreeNode cur = root;
        TreeNode pre = null;
        while (cur != null) {
            if (cur.val < val) {
                TreeNode node = new TreeNode(val);
                node.left = cur;
                if (pre == null) {
                    // node作为新的根节点
                    root = node;
                } else {
                    pre.right = node;
                }
                return root;
            } else {
                pre = cur;
                cur = cur.right;
            }
        }
        // 到这里是未找到位置 则最终pre.right直接连接即可
        pre.right = new TreeNode(val);
        return root;
    }

    public TreeNode insertIntoMaxTree1(TreeNode root, int val) {
        // 该题就是找出val应该处于的位置 并返回最终构建完成的树
        // 由于val添加到最后，所以我们只需要遍历右子节点
        // 递归 时间复杂度O(N) 空间复杂度O(N)
        if (root == null) {
            return new TreeNode(val);
        }
        if (root.val < val) {
            TreeNode node = new TreeNode(val);
            node.left = root;
            return node;
        }
        root.right = insertIntoMaxTree1(root.right, val);
        return root;
    }
}
