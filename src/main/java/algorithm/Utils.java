package algorithm;

import DataStructure.ListNode;
import DataStructure.TreeNode;
import org.omg.CORBA.INTERNAL;
import org.omg.CORBA.MARSHAL;

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
    //[[1,2],[1,37],[21,7],[2,4],[2,6],[3,5]]
    //2
    //4
    public static int[][] strToDArr(String str) {
        str = str.trim();
        str = str.substring(1, str.length() - 1);
        //计算子数组的长度
        int ii = 0;
        int len = 1;
        while (ii < str.length() && str.charAt(ii) != ']') {
            if (str.charAt(ii) == ',') len++;
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

    /**
     * 是否为回文数
     *
     * @param s
     * @param l
     * @param r
     * @return
     */
    public static boolean isPalindrome(String s, int l, int r) {
        int len = r - l + 1;
        for (int i = 0; i < len / 2; i++) {
            if (s.charAt(l + i) != s.charAt(r - i)) {
                return false;
            }
        }
        return true;
    }

    public static int getTreeHeight(TreeNode root) {
        return root == null ? 0 : Math.max(getTreeHeight(root.left), getTreeHeight(root.right)) + 1;
    }

    /**
     * 打印二叉树
     *
     * @param root
     */
    public static void printBinaryTree(TreeNode root) {
        int height = getTreeHeight(root);
        int weight = (int) Math.pow(2, height - 1) * 2 - 1;
        List<int[]> list = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<List<TreeNode>> listList = new LinkedList<>();
        while (!queue.isEmpty()) {
            int len = queue.size();
            List<TreeNode> tmp = new ArrayList<>();
            int nullCnt = 0;
            for (TreeNode treeNode : queue) if (treeNode == null) nullCnt++;
            if (nullCnt == queue.size()) {
                break;
            }
            for (int i = 0; i < len; i++) {
                TreeNode poll = queue.poll();
                tmp.add(poll);
                if (poll != null) {
                    queue.add(poll.left);
                    queue.add(poll.right);
                } else {
                    queue.add(null);
                    queue.add(null);
                }
            }
            listList.add(tmp);
        }
        int[] pre = new int[weight];
        Arrays.fill(pre, Integer.MAX_VALUE);
        int index = 0;
        Collections.reverse(listList);
        for (TreeNode treeNode : listList.get(0)) {
            pre[index] = treeNode == null ? Integer.MIN_VALUE : treeNode.val;
            index += 2;
        }
        int s = 0;
        int f = 2;
        list.add(pre);
        for (int i = 1; i < listList.size(); i++) {
            int ts = s;
            int tf = f;
            int[] arr = new int[weight];
            Arrays.fill(arr, Integer.MAX_VALUE);
            for (TreeNode treeNode : listList.get(i)) {
                arr[(ts + tf) / 2] = treeNode == null ? Integer.MIN_VALUE : treeNode.val;
                int a = tf + 1;
                while (a < weight && pre[a] == Integer.MAX_VALUE) {
                    a++;
                }
                ts = a;
                a++;
                while (a < weight && pre[a] == Integer.MAX_VALUE) {
                    a++;
                }
                tf = a;
            }
            list.add(arr);
            int a = 0;
            while (a < weight && arr[a] == Integer.MAX_VALUE) {
                a++;
            }
            s = a;
            a++;
            while (a < weight && arr[a] == Integer.MAX_VALUE) {
                a++;
            }
            f = a;
            pre = arr;
        }
        Collections.reverse(list);
        for (int[] ints : list) {
            for (int anInt : ints) {
                if (anInt == Integer.MIN_VALUE || anInt == Integer.MAX_VALUE) {
                    System.out.print("  ");
                } else {
                    System.out.print(anInt);
                }
            }
            System.out.println();
            System.out.println();
            System.out.println();
        }
    }

    /**
     * 二分查找左边界/插入位置
     *
     * @param arr
     * @param target
     * @return
     */
    public static int binarySearch_left_bound(int[] arr, int target) {
        int l = 0, r = arr.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] < target) l = mid + 1;
            else r = mid;
        }
        return l;
    }


    public int binarySearch_right_bound(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = l + (r - l + 1) / 2; // +1 其实是上取整，避免最后left 和right对应值相等且等于target，这样mid还是等于left，然后判断赋值left = mid ，这样就死循环了
            if (nums[mid] > target) {
                r = mid - 1;
            } else {
                l = mid;
            }
        }
        return l;
    }

    /**
     * 最大公约数算法
     *
     * @param a
     * @param b
     * @return
     */
    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }



}
