package leetcode.tree;

import leetcode.auxclass.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树的所有路径
 *
 * @author zengxi.song
 * @date 2024/6/13
 */
public class TwoFiveSeven {

    public List<String> binaryTreePaths(TreeNode root) {
        // 广度优先搜索
        if (root == null) {
            return Collections.emptyList();
        }
        List<String> paths = new ArrayList<>();
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<StringBuilder> pathQueue = new LinkedList<>();
        nodeQueue.offer(root);
        pathQueue.offer(new StringBuilder(root.val));
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            StringBuilder path = pathQueue.poll();
            if (node.left == null && node.right == null) {
                paths.add(path.toString());
                continue;
            }
            if (node.left != null) {
                nodeQueue.add(node.left);
                pathQueue.add(new StringBuilder(path).append("->").append(node.left.val));
            }
            if (node.right != null) {
                nodeQueue.add(node.right);
                pathQueue.add(new StringBuilder(path).append("->").append(node.right.val));
            }

        }
        return paths;
    }

    public List<String> binaryTreePaths2(TreeNode root) {
        // 回溯 使用StringBuilder保存string
        if (root == null) {
            return Collections.emptyList();
        }
        List<String> res = new ArrayList<>();
        backTracking(res, new StringBuilder(), root);
        return res;
    }

    private void backTracking(List<String> res, StringBuilder sb, TreeNode node) {
        sb.append(node.val);
        if (node.left == null && node.right == null) {
            res.add(sb.toString());
            return;
        }
        int length = sb.length();
        if (node.left != null) {
            sb.append("->");
            backTracking(res, sb, node.left);
            // 相当于删除最后的节点
            sb.setLength(length);
        }
        if (node.right != null) {
            sb.append("->");
            backTracking(res, sb, node.right);
            // 相当于删除最后的节点
            sb.setLength(length);
        }
    }

    public List<String> binaryTreePaths1(TreeNode root) {
        // 回溯 使用list保存string
        if (root == null) {
            return Collections.emptyList();
        }
        List<String> res = new ArrayList<>();
        backTracking(res, new LinkedList<>(), root);
        return res;
    }

    private void backTracking(List<String> res, LinkedList<String> subList, TreeNode node) {
        subList.add(String.valueOf(node.val));
        if (node.left == null && node.right == null) {
            res.add(String.join("->", subList));
            return;
        }
        if (node.left != null) {
            backTracking(res, subList, node.left);
            subList.removeLast();
        }
        if (node.right != null) {
            backTracking(res, subList, node.right);
            subList.removeLast();
        }
    }
}
