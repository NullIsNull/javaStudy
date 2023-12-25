package dynamicprogramming;

public class Solution70 {

    public int climbStairs(int n) {
        /**
         * 动态规划能解，这个本是一个组合问题。中心思想是当前的台阶的所有可能的组合，最后一种可能可能是一跳，也可能是2跳跳上来的，这两种可能的和就是当前台阶的总的可能数量。
         *
         * 1.写出目标函数。F(i) = ...
         * 2.写几个base case。
         *   f(1) = 1, f(2) = 2, f(3) = 3
         * 3.写出状态迁移方程。
         *   f(i) = f(i-1) + f(i-2)
         * 4.top-down or bottom-up
         *   bottom-up
         * 5.return f(n)
         */
        if (n <= 2) {
            return n;
        }

        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }
}
