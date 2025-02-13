package leetcode.math;

import java.util.ArrayList;
import java.util.List;

/**
 * 两数相除
 *
 * @author zengxi.song
 * @date 2025/2/13
 */
public class TwoNine {

    public int divide(int dividend, int divisor) {
        // 可以采用空间换时间的思路 使用一个数组记录divisor、2*divisor、4*divisor...
        // 类二分查找 时间复杂度O(logN) 空间复杂度O(logN) N是32整数的范围
        // 题目要求平台只能存储32位数字 所以不能使用long来简化计算
        // 唯一可能数字溢出
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        // 特殊处理
        if (dividend == 0) {
            return 0;
        }
        if (divisor == 1) {
            return dividend;
        }
        if (divisor == -1) {
            return -dividend;
        }
        // 为了方便编码 我们可以将除数和被除数转换为符号相同 并将符号记录下来
        boolean negative = (dividend < 0) ^ (divisor < 0);
        // 但是负数转换为正数会溢出 所以需要统一转换为负数
        if (dividend > 0) {
            dividend = -dividend;
        }
        if (divisor > 0) {
            divisor = -divisor;
        }
        List<Integer> auxList = new ArrayList<>();
        auxList.add(divisor);

        int pre = divisor;
        while (pre >= dividend - pre) {
            pre <<= 1;
            auxList.add(pre);
        }
        // 然后从倒序开始遍历
        int res = 0;
        for (int i = auxList.size() - 1; i >= 0; i--) {
            if (auxList.get(i) >= dividend) {
                // 代表2^i次方
                res += 1 << i;
                dividend -= auxList.get(i);
            }
        }
        return negative ? -res : res;
    }

    public int divide1(int dividend, int divisor) {
        // 二分查找+快速乘 时间复杂度O(logN^2) 空间复杂度O(1) N是32整数的范围 之所以是平方 是因为二分查找*快速乘
        // 题目要求平台只能存储32位数字 所以不能使用long来简化计算
        // 唯一可能数字溢出
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        // 特殊处理
        if (dividend == 0) {
            return 0;
        }
        if (divisor == 1) {
            return dividend;
        }
        if (divisor == -1) {
            return -dividend;
        }
        // 为了方便编码 我们可以将除数和被除数转换为符号相同 并将符号记录下来
        boolean negative = (dividend < 0) ^ (divisor < 0);
        // 但是负数转换为正数会溢出 所以需要统一转换为负数
        if (dividend > 0) {
            dividend = -dividend;
        }
        if (divisor > 0) {
            divisor = -divisor;
        }
        // 注意这里都是负数 下面计算要牢牢记住
        // 设结果为Z 所以我们需要找到(最大)的Z*divisor>=dividend 这里采用二分法
        // 不能使用乘法 采用使用加法的快速乘
        int res = 0;
        int left = 1, right = Integer.MAX_VALUE;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (this.quickMulti(dividend, divisor, mid)) {
                res = mid;
                // 这里left+1有可能还会溢出
                if (mid == Integer.MAX_VALUE) {
                    break;
                }
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return negative ? -res : res;
    }

    /**
     * 这个方法返回值为是否mid*divisor>=dividend
     * 但是不允许使用乘法 所以采用快速乘算法
     *
     * @param dividend
     * @param divisor
     * @param mid
     * @return
     */
    private boolean quickMulti(int dividend, int divisor, int mid) {
        // 考虑到采用快速乘也可能会溢出 时刻记住dividend和divisor都是负数哈
        // 所以我们只需要找到第一个(小于)dividend的数就要停止 但是该数有可能已经溢出了 所以要改成减法形式
        int sum = 0;
        while (mid != 0) {
            if ((mid & 1) == 1) {
                // 第一个小于的sum
                if (sum < dividend - divisor) {
                    return false;
                }
                sum += divisor;
            }
            // 当mid=1的时候已经结束了 不需要divisor再乘2了
            if (mid != 1) {
                // 提早判断 减少循环
                if (divisor < dividend - divisor) {
                    return false;
                }
                // dividend大于Integer.MIN_VALUE 所以只要过了上面的if 这里不会溢出
                divisor += divisor;
            }
            // 右移代替除法
            mid >>>= 1;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new TwoNine().divide(-2147483648, -3));
    }
}
