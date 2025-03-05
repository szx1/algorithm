package leetcode.slidingwindow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 串联所有单词的子串
 *
 * @author zengxi.song
 * @date 2025/2/18
 */
public class ThreeZero {

    public List<Integer> findSubstring(String s, String[] words) {
        // 滑动窗口 类似于438题 一个是字母一个是字符串
        int m = words.length, n = s.length(), len = words[0].length();
        if (n < m * len) {
            return Collections.emptyList();
        }
        // 对于每一个s的下标 都做类似于438题的操作
        List<Integer> res = new ArrayList<>();
        // 外层循环的i<len是最关键的 否则后续会有很多无效遍历 容易超时 这里很容易想通 对于i>=len的下标在内层循环都已经遍历过了
        // i<=n - (m * len) 也不要忘掉 如果长度已经小于了 继续遍历下去就没有意义了
        for (int i = 0; i < len && i <= n - (m * len); i++) {
            Map<String, Integer> countMap = new HashMap<>(m);
            for (int j = 0; j < m; j++) {
                countMap.merge(words[j], 1, Integer::sum);
                countMap.merge(s.substring(i + j * len, i + (j + 1) * len), -1, Integer::sum);
            }
            int diff = 0;
            for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
                if (entry.getValue() != 0) {
                    diff++;
                }
            }
            if (diff == 0) {
                res.add(i);
            }
            // s从下标i+len开始
            for (int j = i + len; j <= n - (m * len); j += len) {
                // 处理滑动窗口滑过的字符串
                String preStr = s.substring(j - len, j);
                Integer preCount = countMap.getOrDefault(preStr, 0);
                if (preCount == 0) {
                    diff++;
                } else if (preCount == -1) {
                    diff--;
                }
                countMap.put(preStr, preCount + 1);

                // 处理滑动窗口新添加的字符串
                int end = m * len + j;
                String newStr = s.substring(end - len, end);
                Integer newCount = countMap.getOrDefault(newStr, 0);
                if (newCount == 0) {
                    diff++;
                } else if (newCount == 1) {
                    diff--;
                }
                countMap.put(newStr, newCount - 1);

                if (diff == 0) {
                    res.add(j);
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(new ThreeZero().findSubstring("barfoothefoobarman", new String[]{"foo", "bar"}));
    }
}
