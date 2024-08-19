package leetcode.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;

/**
 * 课程表Ⅱ
 *
 * @author zengxi.song
 * @date 2024/7/26
 */
public class TwoOneZero {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // 广度优先搜索 时间复杂度O(M+N) 空间复杂度O(M+N)
        Map<Integer, List<Integer>> map = new HashMap<>();
        int[] inDeg = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            map.computeIfAbsent(prerequisite[1], k -> new ArrayList<>()).add(prerequisite[0]);
            inDeg[prerequisite[0]]++;
        }
        int[] res = new int[numCourses];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDeg[i] == 0) {
                queue.add(i);
            }
        }
        int index = 0;
        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            res[index++] = poll;
            if (index == numCourses) {
                break;
            }
            Optional.ofNullable(map.get(poll)).ifPresent(list -> list.forEach(item -> {
                if (--inDeg[item] == 0) {
                    queue.add(item);
                }
            }));
        }
        return index == numCourses ? res : new int[0];
    }

    int index;

    public int[] findOrder1(int numCourses, int[][] prerequisites) {
        // 深度优先搜索 时间复杂度O(M+N) 空间复杂度O(M+N)
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] prerequisite : prerequisites) {
            map.computeIfAbsent(prerequisite[1], k -> new ArrayList<>()).add(prerequisite[0]);
        }
        int[] res = new int[numCourses];
        int[] visited = new int[numCourses];
        index = numCourses - 1;
        for (int i = 0; i < numCourses; i++) {
            if (visited[i] == 0) {
                if (!dfs(res, visited, map, i)) {
                    return new int[0];
                }
            }
        }
        return res;
    }

    private boolean dfs(int[] res, int[] visited, Map<Integer, List<Integer>> map, int item) {
        visited[item] = 1;
        for (Integer i : map.getOrDefault(item, new ArrayList<>())) {
            if (visited[i] == 1) {
                return false;
            }
            if (visited[i] == 0 && !dfs(res, visited, map, i)) {
                return false;
            }
        }
        res[index--] = item;
        visited[item] = 2;
        return true;
    }
}
