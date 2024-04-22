package leetcode;

import util.ArrayUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * 前k个高频元素
 *
 * @author zengxi.song
 * @date 2024/4/12
 */
public class ThreeFourSeven {

    private static final Random RANDOM = new Random();

    public int[] topKFrequent(int[] nums, int k) {
        int[] res = new int[k];
        Map<Integer, Integer> countMap = this.getCountMap(nums);
        int[] aux = new int[countMap.size()];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            aux[index++] = entry.getKey();
        }
        // 借助快速排序的性质 最优时间复杂度O(n)
        quickSort(aux, 0, aux.length - 1, res, 0, k, countMap);
        return res;
    }


    public int[] topKFrequent4(int[] nums, int k) {
        // 时间复杂度 O(n)
        Map<Integer, Integer> map = this.getCountMap(nums);
        int[] res = new int[k];
        // 桶排序
        List<List<Integer>> list = new ArrayList<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            list.add(new ArrayList<>());
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            list.get(entry.getValue() - 1).add(entry.getKey());
        }
        int index = 0;
        for (int i = list.size() - 1; i >= 0 && index < k; i--) {
            for (Integer num : list.get(i)) {
                res[index++] = num;
            }
        }
        return res;
    }

    public int[] topKFrequent3(int[] nums, int k) {
        // 维护k个元素的最小堆 自己维护堆 时间复杂度O(nlogk)
        Map<Integer, Integer> map = this.getCountMap(nums);
        int[] heap = new int[k];
        int size = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (size < k) {
                heap[size++] = entry.getKey();
                heapifyUp(heap, size - 1, Comparator.comparingInt(o -> map.getOrDefault(heap[o], 0)));
            } else if (map.getOrDefault(heap[0], 0) < entry.getValue()) {
                heap[0] = entry.getKey();
                heapifyDown(heap, 0, Comparator.comparingInt(o -> map.getOrDefault(heap[o], 0)));
            }
        }
        return heap;
    }

    public int[] topKFrequent2(int[] nums, int k) {
        // 维护k个元素的最小堆 采用PriorityQueue 时间复杂度O(nlogk)
        Map<Integer, Integer> map = this.getCountMap(nums);
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(a -> map.getOrDefault(a, 0)));
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (pq.size() < k) {
                pq.add(entry.getKey());
            } else if (entry.getValue() > map.get(pq.peek())) {
                pq.remove();
                pq.add(entry.getKey());
            }
        }
        return pq.stream().mapToInt(Integer::intValue).toArray();
    }


    public int[] topKFrequent1(int[] nums, int k) {
        // 全量排序 时间复杂度O(nlogn) 题目要求优于O(nlogn)
        Map<Integer, Integer> map = this.getCountMap(nums);
        List<Integer> list = map.keySet().stream()
                .sorted((o1, o2) -> map.getOrDefault(o2, 0) - map.getOrDefault(o1, 0))
                .collect(Collectors.toList());
        return list.subList(0, k).stream().mapToInt(Integer::intValue).toArray();
    }

    private Map<Integer, Integer> getCountMap(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.merge(num, 1, (oldV, newV) -> oldV + 1);
        }
        return map;
    }

    private void heapifyUp(int[] nums, int index, Comparator<Integer> comparator) {
        int parent = (index - 1) / 2;
        while (parent >= 0 && comparator.compare(parent, index) > 0) {
            ArrayUtil.swap(nums, index, parent);
            index = parent;
            parent = (index - 1) / 2;
        }
    }

    private void heapifyDown(int[] nums, int index, Comparator<Integer> comparator) {
        int smallIndex = index;
        int left = 2 * index + 1;
        int right = 2 * index + 2;

        if (left < nums.length && comparator.compare(smallIndex, left) > 0) {
            smallIndex = left;
        }

        if (right < nums.length && comparator.compare(smallIndex, right) > 0) {
            smallIndex = right;
        }

        if (smallIndex != index) {
            ArrayUtil.swap(nums, smallIndex, index);
            heapifyDown(nums, smallIndex, comparator);
        }
    }

    private void quickSort(int[] nums, int start, int end, int[] res, int resIndex, int k, Map<Integer, Integer> countMap) {
        if (start > end) {
            return;
        }
        int randomIndex = RANDOM.nextInt(end - start + 1) + start;
        ArrayUtil.swap(nums, start, randomIndex);
        int key = nums[start], keyCount = countMap.get(key);
        int x = start, y = end;
        while (x < y) {
            while (x < y && countMap.get(nums[y]) >= keyCount) {
                y--;
            }
            nums[x] = nums[y];
            while (x < y && countMap.get(nums[x]) <= keyCount) {
                x++;
            }
            nums[y] = nums[x];
        }
        nums[x] = key;
        if (k <= end - x) {
            // 所有的元素都在右侧
            quickSort(nums, x + 1, end, res, resIndex, k, countMap);
        } else {
            // 所有右侧的元素加上左边k-end+x-1个
            for (int i = x; i <= end; i++) {
                res[resIndex++] = nums[i];
            }
            quickSort(nums, start, y - 1, res, resIndex, k - end + x - 1, countMap);
        }
    }

    public static void main(String[] args) {
        ThreeFourSeven threeFourSeven = new ThreeFourSeven();
        int[] nums = {1, 1, 1, 2, 2, 3};
        threeFourSeven.topKFrequent(nums, 2);
    }
}
