package graphbestfirstserch;

import org.junit.Test;

import java.util.*;

public class Solution787 {


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
        int res = findCheapestPrice(3, edges, 0, 3, 1);
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

    @Test
    public void test4() {
        int n = 4;
        int[][] edges = new int[][]{{0,1,1},{0,2,5},{1,2,1},{2,3,1}};
        int src = 0, dst = 3, k = 1;
        int res = findCheapestPrice(n, edges, src, dst, k);
        System.out.println(res); //
    }

    // 错误的算法。正确的看下Solution787E1
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        /**
         * 先写汉语，后写英语。图论（最优优先，用最优的来扩展最优解）。
         * 主要是想就是：从目标节点，用最优解方式扩展k次，1如果扩展到的节点，正好是目标节点，那么返回该节点的结果。2如果扩展过程中没有扩展到目标节点
         * ，那就扩展k次，然后从堆中找出目标节点（目标节点被前k次扩展的影响到过），如果堆中没有目标节点，就说明目标节点根本不可达。 ------错误。有中场景，就是你扩展了k次，都是在一个方向上
         *  扩展的，没有影响到虽然长但是也是可达的节点。K不能用来扩展k次。现在扩展不仅要看cost，还要看此节点此cost是经过几次转机过来的，如果转机次数比k小，那么此节点cost虽然大，但是还是可以扩展影响的。
         * 1. 先构建一个图（单向图，无环），用Map：Map<起始节点，List<[目标节点，cost]>>。
         * 2. 定义一个类Cell, 把节点和总的cost包进去，用cost来衡量对象的大小。
         * 3. Priority<Cell> heap = new ...
         * 4. 把初始节点，放到堆里面
         *    把初始节点的cost记录下来，Map<节点，最小的cost> cost = ...
         *    int level = 0;
         *    while 堆不为空
         *      1) heap中poll一个下来，如果cost中不存在，就放到cost中（存在就不管了，说明已经展开过了，它所影响已经计算过了）
         *      2) 如果这个节点是目标节点，那么直接返回这个节点中的cost
         *         否则，如果level <= k && 还没有展开过此孩子节点（cost中不存在）， 那么展开此节点的孩子节点，把孩子节点的cost = cur + 本节点的.cost 算到cell中，加入到heap中。
         * 5. 如果第四步没返回，代码走到这里，把堆中存量的不停的poll，把目标节点poll出来，直到poll到目标节点为止，返回目标节点。
         * 6. 目标节点都没有，说明不可达，返回-1
         */
        // 1
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] flight : flights) {
            graph.computeIfAbsent(flight[0], p -> new ArrayList<>()).add(new int[]{flight[1], flight[2]});
        }
        // 2
        Map<Integer, Integer> cost = new HashMap<>();
        PriorityQueue<Cell> heap = new PriorityQueue<>();
        heap.offer(new Cell(src, 0));
//        cost.put(src, 0);
        int level = 0;
        while (!heap.isEmpty()) {
            Cell poll = heap.poll();
            if (!cost.containsKey(poll.node)) {
                cost.put(poll.node, poll.cost);
            } else {
                continue;
            }
            if (poll.node == dst) {
                return poll.cost;
            } else {
                if (level <= k) {
                    List<int[]> childs = graph.getOrDefault(poll.node, new ArrayList<>());
                    for (int[] child : childs) {
                        heap.offer(new Cell(child[0], poll.cost + child[1]));
                    }
                }
            }
            level++;
        }
        // 5 return
        int res = -1;
        while (!heap.isEmpty()) {
            Cell poll = heap.poll();
            if (poll.node == dst) {
                res = poll.cost;
                break;
            }
        }
        return res;
    }

    public static class Cell implements Comparable<Cell>{
        Integer node;
        Integer cost;

        public Cell(Integer node, Integer cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compareTo(Cell o) {
            return this.cost - o.cost;
        }
    }

}
