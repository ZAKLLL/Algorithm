package DataStructure;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: suanfa
 * @description: 前缀树
 * @author: ZakL
 * @create: 2020-01-02 17:53
 **/
public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        if (word == null) return;
        char[] chs = word.toCharArray();
        int index;
        TrieNode node = root;
        for (char ch : chs) {
            index = ch - 'a';
            if (node.nexts[index] == null) {
                node.nexts[index] = new TrieNode();
            }
            node = node.nexts[index];
            node.path++;
        }
        node.end++;
    }

    /**
     * @param word
     * @return nums Of word
     */
    public int search(String word) {
        if (word == null) return 0;
        char[] chs = word.toCharArray();
        TrieNode node = root;
        int index;
        for (char ch : chs) {
            index = ch - 'a';
            if (node.nexts[index] == null) {
                return 0;
            }
            node = node.nexts[index];
        }
        return node.end;
    }

    /**
     * 删除字段树中的某个字符串
     *
     * @param word
     */
    public void delete(String word) {
        if (search(word) != 0) {
            char[] chs = word.toCharArray();
            TrieNode node = root;
            int index;
            for (char ch : chs) {
                index = ch - 'a';
                if (--node.nexts[index].path == 0) {
                    node.nexts[index] = null;
                    return;
                }
                node = node.nexts[index];
            }
            node.end--;
        }
    }

    /**
     * @param pre
     * @return 当前前缀字符串出现的次数
     */
    public int prefixNumber(String pre) {
        if (pre == null) return 0;
        char[] chs = pre.toCharArray();
        int index;
        TrieNode node = root;
        for (char ch : chs) {
            index = ch - 'a';
            if (node.nexts[index] == null) return 0;
            node = node.nexts[index];
        }
        return node.path;
    }

//    public boolean startsWith(String prefix) {
//        return prefixNumber(prefix) != 0;
//    }

    public List<String> startsWith(String pre) {
        ArrayList<String> res = new ArrayList<>();
        if (prefixNumber(pre) == 0) return res;
        char[] chs = pre.toCharArray();
        int index;
        TrieNode node = root;
        for (char ch : chs) {
            index = ch - 'a';
            if (node.nexts[index] == null) return res;
            node = node.nexts[index];
        }
        List<String> dfs = dfs(node);
        for (int i = 0; i < dfs.size(); i++) {
            dfs.set(i, pre + dfs.get(i));
        }
        return dfs;
    }

    private List<String> dfs(TrieNode node) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            TrieNode next = node.nexts[i];
            if (next != null) {
                List<String> dfs = dfs(next);
                for (String df : dfs) {
                    res.add((char) (i + 'a') + df);
                }
            }
        }
        return res;
    }
}


class TrieNode {
    public int path; //有多少字符串经过该点
    public int end;  //有多少字符串以该点结尾
    public TrieNode[] nexts; //当前点的儿子节点字母

    public TrieNode() {
        path = 0;
        end = 0;
        nexts = new TrieNode[26];
    }
}


