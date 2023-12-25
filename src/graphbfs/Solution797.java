package graphbfs;

import org.junit.Test;

import java.util.*;

public class Solution797 {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        Integer integer = list.get(2);
        System.out.println(integer);

    }

    @Test
    public void test1() {
        int[][] graph = new int[4][];
        int[] int0 = new int[]{1,2};
        int[] int1 = new int[]{3};
        int[] int2 = new int[]{3};
        int[] int3 = new int[0];
        graph[0] = int0;
        graph[1] = int1;
        graph[2] = int2;
        graph[3] = int3;

        List<List<Integer>> res = allPathsSourceTarget(graph);
        System.out.println(res);
    }

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        /**
         * 先写汉语，后写英语。图的遍历（BFS先搞下）
         * 1. List<List<Integer>> res = xxx;
         *    Queue<List<Integer>> queue;
         *    Integer end = graph.size - 1;
         * 2. 把入口加入到queue中。
         * 3. while queue不为空
         *    3.1 获得queue.size()
         *    3.2 for queue.size
         *        a List字符串 = queue.poll;
         *        b List字符串的最后一个数字
         *        c 如果最后一个数字是 end，加到res。
         *          否则，List leafs = graph[最后一个数字]
         *               List字符串copy.add 各个leaf
         *               然后add到queue里面。
         * 4. return res;
         *
         *
         */
        // 1
        List<List<Integer>> res = new ArrayList<>();
        Queue<List<Integer>> queue = new LinkedList<>();
        Integer end = graph.length - 1;
        // 2
        queue.offer(Arrays.asList(0));

        // 3
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                List<Integer> poll = queue.poll();
                int length = poll.size();
                Integer leaf = poll.get(length - 1);
                if (leaf.equals(end)) {
                    res.add(poll);
                } else {
                    int[] ints = graph[leaf];
                    for (int anInt : ints) {
                        List<Integer> integers = new ArrayList<>(poll);
                        integers.add(anInt);
                        queue.offer(integers);
                    }
                }
            }
        }
        return res;
    }
}
