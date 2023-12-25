package graphbfs;

import org.junit.Test;

import java.util.*;

public class Solution310 {

    @Test
    public void test1() {
        Integer n = 4;
        int[][] edges = new int[][]{{1,0},{1,2},{1,3}};
        List<Integer> res = findMinHeightTrees1(n, edges);
        System.out.println(res); // 1
    }

    @Test
    public void test2() {
        Integer n = 6;
        int[][] edges = new int[][]{{3,0},{3,1},{3,2},{3,4},{5,4}};
        List<Integer> res = findMinHeightTrees1(n, edges);
        System.out.println(res); // 2
    }

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        /**
         * 先写汉语，后写英语。图论算法（BFS解决下）
         * 1. 先用adjacency list表达一个图。
         * 2. 从0到n-1，依次做为root节点，然后找出这个节点作为root的树的路径的深度。
         * 3. 2中的的记录以此节点的路径的深度，用什么结构存下来呢？ 用TreeMap<深度，List<节点>> treeMap = ...
         * 4，return treeMap.poll.getValue()
         */
        // 1
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            int i = edge[0];
            int j = edge[1];
            graph.computeIfAbsent(i, k -> new ArrayList<>()).add(j);
            graph.computeIfAbsent(j, k -> new ArrayList<>()).add(i);
        }
        // 2
        TreeMap<Integer, List<Integer>> treeMap = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            // 以此节点为root，计算深度
            Queue<Integer> queue = new LinkedList();
            Set<Integer> visited = new HashSet<>();
            queue.add(i);
            visited.add(i);
            Integer level = -1;
            while (!queue.isEmpty()) {
                level++;
                int size = queue.size();
                for (int s = 0; s < size; s++) {
                    Integer poll = queue.poll();
                    List<Integer> childs = graph.getOrDefault(poll, new ArrayList<>());
                    for (Integer child : childs) {
                        if (!visited.contains(child)) {
                            queue.add(child);
                            visited.add(child);
                        }
                    }
                }
            }
            // 更新treeMap
            treeMap.computeIfAbsent(level, k-> new ArrayList<>()).add(i);
        }
        // 3 返回
        return treeMap.firstEntry().getValue();
    }

    @Test
    public void test3() {
        Integer n = 4;
        int[][] edges = new int[][]{{1,0},{1,2},{1,3}};
        List<Integer> res = findMinHeightTrees1(n, edges);
        System.out.println(res); // 1
    }

    @Test
    public void test4() {
        Integer n = 6;
        int[][] edges = new int[][]{{3,0},{3,1},{3,2},{3,4},{5,4}};
        List<Integer> res = findMinHeightTrees1(n, edges);
        System.out.println(res); // 2
    }

    @Test
    public void test5() {
        Integer n = 7;
        int[][] edges = new int[][]{{0,1},{1,2},{1,3},{2,4},{3,5},{4,6}};
        List<Integer> res = findMinHeightTrees1(n, edges);
        System.out.println(res); // 2
    }

    public List<Integer> findMinHeightTrees1(int n, int[][] edges) {
        /**
         * 先写汉语，后写英语。图论（BFS解决）。上面的算法复杂度过高，超时。
         * 1. 先找到出度为1的所有节点。 1.1如果出度为0（就是只有一个节点怎么办呢？单独返回吧). 1.2如果有两个节点呢，应该能cover注。
         * 2. 加入queue里面，并把这些出度为1的节点标识为visited。
         * 3.0 List<Integer> res = ...;
         * 3. 进行BFS，剥到最后一次，这次的节点就是最后的结果。
         *    每次进循环就先把res清空，然后把当前的queue里面的节点加入res里面。
         * 4. return res；
         */
        if (n == 1) {
            return Arrays.asList(0);
        }
        // 1 先construct一个图，然后找到出度为1的节点。加入到queue里面。
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            int i = edge[0];
            int j = edge[1];
            graph.computeIfAbsent(i, k -> new ArrayList<>()).add(j);
            graph.computeIfAbsent(j, k -> new ArrayList<>()).add(i);
        }
        // 2
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        for (Map.Entry<Integer, List<Integer>> entry : graph.entrySet()) {
            List<Integer> value = entry.getValue();
            if (value.size() == 1) {
                queue.add(entry.getKey());
                visited.add(entry.getKey());
            }
        }
        // 3
        List<Integer> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            res.clear();
            for (int i = 0; i < size; i++) {
                Integer poll = queue.poll();
                res.add(poll);
                List<Integer> integers = graph.get(poll);
                for (Integer integer : integers) {
                    if (!visited.contains(integer)) {
                        queue.add(integer);
                        visited.add(integer); // 这个里面有问题。
                    }
                }
            }
        }
        return res;
    }

    @Test
    public void test6() {
        Integer n = 7;
        int[][] edges = new int[][]{{0,1},{1,2},{1,3},{2,4},{3,5},{4,6}};
        List<Integer> res = findMinHeightTrees2(n, edges);
        System.out.println(res); // 2
    }

    public List<Integer> findMinHeightTrees2(int n, int[][] edges) {
        /**
         * 先写汉语，后写英语。图论BFS
         * 1. 先根据输入，construct一个图，Map<节点，List<Integer>> graph = ...;
         * 2. 用Map<节点, 度多少> out = ...来存放每个节点的度。
         * 3 List<Integer> res;
         * 4 把度为1的节点，放入queue里面。Queue queue = ...; 并且标识这些度为1的节点为visited。
         * 5. while queue不为空
         *    3.1 先把res给清空。
         *    3.2 根据queue的size，把queue中的节点poll出来。 for size
         *        a. poll出来
         *        b. 把这个poll出来的加入到res
         *        c. 找出这个节点的孩子节点，遍历孩子节点。
         *           1.把这个孩子节点的度都减1。
         *           2.如果孩子节点的度为1，&& 孩子节点不是visited节点，那么加入queue。
         * 6. return res；
         */
        // 1
        if (n == 1) {
            return Arrays.asList(0);
        }
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            int i = edge[0];
            int j = edge[1];
            graph.computeIfAbsent(i, k -> new ArrayList<>()).add(j);
            graph.computeIfAbsent(j, k -> new ArrayList<>()).add(i);
        }
        // 2
        Map<Integer, Integer> out = new HashMap<>();
        for (Map.Entry<Integer, List<Integer>> entry : graph.entrySet()) {
            out.put(entry.getKey(), entry.getValue().size());
        }
        // 3
        List<Integer> res = new ArrayList<>();
        Queue<Integer> queue = new LinkedList();
        Set<Integer> visited = new HashSet<>();
        // 4
        for (Map.Entry<Integer, Integer> entry : out.entrySet()) {
            if (entry.getValue() == 1) {
                queue.add(entry.getKey());
                visited.add(entry.getKey()); // ?
            }
        }
        // 5
        while (!queue.isEmpty()) {
            res.clear();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Integer poll = queue.poll();
                res.add(poll);
                List<Integer> childes = graph.get(poll);
                for (Integer child : childes) {
                    Integer outCome = out.get(child);
                    out.put(child, outCome - 1);
                    if (outCome - 1 == 1 && !visited.contains(child)) {
                        queue.add(child);
                        visited.add(child);
                    }
                }
            }
        }
        // 6
        return res;
    }
}
