package graphbfs;

import org.junit.Test;

import java.util.*;

public class Solution863 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    @Test
    public void test1() {
        TreeNode treeNode0 = new TreeNode(0);
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode root = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode6 = new TreeNode(6);
        TreeNode treeNode7 = new TreeNode(7);
        TreeNode treeNode8 = new TreeNode(8);

        root.left = treeNode5;
        root.right = treeNode1;

        treeNode5.left = treeNode6;
        treeNode5.right = treeNode2;

        treeNode2.left = treeNode7;
        treeNode2.right = treeNode4;

        treeNode1.left = treeNode0;
        treeNode1.right = treeNode8;

        List<Integer> res = distanceK(root, treeNode5, 2);
        System.out.println(res);
    }

    @Test
    public void test2() {
        TreeNode root = new TreeNode(1);

        List<Integer> res = distanceK(root, root, 3);
        System.out.println(res);
    }

    @Test
    public void test3() {
        TreeNode root = new TreeNode(0);
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        root.left = treeNode2;
        root.right = treeNode1;
        treeNode1.left = treeNode3;
        List<Integer> res = distanceK(root, treeNode3, 3);
        System.out.println(res);
    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        /**
         * 先写汉语，后写英语。图论BFS。
         * 1. 先把树的结构，转换成图的结构。Map<节点key, List<Integer>>，用BFS遍历树的方式转，注意出度不要漏。
         * 2. 先把target放入Queue<Integer> queue中，然后向外扩延k次即可。
         */
        // 1
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (poll.left != null) {
                    graph.computeIfAbsent(poll.val, k1 -> new ArrayList<>()).add(poll.left.val);
                    // 有向图
                    graph.computeIfAbsent(poll.left.val, k1 -> new ArrayList<>()).add(poll.val);
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    // 干两件事情。1.把有向图给构建好。 2.把右孩子给放入queue中，进行下一次循环。
                    graph.computeIfAbsent(poll.val, k1 -> new ArrayList<>()).add(poll.right.val);
                    // 在反着放。
                    graph.computeIfAbsent(poll.right.val, k1 -> new ArrayList<>()).add(poll.val);
                    // 2
                    queue.add(poll.right);
                }
            }
        }
        // 2
        Queue<Integer> queueGraph = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queueGraph.add(target.val);
        visited.add(target.val);
        int level = -1;
        List<Integer> res = new ArrayList<>();
        while (!queueGraph.isEmpty()) {
            level++;
            int size = queueGraph.size();
            for (int i = 0; i < size; i++) {
                Integer poll = queueGraph.poll();
                List<Integer> integers = graph.getOrDefault(poll, new ArrayList<>());
                if (level == k) {
                    res.add(poll);
                } else {
                    for (int j = 0; j < integers.size(); j++) {
                        if (!visited.contains(integers.get(j))) {
                            queueGraph.add(integers.get(j));
                            visited.add(integers.get(j));
                        }
                    }
                }
            }
            if (level == k) {
                return res;
            }
        }
        return res;
    }
}
