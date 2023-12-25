package graphbestfirstserch;

import org.junit.Test;

import java.util.*;

public class Solution787E1 {

    @Test
    public void test1() {
        int n = 3;
        int[][] edges = new int[][]{{0,1,100},{1,2,100},{0,2,500}};
        int src = 0, dst = 2, k = 1;
        int res = findCheapestPrice(3, edges, 0, 2, 1);
        System.out.println(res); // 200
    }

    @Test
    public void test2() {
        int n = 4;
        int[][] edges = new int[][]{{0,1,100},{1,2,100},{2,0,100},{1,3,600},{2,3,200}};
        int src = 0, dst = 3, k = 1;
        int res = findCheapestPrice(n, edges, src, dst, k);
        System.out.println(res); // 700
    }

    @Test
    public void test3() {
        int n = 5;
        int[][] edges = new int[][]{{4,1,1},{1,2,3},{0,3,2},{0,4,10},{3,1,1},{1,4,3}};
        int src = 2, dst = 1, k = 1;
        int res = findCheapestPrice(n, edges, src, dst, k);
        System.out.println(res); // -1
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // 1
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] flight : flights) {
            graph.computeIfAbsent(flight[0], p -> new ArrayList<>()).add(new int[]{flight[1], flight[2]});
        }
        //2
        PriorityQueue<Cell> heap = new PriorityQueue<>();
        heap.add(new Cell(src, 0, 0));
        // 3
        int[] cost = new int[n];
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[0] = 0;
        int[] stop = new int[n];
        Arrays.fill(cost, Integer.MAX_VALUE);
        stop[0] = 0;

        int res = -1;
        while (!heap.isEmpty()) {
            Cell poll = heap.poll();
            if (poll.node == dst) {
                return poll.cost;
            }
            if (poll.stop <= k && graph.containsKey(poll.node)) {
                List<int[]> childrens = graph.get(poll.node);
                for (int[] child : childrens) {
                    // 当这个节点的花费更多 且 中转次数更多的时候，就不放到堆里面了。
                    if(poll.cost + child[1]  > cost[child[0]] && poll.stop + 1  > stop[child[0]]) {
                        continue;
                    }
                    heap.add(new Cell(child[0], poll.cost + child[1], poll.stop + 1));
                    cost[child[0]] = poll.cost + child[1];
                    stop[child[0]] = poll.stop + 1;
                }
            }
        }

        return res;
    }

    static class Cell implements Comparable<Cell> {
        Integer node;
        Integer cost;
        Integer stop;

        public Cell(Integer node, Integer cost, Integer stop) {
            this.node = node;
            this.cost = cost;
            this.stop = stop;
        }

        @Override
        public int compareTo(Cell o) {
            return this.cost - o.cost;
        }
    }
}
