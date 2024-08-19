package leetcode.tree;

import leetcode.auxclass.bt.Node;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 填充每个节点的下一个右侧节点指针
 *
 * @author zengxi.song
 * @date 2024/8/2
 */
public class OneOneSix {

    public Node connect(Node root) {
        // 层序遍历 使用next指针 时间复杂度O(N) 空间复杂度O(1)
        if (root == null) {
            return root;
        }
        // 从根节点开始
        Node leftmost = root;
        while (leftmost.left != null) {
            // 遍历这一层节点组织成的链表，为下一层的节点更新 next 指针
            Node head = leftmost;
            while (head != null) {
                head.left.next = head.right;
                if (head.next != null) {
                    head.right.next = head.next.left;
                }
                // 指针向后移动
                head = head.next;
            }
            // 去下一层的最左的节点
            leftmost = leftmost.left;
        }
        return root;
    }

    public Node connect1(Node root) {
        // 层序遍历 使用队列 时间复杂度O(N) 空间复杂度O(N) 不符合题目要求
        if (root == null) {
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node pre = null;
            for (int i = queue.size() - 1; i >= 0; i--) {
                Node poll = queue.poll();
                if (pre != null) {
                    pre.next = poll;
                }
                pre = poll;
                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
            }
        }
        return root;
    }
}
