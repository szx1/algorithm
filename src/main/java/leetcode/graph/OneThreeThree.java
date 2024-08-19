package leetcode.graph;

import leetcode.auxclass.graph.Node;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 克隆图
 *
 * @author zengxi.song
 * @date 2024/8/2
 */
public class OneThreeThree {

    Map<Integer, Node> visitedMap = new HashMap<>();

    public Node cloneGraph(Node node) {
        // bfs 不使用computeIfAbsent 这个相比较get put在leetCode上慢
        if (node == null) {
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        visitedMap.put(node.val, new Node(node.val));
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            for (Node neighbor : poll.neighbors) {
                if (!visitedMap.containsKey(neighbor.val)) {
                    queue.add(neighbor);
                    visitedMap.put(neighbor.val, new Node(neighbor.val));
                }
                visitedMap.get(poll.val).neighbors.add(visitedMap.get(neighbor.val));
            }
        }
        return visitedMap.get(node.val);
    }

    public Node cloneGraph2(Node node) {
        // bfs 时间复杂度O(N) 空间复杂度O(N)
        if (node == null) {
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            Node newNode = visitedMap.computeIfAbsent(poll.val, k -> new Node(poll.val));
            for (Node neighbor : poll.neighbors) {
                if (!visitedMap.containsKey(neighbor.val)) {
                    queue.add(neighbor);
                }
                newNode.neighbors.add(visitedMap.computeIfAbsent(neighbor.val, k -> new Node(neighbor.val)));
            }
        }
        return visitedMap.get(node.val);
    }

    public Node cloneGraph1(Node node) {
        // dfs 时间复杂度O(N) 空间复杂度O(N)
        if (node == null) {
            return null;
        }
        if (visitedMap.containsKey(node.val)) {
            return visitedMap.get(node.val);
        }
        Node res = new Node(node.val);
        visitedMap.put(res.val, res);
        for (Node neighbor : node.neighbors) {
            res.neighbors.add(cloneGraph1(neighbor));
        }
        return res;
    }
}
