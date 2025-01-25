package leetcode.string.kmp;

/**
 * 找出字符串中第一个匹配项的下标
 *
 * @author zengxi.song
 * @date 2025/1/15
 */
public class TwoEight {

    public int strStr(String haystack, String needle) {
        // KMP算法 时间复杂度O(M+N) 空间复杂度O(N)
        int m = haystack.length(), n = needle.length();
        // 首先创建next数组 next[i]表示i不匹配时应该回退到的下标
        int[] next = new int[n];
        next[0] = -1;
        // j表示前缀的结尾下标 i表示后缀的结尾下标
        int i = 0, j = -1;
        while (i < n - 1) {
            if (j == -1 || needle.charAt(i) == needle.charAt(j)) {
                // next[i]表示i不匹配时应该回退到的下标 所以这里同时对i和j进行自增
                i++;
                j++;
                next[i] = j;
            } else {
                j = next[j];
            }
        }
        j = 0;
        for (i = 0; i <= m; i++) {
            while (j != -1 && haystack.charAt(i) != needle.charAt(j)) {
                j = next[j];
            }
            j++;
            if (j == n) {
                return i - j + 1;
            }
        }
        return -1;
    }

    public int strStr1(String haystack, String needle) {
        // 暴力匹配 时间复杂度O(MN) 空间复杂度O(1)
        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            if (haystack.charAt(i) == needle.charAt(0)) {
                boolean match = true;
                for (int j = 1; j < needle.length(); j++) {
                    if (haystack.charAt(i + j) != needle.charAt(j)) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new TwoEight().strStr("aababaf", "abaf"));
    }
}
