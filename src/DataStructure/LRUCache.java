package DataStructure;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @program: suanfa
 * @description:
 * @author: ZakL
 * @create: 2019-10-02 19:21
 **/
public class LRUCache {
    private List<Integer> ops;
    private int size;
    private HashMap<Integer, Integer> map = new HashMap<>();

    public LRUCache(int capacity) {
        ops = new LinkedList<>();
        size = capacity;
    }

    public int get(int key) {
        //判断是否在ops中
        if (!ops.contains(key)) return -1;
        Integer integer = map.get(key);
        //将当前操作更新为最新操作
        ops.remove(ops.indexOf(key));
        ops.add(key);
        return integer;
    }

    public void put(int key, int value) {
        map.put(key, value);
        if (ops.contains(key)) {
            ops.remove(ops.indexOf(key));
        }
        if (ops.size() == size) {
            ops.remove(0);
        }
        ops.add(key);
    }
}
