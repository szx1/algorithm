package leetcode.tree;

import leetcode.auxclass.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * 从前序与中序遍历序列构造二叉树
 *
 * @author zengxi.song
 * @date 2024/6/14
 */
public class OneZeroFive {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 迭代
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        // 前序遍历的第一个节点即为root
        TreeNode root = new TreeNode(preorder[0]);
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        int inorderIndex = 0;
        for (int i = 1; i < preorder.length; i++) {
            TreeNode node = stack.peek();
            // 说明左子树还存在元素 即此时的preorder的下标i为左子树
            if (node.val != inorder[inorderIndex]) {
                TreeNode left = new TreeNode(preorder[i]);
                node.left = left;
                stack.push(left);
            } else {
                // 说明此时没有左子树 我们就要检查是否存在右子树
                // 我们一直将inorderIndex++ 直到出现不等于的值 说明此时i为当前节点的右子树
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                    node = stack.pop();
                    inorderIndex++;
                }
                // 此时i就是node的右子树
                node.right = new TreeNode(preorder[i]);
                stack.push(node.right);
            }
        }
        return root;
    }

    private int preIndex;

    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        // 递归 buildTree直接返回node
        Map<Integer, Integer> indexMap = new HashMap<>(inorder.length);
        for (int i = 0; i < inorder.length; i++) {
            indexMap.put(inorder[i], i);
        }
        preIndex = 0;
        return buildTree2(preorder, 0, inorder.length - 1, indexMap);
    }

    public TreeNode buildTree2(int[] preorder, int inLeft, int inRight, Map<Integer, Integer> indexMap) {
        if (inLeft > inRight) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preIndex]);
        Integer index = indexMap.get(preorder[preIndex]);

        preIndex++;
        root.left = buildTree2(preorder, inLeft, index - 1, indexMap);
        root.right = buildTree2(preorder, index + 1, inRight, indexMap);
        return root;
    }

    public TreeNode buildTree1(int[] preorder, int[] inorder) {
        // 递归 buildTree用于构建结构 不返回节点
        // 前序:中左右 中序:左中右
        // 前序的第一个节点即为root
        TreeNode root = new TreeNode(preorder[0]);
        Map<Integer, Integer> indexMap = new HashMap<>(inorder.length);
        for (int i = 0; i < inorder.length; i++) {
            indexMap.put(inorder[i], i);
        }
        preIndex = 0;
        buildTree1(root, preorder, 0, inorder.length - 1, indexMap);
        return root;

    }

    public void buildTree1(TreeNode root, int[] preorder, int inLeft, int inRight, Map<Integer, Integer> indexMap) {
        if (inLeft > inRight) {
            return;
        }
        Integer index = indexMap.get(root.val);

        preIndex++;
        if (index > inLeft) {
            TreeNode node = new TreeNode(preorder[preIndex]);
            root.left = node;
            buildTree1(node, preorder, inLeft, index - 1, indexMap);
        }

        if (index < inRight) {
            TreeNode node = new TreeNode(preorder[preIndex]);
            root.right = node;
            buildTree1(node, preorder, index + 1, inRight, indexMap);
        }
    }
}
