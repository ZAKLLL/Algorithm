package DataStructure;

/**
 * @program: suanfa
 * @description: 树状数组
 * @author: ZakL
 * @create: 2020-02-23 16:46
 **/
public class TreeArray {
    //树状数组
    private int[] treeArr;
    //原数组，初始化使用
    private int[] sur;

    public TreeArray(int[] arr) {
        this.sur = arr;
        this.treeArr = new int[arr.length + 1];
        init();
    }


    private void init() {
        for (int i = 1; i < treeArr.length; i++) {
            int count = lowBit(i);
            int sum = 0;
            for (int j = i; j >= i + 1 - count; j--) {
                sum += sur[j - 1];
            }
            treeArr[i] = sum;
        }
    }

    public int query(int l, int r) {
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

    public void add(int pos, int v) {
        for (int i = pos; i < treeArr.length; i += lowBit(i)) {
            treeArr[i] += v;
        }
    }

    private int lowBit(int x) {
        return x & (-x);
    }


}
