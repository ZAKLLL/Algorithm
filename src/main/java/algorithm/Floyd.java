package algorithm;

/**
 * @author ZhangJiaKui
 * @classname Floyd
 * @description Floyd最短路径算法
 * @date 2020/10/15 8:58
 */
public class Floyd {

    /**
     * 无向图求最短路径
     * graph[i][j]的值为ij两点直接相连的权值
     * 不相连的情况 graph[i][j]==Integer.MAX_VALUE;
     *
     * @param graph
     * @return
     */
    public int[][] UndirectedFloyd(int[][] graph) {
        int n = graph.length;
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == j || i == k || j == k || graph[i][k] == Integer.MAX_VALUE || graph[k][j] == Integer.MAX_VALUE)
                        continue;
                    if (graph[i][j] > graph[i][k] + graph[k][i]) {
                        graph[i][j] = graph[i][k] + graph[k][i];
                    }
                }
            }
        }
        return graph;
    }

    public void directedFloyd(int[][] graph) {
        int n = graph.length;
        int[][] path = new int[n][n];
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == j || i == k || j == k || graph[i][k] == Integer.MAX_VALUE || graph[k][j] == Integer.MAX_VALUE)
                        continue;
                    if (graph[i][j] > graph[i][k] + graph[k][i]) {
                        graph[i][j] = graph[i][k] + graph[k][i];
                        path[i][j] = k;
                    }
                }
            }
        }
    }


}
