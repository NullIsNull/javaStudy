package graphdfs;

import java.util.*;

public class Solution133 {

    class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int val) {
            this.val = val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int val, ArrayList<Node> _neighbors) {
            this.val = val;
            neighbors = _neighbors;
        }
    }

    //0
    Map<Integer, Node> map = new HashMap<>();
    Set<Node> visited = new HashSet<>();
    public Node cloneGraph(Node node) {
        /**
         * 先写汉语，后写英语。(图论DFS)
         * 中心思想就是：深度遍历，前序遍历。
         * 0. 弄个map，把已经clone出来的节点放到map里面，不要重复clone。Map<Integer, Node> map = ...
         * 1. 先把根节点给copy出来（如果map中不存在，就copy，如果存在，就复用map中已经copy的对象）。
         * 1.1 并且标识这个节点为visited节点。如果递归传递这个节点为visited过了，表示已经扩展过了，不能重复扩展，但是这个节点又是别人的孩子节点，直接返回该节点的引用。
         * 2. 然后for这个节点的孩子节点，把孩子节点一个一个的给copy出来(如果map中存在就不copy，从map中取)，每个返回一个reference。然后add到父亲的copy对象中去。
         * 3. 然后返回此父亲节点的reference。
         * dfs的入参就是Node，返回一个reference，入口调用在这个函数的外面。本身这个函数就可以作为递归函数。
         *
         * 总结：我采用了中行怎么玩的模式解决了这个问题。
         */
        // 0特殊场景
        if (node == null) {
            return null;
        }
        // 1
        Node copyRoot = null;
        if (!map.containsKey(node.val)) {
            copyRoot = new Node(node.val);
            map.put(node.val, copyRoot);
        } else {
            copyRoot = map.get(node.val);
        }
        if (visited.contains(node)) {
            return copyRoot; //已经当过top节点展开过了，就不需要再次展开，否则会死循环。
        } else {
            visited.add(node);
        }
        //2
        List<Node> copyRootNeighbors = copyRoot.neighbors;
        for (Node neighbor : node.neighbors) {
            Node copyChildren = cloneGraph(neighbor);
            copyRootNeighbors.add(copyChildren);
        }
        //3
        return copyRoot;
    }
}
