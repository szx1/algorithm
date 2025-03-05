package leetcode.slidingwindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 找到字符串中所有字母异位词
 *
 * @author zengxi.song
 * @date 2024/4/12
 */
public class FourThreeEight {

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (s.length() < p.length()) {
            return res;
        }
        int[] count = new int[26];
        for (int i = 0; i < p.length(); i++) {
            count[s.charAt(i) - 'a']++;
            count[p.charAt(i) - 'a']--;
        }
        int diff = 0;
        for (int i : count) {
            if (i != 0) {
                diff++;
            }
        }
        if (diff == 0) {
            res.add(0);
        }
        for (int i = 1; i < s.length() - p.length() + 1; i++) {
            int index1 = s.charAt(i - 1) - 'a';
            if (count[index1] == 0) {
                diff++;
            } else if (count[index1] == 1) {
                diff--;
            }
            count[index1]--;

            int index2 = s.charAt(i + p.length() - 1) - 'a';
            if (count[index2] == 0) {
                diff++;
            } else if (count[index2] == -1) {
                diff--;
            }
            count[index2]++;
            if (diff == 0) {
                res.add(i);
            }
        }
        return res;
    }

    public List<Integer> findAnagrams3(String s, String p) {
        // 滑动窗口优化版
        List<Integer> res = new ArrayList<>();
        if (s.length() < p.length()) {
            return res;
        }
        int[] sArr = new int[26];
        int[] pArr = new int[26];
        for (int i = 0; i < p.length(); i++) {
            sArr[s.charAt(i) - 'a']++;
            pArr[p.charAt(i) - 'a']++;
        }
        int diff = 0;
        for (int i = 0; i < sArr.length; i++) {
            if (pArr[i] != sArr[i]) {
                diff++;
            }
        }
        if (diff == 0) {
            res.add(0);
        }
        for (int i = 1; i < s.length() - p.length() + 1; i++) {
            int right = i + p.length() - 1;

            int index1 = s.charAt(i - 1) - 'a';
            int index2 = s.charAt(right) - 'a';
            if (index1 == index2) {
                if (diff == 0) {
                    res.add(i);
                }
                continue;
            }
            if (sArr[index1] == pArr[index1]) {
                diff++;
            } else if (sArr[index1] == pArr[index1] + 1) {
                diff--;
            }
            if (sArr[index2] == pArr[index2]) {
                diff++;
            } else if (sArr[index2] == pArr[index2] - 1) {
                diff--;
            }
            sArr[index1]--;
            sArr[index2]++;

            if (diff == 0) {
                res.add(i);
            }
        }
        return res;
    }

    public List<Integer> findAnagrams2(String s, String p) {
        // 版本一会超时 所以要想办法动态维护窗口内的字母个数 滑动窗口维护 这里使用数组
        // 时间复杂度O(M+(N-M)*C) 空间复杂度O(C)
        // (N-M)*C是比较两个数组是否相同的复杂度
        List<Integer> res = new ArrayList<>();
        if (s.length() < p.length()) {
            return res;
        }
        int[] sArr = new int[26];
        int[] pArr = new int[26];
        for (int i = 0; i < p.length(); i++) {
            sArr[s.charAt(i) - 'a']++;
            pArr[p.charAt(i) - 'a']++;
        }
        if (Arrays.equals(sArr, pArr)) {
            res.add(0);
        }
        for (int i = 1; i < s.length() - p.length() + 1; i++) {
            int right = i + p.length() - 1;
            sArr[s.charAt(i - 1) - 'a']--;
            sArr[s.charAt(right) - 'a']++;
            if (Arrays.equals(sArr, pArr)) {
                res.add(i);
            }
        }
        return res;
    }


    public List<Integer> findAnagrams1(String s, String p) {
        // 这个会超时
        List<Integer> res = new ArrayList<>();
        Map<Character, Integer> pMap = new HashMap<>(p.length());
        for (int i = 0; i < p.length(); i++) {
            pMap.merge(p.charAt(i), 0, (oldV, newV) -> oldV + 1);
        }
        for (int i = 0; i < s.length() - p.length() + 1; i++) {
            String substring = s.substring(i, i + p.length());
            if (checkAnagram(substring, pMap)) {
                res.add(i);
            }
        }
        return res;
    }

    private boolean checkAnagram(String s1, Map<Character, Integer> pMap) {
        Map<Character, Integer> map1 = new HashMap<>(s1.length());
        for (int i = 0; i < s1.length(); i++) {
            map1.merge(s1.charAt(i), 0, (oldV, newV) -> oldV + 1);
        }
        for (Map.Entry<Character, Integer> entry : map1.entrySet()) {
            if (!Objects.equals(entry.getValue(), pMap.get(entry.getKey()))) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        FourThreeEight fourThreeEight = new FourThreeEight();
        fourThreeEight.findAnagrams("cbaebabacd", "abc");
    }
}
