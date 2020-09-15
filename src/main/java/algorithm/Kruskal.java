package algorithm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 克鲁斯卡尔最小生成树
 */
public class Kruskal {

    /*-------------并查集-------------------*/
    private int[] fm;
    private int[] size;

    private void makeSet(int n) {
        fm = new int[n + 1];
        size = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            fm[i] = i;
            size[i] = 1;
        }
    }

    private int find(int node) {
        int fa = fm[node];
        if (fa != node) {
            fa = find(fa);
        }
        fm[node] = fa;
        return fa;
    }


    private boolean isSameSet(int a, int b) {
        return find(a) == find(b);
    }

    private void union(int a, int b) {
        if (a < 0 || b < 0) return;
        int fa = find(a);
        int fb = find(b);
        if (fa != fb) {
            int sizeA = size[a];
            int sizeB = size[b];
            if (sizeA > sizeB) {
                fm[fb] = fa;
                size[fa] = sizeA + sizeB;
            } else {
                fm[fa] = fb;
                size[fb] = sizeA + sizeB;
            }
        }
    }

    /**
     * @param n
     * @param pairs
     * @param weights
     * @return
     */
    private List<int[]> kruskal(int n, int[][] pairs, int[] weights) {
        List<int[]> ret = new ArrayList<>();
        makeSet(n);
        List<int[]> sides = new ArrayList<>();
        for (int i = 0; i < pairs.length; i++) {
            int[] sideWithWeight = {pairs[i][0], pairs[i][1], weights[i]};
            sides.add(sideWithWeight);
        }
        //按照权重升序排列
        sides.sort(Comparator.comparingInt(o -> o[2]));
        for (int[] side : sides) {
            //如果添加该行形成环
            int a = side[0], b = side[1];
            if (!isSameSet(a, b)) {
                union(a, b);
                ret.add(side);
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        Kruskal kruskal = new Kruskal();
        List<int[]> kruskal1 = kruskal.kruskal(5, new int[][]{{0, 1}, {1, 2}, {2, 4}, {4, 3}, {3, 0}, {3, 5}}, new int[]{3, 22, 18, 3, 5, 4});
        for (int[] ints : kruskal1) {
            System.out.println(ints[0] + "----" + ints[1] + "------" + ints[2]);
        }
    }

}
