package leetcode.math;

/**
 * 字符串相乘
 *
 * @author zengxi.song
 * @date 2025/2/13
 */
public class FourThree {

    public String multiply(String num1, String num2) {
        // 方法1中每次都循环创建StringBuilder 并有额外的计算字符串相加的开销
        // 可以使用一个固定长度的数组(数组好操作)来保存
        // 时间复杂度O(MN) 空间复杂度O(M+N)
        // 遍历乘数 然后将每次生成的结果固定在合适的位置
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        int m = num1.length(), n = num2.length();
        // 易得 两个字符串相乘的长度 为m+n-1或者m+n 所以这里直接定义数组即可
        int[] sumArr = new int[m + n];
        for (int i = m - 1; i >= 0; i--) {
            int num = num1.charAt(i) - '0';
            int remainder = 0;
            int index = n + i;
            for (int j = n - 1; j >= 0; j--) {
                int sum = num * (num2.charAt(j) - '0') + sumArr[index] + remainder;
                sumArr[index--] = sum % 10;
                remainder = sum / 10;
            }
            while (remainder != 0) {
                int sum = sumArr[index] + remainder;
                sumArr[index--] = sum % 10;
                remainder = sum / 10;
            }
        }
        StringBuilder res = new StringBuilder();
        int i = 0;
        for (; i < sumArr.length; i++) {
            if (sumArr[i] != 0) {
                break;
            }
        }
        while (i < sumArr.length) {
            res.append(sumArr[i++]);
        }
        return res.toString();
    }

    public String multiply1(String num1, String num2) {
        // 时间复杂度O(MN+N^2) 空间复杂度O(M+N)
        // 遍历乘数 然后将每次生成的字符串相加
        // 注意层数每加n 后面需要补n个0
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        StringBuilder res = new StringBuilder("0");
        for (int i = num1.length() - 1; i >= 0; i--) {
            int num = num1.charAt(i) - '0';
            // 初始化本次的StringBuilder
            StringBuilder sb = new StringBuilder();
            for (int k = 0; k < num1.length() - i - 1; k++) {
                sb.append('0');
            }
            int remainder = 0;
            for (int j = num2.length() - 1; j >= 0; j--) {
                int sum = num * (num2.charAt(j) - '0') + remainder;
                sb.append(sum % 10);
                remainder = sum / 10;
            }
            if (remainder != 0) {
                sb.append(remainder);
            }
            res = addStr(res, sb);
        }
        return res.reverse().toString();
    }

    private StringBuilder addStr(StringBuilder s1, StringBuilder s2) {
        StringBuilder res = new StringBuilder();
        int remainder = 0;
        for (int i = 0, j = 0; i < s1.length() || j < s2.length(); i++, j++) {
            int num1 = i < s1.length() ? s1.charAt(i) - '0' : 0;
            int num2 = j < s2.length() ? s2.charAt(j) - '0' : 0;
            int sum = num1 + num2 + remainder;
            res.append(sum % 10);
            remainder = sum / 10;
        }
        if (remainder != 0) {
            res.append(remainder);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new FourThree().multiply("123", "456"));
    }
}
