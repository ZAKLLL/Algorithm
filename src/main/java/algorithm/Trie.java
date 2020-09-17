package algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @program: suanfa
 * @description: 前缀树
 * @author: ZakL
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

    public boolean startsWith(String prefix) {
        return prefixNumber(prefix) != 0;
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


