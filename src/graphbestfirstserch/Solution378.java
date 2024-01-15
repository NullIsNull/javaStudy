package graphbestfirstserch;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution378 {

    public int kthSmallest(int[][] matrix, int k) {
        /**
         * 先写汉语，后写英语。图论（Best-First Search）
         * 中心思想：矩阵的最左上角的元素是最小的。以此为起始点，进行最优展开。展开k次后，返回此节点。比较简单，Digistra算法。
         * 1. 定义一个公共变量 int[][] sides = new int[][] {{0,1}, {0,-1}, {1, 0}, {-1, 0}}
         * 2. PriorityQueue<int[]> heap = ...
         *    弄个能去重坐标的int[][] visited = ...
         * 3. 把第一个元素加入到heap里面。
         *    把第一个元素的坐标加入到visited里面去重。
         *    int cnt = 1
         *    while 堆不为空
         *       1）poll一个出来
         *       2）如果cnt == k 返回poll
         *          否则，for遍历其四周的坐标
         *              a) 如果这个周边坐标没有访问过，就把它加入到heap中
         *              b）把这个周边坐标加入到visited中
         *       3) cnt++
         *  4. 随便返回0;
         */
        //1
        int[][] sides = new int[][]{{0,1}, {0,-1}, {1, 0}, {-1, 0}};
        //2
        PriorityQueue<int[]> heap = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return matrix[o1[0]][o1[1]] - matrix[o2[0]][o2[1]];
            }
        });
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] visited = new int[m][n];
        //3
        heap.add(new int[]{0,0});
        visited[0][0] = 1;
        int cnt = 1;
        while (!heap.isEmpty()) {
            int[] poll = heap.poll();
            if (cnt == k) {
                return matrix[poll[0]][poll[1]];
            }
            for (int[] side : sides) {
                int x = poll[0] + side[0];
                int y = poll[1] + side[1];
                if (x>=0 && x<m && y>=0 && y < n && visited[x][y] != 1) {
                    heap.offer(new int[]{x, y});
                    visited[x][y] = 1;
                }
            }
            cnt++;
        }
        return 0;
    }
}
