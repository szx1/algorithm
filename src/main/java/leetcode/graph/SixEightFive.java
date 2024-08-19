package leetcode.graph;

import leetcode.auxclass.graph.UnionFind;

import java.util.ArrayList;
import java.util.List;

/**
 * 冗余连接Ⅱ
 * 本题和684题最大的区别在于本题是有向图
 *
 * @author zengxi.song
 * @date 2024/8/8
 */
public class SixEightFive {

    public int[] findRedundantDirectedConnection(int[][] edges) {
        // 对于该题 有两种冗余的情况
        // 1.存在一个入度为2的节点 这种情况只需要判断出删除其中哪一条边即可
        // 2.图中存在环 如果存在环 则将后面的边给删除掉
        int n = edges.length;
        int[] inDeg = new int[n + 1];
        for (int[] edge : edges) {
            inDeg[edge[1]]++;
        }
        // 找到入度为2的边
        List<int[]> twoEdge = new ArrayList<>();
        for (int[] edge : edges) {
            if (inDeg[edge[1]] == 2) {
                twoEdge.add(edge);
            }
        }
        UnionFind uf = new UnionFind(n + 1);
        // 存在入度为2的节点
        if (twoEdge.size() > 0) {
            // 需要判断删除哪一条 我们可以尝试删除后面的那一条 然后判断是否可以构成树
            // 如果可以则返回 否则返回另外一条
            if (removeMoreEdge(edges, uf, twoEdge.get(1)[0])) {
                return twoEdge.get(1);
            } else {
                return twoEdge.get(0);
            }
        } else {
            // 这种情况就和684题差不多了 存在环 删除后面的即可
            return removeCycleEdge(edges, uf);
        }

    }

    private boolean removeMoreEdge(int[][] edges, UnionFind uf, int delEdgeSource) {
        uf.init();
        for (int[] edge : edges) {
            if (edge[0] == delEdgeSource) {
                continue;
            }
            // 出现环
            if (uf.connect(edge[0], edge[1])) {
                return false;
            }
            uf.union(edge[0], edge[1]);
        }
        return true;
    }

    private int[] removeCycleEdge(int[][] edges, UnionFind uf) {
        uf.init();
        for (int[] edge : edges) {
            if (uf.connect(edge[0], edge[1])) {
                return edge;
            }
            uf.union(edge[0], edge[1]);
        }
        return edges[edges.length - 1];
    }
}
