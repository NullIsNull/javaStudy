package graphbfs;

import org.junit.Test;

import java.util.*;

public class Solution172 {

    @Test
    public void test1() {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = new ArrayList<>();
        wordList.add("hot");
        wordList.add("dot");
        wordList.add("dog");
        wordList.add("lot");
        wordList.add("log");
        wordList.add("cog");
        int res = ladderLength(beginWord, endWord, wordList);
        System.out.println(res);
    }

    @Test
    public void test2() {
        String beginWord = "hot";
        String endWord = "dog";
        List<String> wordList = new ArrayList<>();
        wordList.add("hot");
        wordList.add("dog");
        int res = ladderLength(beginWord, endWord, wordList);
        System.out.println(res);
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        /**
         * 先写汉语，后写英语。 图论（BFS解决）
         * 0 如果endWord不在wordList中，return 0；
         * 1. 先把beginWord加入到wordList中，用Map构建图。构建一个什么图？构建一个无向图Map<String, List<String>> graph = ...。
         * 2. 2.1 Set<String> visited = ..., Queue queue = ...
         *    2.2先把第一个单词加入queue，然后让其变成visited。
         * 3. int level=1
         *    3.1 while queue不为空
         *        a. int size = queue的大小
         *        b. for size 进行poll一个一个处理
         *           1. 如果poll出来的跟endWord相同
         *              那么就return level
         *              否则，如果它的子没有被visited，就offer到queue里面，并标识为visited。
         *        c. level++
         * 4. return 0;
         *
         */
        // 0
        if (!wordList.contains(endWord)) {
            return 0;
        }
        // 1
        if (!wordList.contains(beginWord)) {
            wordList.add(beginWord);
        }
        Map<String, List<String>> graph = new HashMap<>();
        int n = wordList.size();
        for (int i = 0; i < n - 1; i++) {
            String key = wordList.get(i);
            for (int j = i + 1; j < n; j++) {
                String value = wordList.get(j);
                if (isOneCharacterDiff(key, value)) {
                    List<String> children = graph.get(key);
                    if (children == null) {
                        List<String> temp = new ArrayList<>();
                        temp.add(value);
                        graph.put(key, temp);
                    } else {
                        children.add(value);
                    }

                    List<String> children1 = graph.get(value);
                    if (children1 == null) {
                        List<String> temp = new ArrayList<>();
                        temp.add(key);
                        graph.put(value, temp);
                    } else {
                        children1.add(key);
                    }
                }
            }
        }
        // 2
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        visited.add(beginWord);
        // 3
        int level = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String poll = queue.poll();
                if (poll.equals(endWord)) {
                    return level;
                }
                List<String> leafs = graph.getOrDefault(poll, new ArrayList<>());
                for (String children : leafs) {
                    if (!visited.contains(children)) {
                        queue.offer(children);
                        visited.add(children);
                    }
                }
            }
            level++;
        }
        return 0;
    }

    private boolean isOneCharacterDiff(String s1, String s2) {
        /**
         * 题目约束s1和s2的长度相等。
         * 1. 取s1的长度，遍历这个长度。依次取出s1和s2对应的字符，进行比较，记录不相等的字符的个数。弄个变量，起初不相等的个数是0.
         * 2. 遍历完成后，如果不相等的个数为1，返回true，如果不相等的个数不为1，返回false。
         */
        int diff = 0;
        int length = s1.length();
        for (int i = 0; i < length; i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            if (c1 != c2) {
                diff += 1;
            }
        }
        return diff == 1;
    }
}
