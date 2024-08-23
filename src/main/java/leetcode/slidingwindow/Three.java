package leetcode.slidingwindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 无重复字符的最长子串
 *
 * @author zengxi.song
 * @date 2024/8/23
 */
public class Three {

    public int lengthOfLongestSubstring(String s) {
        // 使用Map维护滑动窗口 value维护下标 这样left指针就不用++然后移除set了
        Map<Character, Integer> map = new HashMap<>();
        int left = 0, right = 0;
        int res = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            Integer index = map.get(c);
            if (index != null) {
                // 如果此时该字符已经存在
                left = Math.max(left, index);
            }
            map.put(c, right);
            res = Math.max(res, right - left);

        }
        return res;
    }

    public int lengthOfLongestSubstring1(String s) {
        // 使用set维护滑动窗口
        Set<Character> set = new HashSet<>();
        int left = 0, right = 0;
        int res = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            while (left < right && set.contains(c)) {
                set.remove(s.charAt(left));
                left++;
            }
            res = Math.max(res, right - left + 1);
            set.add(s.charAt(right++));
        }
        return res;
    }
}
