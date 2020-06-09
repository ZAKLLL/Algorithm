import java.lang.reflect.Array;
import java.util.*;

/**
 * @program: suanfa
 * @description: LeetCode
 * @author: ZakL
 * @create: 2019-09-25 21:24
 **/
public class Solution {
    //    int[][] dir = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    int[] dir = new int[]{1, 0, -1, 0, 1};

    public int[] getStrongest(int[] arr, int k) {
        Arrays.sort(arr);
        int m = arr[((arr.length) - 1) / 2];
        Queue<Integer> pq = new PriorityQueue<>((o1, o2) -> {
            int a = Math.abs(o1 - m);
            int b = Math.abs(o2 - m);
            if (a == b) return o2 - o1;
            return b - a;
        });
        for (int i : arr) {
            pq.add(i);
        }
        int[] res = new int[k];
        for (int i = 0; i < res.length; i++) {
            res[i] = pq.poll();
        }
        return res;
    }
}

class BrowserHistory {
    int curPage;
    LinkedList<String> list;

    public BrowserHistory(String homepage) {
        list = new LinkedList<>();
        list.add(homepage);
        curPage = 0;
    }

    public void visit(String url) {
        list = new LinkedList<>(list.subList(0, ++curPage));
        list.add(url);
    }

    public String back(int steps) {
        curPage = Math.max(curPage - steps, 0);
        return list.get(curPage);
    }

    public String forward(int steps) {
        curPage=Math.min(curPage+steps,list.size()-1);
        return list.get(curPage);
    }
}



