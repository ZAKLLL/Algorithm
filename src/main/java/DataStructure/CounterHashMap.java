package DataStructure;

import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * @author ZhangJiaKui
 * @classname CounterHashMap
 * @description TODO
 * @date 6/29/2021 9:37 AM
 */
public class CounterHashMap<K> extends AbstractMap<K, Integer>
        implements Map<K, Integer> {

    private final Map<K, Integer> counterMap = new HashMap<>();

    public CounterHashMap(List<K> keys) {
        keys.forEach(key -> counterMap.put(key, counterMap.getOrDefault(key, 0) + 1));
    }

    @Override
    public Integer get(Object key) {
        return counterMap.get(getOrDefault(key, 0));
    }

    public int put(K key) {
        counterMap.put(key, counterMap.getOrDefault(key, 0) + 1);
        return counterMap.get(key);
    }

    @NotNull
    @Override
    public Set<Entry<K, Integer>> entrySet() {
        return counterMap.entrySet();
    }
}
