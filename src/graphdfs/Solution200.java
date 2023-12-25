package graphdfs;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution200 {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
    }

    @Test
    public void test1() {
        char[][] inputs = new char[][]{{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}};
        int res = numIslands(inputs);
        System.out.println(res); //1
    }

    @Test
    public void test2() {
        char[][] inputs = new char[][]{{'1','1','0','0','0'},{'1','1','0','0','0'},{'0','0','1','0','0'},{'0','0','0','1','1'}};
        int res = numIslands(inputs);
        System.out.println(res); //3
    }

    int[][] dirs = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};

    public int numIslands(char[][] grid) {
        /**
         * 先写汉语，后写英语。图论（用BFS已经做过了，再用DFS做下）
         * 中心思想：两层for循环，如果发现有个1 且 这个1没有被访问过，那么就以此节点为root节点进行dfs遍历。目标是什么呢？目标是把这一坨的1都
         * 给标识为visited，这样继续for循环的话，如果遇到是1且已经是visited过的，就不管了。用什么遍历方式呢？这种属于多叉树，用前序遍历吧。
         *
         * 1. int[][] visited = ...
         * 2. int res = 0;
         * 3. for m
         *        for n
         *           1) 如果这个节点的值为1 且 没有被visited，那么
         *              res++；
         *              a.把这个节点标识为visited
         *              b.遍历孩子节点，如果这个孩子节点没有出界 且 值为1 且 没有被visited，那么往子问题要答案
         *                把每个孩子节点作为子树的root，把这个子树标识为visited。
         *                （需要一个dfs，入参是 grid，visited，i，j，不需要返回值。）
         * 4. 返回res
         */
        //1
        int m = grid.length;
        int n = grid[0].length;
        int[][] visited = new int[m][n];
        //2
        int res = 0;
        //3
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1' && visited[i][j] == 0) {
                    res++;
                    dfs(grid, visited, i, j);
                }
            }
        }
        return res;
    }

    private void dfs(char[][] grid, int[][] visited, int i, int j) {
        // 前序遍历。先把本节点的事情给干了，把本节点的visited标识为1，标识这个节点已经扩展到。
        visited[i][j] = 1;
        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (x>=0 && x<grid.length && y>=0 && y<grid[0].length && grid[x][y] == '1' && visited[x][y] == 0) {
                dfs(grid, visited, x, y);
            }
        }
    }
}
