package graphbfs;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

public class Solution994 {

    @Test
    public void test1() {
        int[][] grid = new int[][]{{2,1,1},{1,1,0},{0,1,1}};
        int res = orangesRotting(grid);
        System.out.println(res);
    }

    public int orangesRotting(int[][] grid) {
        /**
         * 先写汉语，后写英语。图论BFS
         * 1. 先找出所有的橘子的个数。
         * 2. 要找出所有的腐烂的橘子的坐标，放入Queue<int[]> queue = ...，并且标识为visited。
         * 3. 定义周边坐标，int[][] sides = new int[][]{{0,1}, {0,-1}, {1, 0}, {-1, 0}}
         *    定义一个腐烂橘子总数，int count = 0；
         *    int level = -1；
         * 4. while queue不为空
         *    level++
         *    int size = queue.size
         *    for size 次
         *        a. int[] poll = queue.poll;
         *        b. 腐烂橘子总数加1
         *        c. 看下周边，如果不为空 && 没有出界 && 没有被visited，那么：
         *           1. 加入queue。
         *           2. 标识为visited（腐烂）
         * 5. 如果腐烂的橘子总数 == 橘子总数，那么return level，否则返回-1
         */
        // 1
        int all = 0;
        int m = grid.length;
        int n = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int[][] visited = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 0) {
                    all++;
                }
                // 2
                if (grid[i][j] == 2) {
                    queue.add(new int[]{i, j});
                    visited[i][j] = 1;
                }
            }
        }
        if (all != 0 && queue.isEmpty()) {
            return -1;
        }
        if (all == 0) {
            return 0;
        }
        // 3
        int[][] sides = new int[][]{{0,1}, {0,-1}, {1, 0}, {-1, 0}};
        int bad = 0;
        // 4
        int level = -1;
        while (!queue.isEmpty()) {
            level++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] poll = queue.poll();
                bad++;
                for (int[] side : sides) {
                    int x = side[0];
                    int y = side[1];
                    int x1 = poll[0] + x;
                    int y1 = poll[1] + y;
                    if (x1 >=0 && x1 < grid.length && y1 >= 0 && y1 < grid[0].length && grid[x1][y1] != 0 && visited[x1][y1] != 1) {
                        queue.add(new int[]{x1, y1});
                        visited[x1][y1] = 1;
                    }
                }
            }
        }
        // 5
        return bad == all ? level : -1;
    }
}
