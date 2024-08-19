package leetcode.tree.traversal;

import leetcode.auxclass.Node;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * N叉树的后序遍历
 *
 * @author zengxi.song
 * @date 2024/6/26
 */
public class FiveNineZero {

    public List<Integer> postorder(Node root) {
        // 迭代再次优化 只使用常量记录当前节点是否访问过
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<Node> stack = new ArrayDeque<>();
        stack.push(root);
        Node pre = null;
        while (!stack.isEmpty()) {
            Node peek = stack.peek();
            // 上一个记录的节点如果是子树的最右节点 则也记录
            if (peek.children == null || peek.children.isEmpty() || pre == peek.children.get(peek.children.size() - 1)) {
                Node pop = stack.pop();
                res.add(pop.val);
                pre = pop;
                continue;
            }
            for (int i = peek.children.size() - 1; i >= 0; i--) {
                stack.push(peek.children.get(i));
            }
        }
        return res;
    }

    public List<Integer> postorder3(Node root) {
        // 迭代优化 只记录当前节点是否访问过
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<Node> stack = new ArrayDeque<>();
        Set<Node> visited = new HashSet<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node peek = stack.peek();
            // 只记录当前节点是否访问过
            if (peek.children == null || peek.children.isEmpty() || visited.contains(peek)) {
                Node pop = stack.pop();
                res.add(pop.val);
                continue;
            }
            for (int i = peek.children.size() - 1; i >= 0; i--) {
                stack.push(peek.children.get(i));
            }
            visited.add(peek);
        }
        return res;
    }

    public List<Integer> postorder2(Node root) {
        // 迭代 判断所有子节点是否记录过
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<Node> stack = new ArrayDeque<>();
        Set<Node> visited = new HashSet<>();
        stack.push(root);
        visited.add(root);
        while (!stack.isEmpty()) {
            Node peek = stack.peek();
            // 判断所有子节点是否记录过
            if (peek.children != null && !peek.children.isEmpty() && !visited.containsAll(peek.children)) {
                for (int i = peek.children.size() - 1; i >= 0; i--) {
                    if (visited.add(peek.children.get(i))) {
                        stack.push(peek.children.get(i));
                    }
                }
            } else {
                Node pop = stack.pop();
                res.add(pop.val);
            }
        }
        return res;
    }

    public List<Integer> postorder1(Node root) {
        // 递归
        List<Integer> res = new ArrayList<>();
        postorder(root, res);
        return res;
    }

    private void postorder(Node root, List<Integer> res) {
        if (root == null) {
            return;
        }
        Optional.ofNullable(root.children).ifPresent(children -> {
            children.forEach(node -> postorder(node, res));
        });
        res.add(root.val);
    }
}
