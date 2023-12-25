package dynamicprogramming;

import org.junit.Test;

import java.util.Arrays;

public class Solution746 {

    @Test
    public void test1() {
        int[] ints = new int[]{10,15,20};
        int res = minCostClimbingStairs(ints);
        System.out.println(res);
    }

    public int minCostClimbingStairs(int[] cost) {
        /**
         * 动态规划思路求解。
         * 1. 目标函数 f(i) = ... 表示第i成台阶的最小花费。
         * 2. base cases
         *    f(0) = 0
         *    f(1) = cost[0]
         *
         * 3. 状态迁移方程
         *    f(n) = min(f(n-1), f(n-2)) + cost[n-1]
         * 4. top-down or bottom-up
         *    bottom-up
         * 5. return f(n)
         * --------------------------------
         * 先写汉语，后写英语。动态规划
         * 1. 先new一个n+1个大小的int数组。dp
         * 2. d[0] = 0
         *    dp[1] = cost[0]
         * 3. i从2开始，<=cost.length, 循环：
         *    dp[n] = min(dp[n-1], dp[n-2]) + cost[n-1]
         * 4. return dp[n]
         */
        //1
        int[] newCost = new int[cost.length + 1];
        for (int i = 0; i < cost.length; i++) {
            newCost[i] = cost[i];
        }

        if (cost.length == 0) {
            return 0;
        }
        if (cost.length == 1) {
            return cost[0];
        }
        int n = newCost.length;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = newCost[0];
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.min(dp[i-1], dp[i-2]) + newCost[i-1];
        }

        return dp[n];
    }
}
