package leetcode.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * 排列序列
 *
 * @author zengxi.song
 * @date 2025/2/14
 */
public class SixZero {

    private int[] auxArr;
    private final List<Integer> auxList = new ArrayList<>();

    public String getPermutation(int n, int k) {
        if (n == 1) {
            return "1";
        }
        auxArr = new int[n];
        fillAux(n);
        StringBuilder res = new StringBuilder();
        recursion(n - 1, res, k);
        return res.toString();
    }

    private void recursion(int n, StringBuilder sb, int k) {
        if (sb.length() == auxArr.length) {
            return;
        }
        int index = 0;
        int remainder = 0;
        if (n > 0) {
            index = k / auxArr[n] - 1;
            remainder = k % auxArr[n];
            if (remainder != 0) {
                index += 1;
            } else {
                remainder = auxArr[n];
            }
        }
        sb.append(auxList.get(index));
        auxList.remove(index);
        recursion(n - 1, sb, remainder);
    }

    private void fillAux(int n) {
        int sum = 1;
        for (int i = 1; i < n; i++) {
            sum *= i;
            auxList.add(i);
            auxArr[i] = sum;
        }
        auxList.add(n);
    }

    public static void main(String[] args) {
        System.out.println(new SixZero().getPermutation(4, 9));
    }
}
