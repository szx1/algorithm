package leetcode.tree.traversal;

import leetcode.auxclass.TreeNode;

/**
 * 关于Morris遍历的博客 https://blog.csdn.net/u011386173/article/details/128438450
 *
 * @author zengxi.song
 * @date 2024/8/1
 */
public class Morris {

    private void morris(TreeNode root) {
        // 使用左子树的right空闲指针 可将空间复杂度减少为O(1)
        TreeNode cur = root;
        TreeNode mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight == null) {
                cur = cur.right;
            } else {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                } else {
                    // 复原树
                    mostRight.right = null;
                    cur = cur.right;
                }
            }
        }
    }
}
