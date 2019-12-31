import DataStructure.TreeNode;

import java.util.Iterator;
import java.util.LinkedList;

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

    public static TreeNode generateTreeNode(int[] arr) {
        LinkedList<TreeNode> treeNodes = new LinkedList<>();
        for (int i : arr) {
            treeNodes.add(new TreeNode(i));
        }
        TreeNode poll = treeNodes.poll();
        LinkedList<TreeNode> used = new LinkedList<>();
        used.add(poll);
        while (treeNodes.size() > 0) {
            int len = used.size();
            for (int i = 0; i < len * 2; i++) {
                if (treeNodes.isEmpty()) {
                    break;
                }
                used.add(treeNodes.poll());
            }
            int index = len;
            for (int i = 0; i < len; i++) {
                if (index < used.size()) {
                    TreeNode leftNode = used.get(index++);
                    used.get(i).left = leftNode.val == Integer.MIN_VALUE ? null : leftNode;
                }
                if (index < used.size()) {
                    TreeNode rightNode = used.get(index++);
                    used.get(i).right = rightNode.val == Integer.MIN_VALUE ? null : rightNode;
                }
            }
            for (int i = 0; i < len; i++) {
                used.removeFirst();
            }
            Iterator<TreeNode> iterator = used.iterator();
            while (iterator.hasNext()) {
                if (iterator.next().val == Integer.MIN_VALUE) {
                    iterator.remove();
                } else break;
            }
        }
        return poll;
    }

    public static void main(String[] args) {
        TreeNode treeNode = generateTreeNode(new int[]{1, Integer.MIN_VALUE, 3, 4, 5, Integer.MIN_VALUE, 6, 8});
    }
}
