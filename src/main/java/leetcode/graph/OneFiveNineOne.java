package leetcode.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 奇怪的打印机Ⅱ
 *
 * @author zengxi.song
 * @date 2024/10/16
 */
public class OneFiveNineOne {

    public boolean isPrintable(int[][] targetGrid) {
        // 首先遍历数组 找到所有颜色以及记录他们所处矩形的范围
        int m = targetGrid.length, n = targetGrid[0].length;
        Map<Integer, Combination> colorMap = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Combination combination = colorMap.get(targetGrid[i][j]);
                if (combination == null) {
                    combination = new Combination();
                    combination.i1 = combination.i2 = i;
                    combination.j1 = combination.j2 = j;
                } else {
                    if (combination.i1 > i) {
                        combination.i1 = i;
                    }
                    if (combination.i2 < i) {
                        combination.i2 = i;
                    }
                    if (combination.j1 > j) {
                        combination.j1 = j;
                    }
                    if (combination.j2 < j) {
                        combination.j2 = j;
                    }
                }
                colorMap.put(targetGrid[i][j], combination);
            }
        }
        // 然后遍历colorMap构建图
        Map<Integer, List<Integer>> graphMap = new HashMap<>();
        for (Map.Entry<Integer, Combination> entry : colorMap.entrySet()) {
            Combination value = entry.getValue();
            for (int i = value.i1; i <= value.i2; i++) {
                for (int j = value.j1; j <= value.j2; j++) {
                    if (targetGrid[i][j] != entry.getKey()) {
                        graphMap.computeIfAbsent(targetGrid[i][j], k -> new ArrayList<>()).add(entry.getKey());
                    }
                }
            }
        }
        // 检查图中有没有环 这里使用dfs
        boolean[] searched = new boolean[61];
        for (Map.Entry<Integer, List<Integer>> entry : graphMap.entrySet()) {
            if (searched[entry.getKey()]) {
                continue;
            }

            if (!dfs(graphMap, entry.getKey(), searched, new boolean[61])) {
                return false;
            }

        }
        return true;
    }

    private boolean dfs(Map<Integer, List<Integer>> graphMap, int color, boolean[] searched, boolean[] visited) {
        if (visited[color]) {
            return false;
        }
        visited[color] = true;
        for (Integer relation : graphMap.getOrDefault(color, new ArrayList<>())) {
            if (searched[relation]) {
                continue;
            }
            if (!dfs(graphMap, relation, searched, visited)) {
                return false;
            }
        }
        searched[color] = true;
        return true;
    }

    static class Combination {
        int i1, i2;
        int j1, j2;
    }

    public static void main(String[] args) {
//        System.out.println(new OneFiveNineOne().isPrintable(new int[][]{{6, 2, 2, 5}, {2, 2, 2, 5}, {2, 2, 2, 5}, {4, 3, 3, 4}}));
//        System.out.println(new OneFiveNineOne().isPrintable(new int[][]{{1, 1, 1, 1}, {1, 1, 3, 3}, {1, 1, 3, 4}, {5, 5, 1, 4}}));
        System.out.println(new OneFiveNineOne().isPrintable(new int[][]{{1, 1, 1}, {3, 1, 3}}));
    }
}
