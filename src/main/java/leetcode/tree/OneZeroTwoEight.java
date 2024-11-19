package leetcode.tree;

import leetcode.auxclass.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 从先序遍历还原二叉树
 *
 * @author zengxi.song
 * @date 2024/10/13
 */
public class OneZeroTwoEight {

    private int index = 0;

    public TreeNode recoverFromPreorder(String traversal) {
        // 先将字符串表示的树结构拆解
        List<Node> nodes = new ArrayList<>();
        int num = 0, depth = 0;
        for (int i = 0; i < traversal.length(); i++) {
            char c = traversal.charAt(i);
            if (c == '-') {
                if (traversal.charAt(i - 1) != '-') {
                    nodes.add(new Node(depth, num));
                    // 初始化值
                    depth = 1;
                    num = 0;
                } else {
                    depth++;
                }
            } else {
                num = num * 10 + (c - '0');
            }
        }
        if (num != 0) {
            nodes.add(new Node(depth, num));
        }
        return dfs(-1, nodes);
    }

    private TreeNode dfs(int depth, List<Node> nodes) {
        if (index >= nodes.size()) {
            return null;
        }
        Node node = nodes.get(index);
        if (node.depth <= depth) {
            return null;
        }
        index++;
        TreeNode treeNode = new TreeNode(node.val);
        treeNode.left = dfs(node.depth, nodes);
        treeNode.right = dfs(node.depth, nodes);
        return treeNode;
    }

    static class Node {
        private int depth;
        private int val;

        public Node(int depth, int val) {
            this.depth = depth;
            this.val = val;
        }
    }

    public static void main(String[] args) {
        new OneZeroTwoEight().recoverFromPreorder("1-2--3--4-5--6--7");
    }
}
