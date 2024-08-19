package leetcode.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 引爆最多的炸弹
 *
 * @author zengxi.song
 * @date 2024/7/22
 */
public class TwoOneZeroOne {

    public int maximumDetonation(int[][] bombs) {
        // 建立有向图(有没有环无所谓)+广度优先搜索 时间复杂度O(n^3) 空间复杂度O(n^2)
        int n = bombs.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && this.canBomb(bombs, i, j)) {
                    map.computeIfAbsent(i, k -> new ArrayList<>()).add(j);
                }
            }
        }
        // 遍历所有炸弹 计算最大值
        int res = 0;
        for (int i = 0; i < n; i++) {
            int count = 0;
            boolean[] visited = new boolean[n];
            Queue<Integer> queue = new LinkedList<>();
            queue.add(i);
            visited[i] = true;
            while (!queue.isEmpty()) {
                Integer poll = queue.poll();
                count++;
                for (Integer item : map.computeIfAbsent(poll, k -> new ArrayList<>())) {
                    if (!visited[item]) {
                        queue.add(item);
                        visited[item] = true;
                    }
                }
            }
            res = Math.max(res, count);
        }
        return res;
    }

    public int maximumDetonation1(int[][] bombs) {
        // 建立有向图(有没有环无所谓)+深度优先搜索 时间复杂度O(n^3) 空间复杂度O(n^2)
        int n = bombs.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && this.canBomb(bombs, i, j)) {
                    map.computeIfAbsent(i, k -> new ArrayList<>()).add(j);
                }
            }
        }
        // 遍历所有炸弹 计算最大值
        int res = 0;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, dfs(map, new boolean[n], i));
        }
        return res;
    }

    private int dfs(Map<Integer, List<Integer>> map, boolean[] visited, int i) {
        if (visited[i]) {
            return 0;
        }
        int count = 0;
        visited[i] = true;
        count++;
        for (Integer item : map.computeIfAbsent(i, k -> new ArrayList<>())) {
            if (!visited[item]) {
                count += dfs(map, visited, item);
            }
        }
        return count;
    }

    private boolean canBomb(int[][] bombs, int i, int j) {
        long x = Math.abs(bombs[i][0] - bombs[j][0]);
        long y = Math.abs(bombs[i][1] - bombs[j][1]);
        return (long) bombs[i][2] * bombs[i][2] >= x * x + y * y;
    }

    public static void main(String[] args) {
        new TwoOneZeroOne().maximumDetonation1(new int[][]{{2, 1, 3}, {6, 1, 4}});
    }
}
