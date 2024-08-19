package leetcode.tree.bst;

import leetcode.auxclass.TreeNode;

/**
 * 删除二叉搜索树中的节点
 *
 * @author zengxi.song
 * @date 2024/7/4
 */
public class FourFiveZero {

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val == key) {
            return rebuild(root);
        }
        // 迭代 遍历树查找 然后删除 最坏时间复杂度O(n) 空间复杂度O(1)
        TreeNode pre = new TreeNode();
        boolean left = true;
        TreeNode point = root;
        while (point != null) {
            if (point.val > key) {
                pre = point;
                point = point.left;
                left = true;
            } else if (point.val < key) {
                pre = point;
                point = point.right;
                left = false;
            } else {
                if (left) {
                    pre.left = rebuild(point);
                } else {
                    pre.right = rebuild(point);
                }
                break;
            }
        }

        return root;
    }

    private TreeNode rebuild(TreeNode root) {
        if (root.left == null) {
            return root.right;
        }
        if (root.right == null) {
            return root.left;
        }
        // 找到最右侧最小的 将左子树直接挂上
        TreeNode point = root.right;
        while (point.left != null) {
            point = point.left;
        }
        point.left = root.left;
        return root.right;
    }

    public TreeNode deleteNode1(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val == key) {
            return rebuild(root);
        }
        // 递归 然后删除 最坏时间复杂度O(n) 空间复杂度O(n)

        if (root.val > key) {
            root.left = deleteNode1(root.left, key);
        } else {
            root.right = deleteNode1(root.right, key);
        }

        return root;
    }

}
