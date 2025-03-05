package leetcode.auxclass;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author zengxi.song
 * @date 2024/7/12
 */
@NoArgsConstructor
@AllArgsConstructor
public class ListNode {

    public int val;
    public ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    public boolean isMatch(String s, String p) {
        return recursion(s.toCharArray(), p.toCharArray(), 0, 0, new Boolean[s.length() + 1][p.length() + 1]);
    }

    private boolean recursion(char[] charS, char[] charP, int i, int j, Boolean[][] memo) {
        if (memo[i][j] != null) {
            return memo[i][j];
        }
        if (i >= charS.length) {
            if (j >= charP.length) {
                return memo[i][j] = true;
            }
            // 要求p剩余全部是 a*a*a*这种
            // 奇数直接false
            if (((charP.length - j) & 1) == 1) {
                return memo[i][j] = false;
            }
            int tmp = j;
            j++;
            for (; j < charP.length; j += 2) {
                if (charP[j] != '*') {
                    while (tmp <= j) {
                        memo[i][tmp++] = false;
                    }
                    return false;
                }
            }
            return true;
        }
        if (j >= charP.length) {
            return memo[i][j] = false;
        }
        if (charP[j] == '.' || charS[i] == charP[j]) {
            return memo[i][j] = recursion(charS, charP, i + 1, j + 1, memo);
        }
        if (charP[j] == '*') {
            if (recursion(charS, charP, i, j + 1, memo)) {
                return memo[i][j] = true;
            }
            char pre = charP[j - 1];
            for (int x = i + 1; x <= charS.length && (pre == '.' || charS[x - 1] == pre); x++) {
                if (recursion(charS, charP, x, j + 1, memo)) {
                    return memo[i][j] = true;
                }
            }
        }
        if (j < charP.length - 1) {
            // j和i没匹配上 判断下一个字符是否为* 如果是说明还有一线生机
            if (charP[j + 1] == '*') {
                return recursion(charS, charP, i, j + 2, memo);
            } else {
                // 否则这个字符可以直接中断匹配了
                return memo[i][j] = false;
            }
        }
        return memo[i][j] = false;
    }

    public static void main(String[] args) {
        System.out.println(new ListNode().isMatch("bbbba", ".*a*a"));
    }

}
