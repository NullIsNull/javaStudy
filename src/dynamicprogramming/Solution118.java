package dynamicprogramming;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Solution118 {

    @Test
    public void test1() {
        List<List<Integer>> res = generate(5);
        System.out.println(res);
    }

    public List<List<Integer>> generate(int numRows) {
        /**
         * 由小见大，动态规划能解。
         * 1. 先写出目标函数。
         *    f(n, i) = ...,代表第n行，第i个数。
         * 2. 写几个base case
         *    f(1, 1) = 1, f(2, 1) = 1, f(2, 2) = 1
         * 3. 写出状态转移方程。
         *    f(n, i) = f(n-1, i-1) + f(n-1, i)
         * 4. top-down or bottom-up?
         *    bottom-up
         * 5. return f(n, n)，过程中的数据要保存下来留着返回。
         *
         * // 先写汉语，后写英语。动态规划。
         * 1. 先new一个List<List<Integer>> = ...
         * 2. res(0).add(1) 把第一行的元素填满。
         * 3. for i>=1 in numRows, i<numRows
         *       List<Integer> temp = ...
         *       for (int j==0;j<i;j++)
         *          如果j==0
         *              temp.add(1)
         *          否则如果j==i-1
         *              temp.add(1)
         *          否则：
         *              res.add(res(i-1)[j-1] + res[i-1][j])
         * 4. return res
         */
        //1
        List<List<Integer>> res = new ArrayList<>(numRows);
        //2
//        res.add(List.of(1));
        //3 i代表多少行， j代表每行要填充的元素
        for (int i = 1; i < numRows; i++) {
            List<Integer> temp = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    temp.add(1);
                } else if (j == i) {
                    temp.add(1);
                } else {
                    temp.add(res.get(i-1).get(j-1) + res.get(i-1).get(j));
                }
            }
            res.add(temp);
        }
        return res;
    }
}
