package graphbfs;

import org.junit.Test;

import java.util.*;

public class Solution1791 {

    @Test
    public void test1() {
        int[][] edges = {{1,2},{2,3},{4,2}};
        int res = findCenter(edges);
        System.out.println(res);
    }

    public int findCenter(int[][] edges) {
        /**
         * 先写汉语，后写英语。
         * 如果是心形的，直接统计二维数组里面的点出现的次数就可以了。
         * 1. 弄个TreeMap<integer, integer>, key是节点的号，value是出现的次数。
         * 2. 那个key出现的次数最多，就是中心节点。
         *
         */
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] edge : edges) {
            int node = edge[0];
            int node1 = edge[1];
            Integer integer = map.get(node);
            if (integer == null) {
                map.put(node, 0);
            } else {
                map.put(node, integer + 1);
            }

            Integer value1 = map.get(node1);
            if (value1 == null) {
                map.put(node1, 0);
            } else {
                map.put(node1, value1 + 1);
            }
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            
        }
        return map.get(0);
    }
}
