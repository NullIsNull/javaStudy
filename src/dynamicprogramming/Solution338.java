package dynamicprogramming;

import org.junit.Test;

public class Solution338 {

    @Test
    public void test1() {
        int[] res = countBits(0);
        for (int re : res) {
            System.out.println(re);
        }
        System.out.println(res);
    }

    public int[] countBits(int n) {
        /**
         * 用动态规划来解。
         * 1. 写出目标函数。f(i)=... 表示整数i中含有1的个数。
         * 2. base cases
         *    f(0) = 0
         *    f(1) = 1
         *    f(2) = 1
         *    f(3) = 2
         * 3. 状态转移方程 f(n)
         *    f(n) = f(n/2) + n%2
         * 4. top-down or bottom-up ?
         *    bottom-up
         * 5. return 这个数组。
         *----------------------
         * 1. 先new个数组出来， int[] dp = new int[n+1]
         * 2. dp[0] = 0; dp[1] = 1
         * 3. for i=2, i<=n; i++
         *     dp[i] = dp[i/2] + i%2
         * 4. return dp
         */
        //1
        if (n == 0) {
            return new int[1];
        }
        int[] dp = new int[n+1];
        //2
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i/2] + i%2;
        }
        return dp;
    }
}
