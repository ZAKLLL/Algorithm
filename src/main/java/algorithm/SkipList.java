package algorithm;

import java.util.Random;

/**
 * @author ZhangJiaKui
 * @classname SkipList
 * @description 跳表
 * @date 2020/10/9 15:55
 */
public class SkipList<T> {
    //跳表数据节点数量
    public int n;
    //height
    public int h;
    //head
    private SkipListEntry head;
    //tail
    private SkipListEntry tail;
    //生成randomLevel
    private final Random random;

    public SkipList() {
        //首尾设置为极小值极大值代替null
        head = new SkipListEntry(Integer.MIN_VALUE, null);
        tail = new SkipListEntry(Integer.MAX_VALUE, null);
        head.right = tail;
        tail.left = head;
        n = 0;
        h = 0;
        random = new Random();
    }

    public SkipListEntry findEntry(int key) {
        SkipListEntry p = head;
        //跳到最后一层的目标key 的位置上(存在该key)或者key左边的位置上(不存在该key) 类比bisect.left
        while (true) {

            //找到比目标值大的节点
            while (p.right.key != Integer.MAX_VALUE && p.right.key < key) {
                p = p.right;
                //找到目标值
                if (p.key == key) {
                    return p;
                }
            }
            //跳到下一层
            if (p.down != null) {
                p = p.down;
            } else {
                break;
            }
        }
        return p;
    }

    /**
     * map.get()
     *
     * @param key
     * @return
     */
    public T get(int key) {
        SkipListEntry p = findEntry(key);
        if (p.key == key) {
            return p.value;
        }
        return null;
    }

    public void insert(int key, T value) {
        SkipListEntry p, q;
        //查找适合插入的位置
        p = findEntry(key);
        //存在该Key,更新键值对即可
        if (p.key == key) {
            p.value = value;
            return;
        }
        //如果不存在,则需要进行新增(在最后一层进行新增操作)
        q = new SkipListEntry(key, value);
        q.left = p;
        q.right = p.right;
        p.right.left = q;
        p.right = q;

        //最底一层
        int i = 0;

        //抛硬币决定是否向上层插入
        while (random.nextDouble() < 0.5) {
            if (i >= h) {
                addEmptyLevel();
            }
            //找到左边最近一个具有上级索引的entry
            while (p.up == null) {
                p = p.left;
            }
            //更新当前指针位置到上级索引
            p = p.up;
            //需要在上层节点 插入新增的节点作为索引
            SkipListEntry e = new SkipListEntry(key, value);
            //更新上层节点的连接情况
            e.left = p;
            e.right = p.right;
            e.down = q;

            p.right.left = e;
            p.right = e;
            q.up = e;

            //更新q的位置到e,此时p,q仍然相邻,p在q左边,继续循环判断是否需要继续在上方插入节点做索引
            q = e;
            //更新当前所在层数位置
            i++;
        }
        //更新链表长度
        n++;
    }

    /**
     * 在顶部添加一条新的索引
     */
    private void addEmptyLevel() {
        SkipListEntry nHead, nTail;
        nHead = new SkipListEntry(Integer.MIN_VALUE, null);
        nTail = new SkipListEntry(Integer.MAX_VALUE, null);
        nHead.right = nTail;
        nHead.down = head;

        nTail.left = nHead;
        nTail.down = tail;

        head.up = nHead;
        tail.up = nTail;

        head = nHead;
        tail = nTail;

        h++;
    }

    /**
     * 删除指定值
     *
     * @param key
     * @return
     */
    public T remove(int key) {
        SkipListEntry p, tmp;
        p = findEntry(key);
        if (p.key != key) {
            return null;
        }
        T oldValue = p.value;
        while (p != null) {
            tmp = p.up;
            p.left.right = p.right;
            p.right.left = p.left;
            p = tmp;
        }
        return oldValue;
    }

    /**
     * 跳表节点
     */
    class SkipListEntry {
        Integer key;
        T value;
        SkipListEntry left;
        SkipListEntry right;
        SkipListEntry up;
        SkipListEntry down;

        public SkipListEntry(Integer key, T value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "SkipListEntry{" +
                    "key=" + key +
                    ", value=" + value;
        }
    }

}


