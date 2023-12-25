package graphbfs;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

public class Solution200 {
    
    @Test
    public void test1() {
        char[][] inputs = new char[][]{{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}};
        int res = numIslands(inputs);
        System.out.println(res);
    }

    @Test
    public void test2() {
        char[][] inputs = new char[][]{{'1','1','0','0','0'},{'1','1','0','0','0'},{'0','0','1','0','0'},{'0','0','0','1','1'}};
        int res = numIslands(inputs);
        System.out.println(res);
    }

    public int numIslands(char[][] grid) {
        /**
         * 先写汉语，后写英语。图论（先用BFS做下）
         * 中心思想就是：两层for循环，如果找到一个1且这个1没有被访问过，那么就以这个1为起点进行扩散。queue为空的时候，表示这个岛屿结束，
         * for循环继续找下一个没有被visited的1。
         * 1. int[][] visited = ...先搞一个visited的对应二维数组。
         * 2. 再搞一个int[][] dirs = ...表达节点的四周。
         * 3. int res = 0， Queue<int[]> queue = ...
         *    for m
         *       for n
         *          1. 如果grid[i][j]的值等于1 且 没有被visited过,那么
         *             res++
         *             把这个节点加入到queue里面，并标识此节点为visited。
         *                a. while queue不为空
         *                    for循环这个节点四周的节点
         *                        1) 如果这个四周节点的没出界 且 值为1 且 这个周节点没有被visited过，那么
         *                           把这个周节点加入到queue里面
         *                           表示这个周节点为visited
         *                        2) 否则，continue
         * 4. return res;
         */
        //1
        int m = grid.length;
        int n = grid[0].length;
        int[][] visited = new int[m][n];
        //2
        int[][] dirs = new int[][]{{0, 1}, {0,-1}, {1,0}, {-1,0}};
        //3
        int res = 0;
        Queue<int[]> queue = new LinkedList();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1' && visited[i][j] == 0) {
                    res++;
                    queue.add(new int[]{i, j});
                    visited[i][j] = 1;
                    while (!queue.isEmpty()) {
                        int[] poll = queue.poll();

                        for (int[] dir : dirs) {
                            int x = poll[0] + dir[0];
                            int y = poll[1] + dir[1];
                            if (x >=0 && x < m && y >=0 && y < n && grid[x][y] == '1' && visited[x][y] == 0) {
                                queue.offer(new int[]{x, y});
                                visited[x][y] = 1;
                            }
                        }
                    }
                }
            }
        }
        return res;
    }
}
