package DataStructure;

/**
 * @program: suanfa
 * @description: 树状数组
 * @author: ZakL
 * @create: 2020-02-23 16:46
 **/
public class TreeArray {

    private int[] treeArr;
    private int[] sur;

    public TreeArray(int[] arr) {
        this.sur = arr;
        this.treeArr = new int[arr.length + 1];
        init();
    }


    private void init() {
        for (int i = 1; i < treeArr.length; i++) {
            int i1 = lowBit(i);
            int sum = 0;
            for (int j = i; j >= i + 1 - i1; j--) {
                sum += sur[j - 1];
            }
            treeArr[i] = sum;
        }
    }

    private int query(int l, int r) {
        int sumr = 0, suml = 0;
        while (r > 0) {
            sumr += treeArr[r];
            r -= lowBit(r);
        }
        while (l > 0) {
            suml += treeArr[l];
            l -= lowBit(l);
        }
        return sumr - suml;
    }

    private void add(int pos, int v) {
        for (int i = pos; i < treeArr.length; i += lowBit(i)) {
            treeArr[i] += v;
        }
    }

    private int lowBit(int x) {
        return x & (-x);
    }

    public static void main(String[] args) {
        TreeArray treeArray = new TreeArray(new int[]{1, 2, 3, 4, 5, 6, 7, 8});
        treeArray.add(4, 1);
    }

}
