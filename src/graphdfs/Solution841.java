package graphdfs;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution841 {

    /**
     * 先写汉语，后写英语。图论DFS。
     * 中心思想：先从0房间开始进，然后把这个0房间已经进入的状态给记录下来。然后遍历这个房间的钥匙，for循环深度遍历每个房间。直到dfs返回把每个可达的房间
     * 都给返回。
     * 1. 定义一个visited,表示这个已经展开过的房间号。Set<Integer> visited = ...
     * 2. 题目给的函数，本身不能作为dfs函数，需要重新定义一个。 void dfs(Integer(房间号)， visited， rooms)
     *    1) 前序遍历，先把这个房间号标识为visited。
     *    2）然后找到这个房间里面的钥匙，for循环遍历每个钥匙对应的房间，调用dfs。
     * 3. return visited.size == rooms.size
     */
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        //1
        Set<Integer> visited = new HashSet<>();
        //2 dfs入口
        dfs(0, visited, rooms);
        return visited.size() == rooms.size();
    }

    void dfs(Integer room, Set<Integer> visited, List<List<Integer>> rooms) {
        //2 1) 前序遍历
        if (visited.contains(room)) {
            return;
        }
        visited.add(room); // 前序遍历先干的活
        List<Integer> keys = rooms.get(room);
        for (Integer key : keys) {
            dfs(key, visited, rooms);
        }
    }

}
