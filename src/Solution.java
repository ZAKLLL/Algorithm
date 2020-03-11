import DataStructure.TreeNode;

import java.util.*;

/**
 * @program: suanfa
 * @description: LeetCode
 * @author: ZakL
 * @create: 2019-09-25 21:24
 **/
public class Solution {


    public double frogPosition(int n, int[][] edges, int t, int target) {
        if (n == 1) return 1;
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        boolean[] vis = new boolean[n + 1];
        for (int[] edge : edges) {
            if (!map.containsKey(edge[0])) map.put(edge[0], new LinkedList<>());
            if (!map.containsKey(edge[1])) map.put(edge[1], new LinkedList<>());
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }
        dfs(map, 1, 1, t, target, 1, vis);
        return res;
    }

    boolean find;
    double res = 0;

    public void dfs(HashMap<Integer, List<Integer>> map, int num, int level, int t, int target, double probability, boolean[] vis) {
        if (find || level > t || map.get(num) == null || map.get(num).size() == 0) return;
        vis[num] = true;
        List<Integer> integers = map.get(num);
        Iterator<Integer> iterator = integers.iterator();
        while (iterator.hasNext()) {
            if (vis[iterator.next()]) {
                iterator.remove();
                break;
            }
        }
        double v = probability / integers.size();
        for (Integer integer : integers) {
            if (integer == target) {
                List<Integer> integers1 = map.get(integer);
                find = true;
                //叶子节点或者刚好时间
                if (level == t || integers1.size() == 1) {
                    res = v;
                }
                return;
            }
            if (vis[integer]) continue;
            dfs(map, integer, level + 1, t, target, v, vis);
            if (find) return;
        }
        vis[num] = false;
    }

    public int maxProfit(int[] prices) {
        int maxValue = 0, min = Integer.MAX_VALUE;
        for (int price : prices) {
            if (price < min) min = price;
            else maxValue = Math.max(maxValue, price - min);
        }
        return maxValue;
    }

    int maxPath = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        h(root);
        return maxPath;
    }

    public int h(TreeNode root) {
        if (root == null) return 0;
        int l = 0, r = 0;
        if (root.left != null) {
            l = h(root.left) + 1;
        }
        if (root.right != null) {
            r = h(root.right) + 1;
        }
        maxPath = Math.max(maxPath, l + r);
        return l + r;
    }

    public int numPairsDivisibleBy60(int[] time) {
        if (time == null || time.length == 0) return 0;
        int res = 0;
        int[] cache = new int[60];
        for (int i : time) {
            int a = i % 60;
            if (a == 0) res++;
            else {
                if (cache[60 - a] > 0) {
                    res++;
                } else {
                    cache[a]++;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        new Solution().numPairsDivisibleBy60(Utils.strtoArr("[30,20,150,100,40]"));
    }

}

