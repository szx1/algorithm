package leetcode.tree.traversal;

import leetcode.auxclass.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 二叉树的前序遍历
 * 关于Morris遍历的博客 https://blog.csdn.net/u011386173/article/details/128438450
 *
 * @author zengxi.song
 * @date 2024/6/26
 */
public class OneFourFour {

    public List<Integer> preorderTraversal(TreeNode root) {
        // Morris遍历 利用空闲指针(左孩子最右节点的右指针)减少空间复杂度 时间复杂度O(n) 空间复杂度O(1)
        List<Integer> res = new ArrayList<>();
        TreeNode mostRight = null;
        TreeNode cur = root;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight == null) {
                // 没有左节点 则输出
                res.add(cur.val);
                cur = cur.right;
            } else {
                // 寻找当前左子树的最右节点 并将其right指向cur
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    res.add(cur.val);
                    mostRight.right = cur;
                    cur = cur.left;
                } else {
                    // 此时的cur其实是第二次访问了
                    // 这时候取right即可
                    cur = cur.right;
                    // 复原树
                    mostRight.right = null;
                }
            }
        }

        return res;
    }

    public List<Integer> preorderTraversal2(TreeNode root) {
        // 迭代 时间复杂度O(n) 空间复杂度O(n)
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            res.add(pop.val);
            if (root.right != null) {
                stack.push(root.right);
            }
            if (root.left != null) {
                stack.push(root.left);
            }
        }
        return res;
    }

    public List<Integer> preorderTraversal1(TreeNode root) {
        // 递归 时间复杂度O(n) 空间复杂度O(n)
        List<Integer> res = new ArrayList<>();
        preorder(root, res);
        return res;
    }

    private void preorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        preorder(root.left, res);
        preorder(root.right, res);
    }
}
