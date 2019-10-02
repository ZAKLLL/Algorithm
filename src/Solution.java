import java.util.*;

/**
 * @program: suanfa
 * @description: LeetCode 新类
 * @author: ZakL
 * @create: 2019-09-25 21:24
 **/
public class Solution {

    public Node connect(Node root) {
        if (root == null || root.left == null) return root;
        root.left.next = root.right;
        if (root.next != null) {
            root.right.next = root.next.left;
        }
        connect(root.left);
        connect(root.right);
        return root;
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode start;
        ListNode end;
        start = dummy;
        end = dummy;
        for (int i = 0; i < n; i++) {
            end = end.next;
        }
        System.out.println(end.val);
        while (end.next != null) {
            end = end.next;
            start = start.next;
        }
        start.next = start.next.next;
        return dummy.next;
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        return 0;
    }

    public void solve(char[][] board) {
        if (board.length == 0) return;
        int w = board.length;
        int h = board[0].length;
        //判断该字符是否为边界字符
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                boolean isBorder = i == 0 || i == w - 1 || j == 0 || j == h - 1;
                if (isBorder && board[i][j] == 'O') dfs(board, i, j);
            }
        }
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                if (board[i][j] == 'O') board[i][j] = 'X';
            }
        }
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                if (board[i][j] == '#') board[i][j] = 'O';
            }
        }
    }


    private void dfs(char[][] board, int i, int j) {
        //使用‘#’来标记与边界关联的O
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] == '#' || board[i][j] == 'X')
            return;

        board[i][j] = '#';
        dfs(board, i - 1, j); // 向上查找
        dfs(board, i + 1, j); // 向下查找
        dfs(board, i, j - 1); // 向左查找
        dfs(board, i, j + 1); // 向右查找
    }

    public List<List<String>> partition(String s) {

        return null;
    }

    List<Integer> res;

    public List<Integer> inorderTraversal(TreeNode root) {
        res = new LinkedList<>();
        help(root);
        return null;
    }

    public void help(TreeNode treeNode) {
        if (treeNode == null) return;
        res.add(treeNode.val);
        help(treeNode.left);
        help(treeNode.right);
    }

    public static String multiply(String num1, String num2) {
        if (num1.length() == 1 && num1.equals("0") || num2.length() == 1 && num2.equals("0")) return "0";
        char[] chars1 = num1.toCharArray();
        char[] chars2 = num2.toCharArray();
        List<String> strings = new ArrayList<>();
        int carry;
        StringBuilder sb = new StringBuilder();
        for (int i = num1.length() - 1; i >= 0; i--) {
            carry = 0;
            sb.delete(0, sb.length());
            //补0
            for (int k = 0; k < num1.length() - 1 - i; k++) {
                sb.append('0');
            }
            for (int j = num2.length() - 1; j >= 0; j--) {
                int temp = (chars1[i] - '0') * (chars2[j] - '0') + carry;
                carry = temp / 10;
                sb.append(temp % 10);
            }
            if (carry != 0) sb.append(carry);
            strings.add(sb.toString());
        }
        sb.delete(0, sb.length());
        int temp;
        carry = 0;
        for (int i = 0; i < strings.get(strings.size() - 1).length(); i++) {
            temp = 0;
            for (String s : strings) {
                if (s.length() >= i + 1) {
                    temp += s.charAt(i) - 48;
                }
            }
            temp += carry;
            carry = temp / 10;
            sb.append(temp % 10);
        }
        if (carry != 0) sb.append(carry);
        return sb.reverse().toString();
    }

    public static int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas.length == 1) return gas[0] >= cost[0] ? 0 : -1;
        int currentGas;
        outer:
        for (int i = 0; i < gas.length; i++) {
            currentGas = 0;
            if (gas[i] < cost[i]) continue;
            currentGas += gas[i];
            currentGas -= cost[i];
            if (i != gas.length - 1) {
                for (int j = i + 1; j < gas.length; j++) {
                    currentGas += gas[j];
                    if (currentGas < cost[j]) continue outer;
                    else if (j == gas.length - 1 && i == 0 && currentGas >= cost[j]) return 0;
                    currentGas -= cost[j];
                }
            }
            for (int k = 0; k < i; k++) {
                if (k == i - 1) {
                    currentGas += gas[k];
                    if (currentGas >= cost[k]) return i;
                }
                currentGas += gas[k];
                if (currentGas < cost[k]) continue outer;
                currentGas -= cost[k];
            }

        }
        return -1;
    }

    public static boolean publicStr(String a, String b) {
        if (a.length() != b.length()) return false;
        if (!b.contains(String.valueOf(a.charAt(0)))) return false;
        for (int i = 0; i <= a.length(); i++) {
            String tmp = a.substring(0, i + 1);
            if (!b.contains(tmp)) {
                return tmp.length() - 1 == b.length() - b.indexOf(tmp.substring(0, tmp.length() - 1));
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int k = 3;//t


    }

}

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

class LRUCache {


    private List<Integer> ops;
    private int size;
    private HashMap<Integer, Integer> map = new HashMap<>();

    public LRUCache(int capacity) {
        ops = new LinkedList<>();
        size = capacity;
    }

    public int get(int key) {
        //判断是否在ops中
        if (!ops.contains(key)) return -1;
        Integer integer = map.get(key);
        //将当前操作更新为最新操作
        ops.remove(ops.indexOf(key));
        ops.add(key);
        return integer;
    }

    public void put(int key, int value) {
        map.put(key, value);
        if (ops.contains(key)) {
            ops.remove(ops.indexOf(key));
        }
        if (ops.size() == size) {
            ops.remove(0);
        }
        ops.add(key);
    }
}
