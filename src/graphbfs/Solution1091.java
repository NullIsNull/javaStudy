package graphbfs;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

public class Solution1091 {
    @Test
    public void test1() {
        int[][] grid = new int[][]{{0,0,0},{1,1,0},{1,1,0}};
        int res = shortestPathBinaryMatrix(grid);
        System.out.println(res);
    }

    public int shortestPathBinaryMatrix(int[][] grid) {
        /**
         * 先写汉语，后写英语。图论BFS。
         * 1. 如果第一个节点坐标的值不是0，return -1；
         * 2. Queue<int[]> queue = ...，把第一个点的坐标放入queue中, 并且标识为visited。
         * 3.0 int level = 0
         *     int[] side = new int[]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}，{1, 1}, {1,-1}, {-1, 1}, {-1, -1}}
         *     int[][] visited = ...
         * 3. while queue不为空
         *    3.1 level++
         *    3.2 int size = queue的大小。
         *    3.3 for size次
         *        a. queue.poll出来
         *        b. 如果其坐标是最右下角的一个坐标，直接return level。
         *        c. 如果不是，找其四周节点，如果四周节点“没出界 && 有0 && 并没有被visited”，那么就放入queue。
         * 4. return -1;
         */
        // 1
        if (grid[0][0] != 0) {
            return -1;
        }
        // 2
        Queue<int[]> queue = new LinkedList<>();
        int[][] visited = new int[grid.length][grid[0].length];
        queue.add(new int[]{0, 0});
        visited[0][0] = 1;
        // 3
        int level = 0;
        int[][] sides = new int[][]{{0, 1}, {0,-1}, {1, 0}, {-1, 0},{1, 1}, {1,-1}, {-1, 1}, {-1, -1}};
        // 3.1
        while (!queue.isEmpty()) {
            level++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] poll = queue.poll();
                if (poll[0] == grid.length - 1 && poll[1] == grid[0].length - 1) {
                    return level;
                } else {
                    for (int[] side : sides) {
                        int x = side[0];
                        int y = side[1];
                        int x1 = poll[0] + x;
                        int y1 = poll[1] + y;
                        if (x1 >= 0 && x1 < grid.length && y1 >= 0 && y1 < grid[0].length && grid[x1][y1] == 0 && visited[x1][y1] != 1) {
                            queue.add(new int[]{x1, y1});
                            visited[x1][y1] = 1;
                        }
                    }
                }
            }
        }
        return -1;
    }
}
