package algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Tarjan求有向图 强连通分量
 */
public class Tarjan1 {
    private int time = 0;
    private Stack<Integer> stack = new Stack<>();
    private List<List<Integer>> ret = new ArrayList<>();
    private boolean[] inStack;
    private int[] dfn;
    private int[] low;
    private int[][] graph;
    private int V;

    public void tarjan(int[][] graph) {
        this.graph = graph;
        this.V = graph.length;
        inStack = new boolean[V];
        dfn = new int[V];
        low = new int[V];
        for (int x = 0; x < V; x++) {
            if (dfn[x] == 0) {
                dfs(x);
            }
        }
    }

    private void dfs(int x) {
        stack.push(x);
        inStack[x] = true;
        dfn[x] = low[x] = ++time;
        for (int y = 0; y < V; y++) {
            if (graph[x][y] == 1) {
                if (dfn[y] == 0) {
                    dfs(y);
                    low[x] = Math.min(low[x], low[y]);
                } else if (inStack[y]) {
                    low[x] = Math.min(low[x], low[y]);
                }
            }
        }
        if (dfn[x] == low[x]) {
            List<Integer> l = new ArrayList<>();
            while (stack.peek() != x) {
                Integer pop = stack.pop();
                l.add(pop);
                inStack[pop] = false;
            }
            l.add(stack.pop());
            ret.add(l);
        }
    }

    public static void main(String[] args) {
        int[][] arr = new int[][]{{0, 1}, {1, 2}, {2, 3}, {2, 4}, {3, 4}, {3, 1}, {0, 5}, {5, 6}, {6, 0}, {4, 2}};
        int[][] graph = new int[7][7];
        for (int[] ints : arr) {
            graph[ints[0]][ints[1]] = 1;
        }
        Tarjan1 tarjan = new Tarjan1();
        tarjan.tarjan(graph);
        for (List<Integer> integers : tarjan.ret) {
            for (Integer integer : integers) {
                System.out.print(integer + "-");
            }
            System.out.println();
        }
    }
}
