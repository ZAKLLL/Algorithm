package algorithm;

import javafx.util.Pair;

import java.util.*;

/**
 * @Classname Dijkstra
 * @Description Dijkstra算法模板
 * @Date 2020/7/12 20:44
 * @Created by zhangjiakui
 */
public class Dijkstra {
    public HashMap<Character, Integer> init_distance(HashMap<Character, HashMap<Character, Integer>> graph, Character s) {
        HashMap<Character, Integer> distance = new HashMap<>();
        distance.put(s, 0);
        for (Character vertex : graph.keySet()) {
            if (s != vertex) {
                distance.put(vertex, Integer.MAX_VALUE);
            }
        }
        return distance;
    }

    public HashMap<Character, Integer> dijkstra(HashMap<Character, HashMap<Character, Integer>> graph, Character s) {
        Queue<Pair<Integer, Character>> pq = new PriorityQueue<>(Comparator.comparingInt(Pair::getKey));
        pq.add(new Pair<>(0, s));
        Set<Character> seen = new HashSet<>();
        HashMap<Character, Character> parent = new HashMap<>();
        HashMap<Character, Integer> distance = init_distance(graph, s);
        while (!pq.isEmpty()) {
            Pair<Integer, Character> poll = pq.poll();
            int dist = poll.getKey();
            Character vertex = poll.getValue();
            seen.add(vertex);
            Set<Character> nodes = graph.get(vertex).keySet();
            for (Character w : nodes) {
                if (!seen.contains(w)) {
                    int curDist = dist + graph.get(vertex).get(w);
                    if (curDist < distance.get(w)) {
                        distance.put(w, curDist);
                        pq.add(new Pair<>(curDist, w));
                        parent.put(w, vertex);
                    }
                }
            }
        }
        return distance;
    }
}
