package leetcode.graph;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 重新安排行程
 *
 * @author zengxi.song
 * @date 2024/8/9
 */
public class ThreeThreeTwo {

    public List<String> findItinerary(List<List<String>> tickets) {
        // dfs 改成treeMap就可以多最后一条用例了
        // 记录映射关系 value按照字典序排序
        Map<String, Map<String, Integer>> ticketMap = new HashMap<>();
        tickets.sort(Comparator.comparing(o -> o.get(1)));
        for (List<String> ticket : tickets) {
            ticketMap.computeIfAbsent(ticket.get(0), k -> new TreeMap<>()).merge(ticket.get(1), 1, Integer::sum);
        }
        final String start = "JFK";
        List<String> res = new ArrayList<>();
        res.add(start);
        dfs(ticketMap, start, res, tickets.size());
        return res;
    }

    private boolean dfs(Map<String, Map<String, Integer>> ticketMap, String item, List<String> res, int ticketsNum) {
        if (res.size() == ticketsNum + 1) {
            return true;
        }
        for (Map.Entry<String, Integer> entry : ticketMap.getOrDefault(item, new TreeMap<>()).entrySet()) {
            Integer count = entry.getValue();
            if (count > 0) {
                res.add(entry.getKey());
                entry.setValue(count - 1);
                boolean dfs = dfs(ticketMap, entry.getKey(), res, ticketsNum);
                if (dfs) {
                    return true;
                }
                res.remove(res.size() - 1);
                entry.setValue(count);
            }
        }
        return false;
    }

    public List<String> findItinerary1(List<List<String>> tickets) {
        // dfs 回溯思想 这套代码过不了最后一个用例 超时
        // 记录映射关系 value按照字典序排序
        Map<String, List<Target>> ticketMap = new HashMap<>();
        tickets.sort(Comparator.comparing(o -> o.get(1)));
        for (List<String> ticket : tickets) {
            ticketMap.computeIfAbsent(ticket.get(0), k -> new ArrayList<>()).add(new Target(ticket.get(1)));
        }
        final String start = "JFK";
        List<String> res = new ArrayList<>();
        res.add(start);
        dfs(ticketMap, start, res, tickets);
        return res;
    }

    private boolean dfs(Map<String, List<Target>> ticketMap, String item, List<String> res, List<List<String>> tickets) {
        if (res.size() == tickets.size() + 1) {
            return true;
        }
        for (Target target : ticketMap.getOrDefault(item, new ArrayList<>())) {
            if (!target.visited) {
                res.add(target.name);
                target.visited = true;
                boolean dfs = dfs(ticketMap, target.name, res, tickets);
                if (dfs) {
                    return true;
                }
                res.remove(res.size() - 1);
                target.visited = false;
            }
        }
        return false;
    }

    static class Target {
        String name;
        boolean visited;

        public Target(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) {
        List<List<String>> tickets = new ArrayList<>();
        tickets.add(Lists.newArrayList("EZE", "AXA"));
        tickets.add(Lists.newArrayList("TIA", "ANU"));
        tickets.add(Lists.newArrayList("ANU", "JFK"));
        tickets.add(Lists.newArrayList("JFK", "ANU"));
        tickets.add(Lists.newArrayList("ANU", "EZE"));
        tickets.add(Lists.newArrayList("TIA", "ANU"));
        tickets.add(Lists.newArrayList("AXA", "TIA"));
        tickets.add(Lists.newArrayList("TIA", "JFK"));
        tickets.add(Lists.newArrayList("ANU", "TIA"));
        tickets.add(Lists.newArrayList("JFK", "TIA"));
        new ThreeThreeTwo().findItinerary(tickets);
    }
}
