package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 任务调度器
 *
 * @author zengxi.song
 * @date 2024/4/24
 */
public class SixTwoOne {

    public int leastInterval(char[] tasks, int n) {
        // 找规律贪心 时间复杂度O(N) 空间复杂度O(M)  M是任务的种类 N是任务的个数
        // 首先计算最大运行次数
        Map<Character, Integer> countMap = new HashMap<>();
        int maxRun = 0;
        for (char ch : tasks) {
            maxRun = Math.max(countMap.merge(ch, 1, Integer::sum), maxRun);
        }
        // 因为存在最大运行次数的任务可能有多个 计算总数
        int maxRunTaskNum = 0;
        for (Map.Entry<Character, Integer> entry : countMap.entrySet()) {
            if (maxRun == entry.getValue()) {
                maxRunTaskNum++;
            }
        }
        // 计算公式  之所以需要和taskLength比较 可能存在n较小的情况且maxRun不足以覆盖其他所有任务的执行
        // 比如AABBCCC n 为1 公式的计算结果是 CACBC也就是4+1 此时只需要CABCABC即可 也就是说剩几个补充几个 因为这个间隔一定符合n的要求 最后就是tasks.length
        // 换而言之 公式的结果只保证两个maxRun任务之间的间隙可以放入其他所有的任务 如果放不下说明没有间隙 就是tasks.length即可
        return Math.max((maxRun - 1) * (n + 1) + maxRunTaskNum, tasks.length);
    }

    public int leastInterval4(char[] tasks, int n) {
        // 官方做法 时间复杂度O(MN) 空间复杂度O(M)  M是任务的种类 N是任务的个数 leetCode用时57ms
        Map<Character, Integer> countMap = new HashMap<>();
        // 统计所有任务的个数
        for (char ch : tasks) {
            countMap.merge(ch, 1, Integer::sum);
        }
        int m = countMap.size();
        // 使用两个list表示待运行次数和下一次有效时间
        // 下标无所谓 只要两个list能对应上即可
        List<Integer> nextValidTime = new ArrayList<>(m);
        List<Integer> taskRunNums = new ArrayList<>(m);
        for (Map.Entry<Character, Integer> entry : countMap.entrySet()) {
            // 将每一个运行时间初始化为1
            nextValidTime.add(1);
            taskRunNums.add(entry.getValue());
        }
        int time = 0;
        // 遍历所有任务
        for (int i = 0; i < tasks.length; i++) {
            time++;
            int minValid = Integer.MAX_VALUE;
            for (int j = 0; j < m; j++) {
                if (taskRunNums.get(j) > 0) {
                    minValid = Math.min(minValid, nextValidTime.get(j));
                }
            }
            // 最外围遍历的所有任务 所以这里的minValid一定会被更新的 不会出现MaxValue
            time = Math.max(time, minValid);
            // 然后选取冷却中&&剩余最大待运行次数的任务
            int max = Integer.MIN_VALUE;
            int maxIndex = 0;
            for (int j = 0; j < m; j++) {
                Integer cur = taskRunNums.get(j);
                if (cur > 0 && cur > max && nextValidTime.get(j) <= time) {
                    maxIndex = j;
                    max = cur;
                }
            }
            nextValidTime.set(maxIndex, time + n + 1);
            taskRunNums.set(maxIndex, taskRunNums.get(maxIndex) - 1);
        }
        return time;
    }

    public int leastInterval3(char[] tasks, int n) {
        Map<Character, Integer> countMap = new HashMap<>();
        for (char task : tasks) {
            countMap.merge(task, 1, Integer::sum);
        }
        List<Aux> list = new ArrayList<>();
        for (Map.Entry<Character, Integer> entry : countMap.entrySet()) {
            list.add(new Aux(entry.getValue(), 1));
        }
        int time = 0;
        while (true) {
            if (list.isEmpty()) {
                break;
            }
            // 相当于每次推进一个格子
            time++;
            // 避免time空转
            int minRunTime = Integer.MAX_VALUE;
            for (Aux pair : list) {
                minRunTime = Math.min(minRunTime, pair.nextRunTime);
            }
            // minRunTime可以保证小于MaxInt
            time = Math.max(time, minRunTime);
            // 找到运行次数最大且下一次运行时间有效的任务
            Aux candidate = null;
            for (Aux pair : list) {
                // 上面避免了空转 这里可以省略 为了表明思路 暂留
                if (pair.nextRunTime > time) {
                    continue;
                }
                if (candidate == null || pair.remainder >= candidate.remainder) {
                    candidate = pair;
                }
            }
            candidate.nextRunTime = time + n + 1;
            if (--candidate.remainder == 0) {
                list.remove(candidate);
            }
        }
        return time;
    }

    class Aux {
        int remainder;
        int nextRunTime;

        public Aux(int remainder, int nextRunTime) {
            this.remainder = remainder;
            this.nextRunTime = nextRunTime;
        }
    }

    public int leastInterval2(char[] tasks, int n) {
        // 利用PriorityQueue优先考虑需要运行次数最多的任务 leetCode时间707ms
        Map<Character, Integer> countMap = new HashMap<>();
        // 统计所有任务的个数
        for (char ch : tasks) {
            countMap.merge(ch, 1, Integer::sum);
        }
        int[] place = new int[26];
        // -1代表没有这个任务
        Arrays.fill(place, -1);
        PriorityQueue<Pair> priorityQueue = new PriorityQueue<>((o1, o2) -> o2.num - o1.num);
        for (Map.Entry<Character, Integer> entry : countMap.entrySet()) {
            priorityQueue.add(new Pair(entry.getKey(), entry.getValue()));
            place[entry.getKey() - 'A'] = 0;
        }

        int time = 0;
        while (!priorityQueue.isEmpty()) {
            time++;
            // 为了避免无关的遍历 遍历下所有的位置 记录最小有效的时间
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < place.length; i++) {
                if (place[i] > 0) {
                    place[i]--;
                }
                min = Math.min(min, place[i]);
            }
            if (min > 0) {
                time += min;
                for (int i = 0; i < place.length; i++) {
                    if (place[i] > 0) {
                        place[i] -= min;
                    }
                }
            }

            List<Pair> waitRun = new ArrayList<>();
            while (!priorityQueue.isEmpty()) {
                Pair poll = priorityQueue.poll();
                if (place[poll.task - 'A'] == 0) {
                    // n是间隔 这里设置为n+1
                    place[poll.task - 'A'] = n + 1;
                    poll.num--;
                    if (poll.num > 0) {
                        priorityQueue.add(poll);
                    }
                    break;
                } else {
                    waitRun.add(poll);
                }
            }
            for (Pair pair : waitRun) {
                if (pair.num > 0) {
                    priorityQueue.add(pair);
                }
            }
        }
        return time;
    }

    public int leastInterval1(char[] tasks, int n) {
        // 利用PriorityQueue优先考虑需要运行次数最多的任务 leetCode时间746ms
        Map<Character, Integer> countMap = new HashMap<>();
        // 统计所有任务的个数
        for (char ch : tasks) {
            countMap.merge(ch, 1, Integer::sum);
        }
        PriorityQueue<Pair> priorityQueue = new PriorityQueue<>((o1, o2) -> o2.num - o1.num);
        for (Map.Entry<Character, Integer> entry : countMap.entrySet()) {
            priorityQueue.add(new Pair(entry.getKey(), entry.getValue()));
        }
        int[] place = new int[26];
        int time = 0;
        while (!priorityQueue.isEmpty()) {
            time++;
            // 遍历下所有的位置 将间隔减一
            for (int i = 0; i < place.length; i++) {
                if (place[i] > 0) {
                    place[i]--;
                }
            }
            List<Pair> waitRun = new ArrayList<>();
            while (!priorityQueue.isEmpty()) {
                Pair poll = priorityQueue.poll();
                if (place[poll.task - 'A'] == 0) {
                    // n是间隔 这里设置为n+1
                    place[poll.task - 'A'] = n + 1;
                    poll.num--;
                    if (poll.num > 0) {
                        priorityQueue.add(poll);
                    }
                    break;
                } else {
                    waitRun.add(poll);
                }
            }
            for (Pair pair : waitRun) {
                if (pair.num > 0) {
                    priorityQueue.add(pair);
                }
            }
        }
        return time;
    }

    static class Pair {
        Character task;
        int num;

        public Pair(Character task, int num) {
            this.task = task;
            this.num = num;
        }
    }

    public static void main(String[] args) {
        new SixTwoOne().leastInterval(new char[]{'A', 'A', 'B'}, 2);
    }
}
