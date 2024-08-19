package leetcode.tree;

import leetcode.auxclass.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 合并二叉树
 *
 * @author zengxi.song
 * @date 2024/6/23
 */
public class SixOneSeven {

    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        // bfs 迭代 层序遍历 时间复杂度O(min(m,n)) 空间复杂度O(min(m,n))
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue1.add(root1);
        queue2.add(root2);
        TreeNode root = new TreeNode(root1.val + root2.val);
        queue.add(root);
        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            TreeNode node = queue.poll();
            TreeNode node1 = queue1.poll();
            TreeNode node2 = queue2.poll();
            if (node1.left == null) {
                node.left = node2.left;
            } else if (node2.left == null) {
                node.left = node1.left;
            } else {
                node.left = new TreeNode(node1.left.val + node2.left.val);
                queue.add(node.left);
                queue1.add(node1.left);
                queue2.add(node2.left);
            }


            if (node1.right == null) {
                node.right = node2.right;
            } else if (node2.right == null) {
                node.right = node1.right;
            } else {
                node.right = new TreeNode(node1.right.val + node2.right.val);
                queue.add(node.right);
                queue1.add(node1.right);
                queue2.add(node2.right);
            }
        }
        return root;
    }

    public TreeNode mergeTrees1(TreeNode root1, TreeNode root2) {
        // dfs 递归 时间复杂度 O(min(m,n)) 空间复杂度O(min(m,n))
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        TreeNode root = new TreeNode(root1.val + root2.val);
        root.left = mergeTrees1(root1.left, root2.left);
        root.right = mergeTrees1(root1.right, root2.right);
        return root;
    }

    public TreeNode mergeTrees2(TreeNode root1, TreeNode root2) {
        // dfs 递归 时间复杂度 O(min(m,n)) 空间复杂度O(min(m,n)) 使用root1作为返回的tree 减少点空间占用 缺点是改变原来的树的值
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        root1.val = root1.val + root2.val;
        root1.left = mergeTrees2(root1.left, root2.left);
        root1.right = mergeTrees2(root1.right, root2.right);
        return root1;
    }

}
