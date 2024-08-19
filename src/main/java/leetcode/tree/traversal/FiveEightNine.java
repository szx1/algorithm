package leetcode.tree.traversal;

import leetcode.auxclass.Node;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * N叉树的前序遍历
 *
 * @author zengxi.song
 * @date 2024/6/26
 */
public class FiveEightNine {

    public List<Integer> preorder(Node root) {
        // 迭代 利用栈
        // 时间复杂度O(n) 空间复杂度O(n)
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<Node> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node pop = stack.pop();
            res.add(pop.val);
            List<Node> children = pop.children;
            if (children != null) {
                // LIFO 从右边开始入栈
                for (int i = children.size() - 1; i >= 0; i--) {
                    stack.push(children.get(i));
                }
            }
        }
        return res;
    }

    public List<Integer> preorder1(Node root) {
        // 递归 n叉树和二叉树的区别只在于子树的分支
        // 时间复杂度O(n) 空间复杂度O(n)
        List<Integer> res = new ArrayList<>();
        preorder(root, res);
        return res;
    }

    private void preorder(Node root, List<Integer> res) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        if (root.children != null) {
            for (Node child : root.children) {
                preorder(child, res);
            }
        }
    }
}
