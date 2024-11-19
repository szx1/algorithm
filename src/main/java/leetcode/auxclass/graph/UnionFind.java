package leetcode.auxclass.graph;

/**
 * 并查集的模板类
 * 并查集单次操作(Union和Find)的时间复杂度O(α(n)) a为反阿克曼函数 在n较大时也不会超过5可以认为近似于常数级
 * 可解决图论中无向图(部分双向边的有效图问题也可以)的连通性问题
 *
 * @author zengxi.song
 * @date 2024/8/8
 */
public class UnionFind {

    protected final int[] parent;
    protected final int[] rank;

    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public void init() {
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
    }

    public void union(int x, int y) {
        int rootx = find(x);
        int rooty = find(y);
        if (rootx != rooty) {
            if (rank[rootx] > rank[rooty]) {
                parent[rooty] = rootx;
            } else if (rank[rootx] < rank[rooty]) {
                parent[rootx] = rooty;
            } else {
                parent[rooty] = rootx;
                rank[rootx]++;
            }
        }
    }

    public int find(int item) {
        // 找root节点 只有root节点的值才是自己
        if (parent[item] != item) {
            parent[item] = find(parent[item]);
        }
        return parent[item];
    }

    public boolean connect(int x, int y) {
        return find(x) == find(y);
    }

    public int diffCount() {
        int count = 0;
        for (int i = 0; i < parent.length; i++) {
            if (parent[i] == i) {
                count++;
            }
        }
        return count;
    }
}
