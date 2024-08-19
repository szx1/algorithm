package leetcode.tree.traversal;

import leetcode.auxclass.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 二叉树的后序遍历
 *
 * @author zengxi.song
 * @date 2024/6/26
 */
public class OneFourFive {

    public List<Integer> postorderTraversal(TreeNode root) {
        // 迭代 使用常量记录是否访问过
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        TreeNode pre = null;
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (!stack.isEmpty() || root != null) {
            // 后序遍历依然是先到达最深左节点
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            TreeNode peek = stack.peek();
            if (peek.right != null && peek.right != pre) {
                root = peek.right;
            } else {
                TreeNode pop = stack.pop();
                res.add(pop.val);
                pre = pop;
            }
        }
        return res;
    }

    public List<Integer> postorderTraversal2(TreeNode root) {
        // 迭代 使用set记录是否访问过
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Set<TreeNode> visited = new HashSet<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (!stack.isEmpty() || root != null) {
            // 后序遍历依然是先到达最深左节点
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            TreeNode peek = stack.peek();
            if (peek.right != null && !visited.contains(peek.right)) {
                root = peek.right;
            } else {
                TreeNode pop = stack.pop();
                res.add(pop.val);
                visited.add(pop);
            }
        }
        return res;
    }

    public List<Integer> postorderTraversal1(TreeNode root) {
        // 递归
        List<Integer> res = new ArrayList<>();
        postorder(root, res);
        return res;
    }

    private void postorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        postorder(root.left, res);
        postorder(root.right, res);
        res.add(root.val);
    }
}
