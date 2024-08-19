package leetcode.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;

/**
 * 课程表
 *
 * @author zengxi.song
 * @date 2024/7/25
 */
public class TwoZeroSeven {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 广度优先搜索 时间复杂度O(M+N) 空间复杂度O(M+N)
        Map<Integer, List<Integer>> map = new HashMap<>();
        // 保存节点入度
        int[] inDeg = new int[numCourses];
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
        int visited = 0;
        while (!queue.isEmpty()) {
            visited++;
            Integer poll = queue.poll();
            Optional.ofNullable(map.get(poll)).ifPresent(list -> list.forEach(item -> {
                if (--inDeg[item] == 0) {
                    queue.add(item);
                }
            }));
        }
        return visited == numCourses;
    }

    public boolean canFinish1(int numCourses, int[][] prerequisites) {
        // 深度优先搜索 时间复杂度O(M+N) 空间复杂度O(M+N)
        // 保存节点入度
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] prerequisite : prerequisites) {
            map.computeIfAbsent(prerequisite[1], k -> new ArrayList<>()).add(prerequisite[0]);
        }
        Set<Integer> searched = new HashSet<>();
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            if (!searched.contains(entry.getKey())) {
                boolean[] visited = new boolean[numCourses];
                if (!dfs(searched, visited, map, entry.getKey())) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean dfs(Set<Integer> searched, boolean[] visited, Map<Integer, List<Integer>> map, int item) {
        visited[item] = true;
        for (Integer i : map.getOrDefault(item, new ArrayList<>())) {
            if (searched.contains(i)) {
                continue;
            }
            if (visited[i] || !dfs(searched, visited, map, i)) {
                return false;
            }
        }
        // 如果该节点没有环 则加入已搜索队列
        searched.add(item);
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new TwoZeroSeven().canFinish(2, new int[][]{{1, 0}}));
        System.out.println(new TwoZeroSeven().canFinish(5, new int[][]{{1, 4}, {2, 4}, {3, 1}, {3, 2}}));
    }
}
