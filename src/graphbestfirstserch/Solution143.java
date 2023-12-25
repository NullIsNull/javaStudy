package graphbestfirstserch;

import org.junit.Test;

import java.util.*;

public class Solution143 {

    @Test
    public void test1() {
        int[][] times = new int[][]{{2,1,1},{2,3,1},{3,4,1}};
        int res = networkDelayTime(times, 4, 2);
        System.out.println(res);
    }


    public int networkDelayTime(int[][] times, int n, int k) {
        /**
         * 图论Best-First search.  问题：存不存在你都放过了，然后再再heap中找了一个最小的？
         * 1. 先构建图，用邻接list构建一个图。Map<节点编号，List<类(目标节点，时间花费)>>。要搞个类，存放目标节点和cost，且用cost来决定两个对象的大小。
         * 2. PriorityQueue heap = ..., new一个堆出来。
         * 3. 把起始节点首先放入堆里面。
         *    4.0 Map<节点，每个节点的最小时间> cost = ...
         * 4. while 堆不为空
         *    4.1 poll出来一个（poll出来的这个节点是到起点最短的路径）
         *    4.2 如果cost中不存在，就放在cost的map中。然后把这个节点连接的孩子都基于poll，加上poll节点到孩子节点的cost放入heap中。
         *        否则表示前面已经找到过更小的了，就不放了，continue。
         * 5. （4中所有节点离起始节点的最近距离都已经找到了）
         *     如果cost.size != n, return -1;
         *     找到cost中的离起始节点的最大值，return 该值。
         */

        // 1
        Map<Integer, List<Cell>> graph = new HashMap<>();
        for (int[] time : times) {
            graph.computeIfAbsent(time[0], t -> new ArrayList<Cell>()).add(new Cell(time[1], time[2]));
        }
        // 2
        PriorityQueue<Cell> heap = new PriorityQueue<>();
        heap.offer(new Cell(k, 0));
        // 4
        Map<Integer, Integer> cost = new HashMap<>();
        while (!heap.isEmpty()) {
            Cell poll = heap.poll();
            if (!cost.containsKey(poll.node)) {
                // 当前节点是最小的节点，需要放进cost
                cost.put(poll.node, poll.cost);
                List<Cell> cells = graph.getOrDefault(poll.node, new ArrayList<>());
                for (Cell cell : cells) {
                    if (!cost.containsKey(cell.node)) {
                        heap.offer(new Cell(cell.node, poll.cost + cell.cost));
                    }
                }
            } else {
                if (cost.get(poll.node) > poll.cost) {
                    cost.put(poll.node, poll.cost);
                }
            }
        }

        if (cost.size() != n) {
            return -1;
        }

        int res = -1;
        for (Map.Entry<Integer, Integer> entry : cost.entrySet()) {
            res = Math.max(entry.getValue(), res);
        }

        return res;
    }

    /**
     * 这个类包含两种意思吧。1是目标节
     */
    static class Cell implements Comparable<Cell> {
        int node = 0; // 节点的key
        int cost = 0; // 节点离起始节点的距离

        public Cell(int end, int cost) {
            this.node = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Cell o) {
            return this.cost - o.cost;
        }
    }
}
