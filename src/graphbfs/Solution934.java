package graphbfs;

import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Solution934 {

    @Test
    public void test1() {
        int[][] gride = new int[][]{{0,1,0,0,0},{0,1,0,1,1},{0,0,0,0,1},{0,0,0,0,0},{0,0,0,0,0}};
        int res = shortestBridge(gride);
        System.out.println(res);
    }

    @Test
    public void test2() {
        int[][] gride = new int[][]{{0,0,0,0,0,0},{0,1,0,0,0,0},{1,1,0,0,0,0},{1,1,0,0,0,0},{0,0,0,0,0,0},{0,0,1,1,0,0}};
        int res = shortestBridge(gride);
        System.out.println(res);
    }

    public int shortestBridge(int[][] grid) {
        /**
         * 先写汉语，后写英语。图论（BFS解决）这个grid横着想和竖着想，不影响结果，按照脑海中的习惯来。
         * 1. 先遍历这个二维矩阵，把第一个遇到的1加入的queue里面。Queue queue = ...
         * 2. 以此为root节点，遍历所有的子节点，把岛屿的第一个节点加入到队列中。用bfs，把这个岛屿找到的1全部加入queue2中。直到孩子都为0（队列都为空的时候）的时候停止。
         *    把这个为1的都加入到queue2中。并且标识为visited。
         * 3.0 把queue2中的都拿出来，把队列的四周的第一梯队的0都放入queue2中。
         * 3. int level为0
         *    3.1 while queue2不为空。
         *        int size = 取queue中的个数。
         *        for 循环size，挨个取node。
         *            a. 如果该node的值为1，那么return level
         *            b. 否则把这个node四周都加入到queue里面（四周用坐标数组来计算下 && 没有被visited，就加入到queue里面）
         *    3.2 level++
         * 4. return level
         */
        // 1找到第一个1
        Queue<int[]> queueOf1 = new LinkedList<>();
        Queue<int[]> queueOf2 = new LinkedList<>();
        int[][] sides = new int[][]{{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        int m = grid.length;
        int n = grid[0].length;
        int[][] visited1 = new int[m][n];
        int[][] visited2 = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    queueOf1.add(new int[]{i, j});
                    queueOf2.add(new int[]{i, j});
                    visited1[i][j] = 1;
                    visited2[i][j] = 1;
                    break;
                }
            }
            if (!queueOf1.isEmpty()) {
                break;
            }
        }
        // 2把第一个岛的全部1放入queue1
        while (!queueOf1.isEmpty()) {
            int size = queueOf1.size();
            for (int i = 0; i < size; i++) {
                int[] poll = queueOf1.poll();
                int x = poll[0];
                int y = poll[1];
                for (int[] side : sides) {
                    int x1 = x + side[0];
                    int y1 = y + side[1];
                    if (x1 >= 0 && x1 < m && y1 >= 0 && y1 < n && grid[x1][y1] == 1 && visited1[x1][y1] != 1) {
                        queueOf1.add(new int[]{x1, y1});
                        visited1[x1][y1] = 1;
                        queueOf2.add(new int[]{x1, y1});
                        visited2[x1][y1] = 1;
                    }
                }
            }
        }
        //3.0 第一层的0全部加入queue2
        int size = queueOf2.size();
        for (int i = 0; i < size; i++) {
            int[] poll = queueOf2.poll();
            int x = poll[0];
            int y = poll[1];
            for (int[] side : sides) {
                int x1 = x + side[0];
                int y1 = y + side[1];
                if (x1 >= 0 && x1 < m && y1 >= 0 && y1 < n && grid[x1][y1] == 0 && visited2[x1][y1] != 1) {
                    queueOf2.add(new int[]{x1, y1});
                    visited2[x1][y1] = 1;
                }
            }
        }
        // 3.1
        int level = 0;
        while (!queueOf2.isEmpty()) {
            int size1 = queueOf2.size();
            for (int i = 0; i < size1; i++) {
                int[] poll = queueOf2.poll();
                int x = poll[0];
                int y = poll[1];
                if (grid[x][y] == 1) {
                    return level;
                }
                for (int[] side : sides) {
                    int x1 = x + side[0];
                    int y1 = y + side[1];
                    if (x1 >= 0 && x1 < m && y1 >= 0 && y1 < n && visited2[x1][y1] != 1) {
                        queueOf2.add(new int[]{x1, y1});
                        visited2[x1][y1] = 1;
                    }
                }
            }
            level++;
        }
        //4
        return level;
    }
}
