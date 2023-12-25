package dynamicprogramming;

import org.junit.Test;

public class Solution121 {

    @Test
    public void test1() {
        int[] ints = new int[]{7,1,5,3,6,4};
        int res = maxProfit(ints);
        System.out.println(res);
    }

    public int maxProfit(int[] prices) {
        /**
         * 应该可以用动态规划来解决。这个应该也算动态规划，为什么算呢？还是因为问题是从小处着手开始解决，慢慢的找到cost的最小值。
         * 第i天的最大利润就是前i天的最小成本和第i天的价格差距。
         * 1. 确定目标函数。
         *    f(i)表示第i天的最大利润
         * 2. base cases。
         *    cost = p[1]
         *    f(1, cost) = 0
         *    f(2, min(cost, p[2])) = p[2] - min(p[2], cost)
         * 3. 总结出状态转移方程。
         *    f(n, cost) = p[n] - min(p[n], cost)
         * 4. top-down or bottom-up ?
         *    bottom-up
         * 5. return f(n)
         * -------------------------
         * 1. 先弄个cost = p[0]，再弄个int res = 0留存放最大利润。
         * 2. for i < prices.length // i从1开始，就是从第二天开始
         *       1）更新cost = min[cost, p[i]]
         *       2) 如果p[i]比cost大，
         *          那么dp[i] = p[i] - cost
         *          res = max(dp[i], res)
         * 3. return dp中的最大值。
         */
        //1
        int cost = prices[0];
        int res = 0;
        //2
        for (int i = 1; i < prices.length; i++) {
            cost = Math.min(cost, prices[i]);
            if (prices[i] > cost) {
                res = Math.max(res, prices[i] - cost);
            }
        }
        return res;
    }
}
