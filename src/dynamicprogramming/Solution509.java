package dynamicprogramming;

public class Solution509 {

    public int fib(int n) {
        /**
         * 斐波那契数的计算。动态规划思路求解。
         * 1. 确定目标函数。f(i) = ...
         * 2. base cases
         *    f(0) = 0
         *    f(1) = 1
         * 3. 确定状态转移方程。
         *    f(n) = f(n-1) + f(n-2)
         * 4. top-down or bottom-up ?
         *    bottom-up
         * 5. return f(n)
         * ----------------------
         * 1. 先弄个n个大小的数组 int[] dp = new int[n+1]
         * 2. for i=2,i<=n,i++
         *      dp[i] = dp[i-1]+dp[i-2]
         * 3. return dp[n]
         */
        //1
        if (n == 1 || n == 0) {
            return n;
        }

        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }
}
