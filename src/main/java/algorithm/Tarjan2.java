package algorithm;

import java.util.*;

/**
 * Tarjan无向图 求割点和桥
 */
public class Tarjan2 {
    private int time = 0;
    private int[] dfn;
    private int[] low;
    //父节点
    private int[] fa;
    private int[][] graph;
    private int V;

    //割点结果集
    private Set<Integer> cutPoint = new HashSet<>();
    //桥结果集
    private List<int[]> bridges = new ArrayList<>();

    public void tarjan(int[][] graph) {
        this.graph = graph;
        this.V = graph.length;
        dfn = new int[V];
        low = new int[V];
        fa = new int[V];
        Arrays.fill(fa, -1);
        for (int x = 0; x < V; x++) {
            //x 点还没有访问过
            if (dfn[x] == 0) {
                dfs(x);
            }
        }
    }

    private void dfs(int x) {
        dfn[x] = low[x] = ++time;
        //当前点在深度搜索树中的子树数量
        int child = 0;
        for (int y = 0; y < V; y++) {
            if (graph[x][y] == 1) {
                if (dfn[y] == 0) {
                    child++;
                    //标记当前y点父亲为x
                    fa[y] = x;
                    dfs(y);
                    //割点case
                    //x是root,且x有两个以上儿子
                    if (fa[x] == -1 && child >= 2) {
                        cutPoint.add(x);
                    }
                    //x不是root,但是x有儿子y,且low[y]>=dfn[x]
                    if (fa[x] != -1 && low[y] >= dfn[x]) {
                        cutPoint.add(x);
                    }
                    //桥的判定
                    if (low[y] > dfn[x]) {
                        bridges.add(new int[]{x, y});
                    }
                    //更新追溯值
                    low[x] = Math.min(low[x], low[y]);

                }
                //儿子到父亲的情况不更新
                else if (y != fa[x]) {
                    low[x] = Math.min(low[x], low[y]);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] arr = new int[][]{{0, 1}, {1, 2}, {2, 3}, {2, 4}, {3, 4}, {3, 1}, {0, 5}, {5, 6}, {4, 2}};
        int[][] graph = new int[7][7];
        for (int[] ints : arr) {
            graph[ints[0]][ints[1]] = 1;
            graph[ints[1]][ints[0]] = 1;
        }
        Tarjan2 tarjan = new Tarjan2();
        tarjan.tarjan(graph);
        for (Integer integer : tarjan.cutPoint) {
            System.out.print(integer + "--");
        }
        System.out.println();
        System.out.println("------------");
        for (int[] bridge : tarjan.bridges) {
            System.out.println(bridge[0] + "---" + bridge[1]);
        }
    }
}
