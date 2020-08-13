package algorithm;

import DataStructure.ListNode;
import DataStructure.TreeNode;

import java.io.*;
import java.util.*;

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
        data = data.trim();
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

    public static ListNode genListNode(String str) {
        str = str.trim();
        ListNode preNode = new ListNode(0);
        ListNode root = preNode;
        str = str.replace("[", "").replace("]", "");
        String[] split = str.split(",");
        for (String s : split) {
            root.next = new ListNode(Integer.parseInt(s));
            root = root.next;
        }
        return preNode.next;
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
        str = str.trim();
        str = str.substring(1, str.length() - 1);
        //计算子数组的长度
        int ii = 0;
        int len = 0;
        while (ii < str.length() && str.charAt(ii) != ']') {
            if (Character.isDigit(str.charAt(ii))) len++;
            ii++;
        }
        str = str.replace("[", "").replace("]", "");
        String[] split = str.split(",");
        int[][] res = new int[split.length / len][len];
        int index = 0;
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < len; j++) {
                res[i][j] = Integer.parseInt(split[index++]);
            }
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

    /**
     * @param str
     * @return List<String>
     */
    public static List<String> strToList(String str) {
        str = str.trim();
        str = str.substring(1, str.length() - 1);
        if (str.length() == 0) return new ArrayList<>();
        List<String> res = new ArrayList<>();
        String[] split = str.split(",");
        for (String s : split) {
            s = s.trim();
            s = s.substring(1, s.length() - 1);
            res.add(s);
        }
        return res;
    }


    static List<String> paths;

    static void imagePathReplace() {
        paths = new ArrayList<>();
        File f = new File("C:\\Users\\Administrator\\Documents\\Study_notes");
        //C:\Users\Administrator\Documents\Study_notes\images
        h(f);
        List<String[]> list = new ArrayList<>();
        for (String path : paths) {
            list.add(new String[]{path, getRePath(path.substring(path.indexOf("Study_notes")))});
        }
        for (String[] strings : list) {
            modify(strings[0], strings[1]);
        }
    }

    static void modify(String path, String target) {
        String path2 = path.substring(0, path.length() - 3) + "2.md";
        try (BufferedReader in = new BufferedReader(new FileReader(path)); PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(path2)))) {
            String line;
            while ((line = in.readLine()) != null) {
                if (line.contains("C:\\Users\\HP\\AppData\\Roaming\\Typora\\typora-user-images\\")) {
                    line = line.replace("C:\\Users\\HP\\AppData\\Roaming\\Typora\\typora-user-images\\", target);
                }
                out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        File file = new File(path);
        if (!file.delete()) return;
        boolean b = new File(path2).renameTo(file);
        System.out.println(b);

    }

    static private String getRePath(String imagesPath) {
        String[] split = imagesPath.split("\\\\");
        int length = split.length - 2;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append("../");
        }
        sb.append("images/");
        return sb.toString();

    }

    private static void h(File f) {
        if (f.isDirectory()) {
            Arrays.stream(Objects.requireNonNull(f.listFiles())).forEach(Utils::h);
        } else {
            if (f.getAbsolutePath().endsWith(".md"))
                paths.add(f.getAbsolutePath());
        }
    }

    /**
     * 快速生成含有初始kv值的map
     */
    public static <E, T> Map<E, T> mapUtils(E[] keys, T[] values) {
        if (keys == null || values == null) return new HashMap<E, T>();
        else if (keys.length != values.length) {
            new Exception("键值对数量不匹配").printStackTrace();
        }
        Map<E, T> map = new HashMap<>();
        for (int i = 0; i < keys.length; i++) {
            map.put(keys[i], values[i]);
        }
        return map;
    }

    public static <E> List<E> newArrayList(E... args) {
        List<E> list = new ArrayList<>();
        Collections.addAll(list, args);
        return list;
    }

    public static <E> Map<E, Integer> countMap(Collection<E> params) {
        Map<E, Integer> map = new HashMap<>();
        for (E param : params) {
            map.put(param, map.getOrDefault(param, 0) + 1);
        }
        return map;
    }

    public static boolean isPalindrome(String s, int l, int r) {
        int len = r - l + 1;
        for (int i = 0; i < len / 2; i++) {
            if (s.charAt(l + i) != s.charAt(r - i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {



    }
}
