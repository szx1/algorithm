package leetcode.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 计数质数
 *
 * @author zengxi.song
 * @date 2024/7/17
 */
public class TwoZeroFour {

    public int countPrimes(int n) {
        // 线性 提前打标
        // 方法2会重复打标 此方法可以只对质数的倍数进行打标 避免重复打标
        // 时间复杂度O(N) 空间复杂度O(N)
        List<Integer> primes = new ArrayList<>();
        int[] isPrime = new int[n];
        Arrays.fill(isPrime, 1);
        for (int i = 2; i < n; ++i) {
            if (isPrime[i] == 1) {
                primes.add(i);
            }
            for (int j = 0; j < primes.size() && i * primes.get(j) < n; ++j) {
                isPrime[i * primes.get(j)] = 0;
                // 如果i可以被某个质数整除 则可以直接跳过
                // 因为后续会遍历到
                if (i % primes.get(j) == 0) {
                    break;
                }
            }
        }
        return primes.size();
    }

    public int countPrimes2(int n) {
        // 埃氏筛 提前打标 时间复杂度O(NloglogN) 空间复杂度O(N)
        int[] isPrime = new int[n];
        Arrays.fill(isPrime, 1);
        int ans = 0;
        for (int i = 2; i < n; ++i) {
            if (isPrime[i] == 1) {
                ans += 1;
                // i的倍数肯定不是质数
                if (i * i < n) {
                    for (int j = i * i; j < n; j += i) {
                        isPrime[j] = 0;
                    }
                }
            }
        }
        return ans;
    }

    public int countPrimes1(int n) {
        if (n <= 2) {
            return 0;
        }
        // 对于每个数 循环遍历到根号n来判断是否为质数
        // 超时
        int res = 0;
        for (int i = 2; i < n; i++) {
            if (i != 2 && (i & 1) == 0) {
                continue;
            }
            if (this.isPrime(i)) {
                res++;
            }
        }
        return res;
    }

    private boolean isPrime(int num) {
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
