package TAG.LinkedList;

import java.util.HashMap;

/**
 * 双向链表
 */
class DLinkedNode {
    public int val;
    // 用于hash表查找、删除
    public String key;
    public DLinkedNode pre, post;

    public DLinkedNode(int v) {
        val = v;
        key = "";
        pre = null;
        post = null;
    }
}

/**
 * 使用hash表 + 双向链表模拟实现的LRU，使得放入和移除都是 O(1)，
 * 参考：https://blog.csdn.net/hopeztm/article/details/79547052
 * hash表：保证查找的时间复杂度是O(1)
 * 链表：保证删除、插入的时间复杂度是O(1)
 * 双向：方便删除节点之后链表的恢复
 *
 * 缺点：如果LRU链表本身很短，双向链表的双指针会显得浪费空间
 */
public class LRU {
    private HashMap<String, DLinkedNode>
            cache;
    private int count;
    private int capacity;
    private DLinkedNode head, tail;

    public LRU(int capacity) {
        this.count = 0;
        this.capacity = capacity;
        this.cache = new HashMap<>();

        head = new DLinkedNode(-1);
        tail = new DLinkedNode(-1);
        head.pre = null;
        head.post = tail;
        tail.pre = head;
        tail.post = null;
    }

    public int get(int value) {
        DLinkedNode node = cache.get(value + "");
        if (node == null) {
            return -1; // should raise exception here.
        }
        // move the accessed node to the head;
        this.moveToHead(node);
        return node.val;
    }

    public void set(int value) {
        DLinkedNode node = cache.get(value + "");

        if (node == null) {
            DLinkedNode newNode = new DLinkedNode(value);
            newNode.key = value + "";
            this.cache.put(value + "", newNode);
            this.insertHead(newNode);
            ++count;
            if (count > capacity) {
                // pop the tail
                DLinkedNode tail = this.popTail();
                this.cache.remove(tail.key);
                --count;
            }
        } else {
            this.moveToHead(node);
        }
    }

    /**
     * 双向链表中插入表头
     *
     * @param node
     */
    private void insertHead(DLinkedNode node) {
        DLinkedNode next = head.post;
        head.post = node;
        node.pre = head;
        node.post = next;
        next.pre = node;
    }

    /**
     * 双向链表中删除节点
     *
     * @param node
     */
    private void removeNode(DLinkedNode node) {
        DLinkedNode pre = node.pre;
        DLinkedNode post = node.post;
        pre.post = post;
        post.pre = pre;
        node.post = null;
        node.pre = null;
    }

    /**
     * 将某个节点移动到双向链表表头
     *
     * @param node
     */
    private void moveToHead(DLinkedNode node) {
        this.removeNode(node);
        this.insertHead(node);
    }

    /**
     * 删除表尾
     *
     * @return
     */
    private DLinkedNode popTail() {
        DLinkedNode pre = tail.pre;
        this.removeNode(pre);
        return pre;
    }

    public DLinkedNode getTail() {
        return tail;
    }

    public DLinkedNode getHead() {
        return head;
    }

    public void print() {
        DLinkedNode tmp = head.post;
        while (tmp != null && tmp != tail) {
            System.out.printf(tmp.val + " ");
            tmp = tmp.post;
        }
        System.out.println();
    }

    public void printRvsOrder() {
        DLinkedNode tmp = tail.pre;
        while (tmp != null && tmp != head) {
            System.out.printf(tmp.val + " ");
            tmp = tmp.pre;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LRU lru = new LRU(4);
        for (int i = 0; i < 200; i++) {
            int rand = (int) (Math.random() * 10);
            System.out.print("set: " + rand + "\n");
            lru.set(rand);
            lru.print();
            rand = (int) (Math.random() * 10);
            System.out.print("get: " + rand + "\n");
            int find = lru.get(rand);
            System.out.println(find);
            if(find != -1)
                lru.print();
        }
    }
}
