package graphbfs;

import java.util.LinkedList;
import java.util.Queue;

public class Solution471 {
    public int[][] updateMatrix(int[][] mat) {
        /**
         * 先写汉语，后写英语。图论（BFS）
         * 1. 先把值为0的放在queue里面, Priority<int[]> queue = ..., 然后标记这些放到queue里面的已经被访问过了。
         *    int[][] visted = ...
         *    int[][] res = ...
         *
         *    int level = 0
         * 2. while queue不为空
         *    2.1 queue的size取出来。把这一批一个一个处理掉。
         *        a. 把res[][]对应的位置塞成level。
         *        b. 这个节点的下一层，如果没有出界和没有被访问过，就把它加入到queue里面。
         *        c. 塞到queue中，就设置这个格子被访问了。
         *    2.2 level++
         *
         * 3. return res.
         */

        int[][] side = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        // 1
        Queue<int[]> queue = new LinkedList<>();
        int m = mat.length;
        int n = mat[0].length;
        int[][] visited = new int[m][n];
        int[][] res = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                    visited[i][j] = 1;
                }
            }
        }
        // 2
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] poll = queue.poll();
                int x = poll[0];
                int y = poll[1];
                res[x][y] = level;

                for (int[] ints : side) {
                    int i1 = ints[0] + x;
                    int i2 = ints[1] + y;
                    if (i1 >= 0 && i1 < m && i2 >= 0 && i2 < n && visited[i1][i2] != 1) {
                        queue.offer(new int[]{i1, i2});
                        visited[i1][i2] = 1;
                    }
                }
            }
            level++;
        }
        return res;
    }
}
