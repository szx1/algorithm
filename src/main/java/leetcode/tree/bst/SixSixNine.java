package leetcode.tree.bst;

import leetcode.auxclass.TreeNode;

/**
 * 修剪二叉搜索树
 *
 * @author zengxi.song
 * @date 2024/7/4
 */
public class SixSixNine {

    public TreeNode trimBST(TreeNode root, int low, int high) {
        // 迭代 时间复杂度O(n) 空间复杂度O(1)
        // 先迭代找到根节点
        while (root != null && (root.val < low || root.val > high)) {
            if (root.val < low) {
                root = root.right;
            } else {
                root = root.left;
            }
        }
        if (root == null) {
            return null;
        }
        // 遍历调整子节点
        TreeNode leftNode = root;
        // 遍历直至叶子节点
        while (leftNode.left != null) {
            if (leftNode.left.val < low) {
                leftNode.left = leftNode.left.right;
            } else {
                leftNode = leftNode.left;
            }
        }
        TreeNode rightNode = root;
        while (rightNode.right != null) {
            if (rightNode.right.val > high) {
                rightNode.right = rightNode.right.left;
            } else {
                rightNode = rightNode.right;
            }
        }
        return root;
    }


    public TreeNode trimBST2(TreeNode root, int low, int high) {
        if (root == null) {
            return null;
        }
        // 递归 时间复杂度O(n) 空间复杂度O(n)
        if (root.val < low) {
            return trimBST(root.right, low, high);
        }
        if (root.val > high) {
            return trimBST(root.left, low, high);
        }
        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);
        return root;
    }

    public TreeNode trimBST1(TreeNode root, int low, int high) {
        if (root == null) {
            return null;
        }
        // 这里可以直接用递归来代替
        // 时间复杂度O(n) 空间复杂度O(n)
        if (root.val > high) {
            TreeNode point = root;
            while (point != null) {
                if (point.val <= high) {
                    return trimBST1(point, low, high);
                }
                point = point.left;
            }
            return null;
        }
        if (root.val < low) {
            TreeNode point = root;
            while (point != null) {
                if (point.val >= low) {
                    return trimBST1(point, low, high);
                }
                point = point.right;
            }
            return null;
        }
        // root的值在范围内 可以直接返回
        root.left = trimBST1(root.left, low, high);
        root.right = trimBST1(root.right, low, high);

        return root;
    }
}
