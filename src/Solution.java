import DataStructure.*;

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

    public String longestPalindrome(String s) {
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

    public static void main(String[] args) {
        int[][] nums = new int[][]{
                {0, 0, 0, 5},
                {4, 3, 1, 4},
                {0, 1, 1, 4},
                {1, 2, 1, 3},
                {0, 0, 1, 1},
        };
        new Solution().setZeroes(nums);
        for (int[] num : nums) {
            for (int i : num) {
                System.out.print(i + " ");
            }
            System.out.println("");
        }
        System.out.println();

    }

//      备用TreeNode
//    {
//        TreeNode t3 = new TreeNode(3);
//        TreeNode t5 = new TreeNode(5);
//        TreeNode t6 = new TreeNode(6);
//        TreeNode t2 = new TreeNode(2);
//        TreeNode t7 = new TreeNode(7);
//        TreeNode t4 = new TreeNode(4);
//        TreeNode t1 = new TreeNode(1);
//        TreeNode t0 = new TreeNode(0);
//        TreeNode t8 = new TreeNode(8);
//        t3.left = t5;
//        t3.right = t1;
//        t5.left = t6;
//        t5.right = t2;
//        t2.left = t7;
//        t2.right = t4;
//        t1.left = t0;
//        t1.right = t8;
//        Solution solution = new Solution();
//        TreeNode treeNode = solution.lowestCommonAncestor(t3, t5, t2);
//        System.out.println(treeNode.val);
//    }

}

