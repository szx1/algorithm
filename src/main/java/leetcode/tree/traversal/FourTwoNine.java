package leetcode.tree.traversal;

import leetcode.auxclass.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

/**
 * N叉树的层序遍历
 *
 * @author zengxi.song
 * @date 2024/6/26
 */
public class FourTwoNine {

    public List<List<Integer>> levelOrder(Node root) {
        // 利用队列进行层序遍历 时间复杂度O(n)  空间复杂度O(n)
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> subList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node poll = queue.poll();
                subList.add(poll.val);
                Optional.ofNullable(poll.children).ifPresent(queue::addAll);
            }
            res.add(subList);
        }
        return res;
    }
}
