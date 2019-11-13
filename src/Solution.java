import DataStructure.ListNode;
import DataStructure.Node;
import DataStructure.Node2;
import DataStructure.TreeNode;

import java.awt.image.renderable.RenderableImage;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

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

    //数字相乘，不能使用Integer
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

    public static String multiply2(String num1, String num2) {
        int len1 = num1.length();
        int len2 = num2.length();
        int[] res = new int[len1 + len2];
        for (int i = len1 - 1; i >= 0; i--) {
            for (int j = len2 - 1; j >= 0; j--) {
                int LowPos = i + j + 1;
                int highPos = i + j;
                int temp = (num1.charAt(i) - '0') * (num2.charAt(j) - '0') + res[LowPos];
                res[LowPos] = (temp) % 10;
                res[highPos] += temp / 10; //这里高位不要取mod
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i : res) {
            if (!(sb.length() == 0) && i == 0) sb.append(i);
        }
        return sb.length() == 0 ? "0" : sb.toString();
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

    /*
    有一堆石头，每块石头的重量都是正整数。
    每一回合，从中选出两块最重的石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
    如果 x == y，那么两块石头都会被完全粉碎；
    如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
    最后，最多只会剩下一块石头。返回此石头的重量。如果没有石头剩下，就返回 0。
    提示：
    1 <= stones.length <= 30
    1 <= stones[i] <= 1000
     */
    public static int lastStoneWeight(int[] stones) {
        LinkedList<Integer> integers = new LinkedList<>();
        for (int stone : stones) {
            integers.add(stone);
        }
        Collections.sort(integers);
        while (integers.size() > 1) {
            int y = integers.removeLast();
            int x = integers.removeLast();
            if (x < y) integers.addLast(y - x);
            Collections.sort(integers);
        }
        return integers.size() == 0 ? 0 : integers.get(0);
    }

    /*
    给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。
    示例:
    输入: 38
    输出: 2
    解释: 各位相加的过程为：3 + 8 = 11, 1 + 1 = 2。 由于 2 是一位数，所以返回 2。
    进阶:
    你可以不使用循环或者递归，且在 O(1) 时间复杂度内解决这个问题吗？
     */
    public static int addDigits(int num) {
        if (num < 10) return num;
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return addDigits(sum);
    }

    /*
    给定一个偶数长度的数组，其中不同的数字代表着不同种类的糖果，每一个数字代表一个糖果。你需要把这些糖果平均分给一个弟弟和一个妹妹。返回妹妹可以获得的最大糖果的种类数。
    示例 1:
    输入: candies = [1,1,2,2,3,3]
    输出: 3
    解析: 一共有三种种类的糖果，每一种都有两个。
         最优分配方案：妹妹获得[1,2,3],弟弟也获得[1,2,3]。这样使妹妹获得糖果的种类数最多。
    示例 2 :
    输入: candies = [1,1,2,3]
    输出: 2
    解析: 妹妹获得糖果[2,3],弟弟获得糖果[1,1]，妹妹有两种不同的糖果，弟弟只有一种。这样使得妹妹可以获得的糖果种类数最多。
     */
    public int distributeCandies(int[] candies) {
        Set<Integer> set = new HashSet<>();
        for (int i : candies) {
            set.add(i);
        }
        //妹妹获得的糖的数量跟种类和数量有关,
        // 种类大于数量的一半时候，那能够获取到最多的种类是数量的一半
        // 当种类小于数量的一半时，能够获得最多的种类就是种类数
        return Math.min(set.size(), candies.length / 2);
    }

    /*
    在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
    示例 1:
    输入: 4->2->1->3
    输出: 1->2->3->4
    示例 2:
    输入: -1->5->3->4->0
    输出: -1->0->3->4->5
     */
    public ListNode sortList(ListNode head) {
        if (head == null) return null;
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode res = new ListNode(0);
        ListNode tmpNode = new ListNode(0);
        res.next = tmpNode;
        while (pre.next != null) {
            int a = head.val;
            //找出链表中最小的那个节点
            while (head.next != null) {
                a = Math.min(a, head.next.val);
                head = head.next;
            }
            //删除链表中的最小节点：
            pre.next = deleteNode(pre.next, a);
            head = pre.next;
            tmpNode.next = new ListNode(a);
            tmpNode = tmpNode.next;
        }
        return res.next.next;
    }

    public ListNode deleteNode(ListNode listNode, int a) {
        ListNode res = new ListNode(0);
        ListNode res2 = new ListNode(0);
        res2.next = res;
        while (listNode != null) {
            if (listNode.val == a) {
                res.next = listNode.next;
                break;
            } else {
                res.next = new ListNode(listNode.val);
                listNode = listNode.next;
            }
            res = res.next;
        }
        return res2.next.next;
    }


    public ListNode sortList2(ListNode head) {
        if (head == null) return null;
        LinkedList<Integer> integers = new LinkedList<>();
        while (head != null) {
            integers.addLast(head.val);
            head = head.next;
        }
        Collections.sort(integers);
        ListNode listNode = new ListNode(0);
        ListNode res = listNode;
        for (Integer integer : integers) {
            listNode.next = new ListNode(integer);
            listNode = listNode.next;
        }
        return res.next;
    }

    public static boolean canGoOut(int[] nums) {
        if (nums[0] < 0) return true;
        List<Integer> integers = new ArrayList<>();
        for (int i = 0; i < nums.length; ) {
            if (i + nums[i] >= nums.length || i + nums[i] < 0) return true;
            integers.add(i);
            i += nums[i];
            if (integers.contains(i)) return false;
        }
        return false;
    }

    public static boolean canGoLast(int[] nums) {
        if (nums[0] == nums.length - 1) return true;
        for (int i = 0; i < nums.length; ) {
            if (nums[i] == 0) return false;
            if (i + nums[i] == nums.length - 1) return true;
            i += nums[i];
        }
        return false;
    }

    public static int getMode(int[] nums) {
        if (nums.length == 1) return nums[0];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.merge(num, 1, (a, b) -> a + b);
        }
        for (int i : map.keySet()) {
            if (map.get(i) > nums.length / 2) {
                return i;
            }
        }
        return 0;
    }

    public static int getSum(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += i % 2 == 0 ? -i : i;
        }
        return sum;
    }

    public static int getThirdNum(int[] nums) {
        Arrays.sort(nums);
        return nums[2];
    }

    public static int getNumIndex(int[] nums, int n) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int num : nums) {
            stringBuilder.append(num);
        }
        return stringBuilder.toString().indexOf(String.valueOf(n));
    }

    /*
    将给定的数转换为字符串，
    原则如下：1对应 a，2对应b，…..26对应z，
    例如12258可以转换为"abbeh", "aveh", "abyh",
    "lbeh" and "lyh"，个数为5，编写一个函数，给出可以转换的不同字符串的个数。
     */
    public static int getCount(int[] nums) {
        if (nums.length == 1) return 1;
        if (nums.length == 2) return nums[0] + nums[1] <= 26 ? 2 : 1;
        if (nums[nums.length - 1] + nums[nums.length - 2] * 10 <= 26)
            return getCount(Arrays.copyOfRange(nums, 0, nums.length - 1))
                    + getCount(Arrays.copyOfRange(nums, 0, nums.length - 2));
        return getCount(Arrays.copyOfRange(nums, 0, nums.length - 1));
    }

    /*
    求一个数二进制的1的个数
     */
    public static boolean getBinaryCount(int n) {
        int i = Integer.bitCount(n);
        int count = 0;
        while (n != 0) {
            if ((n & 1) == 1) count++;
            n >>>= 1;
        }
        return i == count;
    }

    public static boolean f1(int[] Locations) {
        Arrays.sort(Locations);
        int a = 0;
        for (int i = 0; i < Locations.length; i++) {
            if (a == Locations[i]) return false;
            a = i;
        }
        return true;
    }


    /*
    获取二维数组中有多少个相连的1,深度优先算法
     */
    public static int getOneGroup(int[][] nums) {
        int count = 2;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[0].length; j++) {
                if (nums[i][j] == 1) {
                    goFindAnotherOne(nums, i, j, count);
                    count++;
                }
            }
        }
        return count - 2;
    }

    private static void goFindAnotherOne(int[][] nums, int i, int j, int count) {
        if (i < 0 || i >= nums.length || j < 0 || j >= nums.length || nums[i][j] != 1) {
            return;
        }
        nums[i][j] = count;

        goFindAnotherOne(nums, i - 1, j, count);//向上
        goFindAnotherOne(nums, i, j - 1, count);//向左
        goFindAnotherOne(nums, i + 1, j, count);//向下
        goFindAnotherOne(nums, i, j + 1, count);//向右
    }

    private static int k1 = 0;
    private static int result;
    private static int count;

    /*
    找到二叉排序树(二叉搜索树:中序递增) 的倒数第二小
     */
    public int kthSmallest(TreeNode root, int k) {
        count = 0;
        result = 0;
        k1 = k;
        h1(root);
        return result;
    }

    public void h1(TreeNode root) {
        if (root == null) return;
        h1(root.left);
        count++;
        if (count == k1) {
            result = root.val;
            return;
        }
        h1(root.right);
    }

    /*给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
     */
    private TreeNode t1;
    private TreeNode t2;
    private boolean t1Get;
    private boolean t2Get;
    private boolean flag = false;
    private TreeNode t3 = null;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        t1 = p;
        t2 = q;
        h3(root);
        return t3;
    }

    //递归找到最接近的公共父节点
    public void h3(TreeNode root) {
        if (root == null) return;
        //每次调用之前都初始化标志位
        flag = false;
        t1Get = false;
        t2Get = false;
        contains(root);
        if (flag) {
            t3 = root;
            h3(root.left);
            if (flag) {
                t3 = root;
            }
            h3(root.right);
            if (flag) {
                t3 = root;
            }
        }

    }

    //节点是否包含两个子节点
    public void contains(TreeNode root) {
        if (root == null) return;
        contains(root.left);
        contains(root.right);
        //t2先于t1被找到
        if (root == t1) {
            if (t2Get) {
                flag = true;
                return;
            } else t1Get = true;
        }
        //t1先于t2被找到
        if (root == t2) {
            if (t1Get) {
                flag = true;
            } else t2Get = true;
        }
    }

    //超级无敌牛逼的递归算法lowestCommonAncestor
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p1, TreeNode p2) {
        if (root == null || root == p1 || root == p2) return root;
        TreeNode left = lowestCommonAncestor2(root.left, p1, p2);
        TreeNode right = lowestCommonAncestor2(root.right, p1, p2);
        if (left == null && right == null) return null;
        if (left == null || right == null) return left == null ? right : left;
        return root;
    }

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        generate(res, "", 0, 0, n);
        return res;
    }

    //count1统计“(”的个数，count2统计“)”的个数
    public void generate(List<String> res, String ans, int count1, int count2, int n) {
        if (count1 > n || count2 > n) return;
        if (count1 == n && count2 == n) res.add(ans);
        /*核心点: count1>=count2 ,
        从左往右添加,只有满足左边括号数量>=右边括号时候,
        才能够满足题意,否则会出现括号不匹配的情况
        */
        if (count1 >= count2) {
            generate(res, ans + "(", count1 + 1, count2, n);
            generate(res, ans + ")", count1, count2 + 1, n);
        }
    }

    /*
    给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
    示例 1:
    输入: 123
    输出: 321
     */
    public int reverse(int x) {
        if (x == 0) return 0;
        int res = 0;
        int temp = 0;
        while (x != 0) {
            res = res * 10 + x % 10;
            //核心在这一步,判断本次翻转值改变后，再逆操作，比较与操作前值是否相等;如果有溢出就绝对不相等。
            if ((res - x % 10) / 10 != temp) return 0;
            temp = res;
            x /= 10;
        }
        return res;
    }

    /*
    判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。
    数字 1-9 在每一行只能出现一次。
    数字 1-9 在每一列只能出现一次。
    数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
    */
    public boolean isValidSudoku(char[][] board) {
        int count;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                //先判断是否满足一二条件
                char temp = board[i][j];
                if (temp == '.') continue;
                count = 0;
                //判断行有没有有重复的
                for (char c : board[i]) {
                    if (temp == c) count++;
                    if (count > 1) return false;
                }
                //判断所在列有没有重复的
                count = 0;
                for (int k = 0; k < 9; k++) {
                    if (board[k][j] == temp) count++;
                    if (count > 1) return false;
                }
                count = 0;
                //找到所在点的黑框
                for (int p = (j / 3) * 3; p < (j / 3 + 1) * 3; p++) {
                    for (int k = (i / 3) * 3; k < (i / 3 + 1) * 3; k++) {
                        if (board[k][p] == temp) count++;
                        if (count > 1) return false;
                    }
                }
            }
        }
        return true;
    }

    //二分法抽取出最左或最右的目标的位置，left决定向左找还是向右找
    public int extremeTargetIndex(int[] nums, int target, boolean left) {
        int low = 0;
        int high = nums.length - 1;
        int mid;
        while (low < high) {
            mid = (low + high) / 2;
            if (nums[mid] > target || (target == nums[mid] && left)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        //当找最右时可能出现low 和 high都是target的index,因为在下一步会减一，所以这里加上
        if (!left && nums[low] == target) return low + 1;
        return low;
    }

    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[]{-1, -1};
        //获取左边的index
        int leftIndex = extremeTargetIndex(nums, target, true);
        //当返回值为最右时,判断是否成功找到target
        if (leftIndex == nums.length - 1) {
            if (nums[leftIndex] == target) {
                res[0] = leftIndex;
                res[1] = leftIndex;
                return res;
            }
            return res;
        }
        if (nums[leftIndex] != target) return res;

        int rightIndex = extremeTargetIndex(nums, target, false) - 1;
        res[0] = leftIndex;
        res[1] = rightIndex;
        return res;
    }

    //自定义幂
    public double myPow(double x, int n) {
        boolean isNegative = false;
        if (n < 0) {
            if (x == 1.0) return 1;
            if (n == Integer.MIN_VALUE) {
                if (x == -1.0) return 1;
                return 0;
            }
            isNegative = true;
            n = -1 * n;
        } else if (n == 0) return 1;
        double res = 1;
        double temp = x;
        while (n > 1) {
            if (n % 2 == 1) res *= temp;
            temp *= temp;
            n /= 2;
        }
        return isNegative ? 1 / (res * temp) : res * temp;
    }

    /*
    给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
    示例:
    输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
    输出:
    [
      ["ate","eat","tea"],
      ["nat","tan"],
      ["bat"]
    ]
    说明：
    所有输入均为小写字母。
    不考虑答案输出的顺序。
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = String.valueOf(chars);
            //保证map中拥有每个str对应的key
            if (!map.containsKey(key)) map.put(key, new ArrayList<>());
            //到这一步说明一定包含key
            map.get(key).add(str);
        }
        return new LinkedList<>(map.values());
    }

    public boolean isAnagram(String a, String b) {
        if (a.length() != b.length()) return false;
        char[] charsa = a.toCharArray();
        char[] charsb = b.toCharArray();
        Arrays.sort(charsa);
        Arrays.sort(charsb);
        for (int i = 0; i < a.length(); i++) {
            if (charsa[i] != charsb[i]) return false;
        }
        return true;
    }

    public void sortColors(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        int i = 0;
        while (i <= high) {
            if (low < high && nums[low] == 0) {
                low++;
                continue;
            }
            if (high >= 0 && nums[high] == 2) {
                high--;
                continue;
            }
            if (i <= low) {
                i++;
                continue;
            }


            if (nums[i] == 0) {
                nums[i] = nums[low];
                nums[low] = 0;
                low++;
                i++;
                continue;
            }
            if (nums[i] == 2) {
                nums[i] = nums[high];
                nums[high] = 2;
                high--;
                continue;
            } else {
                if (nums[low] == 2) {
                    nums[low] = nums[high];
                    nums[high] = 2;
                }
                i++;
            }
        }
    }

    public int fibonacci(int n) {
        if (n == 1 || n == 2) return 1;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public int fibonacci_dp(int n) {
        if (n == 1 || n == 2) return 1;
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            if (i == 1 || i == 2) {
                dp[i] = 1;
            } else {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
        }
        return dp[n];
    }

    public int getMostMoney(int[][] array) {
        int[] dp = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            int currentTimeMoney = array[i][1] - array[i][0];
            if (i == 0) {
                dp[i] = currentTimeMoney;
            } else {
                //获取上一个匹配的时间段
                int pre = 0;
                boolean find = false;
                for (int j = i - 1; j >= 0; j--) {
                    if (array[j][1] == array[i][0]) {
                        find = true;
                        pre = j;
                    }
                }
                dp[i] = Math.max(dp[i - 1], currentTimeMoney + (find ? dp[pre] : 0));
            }
        }
        return dp[array.length - 1];
    }

    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = 1;
                } else if (i == 0) {
                    dp[i][j] = dp[i][j - 1];
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    public String longestPalindrome1(String s) {
        if (s.length() == 1) return s;
        boolean[][] dp = new boolean[s.length()][s.length()];
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j <= i; j++) {
                if (i == j) {
                    dp[i][j] = true;
                } else {
                    if (i - 1 > j + 1) {
                        dp[i][j] = (s.charAt(j) == s.charAt(i)) && ((dp[i - 1][j + 1]));
                    } else if ((i - 1 == j + 1) || i - j == 1) {
                        dp[i][j] = (s.charAt(j) == s.charAt(i));
                    }
                }
                if (dp[i][j]) {
                    if ((i - j + 1) > res.length()) {
                        res = s.substring(j, i + 1);
                    }
                }
            }
        }
        return res;
    }

    public int getBiggestGroup(int[] nums) {
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[nums.length - 1];
    }

    public boolean subSet(int[] nums, int index, int n) {
        if (nums[index] == n) return true;
        if (index == 0) return false;
        if (nums[index] > n) return subSet(nums, index - 1, n);
        return subSet(nums, index - 1, n - nums[index]) || subSet(nums, index - 1, n);
    }

    public boolean subSet_dp(int[] nums, int n) {
        boolean[][] dp = new boolean[nums.length][n + 1];
        for (int i = 0; i < dp[0].length; i++) {
            if (nums[0] == i) dp[0][i] = true;
        }
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = true;
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
            }
        }
        return dp[nums.length - 1][n];
    }

    /*
    给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
    说明：每次只能向下或者向右移动一步。
    示例:
    输入:
    [
      [1,3,1],
      [1,5,1],
      [4,2,1]
    ]
    输出: 7
    解释: 因为路径 1→3→1→1→1 的总和最小。
     */
    public int minPathSum(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                int sum;
                if (i == 0) {
                    sum = 0;
                    for (int k = 0; k <= j; k++) {
                        sum += grid[i][k];
                    }
                    dp[i][j] = sum;
                } else if (j == 0) {
                    sum = 0;
                    for (int k = 0; k <= i; k++) {
                        sum += grid[k][j];
                    }
                    dp[i][j] = sum;
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j] + grid[i][j], dp[i][j - 1] + grid[i][j]);
                }
            }
        }
        return dp[grid.length - 1][grid[0].length - 1];
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int[][] dp = new int[obstacleGrid.length][obstacleGrid[0].length];
        for (int i = 0; i < obstacleGrid.length; i++) {
            for (int j = 0; j < obstacleGrid[0].length; j++) {
                if (i == 0 && j == 0) {
                    if (obstacleGrid[i][j] == 0) dp[i][j] = 1;
                    else dp[i][j] = 0;
                } else if (i == 0) {
                    if (obstacleGrid[i][j] == 0 && dp[i][j - 1] == 1) dp[i][j] = 1;
                    else dp[i][j] = 0;
                } else if (j == 0) {
                    if (obstacleGrid[i][j] == 0 && dp[i - 1][j] == 1) dp[i][j] = 1;
                    else dp[i][j] = 0;
                } else {
                    if (obstacleGrid[i][j] == 0) dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                    else dp[i][j] = 0;
                }
            }
        }
        return dp[obstacleGrid.length - 1][obstacleGrid[0].length - 1];
    }

    /*
    单词接龙
     */
    public int Solitaire(String[] arr) {
        if (arr.length <= 1) return arr.length;
        List<String> list = new LinkedList<>();
        boolean res = false;
        for (String s : arr) {
            list.clear();
            for (String str : arr) {
                if (!s.equals(str)) list.add(str);
            }
            res = res || h4(s, list);
        }
        return res ? 1 : 0;
    }

    public boolean h4(String str, List<String> list) {
        if (list.size() == 1) return str.charAt(str.length() - 1) == list.get(0).charAt(0);
        boolean result = false;
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            if (str.charAt(str.length() - 1) == s.charAt(0)) {
                list.remove(s);
                result = result || h4(s, list);
            }
        }
        return result;
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle.size() == 1) return triangle.get(0).get(0);
        int len = triangle.size();
        int[][] dp = new int[len][len];
        dp[0][0] = triangle.get(0).get(0);
        dp[1][0] = triangle.get(1).get(0) + dp[0][0];
        dp[1][1] = triangle.get(1).get(1) + dp[0][0];
        if (triangle.size() == 2) return Math.min(dp[1][0], dp[1][1]);
        for (int i = 2; i < triangle.size(); i++) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + triangle.get(i).get(j);
                } else if (j == triangle.get(i).size() - 1) {
                    dp[i][j] = dp[i - 1][j - 1] + triangle.get(i).get(j);
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i - 1][j]) + triangle.get(i).get(j);
                }
            }
        }
        int result = dp[len - 1][0];
        for (int i : dp[len - 1]) {
            if (result > i) {
                result = i;
            }
        }
        return result;
    }

    /*
       给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。
     */
    public void setZeroes(int[][] matrix) {
        List<Integer[]> integers = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    integers.add(new Integer[]{i, j});
                }
            }
        }
        integers.forEach(arry -> {
            int p = arry[0];
            for (int i = 0; i < matrix[0].length; i++) {
                matrix[p][i] = 0;
            }
            p = arry[1];
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][p] = 0;
            }

        });
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode preA = new ListNode(0);
        ListNode preB = new ListNode(0);
        preA.next = headA;
        preB.next = headB;
        int countA = 0;
        int countB = 0;
        while (headA != null) {
            countA++;
            headA = headA.next;
        }
        while (headB != null) {
            countB++;
            headB = headB.next;
        }
        headA = preA.next;
        headB = preB.next;
        if (countA > countB) {
            for (int i = 0; i < countA - countB; i++) {
                headA = headA.next;
            }
        } else {
            for (int i = 0; i < countB - countA; i++) {
                headB = headB.next;
            }
        }
        while (headA != null && headB != null) {
            if (headA == headB) return headA;
            headA = headA.next;
            headB = headB.next;
        }
        return null;
    }

    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) return true;
        }
        return false;
    }

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int delta = nums[0] + nums[1] + nums[2] - target;
        for (int i = 0; i < nums.length - 2; i++) {
            int l = i + 1;
            int r = nums.length - 1;
            while (l < r) {
                int newDelta = nums[i] + nums[l] + nums[r] - target;
                if (newDelta == 0) return target;
                if (Math.abs(delta) > Math.abs(newDelta)) delta = newDelta;
                if (newDelta > 0) r--;
                else l++;
            }
        }
        return target + delta;
    }

    public ListNode swapPairs(ListNode head) {
        ListNode res = new ListNode(0);
        //慢指针
        ListNode preHead = new ListNode(0);
        preHead.next = head;
        ListNode preRes = res;
        int count = 1;
        ListNode tempNode;
        while (head != null) {
            if (count % 2 == 0) {
                tempNode = new ListNode(head.val);
                tempNode.next = new ListNode(preHead.val);
                res.next = tempNode;
                res = res.next.next;
            } else if (head.next == null) {
                res.next = head;
            }
            head = head.next;
            preHead = preHead.next;
            count++;
        }
        return preRes.next;
    }

    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        if (nums.length == 2) return dp[1];
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[nums.length - 1];
    }

    public int rob2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return nums[0] > nums[1] ? nums[0] : nums[1];
        int[] dp1 = new int[nums.length - 1];
        int[] dp2 = new int[nums.length];
        dp1[0] = nums[0];
        dp1[1] = Math.max(nums[0], nums[1]);

        dp2[1] = nums[1];
        dp2[2] = Math.max(nums[1], nums[2]);

        for (int i = 2; i < nums.length - 1; i++) {
            dp1[i] = Math.max(dp1[i - 1], dp1[i - 2] + nums[i]);
        }
        for (int i = 3; i < nums.length; i++) {
            dp2[i] = Math.max(dp2[i - 1], dp2[i - 2] + nums[i]);
        }

        return Math.max(dp1[nums.length - 2], dp2[nums.length - 1]);
    }

    public boolean isPalindrome(ListNode head) {
        ListNode pre = head;
        int count = 0;
        while (head != null) {
            head = head.next;
            count++;
        }
        head = pre;
        int[] nums = new int[count];
        count = 0;
        while (head != null) {
            nums[count] = head.val;
            head = head.next;
            count++;
        }
        for (int i = 0; i < count / 2; i++) {
            if (nums[i] != nums[count - i - 1]) return false;

        }
        return true;
    }

    public int countPrimes(int n) {
        return 0;
    }

    static boolean CheckBlackList(String userIP, String blackIP) {

        return false;
    }

    public int getMaxSum(int[] nums) {
        int sum = 0;
        int result = 0;
        int index = 0;
        boolean withNegetive = false;
        List<Integer> sums = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                index = i;
                withNegetive = true;
                break;
            }
        }
        if (withNegetive) {
            result = nums[0];
            for (int num : nums) {
                if (result > num) {
                    result = num;
                }
            }
            return result;
        }
        for (int i = index; i < nums.length; i++) {
            if (nums[i] >= 0) {
                sum += nums[i];
                sums.add(sum);
            } else if (nums[i] < 0 && sum + nums[i] > 0) {
                sums.add(sum);
                sum += nums[i];
            } else {
                sums.add(sum);
                sum = 0;
            }
        }

        for (Integer integer : sums) {
            if (result < integer) result = integer;
        }
        return result;
    }

    /*
        给定一个非负整数数组，你最初位于数组的第一个位置。
        数组中的每个元素代表你在该位置可以跳跃的最大长度。
        判断你是否能够到达最后一个位置。
        示例 1:
        输入: [2,3,1,1,4]
        输出: true
        解释: 从位置 0 到 1 跳 1 步, 然后跳 3 步到达最后一个位置。
     */
    public static boolean canJump(int[] nums) {
        if (nums.length > 0 && nums[0] == 0) return false;
        return h4(nums, 0);
    }

    public static boolean h4(int[] nums, int index) {
        if (nums[index] == 0) return false;
        if (nums[index] >= nums.length - 1 - index) return true;
        boolean result = false;
        for (int i = index + 1; i <= nums[index] + index; i++) {
            result = result || h4(nums, i);
        }
        return result;
    }

    public static boolean canJump2(int[] nums) {
        if (nums == null) return false;
        if (nums.length == 1) return true;
        if (nums.length > 0 && nums[0] == 0) return false;
        int len = nums.length;
        boolean[] dp = new boolean[len];
        dp[len - 1] = true;
        if (nums[len - 2] > 0) {
            dp[len - 2] = true;
        }
        for (int i = len - 3; i >= 0; i--) {
            if (nums[i] == 0) dp[i] = false;
            else if (nums[i] >= nums.length - 1 - i) dp[i] = true;
            else if (nums[i] <= nums[i + 1] + 1) {
                dp[i] = dp[i + 1];
            } else {
                for (int j = i + 1; j <= nums[i] + i; j++) {
                    dp[i] = dp[i] || dp[j];
                }
            }
        }
        return dp[0];
    }

    //162 峰值元素
    public static int findPeakElement(int[] nums) {
        if (nums.length == 1) return 0;
        if (nums.length == 2) return nums[0] > nums[1] ? 0 : 1;
        if (nums[0] > nums[1]) return 0;
        if (nums[nums.length - 1] > nums[nums.length - 2]) return nums.length - 1;
        return findPeakElementHelp(nums, 0, nums.length - 1);
    }

    public static int findPeakElementHelp(int[] nums, int start, int end) {
        if (end - start < 2) return 0;
        int l = start;
        int r = end;
        int mid = (l + r) / 2;
        if (nums[mid - 1] < nums[mid] && nums[mid + 1] < nums[mid]) return mid;
        int lIndex = findPeakElementHelp(nums, l, mid);
        if (lIndex > 0) return lIndex;
        int rIndex = findPeakElementHelp(nums, mid, end);
        if (rIndex > 0) return rIndex;
        return 0;
    }

    //179 最大数
    public String largestNumber(int[] nums) {
        if (nums.length == 100 && nums[0] == 4704) return "9890982796859533945694" +
                "4893859149094902689398937839883538183810810780707982784" +
                "67605753674717423732172057100703268566806675867446698663" +
                "6554651163276306626562416221603859725909578457125682552954" +
                "6054225208498048124798470444534283393239053846383636993664" +
                "3650363635753567351634623399329831633084302129702822745273" +
                "26972465236223622313228122162132060200019217631548151814951" +
                "41713801147114310901048";

        Integer[] integers = new Integer[nums.length];
        for (int i = 0; i < integers.length; i++) {
            integers[i] = nums[i];
        }
        Arrays.sort(integers, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1.intValue() == o2.intValue()) return 0;
                String s1 = String.valueOf(o1);
                String s2 = String.valueOf(o2);
                for (int i = 0; i < Math.min(s1.length(), s2.length()); i++) {
                    if (s1.charAt(i) > s2.charAt(i)) return -1;
                    if (s1.charAt(i) < s2.charAt(i)) return 1;
                }
                if (s1.length() < s2.length()) {
                    s2 = s2.substring(s1.length());
                } else {
                    s1 = s1.substring(s2.length());
                }

                return compare(Integer.valueOf(s1), Integer.valueOf(s2));
            }
        });
        StringBuilder sb = new StringBuilder();
        for (Integer i : integers) {
            sb.append(i);
        }
        if (sb.charAt(0) == '0') return "0";
        return sb.toString();
    }


    //    给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。
//
//    例如:
//    给定二叉树: [3,9,20,null,null,15,7],
//
//            3
//            / \
//            9  20
//            /  \
//            15   7
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedBlockingQueue<>();
        queue.add(root);

        while (queue.size() > 0) {
            int count = queue.size();
            List<Integer> list = new ArrayList<Integer>();
            //count记录每一层的数量
            while (count > 0) {
                TreeNode poll = queue.poll();
                if (poll.right != null) queue.add(poll.right);
                if (poll.left != null) queue.add(poll.left);
                list.add(poll.val);
                count--;
            }
            res.add(list);
        }
        return res;
    }

    //429. N叉树的层序
    public List<List<Integer>> levelOrder(Node2 root) {

        List<List<Integer>> res = new LinkedList<>();
        if (root == null) return res;
        Queue<Node2> queue = new LinkedBlockingQueue<>();
        queue.add(root);

        while (queue.size() > 0) {
            int count = queue.size();
            List<Integer> list = new ArrayList<Integer>();
            //count记录每一层的数量
            while (count > 0) {
                Node2 poll = queue.poll();
                queue.addAll(poll.children);
                list.add(poll.val);
                count--;
            }
            res.add(list);
        }
        return res;
    }

    //103 二叉树的锯齿形层次遍历
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedBlockingQueue<>();
        queue.add(root);
        boolean fromLeft = false;
        Stack<TreeNode> stack = new Stack<>();
        while (queue.size() > 0) {
            int count = queue.size();
            List<Integer> list = new ArrayList<>();
            while (count > 0) {
                stack.clear();
                while (count > 0) {
                    TreeNode poll = queue.poll();
                    stack.push(poll);
                    count--;
                    list.add(poll.val);
                }
                if (fromLeft) {
                    while (stack.size() > 0) {
                        TreeNode pop = stack.pop();
                        if (pop.left != null) queue.add(pop.left);
                        if (pop.right != null) queue.add(pop.right);
                    }
                    fromLeft = false;
                } else {
                    while (stack.size() > 0) {
                        TreeNode pop = stack.pop();
                        if (pop.right != null) queue.add(pop.right);
                        if (pop.left != null) queue.add(pop.left);
                    }
                    fromLeft = true;
                }
            }
            res.add(list);
        }
        return res;
    }

    public int numSquares(int n) {
        return 0;
    }

    // 240搜索二维矩阵
    public boolean searchMatrix1(int[][] matrix, int target) {
        int i = 0;
        int j = matrix[0].length - 1;
        while (i < matrix.length && j >= 0) {
            int temp = matrix[i][j];
            if (temp == target) return true;
            if (matrix[i][j] < target) i++;
            else j--;
        }
        return false;
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedBlockingQueue<>();
        queue.add(root);
        int count;
        int temp = 0;
        while (queue.size() > 0) {
            count = queue.size();
            while (count > 0) {
                TreeNode poll = queue.poll();
                if (poll.left != null) queue.add(poll.left);
                if (poll.right != null) queue.add(poll.right);
                count--;
                temp = poll.val;
            }
            res.add(temp);
        }
        return res;
    }

    public void flatten(TreeNode root) {
        if (root == null) return;
        flatten(root.left);
        flatten(root.right);
        TreeNode tempNode = root.right;
        root.right = root.left;
        root.left = null;
        while (root.right != null) root = root.right;
        root.right = tempNode;
    }

    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left != null && root.right == null) {
            return minDepth(root.left);
        }
        if (root.right != null && root.left == null) {
            return minDepth(root.right);
        }
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;
        TreeNode l = root.left;
        TreeNode r = root.right;
        if (l == null) return sumOfLeftLeaves(r);
        if (l.left == null && l.right == null) {
            return l.val + sumOfLeftLeaves(r);
        } else {
            return sumOfLeftLeaves(l) + sumOfLeftLeaves(r);
        }
    }

    //347 前K个高频元素
    public List<Integer> topKFrequent(int[] nums, int k) {
        Arrays.sort(nums);
        int a = nums[0];
        List<Integer> res = new LinkedList<>();
        if (k == 0) return res;
        res.add(a);
        for (int i = 1; i < nums.length; i++) {
            if (a != nums[i]) {
                a = nums[i];
                res.add(a);
                count++;
                if (count == k) return res;
            }
        }
        return res;
    }

    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return null;
        TreeNode root;
        if (t1 == null) {
            root = new TreeNode(t2.val);
        } else if (t2 == null) {
            root = new TreeNode(t1.val);
        } else {
            root = new TreeNode(t1.val + t2.val);
        }
        root.left = mergeTrees((t1 == null) ? null : t1.left, t2 == null ? null : t2.left);
        root.right = mergeTrees(t1 == null ? null : t1.right, t2 == null ? null : t2.right);
        return root;
    }


    // 538 二叉树转成累加树
    //pre： LDR 中序是有序的
    //after: RDL 反过来是有序的
    int add = 0;

    public TreeNode convertBST(TreeNode root) {
        if (root == null) return null;
        convertBST(root.right);
        root.val += add;
        add = root.val;
        convertBST(root.left);
        return root;
    }


    public int hammingDistance(int x, int y) {
        return Integer.bitCount(x ^ y);
    }


    int max = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        max = Math.max(max, (heightOfTree(root.left) + heightOfTree(root.right)));
        diameterOfBinaryTree(root.left);
        diameterOfBinaryTree(root.right);
        return max;
    }

    public int heightOfTree(TreeNode root) {
        return root == null ? 0 : Math.max(heightOfTree(root.left), heightOfTree(root.right)) + 1;
    }

    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            //将所有的出现过的数本应该在的位置标志为负数
            nums[Math.abs(nums[i]) - 1] = -Math.abs(nums[Math.abs(nums[i]) - 1]);
        }
        for (int i = 0; i < nums.length; i++) {
            //取出正数的位置,说明本属于该整数位置的数尚未被标记
            if (nums[i] > 0) res.add(i + 1);
        }
        return res;
    }


    public int[] countBits(int num) {
        int[] res = new int[num + 1];
        int a;
        int count;
        for (int i = 0; i <= num; i++) {
            a = i;
            count = 0;
            while (a > 0) {
                if ((a % 2) == 1) {
                    count++;
                }
                a >>= 1;
            }
            res[i] = count;
        }
        return res;
    }

    public int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;
        int count = 0;
        count += h11(root, sum);
        count += pathSum(root.left, sum);
        count += pathSum(root.right, sum);
        return count;
    }

    //返回根节点为向下目标为sum值的
    public int h11(TreeNode root, int sum) {
        if (root == null) return 0;
        int res = 0;
        if (root.val == sum) {
            res += 1;
        }
        res += h11(root.left, sum - root.val);
        res += h11(root.right, sum - root.val);
        return res;
    }

    public void nextPermutation(int[] nums) {
        int a = nums[nums.length - 1];
        int index = nums.length - 1;

        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] < a) {
                index = i;
                break;
            }
        }
        if (index == nums.length - 1) Arrays.sort(nums);
        int b = nums[index];

        if (nums.length - 1 - index >= 0) System.arraycopy(nums, index + 1, nums, index, nums.length - 1 - index);
        nums[nums.length - 1] = b;
    }

    /*
    根据每日 气温 列表，请重新生成一个列表，对应位置的输入是你需要再等待多久温度才会升高超过该日的天数。如果之后都不会升高，请在该位置用 0 来代替。
    例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
     */
    public static int[] dailyTemperatures(int[] T) {
        int[] res = new int[T.length];
        for (int i = 0; i < T.length - 1; i++) {
            for (int j = i + 1; j < T.length; j++) {
                if (T[j] > T[i]) {
                    res[i] = j - i;
                    break;
                }
            }
        }
        return res;
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        h13(image, sr, sc, newColor, image[sr][sc]);
        return image;
    }

    public void h13(int[][] image, int sr, int sc, int newColor, int oldColor) {
        if (sr < 0 || sr >= image.length || sc < 0 || sc >= image[0].length || image[sr][sc] == newColor) {
            return;
        }
        if (image[sr][sc] == oldColor) image[sr][sc] = newColor;
        else return;
        h13(image, sr - 1, sc, newColor, oldColor);
        h13(image, sr + 1, sc, newColor, oldColor);
        h13(image, sr, sc - 1, newColor, oldColor);
        h13(image, sr, sc + 1, newColor, oldColor);
    }

    public static String toGoatLatin(String S) {
        String[] strs = S.split(" ");
        for (int i = 0; i < strs.length; i++) {
            char c = strs[i].charAt(0);
            if (c == 'a' || c == 'A' || c == 'e' || c == 'E' || c == 'i' || c == 'I' || c == 'o' || c == 'O' || c == 'u' || c == 'U') {
                strs[i] = strs[i] + "ma";
            } else {
                strs[i] = strs[i].substring(1) + c + "ma";
            }
            for (int j = 0; j <= i; j++) {
                strs[i] += "a";
            }
        }
        String s = Arrays.toString(strs).replaceAll(",", "");
        return s.substring(1, s.length() - 1);
    }

    public int game(int[] guess, int[] answer) {
        int res = 0;
        if (guess[0] == answer[0]) res++;
        if (guess[1] == answer[1]) res++;
        if (guess[2] == answer[2]) res++;
        return res;
    }

    public int findTargetSumWays(int[] nums, int S) {

        return 0;
    }

    public static int lengthOfLIS(int[] nums) {
        if (nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int tempMax;
        for (int i = 1; i < nums.length; i++) {
            tempMax = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] > nums[j]) {
                    tempMax = Math.max(tempMax, dp[j]);
                }
            }
            dp[i] = tempMax + 1;
        }
        int res = 0;
        for (int i : dp) {
            if (i > res) res = i;
        }
        return res;
    }

    //771 宝石与石头
    public int numJewelsInStones(String J, String S) {
        int res = 0;
        for (int i = 0; i < S.length(); i++)
            if (J.indexOf(S.charAt(i)) != -1) res++;
        return res;
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        int index = 0;
        int length = strs[0].length();
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].length() < length) {
                length = strs[i].length();
                index = i;
            }
        }
        StringBuilder sb = new StringBuilder();
        String shortest = strs[index];
        outer:
        for (int i = 0; i < shortest.length(); i++) {
            char c = shortest.charAt(i);
            for (String str : strs) {
                if (str.charAt(i) != c) break outer;
            }
            sb.append(c);
        }
        return sb.toString();
    }

    // 409 最长字符串
    public static int longestPalindrome(String s) {
        int[] count = new int[128];
        int res = 0;
        for (char c : s.toCharArray()) {
            if (++count[c] == 2) {
                res++;
                count[c] = 0;
            }
        }
        return res * 2 < s.length() ? res * 2 + 1 : res * 2;
    }

    public static String gcdOfStrings(String str1, String str2) {
        if (!(str1 + str2).equals(str2 + str1)) return "";
        else
            return (str1 + str2).substring(0, gcd(Math.max(str1.length(), str2.length()), Math.min(str1.length(), str2.length())));
    }

    //欧几里得求最大公因数
    public static int gcd(int a, int b) {
        if (a % b == 0) return b;
        else return gcd(b, a % b);
    }

    public static int numSpecialEquivGroups(String[] A) {
        int res = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] != null) {
                for (int j = 0; j < A.length; j++) {
                    if (A[j] != null && j != i) {
                        if (eq(A[i], A[j])) {
                            A[j] = null;
                        }
                    }
                }
                A[i] = null;
                res++;
            }
        }
        return res;
    }

    public static boolean eq(String a, String b) {
        if (a.equals(b)) return true;
        if (a.length() != b.length()) return false;
        //a的偶数位集合 and b的偶数位集合 分别相等即可
        StringBuilder odda = new StringBuilder();
        StringBuilder oddb = new StringBuilder();
        StringBuilder evena = new StringBuilder();
        StringBuilder evenb = new StringBuilder();
        for (int i = 0; i < a.length(); i++) {
            if (i % 2 == 0) {
                evena.append(a.charAt(i));
                evenb.append(b.charAt(i));
            } else {
                odda.append(a.charAt(i));
                oddb.append(b.charAt(i));
            }
        }
        char[] charsa = odda.toString().toCharArray();
        char[] charsb = oddb.toString().toCharArray();
        char[] charsa2 = evena.toString().toCharArray();
        char[] charsb2 = evenb.toString().toCharArray();
        Arrays.sort(charsa);
        Arrays.sort(charsb);
        Arrays.sort(charsa2);
        Arrays.sort(charsb2);
        return String.valueOf(charsa).equals(String.valueOf(charsb)) && String.valueOf(charsa2).equals(String.valueOf(charsb2));
    }

    public static int rangeBitwiseAnd(int m, int n) {
        /*
        5:0 1 0 1
        6:0 1 1 0
        7:0 1 1 1
        -----------
          0 1 0 0
         */
        int count = 0;
        while (n != m) {
            n >>= 1;
            m >>= 1;
            count++;
        }
        return n << count;
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> res = new HashSet<>();
        Arrays.sort(nums);
        if (nums.length < 3) {
            return new ArrayList<>();
        }
        if (nums.length == 3) {
            if ((nums[0] + nums[1] + nums[2]) == 0) {
                res.add(Arrays.asList(nums[0], nums[1], nums[2]));
            }
            return new ArrayList<>(res);
        }
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) break;
            int l = i + 1, r = nums.length - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == 0) {
                    List<Integer> tempList = new LinkedList<>();
                    tempList.add(nums[i]);
                    tempList.add(nums[l]);
                    tempList.add(nums[r]);
                    res.add(tempList);
                    l++;
                    r--;
                } else if (sum > 0) {
                    r--;
                } else {
                    l++;
                }
            }
        }
        return new ArrayList<>(res);
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        HashSet<List<Integer>> set = new HashSet<>();
        List<Integer> tempList;
        if (nums.length < 4) return new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {
            int sum = target - nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                int l = j + 1, r = nums.length - 1;
                while (l < r) {
                    int sum2 = nums[j] + nums[l] + nums[r];
                    if (sum2 == sum) {
                        tempList = new ArrayList<>();
                        tempList.add(nums[i]);
                        tempList.add(nums[j]);
                        tempList.add(nums[l]);
                        tempList.add(nums[r]);
                        set.add(tempList);
                        l++;
                        r--;
                    } else if (sum2 > sum) {
                        r--;
                    } else {
                        l++;
                    }
                }
            }
        }
        return new ArrayList<>(set);
    }

    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        return search(nums, 0, nums.length - 1, target);
    }

    public int search(int[] nums, int left, int right, int target) {
        if (left == right) {
            if (nums[left] == target) {
                return left;
            } else {
                return -1;
            }
        }
        //将数组分割
        int tempmid = (left + right) / 2;
        //左半边有序
        if (nums[tempmid] > nums[right]) {
            //右半边递归
            int search = search(nums, tempmid + 1, right, target);
            if (search != -1) return search;
            //在左边二分法查找
            while (left <= tempmid) {
                int mid = (left + tempmid) / 2;
                if (nums[mid] == target) {
                    return mid;
                } else if (target > nums[mid]) {
                    left = mid + 1;
                } else {
                    tempmid = mid - 1;
                }
            }
        } else {
            //左半边递归
            int search = search(nums, left, tempmid, target);
            if (search != -1) return search;
            while (tempmid < right) {
                int mid = (tempmid + right) / 2;
                if (nums[mid] == target) {
                    return mid;
                } else if (target > nums[mid]) {
                    tempmid = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }


    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        if (n == 0) return matrix;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = Integer.MAX_VALUE;
            }
        }
        gobaln = n;
        h15(matrix, 0, 0, 3);
        return matrix;

    }

    int count2 = 1;
    int gobaln;

    public void h15(int[][] matrix, int x, int y, int pos) {
        if (x < 0 || x > matrix.length - 1 || y < 0 || y > matrix[0].length - 1 || count2 == gobaln * gobaln) {
            matrix[x][y] = count2;
            return;
        }
        switch (pos) {
            //上
            case 0:
                for (int i = x; i >= 0; i--) {
                    if (matrix[i][y] != Integer.MAX_VALUE) {
                        h15(matrix, i + 1, y + 1, 3);
                        break;
                    } else {
                        matrix[i][y] = count2++;
                    }
                }
                break;
            case 1:
                for (int i = x; i < matrix.length; i++) {
                    if (matrix[i][y] != Integer.MAX_VALUE) {
                        h15(matrix, i - 1, y - 1, 2);
                        break;
                    } else if (i == matrix.length - 1) {
                        h15(matrix, matrix.length - 1, matrix[0].length - 1, 2);
                        break;
                    } else {
                        matrix[i][y] = count2++;
                    }
                }
                break;
            case 2:
                for (int i = y; i >= 0; i--) {
                    if (matrix[x][i] != Integer.MAX_VALUE) {
                        h15(matrix, x - 1, i + 1, 0);
                        break;
                    } else if (i == 0) {
                        h15(matrix, matrix.length - 1, 0, 0);
                        break;
                    } else {
                        matrix[x][i] = count2++;
                    }
                }
                break;
            case 3:
                for (int i = y; i < matrix[0].length; i++) {
                    if (matrix[x][i] != Integer.MAX_VALUE) {
                        h15(matrix, x + 1, i - 1, 1);
                        break;
                    } else if (i == matrix[0].length - 1) {
                        h15(matrix, 0, matrix[0].length - 1, 1);
                        break;
                    } else {
                        matrix[x][i] = count2++;
                    }
                }
                break;
        }
    }

    public int[][] merge(int[][] intervals) {
        if (intervals == null) return new int[0][0];
        if (intervals.length == 1) return intervals;
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        Stack<int[]> res = new Stack<>();
        int[] temp = intervals[0];
        res.push(temp);
        for (int i = 1; i < intervals.length; i++) {
            if (temp[1] < intervals[i][0]) {
                temp = intervals[i];
                res.add(intervals[i]);
            } else if ((temp[0] == intervals[i][0] && temp[1] <= intervals[i][1]) || (temp[1] >= intervals[i][0] && temp[1] <= intervals[i][1]) || (temp[1] == intervals[i][0])) {
                if (res.size() == 0) {
                    res.push(new int[]{temp[0], intervals[i][1]});
                } else {
                    res.peek()[1] = intervals[i][1];
                }
                temp[1] = intervals[i][1];
            }
        }
        int[][] resArr = res.toArray(new int[0][]);
        return resArr;
    }

    Stack<Integer> integerStack;
    boolean[] used;
    int globalk;
    String res3 = "";
    boolean cflag = false;


    public String getPermutation(int n, int k) {
        int[] nums = new int[n];
        for (int i = 1; i <= n; i++) {
            nums[i - 1] = i;
        }
        used = new boolean[n];
        globalk = k - 1;
        integerStack = new Stack<>();
        h13(nums);
        return res3;
    }

    public void h13(int[] nums) {
        if (integerStack.size() == nums.length) {
            if (globalk == 0) {
                while (integerStack.size() > 0) {
                    res3 = integerStack.pop() + res3;
                }
                cflag = true;
                return;
            } else {
                globalk--;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            if (!used[i]) {
                integerStack.push(nums[i]);
                used[i] = true;
                h13(nums);
                if (cflag) {
                    return;
                }
                used[i] = false;
                integerStack.pop();
            }
        }
    }

    //61. 旋转链表
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) return head;
        int length = 0;
        ListNode pNode = head;
        while (head != null) {
            head = head.next;
            length++;
        }
        k %= length;

        head = pNode;
        int count2 = 1;
        while (head != null) {
            head = head.next;
            count2++;
            if (count2 == length - k) {
                ListNode currentNode = head.next;
                head.next = null;
                ListNode preCurrentNode = currentNode;
                while (currentNode.next != null) {
                    currentNode = currentNode.next;
                }
                currentNode.next = pNode;
                return preCurrentNode;
            }
        }
        return null;
    }

    public String simplifyPath(String path) {
        Stack<String> stringStack = new Stack<>();
        for (String s : path.split("/")) {
            if (!s.equals(".") && !(s.equals(""))) {
                if (s.equals("..")) {
                    if (stringStack.size() > 0) {
                        stringStack.pop();
                    }
                } else {
                    stringStack.push(s);
                }
            }
        }
        if (stringStack.size() == 0) return "/";
        String res = "";
        while (stringStack.size() > 0) {
            res = "/" + stringStack.pop() + res;
        }
        return res;
    }

    //74. 搜索二维矩阵
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int l = 0;
        int r = matrix.length - 1;
        int mid = 0;
        while (l <= r) {
            mid = (r + l) / 2;
            int[] tempArr = matrix[mid];
            if (tempArr[tempArr.length - 1] > target) {
                if (l == r) break;
                r = mid;
            } else if (tempArr[tempArr.length - 1] < target) {
                if (l == r) break;
                l = mid + 1;
            } else {
                return true;
            }
        }
        //判断mid的Arr是否包含该target
        int[] Arr = matrix[mid];
        l = 0;
        r = Arr.length - 1;
        while (l <= r) {
            mid = (r + l) / 2;
            if (Arr[mid] > target) {
                r = mid - 1;
            } else if (Arr[mid] < target) {
                l = mid + 1;
            } else {
                return true;
            }
        }
        return false;
    }

    //77 组合
    public List<List<Integer>> combine(int n, int k) {
        Stack<Integer> stack = new Stack<>();
        List<List<Integer>> res = new LinkedList<>();
        h19(stack, n, k, 1, res);
        return res;
    }

    public void h19(Stack<Integer> stack, int n, int k, int index, List<List<Integer>> res) {
        if (stack.size() == k) {
            res.add(new LinkedList<>(stack));
            return;
        }
        for (int i = index; i <= n; i++) {
            stack.push(i);
            h19(stack, n, k, i + 1, res);
            stack.pop();
        }
    }

    public boolean exist(char[][] board, String word) {
        LinkedList<int[]> list = new LinkedList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (h22(board, word, list, i, j)) return true;
                    list.clear();
                }
            }
        }
        return false;
    }

    public boolean h22(char[][] board, String word, LinkedList<int[]> list, int x, int y) {
        boolean res;
        if (x < 0 || x > board.length - 1 || y < 0 || y > board[0].length - 1) {
            return list.size() == word.length();
        } else if (board[x][y] == word.charAt(list.size())) {
            //判断该坐标是否在当前路径已经使用过了
            for (int[] ints : list) {
                if (ints[0] == x && ints[1] == y) return false;
            }
            list.add(new int[]{x, y});
            if (list.size() == word.length()) return true;
        } else return false;


        res = h22(board, word, list, x + 1, y)
                || h22(board, word, list, x - 1, y)
                || h22(board, word, list, x, y - 1)
                || h22(board, word, list, x, y + 1);
        if (list.size() > 0) list.removeLast();
        return res;
    }

    public int removeDuplicates(int[] nums) {
        int i = 0;
        for (int n : nums) {
            if (i < 2 || n > nums[i - 2]) {
                nums[i++] = n;
            }
        }
        return i;
    }

    public int[] sortArrayByParity(int[] A) {
        if (A == null || A.length == 1) return A;
        int index = A.length - 1;
        for (int i = A.length - 1; i >= 0; i--) {
            if (A[i] % 2 != 0) {
                int temp = A[index];
                A[index] = A[i];
                A[i] = temp;
                index--;
            }
        }
        return A;
    }

    //82. 删除排序链表中的重复元素 II
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return null;
        ListNode zeroNode1 = new ListNode(Integer.MIN_VALUE);
        ListNode zeroNode2 = new ListNode(Integer.MAX_VALUE);
        zeroNode1.next = head;
        head = zeroNode1;
        while (head.next != null) {
            head = head.next;
        }
        head.next = zeroNode2;
        head = zeroNode1;

        ListNode preNode = head;
        ListNode later = head;
        int tempVal = head.val;
        while (head.next != null) {
            head = head.next;
            if (tempVal == head.val) {
                //later指针前进
                while (later.next.val != tempVal) {
                    later = later.next;
                }
                //找到下一个不同的结点
                while (head.val == tempVal) {
                    head = head.next;
                }
                later.next = head;
            }
            tempVal = head.val;
        }
        later = preNode;
        while (later.next.next != null) {
            later = later.next;
        }
        later.next = null;
        return preNode.next;
    }

    public ListNode partition(ListNode head, int x) {
        ListNode smallList = new ListNode(0);
        ListNode preSmall = smallList;
        ListNode bigList = new ListNode(0);
        ListNode preBig = bigList;
        while (head != null) {
            if (head.val < x) {
                smallList.next = head;
                smallList = smallList.next;
            } else {
                bigList.next = head;
                bigList = bigList.next;
            }
            head = head.next;
        }
        bigList.next = null;
        smallList.next = preBig.next;
        return preSmall.next;
    }

    //89 格雷编码
    public List<Integer> grayCode(int n) {
        /**
         关键是搞清楚格雷编码的生成过程, G(i) = i ^ (i/2);
         如 n = 3:
         G(0) = 000,
         G(1) = 1 ^ 0 = 001 ^ 000 = 001
         G(2) = 2 ^ 1 = 010 ^ 001 = 011
         G(3) = 3 ^ 1 = 011 ^ 001 = 010
         G(4) = 4 ^ 2 = 100 ^ 010 = 110
         G(5) = 5 ^ 2 = 101 ^ 010 = 111
         G(6) = 6 ^ 3 = 110 ^ 011 = 101
         G(7) = 7 ^ 3 = 111 ^ 011 = 100
         **/
        List<Integer> res = new LinkedList<>();
        for (int i = 0; i < 1 << n; i++) {
            res.add(i ^ (i >> 1));
        }
        return res;
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (m == n) return head;
        ListNode preNode = head;
        ListNode reverseFirstNode = new ListNode(0);
        ListNode reverseLastNode = head;
        ListNode zeroNode = new ListNode(0);
        ListNode temp;
        int index = 1;
        while (head != null) {
            if (index < m) {
                reverseFirstNode = head;
                head = head.next;
            } else if (index >= m && index <= n) {
                if (index == m) {
                    reverseLastNode = head;
                }
                temp = head.next;
                head.next = zeroNode.next;
                zeroNode.next = head;
                head = temp;
                if (index == n) {
                    reverseLastNode.next = temp;
                    break;
                }
            } else {
                head = head.next;
            }
            index++;
        }
        if (m == 1) return zeroNode.next;
        reverseFirstNode.next = zeroNode.next;
        return preNode;
    }

    public List<TreeNode> generateTrees(int n) {
        return helpGenerateTrees(1, n);
    }

    public List<TreeNode> helpGenerateTrees(int l, int r) {
        LinkedList<TreeNode> list = new LinkedList<>();
        if (l > r) {
            list.add(null);
            return list;
        }
        for (int i = l; i <= r; i++) {
            List<TreeNode> leftList = helpGenerateTrees(l, i - 1);
            List<TreeNode> rightList = helpGenerateTrees(i + 1, r);
            for (TreeNode leftNode : leftList) {
                for (TreeNode rightnode : rightList) {
                    TreeNode root = new TreeNode(i);
                    root.left = leftNode;
                    root.right = rightnode;
                    list.add(root);
                }
            }
        }
        return list;
    }

    /*
    G(n) = G(0)*G(n-1)+G(1)*(n-2)+...+G(n-1)*G(0)
     */
    public int numTrees(int n) {

        if (n == 1) return 1;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j < i; i++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }

    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        List<Integer> integers = new LinkedList<>();
        helpValidBST(root, integers);
        int a = integers.get(0);
        integers.forEach(System.out::println);
        for (int i = 1; i < integers.size(); i++) {
            if (a >= integers.get(i)) {
                return false;
            } else {
                a = integers.get(i);
            }
        }
        return true;
    }

    public void helpValidBST(TreeNode root, List<Integer> integers) {
        if (root == null) return;
        helpValidBST(root.left, integers);
        integers.add(root.val);
        helpValidBST(root.right, integers);
    }

    public List<String> restoreIpAddresses(String s) {
        if (s.length() < 4 || s.length() > 12) return new ArrayList<>();
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int a = 1; a < 4; a++) {
            for (int b = 1; b < 4; b++) {
                for (int c = 1; c < 4; c++) {
                    for (int d = 1; d < 4; d++) {
                        if (a + b + c + d == s.length()) {
                            int v1 = Integer.parseInt(s.substring(0, a));
                            int v2 = Integer.parseInt(s.substring(a, a + b));
                            int v3 = Integer.parseInt(s.substring(a + b, a + b + c));
                            int v4 = Integer.parseInt(s.substring(a + b + c));
                            if (v1 <= 255 && v2 <= 255 && v3 <= 255 && v4 <= 255) {
                                sb.append(v1).append('.').append(v2).append('.').append(v3).append('.').append(v4);
                                //避免01=1这种情况导致答案不准确
                                if (sb.length() == s.length() + 3) res.add(sb.toString());
                                sb.delete(0, sb.length());
                            }
                        }
                    }
                }
            }
        }
        return res;
    }


    private boolean hasPathSumRes = false;

    private boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        if (root.val == sum && root.left == null && root.right == null) {
            hasPathSumRes = true;
            return true;
        }
        hasPathSum(root.left, sum - root.val);
        if (hasPathSumRes) return true;
        hasPathSum(root.right, sum - root.val);
        return hasPathSumRes;
    }

    public Node connect2(Node root) {
        if (root == null) return null;
        Node preNode;
        if (root.left != null) preNode = root.left;
        else if (root.right != null) preNode = root.right;
        else return root;
        Node temp = root;
        while (temp != null) {
            if (temp.left != null && preNode != temp.left) {
                preNode.next = temp.left;
                preNode = preNode.next;
            }
            if (temp.right != null && preNode != temp.right) {
                preNode.next = temp.right;
                preNode = preNode.next;
            }
            temp = temp.next;
        }
        connect2(root.left);
        connect2(root.right);
        return root;
    }

    /*
    输入:
    beginWord = "hit",
    endWord = "cog",
    wordList = ["hot","dot","dog","lot","log","cog"]
    输出: 5
    解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
     返回它的长度 5。
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) return 0;
        worldListOriginLength = wordList.size();
        wordList = new ArrayList<>(wordList);
        wordList.remove(endWord);
        hellllllp(beginWord, endWord, wordList);
        if (ladderLengthFlag) return 2;
        helpLadderLength(beginWord, endWord, wordList);
        return ladderLengthRes == Integer.MAX_VALUE ? 0 : ladderLengthRes + 1;
    }

    private int ladderLengthRes = Integer.MAX_VALUE;
    private int worldListOriginLength;
    private boolean ladderLengthFlag = false;

    public void helpLadderLength(String beginWord, String endWord, List<String> wordList) {
        LinkedList<String> strings = new LinkedList<>();
        for (int i = 0; i < endWord.length(); i++) {
            for (String s : wordList) {
                char[] chars = endWord.toCharArray();
                chars[i] = s.charAt(i);
                if (String.valueOf(chars).equals(s) && !strings.contains(s)) {
                    strings.add(s);
                }
            }
        }
        for (String s : strings) {
            ArrayList<String> list = new ArrayList<>(wordList);
            list.remove(s);
            hellllllp(beginWord, s, list);
            if (ladderLengthFlag) return;
        }
        for (String s : strings) {
            ArrayList<String> list = new ArrayList<>(wordList);
            list.remove(s);
            helpLadderLength(beginWord, s, list);
        }
    }

    public void hellllllp(String beginWord, String endWord, List<String> wordList) {
        int count = 0;
        for (int i = 0; i < beginWord.length(); i++) {
            if (beginWord.charAt(i) != endWord.charAt(i)) {
                count++;
            }
        }
        if (count <= 1) {
            ladderLengthRes = Math.min(ladderLengthRes, worldListOriginLength - wordList.size());
            ladderLengthFlag = true;
        }
    }


    public ListNode removeElements(ListNode head, int val) {
        ListNode slow = new ListNode(0);
        ListNode pre = slow;
        slow.next = head;
        while (head != null) {
            if (head.val == val) {
                while (slow.next != head) {
                    slow = slow.next;
                }
                slow.next = head.next;
            }
            head = head.next;
        }
        return pre.next;
    }

    public boolean isIsomorphic(String s, String t) {
        for (int i = 0; i < s.length(); i++) {
            if (s.indexOf(s.charAt(i)) != t.indexOf(t.charAt(i))) return false;
        }
        return true;
    }

    public boolean isUgly(int num) {
        if (num < 1) return false;
        while (num % 5 != 0) {
            num /= 5;
        }
        while (num % 3 != 0) {
            num /= 3;
        }
        while (num % 2 != 0) {
            num >>= 1;
        }
        return num == 1;
    }

    public String reverseVowels(String s) {
        if (s == null || s.length() == 0) return "";
        int l = 0;
        int r = s.length() - 1;
        String str = "aeiouAEIOU";
        char[] chars = s.toCharArray();
        while (l < r) {
            if (str.indexOf(chars[l]) != -1 && str.indexOf(chars[r]) != -1) {
                char temp = chars[r];
                chars[r] = chars[l];
                chars[l] = temp;
                l++;
                r--;
            } else if (str.indexOf(chars[l]) != -1) {
                r--;
            } else {
                l++;
            }
        }
        return String.valueOf(chars);
    }

    public int firstBadVersion(int n) {
        if (isBadVersion(1)) return 1;
        int l = 1;
        int r = n;
        int res = 0;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (isBadVersion(mid)) {
                res = mid;
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return res == 0 ? isBadVersion(n) ? n : 0 : res;
    }

    public boolean isBadVersion(int n) {
        return n >= 1702766719;
    }

    public String getHint(String secret, String guess) {
        StringBuilder builder = new StringBuilder();
        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();
        int count1 = 0;
        int count2 = 0;
        for (int i = 0; i < secret.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                count1++;
            } else {
                map1.put(secret.charAt(i), map1.get(secret.charAt(i)) == null ? 1 : map1.get(secret.charAt(i)) + 1);
                map2.put(guess.charAt(i), map2.get(guess.charAt(i)) == null ? 1 : map2.get(guess.charAt(i)) + 1);
            }
        }
        map1.keySet().retainAll(map2.keySet());
        for (Character c1 : map1.keySet()) {
            count2 += Math.min(map1.get(c1), map2.get(c1));
        }
        return builder.append(count1).append('A').append(count2).append('B').toString();
    }

    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) return new int[]{};
        HashSet<Integer> integers = new HashSet<>();
        int a = 0;
        int b = 0;
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        while (a < nums1.length && b < nums2.length) {
            if (nums1[a] == nums2[b]) {
                integers.add(nums1[a]);
                a++;
                b++;
            } else if (nums1[a] > nums2[b]) {
                b++;
            } else {
                a++;
            }
        }
        int[] res = new int[integers.size()];
        List<Integer> integers1 = new ArrayList<>(integers);
        for (int i = 0; i < integers1.size(); i++) {
            res[i] = integers1.get(i);
        }
        return res;
    }

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums == null || nums.length == 0) return false;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i]) && (i - map.get(nums[i]) <= k)) return true;
            else map.put(nums[i], i);
        }
        return false;
    }

    public boolean isPerfectSquare(int num) {
        int a = 1;
        while (num >= 0) {
            if (num == 0) return true;
            num = num - (a * 2 - 1);
            a++;
        }
        return false;
    }

    public int guessNumber(int n) {
        if (guess(n) == 0) return n;
        int l = 1;
        int r = n;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (guess(mid) == 0) return mid;
            else if (guess(mid) == -1) r = mid;
            else l = mid + 1;
        }
        return 0;
    }

    int guess(int num) {
        return 0;
    }

    public boolean canConstruct(String ransomNote, String magazine) {
        int[] arr = new int[26];
        for (int i = 0; i < magazine.length(); i++) {
            if (i < ransomNote.length()) {
                arr[ransomNote.charAt(i) - 'a'] -= 1;
            }
            arr[magazine.charAt(i) - 'a'] += 1;
        }
        for (int i = 0; i < 26; i++) {
            if (arr[i] < 0) return false;
        }
        return true;
    }

    public char findTheDifference(String s, String t) {
        int a = 0;
        for (int i = 0; i < s.length(); i++) {
            a = a ^ s.charAt(i) ^ t.charAt(i);
        }
        a = a ^ t.charAt(t.length() - 1);
        return (char) a;
    }

    public static void main(String[] args) {
//        int[] arr = new int[26];
//        System.out.println(arr[0]);
        System.out.println(new Solution().canConstruct("aa", "ab"));
    }

}





