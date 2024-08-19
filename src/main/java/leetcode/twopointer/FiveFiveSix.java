package leetcode.twopointer;

/**
 * 下一个最大元素Ⅲ
 *
 * @author zengxi.song
 * @date 2024/8/13
 */
public class FiveFiveSix {

    public int nextGreaterElement(int n) {
        // 将数字转为字符数组处理 其本质等同于求下一个排列
        char[] chars = String.valueOf(n).toCharArray();
        int i = chars.length - 2;
        while (i >= 0 && chars[i] >= chars[i + 1]) {
            i--;
        }
        // 代表此时序列降序 无更大值
        if (i == -1) {
            return -1;
        }
        int j = chars.length - 1;
        while (j > i && chars[j] <= chars[i]) {
            j--;
        }
        // 将右侧第一个大于i的数与i交换
        swap(chars, i, j);
        // 将i后面的数递增排序
        reverse(chars, i + 1, chars.length - 1);
        try {
            return Integer.parseInt(new String(chars));
        } catch (Exception e) {
            return -1;
        }
    }

    private void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    private void reverse(char[] chars, int i, int j) {
        while (i < j) {
            swap(chars, i++, j--);
        }
    }

}
