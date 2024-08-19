package leetcode.tree.traversal;

import leetcode.auxclass.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 二叉树的中序遍历
 *
 * @author zengxi.song
 * @date 2024/6/26
 */
public class NineFour {

    public List<Integer> inorderTraversal(TreeNode root) {
        // Morris
        List<Integer> res = new ArrayList<>();
        TreeNode cur = root;
        TreeNode mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight == null) {
                res.add(cur.val);
                cur = cur.right;
            } else {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                // 中序遍历需要在第二次访问到cur的时候再输出
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                } else {
                    res.add(cur.val);
                    mostRight.right = null;
                    cur = cur.right;
                }
            }
        }
        return res;
    }

    public List<Integer> inorderTraversal2(TreeNode root) {
        // 迭代
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                // 先把所有左节点入栈
                root = root.left;
            }
            TreeNode pop = stack.pop();
            res.add(pop.val);
            // 添加右节点
            root = pop.right;
        }
        return res;
    }

    public List<Integer> inorderTraversal1(TreeNode root) {
        // 递归
        List<Integer> res = new ArrayList<>();
        inorder(root, res);
        return res;
    }

    private void inorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        inorder(root.left, res);
        res.add(root.val);
        inorder(root.right, res);
    }
}
