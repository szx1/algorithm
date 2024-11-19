/**
 * 本目录记录背包问题相关的题目 包括0-1背包，完全背包等
 * 可参考 https://blog.csdn.net/qq_41094332/article/details/129049548
 * <ol>
 *     <li>
 *         <b>0-1背包</b> <br>
 *         物品n[],重量w[],价值v[],背包容量target，求最大价值，每个物品最多可以用一次
 *         二维数组遍历顺序无所谓 一维数组先遍历物品 再倒序遍历背包容量
 *     </li>
 *     <li>
 *         <b>完全背包</b> <br>
 *          物品n[],重量w[],价值v[],背包容量target，求最大价值，每个物品使用次数不限
 *          <ol>
 *              组合排列问题 一维数组通用公式 dp[i]+=dp[i-num]
 *              <li>
 *                  组合问题 先遍历物品再遍历背包容量
 *              </li>
 *              <li>
 *                  排列问题 先遍历背包容量再遍历物品
 *              </li>
 *          </ol>
 *     </li>
 * </ol>
 *
 * @author zengxi.song
 * @date 2024/8/21
 */
package leetcode.dp.knapsack;