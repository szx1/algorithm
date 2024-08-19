package leetcode.auxclass.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zengxi.song
 * @date 2024/8/2
 */
public class Node {

    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int val) {
        this.val = val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int val, ArrayList<Node> neighbors) {
        this.val = val;
        this.neighbors = neighbors;
    }
}
