package leetcode.tree.bst;

import leetcode.auxclass.TreeNode;

/**
 * 前序遍历构造二叉搜索树
 *
 * @author zengxi.song
 * @date 2024/6/26
 */
public class OneZeroZeroEight {

    int index;

    public TreeNode bstFromPreorder(int[] preorder) {
        TreeNode root = new TreeNode(preorder[0]);
        root.left = buildLeft(preorder, 1, preorder[0]);
        root.right = buildRight(preorder, 1, preorder[0]);
        return root;
    }

    private TreeNode buildLeft(int[] preorder, int start, int val) {
        if (start > preorder.length - 1) {
            return null;
        }
        if (preorder[start] > val) {
            return null;
        }
        TreeNode node = new TreeNode(preorder[start]);
        node.left = buildLeft(preorder, start + 1, preorder[start]);
        node.right = buildRight(preorder, start + 1, preorder[start]);
        return node;
    }

    private TreeNode buildRight(int[] preorder, int start, int val) {
        if (start > preorder.length - 1) {
            return null;
        }
        int index = start - 1;
        while (index < preorder.length - 1) {
            if (preorder[++index] > val) {
                break;
            }
        }

        TreeNode node = new TreeNode(preorder[index]);
        node.left = buildLeft(preorder, index + 1, preorder[index]);
        node.right = buildRight(preorder, index + 1, preorder[index]);
        return node;
    }
}
