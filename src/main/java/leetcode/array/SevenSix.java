package leetcode.array;

import java.util.HashMap;
import java.util.Map;

/**
 * 最小覆盖子串
 *
 * @author zengxi.song
 * @date 2024/7/8
 */
public class SevenSix {

    public String minWindow(String s, String t) {
        // 滑动窗口加双指针优化 时间复杂度O(m+n) 空间复杂度O(E) E为t中不重复字符的个数
        // 使用常量判断二者差距 减少了循环判断是否包含的时间复杂度
        Map<Character, Integer> sCountMap = new HashMap<>();
        Map<Character, Integer> tCountMap = new HashMap<>();
        int diff = 0;
        for (int i = 0; i < t.length(); i++) {
            tCountMap.merge(t.charAt(i), 1, Integer::sum);
        }
        // 记录有多少个字符
        diff = tCountMap.size();
        int left = 0, right = 0;
        int start = 0, end = s.length() + 1;
        while (right < s.length()) {
            char c = s.charAt(right++);
            Integer tCount = tCountMap.get(c);
            if (tCount == null) {
                continue;
            }
            if (sCountMap.merge(c, 1, Integer::sum).equals(tCount)) {
                diff--;
            }
            while (diff == 0) {
                if (right - left < end - start) {
                    start = left;
                    end = right;
                }
                char c1 = s.charAt(left++);
                Integer sCount = sCountMap.get(c1);
                if (sCount == null) {
                    continue;
                }
                // 因为马上就要-1 所以进行++
                if (sCount.equals(tCountMap.get(c1))) {
                    diff++;
                }
                sCountMap.put(c1, sCount - 1);
            }
        }
        return end - start > s.length() ? "" : s.substring(start, end);
    }

    public String minWindow1(String s, String t) {
        // 滑动窗口加双指针 时间复杂度O(Em+n) 空间复杂度O(E) E为t中不重复字符的个数
        Map<Character, Integer> sCountMap = new HashMap<>();
        Map<Character, Integer> tCountMap = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            tCountMap.merge(t.charAt(i), 1, Integer::sum);
        }
        int left = 0, right = 0;
        int start = 0, end = s.length() + 1;
        while (right < s.length()) {
            sCountMap.merge(s.charAt(right++), 1, Integer::sum);
            while (this.contains(sCountMap, tCountMap)) {
                if (right - left < end - start) {
                    start = left;
                    end = right;
                }
                sCountMap.merge(s.charAt(left++), 1, (o, n) -> o - n);
            }
        }
        return end - start > s.length() ? "" : s.substring(start, end);
    }

    private boolean contains(Map<Character, Integer> countMap, Map<Character, Integer> tCountMap) {
        for (Map.Entry<Character, Integer> entry : tCountMap.entrySet()) {
            if (countMap.getOrDefault(entry.getKey(), 0) < entry.getValue()) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        new SevenSix().minWindow("ADOBECODEBANC", "ABC");
        new SevenSix().minWindow("a", "aa");
    }
}
