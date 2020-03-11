import DataStructure.TreeNode;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;

/**
 * @program: suanfa
 * @description:
 * @author: ZakL
 * @create: 2019-12-20 10:34
 **/
public class Utils {
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static TreeNode generateTreeNode(String data) {
        LinkedList<TreeNode> treeNodes = new LinkedList<>();
        data = data.substring(1, data.length() - 1);
        if (data.length() == 0) return null;
        String[] split = data.split(",");
        for (String s : split) {
            if (s.equals("null")) treeNodes.add(null);
            else treeNodes.add(new TreeNode(Integer.parseInt(s)));
        }
        TreeNode root = treeNodes.poll();
        LinkedList<TreeNode> used = new LinkedList<>();
        used.add(root);
        while (treeNodes.size() > 0) {
            int len = used.size();
            for (int i = 0; i < len * 2; i++) {
                if (treeNodes.isEmpty()) break;
                used.add(treeNodes.poll());
            }
            int index = len;
            for (int i = 0; i < len; i++) {
                if (index < used.size()) {
                    used.get(i).left = used.get(index++);
                }
                if (index < used.size()) {
                    used.get(i).right = used.get(index++);
                }
            }
            for (int i = 0; i < len; i++) {
                used.removeFirst();
            }
            used.removeIf(Objects::isNull);
        }
        return root;
    }

    public static String binarySerialize(TreeNode root) {
        if (root == null) return "[]";
        LinkedList<TreeNode> list = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        list.add(root);
        while (!list.isEmpty()) {
            TreeNode treeNode = list.removeFirst();
            if (treeNode == null) sb.append("null").append(",");
            else {
                sb.append(treeNode.val).append(",");
                list.add(treeNode.left);
                list.add(treeNode.right);
            }
        }
        while (sb.length() >= 5 && sb.substring(sb.length() - 5, sb.length()).equals("null,")) {
            sb.delete(sb.length() - 5, sb.length());
        }
        sb.replace(sb.length() - 1, sb.length(), "]");
        return sb.toString();
    }

    //7
    //[[1,2],[1,3],[1,7],[2,4],[2,6],[3,5]]
    //2
    //4
    public static int[][] strToDArr(String str) {
        str = str.substring(1, str.length() - 1);
        str = str.replace("[", "").replace("]", "");
        String[] split = str.split(",");
        int[][] res = new int[split.length / 2][2];
        int index = 0;
        for (int i = 0; i < res.length; i++) {
            res[i][0] = Integer.parseInt(split[index++]);
            res[i][1] = Integer.parseInt(split[index++]);
        }
        return res;
    }

    public static int[] strtoArr(String str) {
        str = str.substring(1, str.length() - 1);
        str = str.replace("[", "").replace("]", "");
        String[] split = str.split(",");
        int[] res = new int[split.length];
        int index = 0;
        for (int i = 0; i < res.length; i++) {
            res[i] = Integer.parseInt(split[index++]);
        }
        return res;
    }

    public static void main(String[] args) {
    }
}
