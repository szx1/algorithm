package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 字母异位词分组
 *
 * @author zengxi.song
 * @date 2024/4/12
 */
public class FourNine {

    public List<List<String>> groupAnagrams(String[] strs) {
        // 方法2的优化版 时间复杂度O(n(k+m)) 空间复杂度 O(n(m+k)) 其中n为strs的长度 k为数组中字符串的最大长度 m为26，即字母表的大小
        // 通过map来记录 key为通过字母次数拼接的String
        Map<String, List<String>> auxMap = new HashMap<>();
        StringBuilder key = new StringBuilder();
        for (String str : strs) {
            int[] countArr = new int[26];
            for (int i = 0; i < str.length(); i++) {
                countArr[str.charAt(i) - 'a']++;
            }
            key.setLength(0);
            for (int i = 0; i < countArr.length; i++) {
                if (countArr[i] > 0) {
                    key.append(i + 'a');
                    key.append(countArr[i]);
                }
            }
            auxMap.computeIfAbsent(key.toString(), k -> new ArrayList<>()).add(str);
        }
        return new ArrayList<>(auxMap.values());
    }

    public List<List<String>> groupAnagrams3(String[] strs) {
        // 时间复杂度剧高 O(n(k+nm)) 空间复杂度 O(n(m+k)) 其中n为strs的长度 k为数组中字符串的最大长度 m为26，即字母表的大小
        List<List<Aux>> res = new ArrayList<>();
        for (String str : strs) {
            int[] countArr = new int[26];
            for (int i = 0; i < str.length(); i++) {
                countArr[str.charAt(i) - 'a']++;
            }
            boolean insert = false;
            for (List<Aux> re : res) {
                if (Arrays.equals(re.get(0).countArr, countArr)) {
                    insert = true;
                    re.add(new Aux(countArr, str));
                }
            }
            if (!insert) {
                res.add(new ArrayList<>());
                res.get(res.size() - 1).add(new Aux(countArr, str));
            }
        }
        return res.stream().map(re -> re.stream().map(aux -> aux.str).collect(Collectors.toList())).collect(Collectors.toList());
    }

    public static class Aux {

        int[] countArr;
        String str;

        public Aux(int[] countArr, String str) {
            this.countArr = countArr;
            this.str = str;
        }
    }

    public List<List<String>> groupAnagrams2(String[] strs) {
        // 和方法一同理 使用stream
        // 排序后然后hash 因为字母异位词排序后相同
        // 时间复杂度是O(nklogk) 空间复杂度O(nk) n是strs的长度 k为数组中字符串的最大长度
        return new ArrayList<>(Arrays.stream(strs).collect(Collectors.groupingBy(str -> {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            return Arrays.toString(chars);
        })).values());
    }

    public List<List<String>> groupAnagrams1(String[] strs) {
        // 排序后然后hash 因为字母异位词排序后相同
        // 时间复杂度是O(nklogk) 空间复杂度O(nk) n是strs的长度 k为数组中字符串的最大长度
        Map<String, List<String>> auxMap = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            auxMap.computeIfAbsent(new String(chars), k -> new ArrayList<>()).add(str);
        }
        return new ArrayList<>(auxMap.values());
    }

}
