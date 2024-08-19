package leetcode.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * 课程表Ⅳ
 * 特别说明 实现的时候把前置条件看反了 不过将错就错 queries和prerequisites同时看反不影响结果
 *
 * @author zengxi.song
 * @date 2024/7/26
 */
public class OneFourSixTwo {

    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        // bfs 时间复杂度O(numCourses^2+m+n) 空间复杂度O(numCourses^2+n)
        Map<Integer, List<Integer>> map = new HashMap<>();
        int[] inDeg = new int[numCourses];
        // 使用数组
        boolean[][] isPre = new boolean[numCourses][numCourses];
        for (int[] prerequisite : prerequisites) {
            map.computeIfAbsent(prerequisite[1], k -> new ArrayList<>()).add(prerequisite[0]);
            inDeg[prerequisite[0]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDeg[i] == 0) {
                queue.add(i);
            }
        }
        List<Boolean> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            for (Integer item : map.getOrDefault(poll, new ArrayList<>())) {
                isPre[poll][item] = true;
                if (--inDeg[item] == 0) {
                    queue.add(item);
                }
                for (int i = 0; i < numCourses; i++) {
                    isPre[i][item] |= isPre[i][poll];
                }
            }
        }
        for (int[] query : queries) {
            res.add(isPre[query[1]][query[0]]);
        }
        return res;
    }

    private boolean dfs(Map<Integer, List<Integer>> map, boolean[][] isPre, boolean[] visited, int query, int pre) {
        if (visited[pre]) {
            return isPre[pre][query];
        }
        visited[pre] = true;
        for (Integer i : map.getOrDefault(pre, new ArrayList<>())) {
            isPre[pre][i] = true;
            dfs(map, isPre, visited, query, i);
            for (int j = 0; j < isPre.length; j++) {
                isPre[pre][j] |= isPre[i][j];
            }
        }
        return isPre[pre][query];
    }

    public List<Boolean> checkIfPrerequisite1(int numCourses, int[][] prerequisites, int[][] queries) {
        // 深度优先搜索(采用Map) 时间复杂度O(numCourses^2+m+n) 空间复杂度O(numCourses^2+n)
        Map<Integer, List<Integer>> map = new HashMap<>();
        // 官方使用的数组 效率更高
        Map<Integer, Set<Integer>> queryMap = new HashMap<>();
        for (int[] prerequisite : prerequisites) {
            map.computeIfAbsent(prerequisite[1], k -> new ArrayList<>()).add(prerequisite[0]);
            queryMap.computeIfAbsent(prerequisite[1], k -> new HashSet<>()).add(prerequisite[0]);
        }
        // 记录当前节点是否已经初始化queryMap 避免超时的关键
        boolean[] visited = new boolean[numCourses];
        List<Boolean> res = new ArrayList<>();
        for (int[] query : queries) {
            // 这里也可以选择提前初始化queryMap 即放到循环外面
            res.add(fillQueryMap(map, queryMap, queryMap.computeIfAbsent(query[1], k -> new HashSet<>()), visited, query[1]).contains(query[0]));
        }
        return res;
    }

    private Set<Integer> fillQueryMap(Map<Integer, List<Integer>> map, Map<Integer, Set<Integer>> queryMap, Set<Integer> set, boolean[] visited, int item) {
        // 避免超时的关键
        if (visited[item]) {
            return set;
        }
        visited[item] = true;
        for (Integer i : map.getOrDefault(item, new ArrayList<>())) {
            set.addAll(fillQueryMap(map, queryMap, queryMap.computeIfAbsent(i, k -> new HashSet<>()), visited, i));
        }
        return set;
    }

    public static void main(String[] args) {
        new OneFourSixTwo().checkIfPrerequisite(2,
                new int[][]{{1, 0}},
                new int[][]{{0, 1}, {1, 0}});
    }
}
