package utils;

public class _Queue {

}

/**
 * 用数组实现的队列
 */
class ArrayQueue {
    // 数组：items，数组大小：n
    private String[] items;
    private int n = 0;
    // head 表示队头下标，tail 表示队尾下标
    private int head = 0;
    private int tail = 0;

    // 申请一个大小为 capacity 的数组
    public ArrayQueue(int capacity) {
        items = new String[capacity];
        n = capacity;
    }

    // 入队操作，将 item 放入队尾
    public boolean enqueue(String item) {
        // tail == n 表示队列末尾没有空间了
        if (tail == n) {
            // tail ==n && head==0，表示整个队列都占满了
            if (head == 0) return false;
            // 数据搬移
            for (int i = head; i < tail; ++i) {
                items[i-head] = items[i];
            }
            // 搬移完之后重新更新 head 和 tail
            tail -= head;
            head = 0;
        }

        items[tail] = item;
        ++tail;
        return true;
    }


    // 出队
    public String dequeue() {
        // 如果 head == tail 表示队列为空
        if (head == tail) return null;
        // 为了让其他语言的同学看的更加明确，把 -- 操作放到单独一行来写了
        String ret = items[head];
        ++head;
        return ret;
    }
}

class Node {
    public Node next;
    public String s;
    public Node(String s) {
        this.s = s;
    }
}

/**
 * 与ArrayQueue的区别还是很大的
 */
class NodeQueue {
    private Node head;
    /*tail 要指向队列中的最后一个节点，而不是指向最后一个节点之后的位置（也就是恒为空）；
    如果指向空，那么队列就没有尾部指针，怎么向队列的尾部添加新节点呢？*/
    private Node tail;

    public NodeQueue() {
        head = tail = null;
    }

    public void enqueue(String s) {
        // 不存在队列满的情况，但要考虑队列空的情况，要考虑队列空，要考虑队列空
        // 如果不考虑队列空(本方法中不存在if中的内容)，在head=tail=null的时候，直接插入一个新节点，
        // head会一直指向空，肯定是不行的！
        if(tail == null) {
            Node newNode = new Node(s);
            head = newNode;
            /*tail 要指向队列中的最后一个节点，而不是指向空；
            如果指向空，那么队列就没有尾部指针，怎么向队列的尾部添加呢？*/
            tail = newNode;
        }
        else {
            Node node = new Node(s);
            tail.next = node;
            tail = tail.next;
        }
    }

    public String dequeue() {
        if(head == null) {
            return null;
        }
        String res = head.s;
        head = head.next;
        if(head == null)
            tail = null;
        return res;
    }

    public void printAll() {
        Node p = head;
        while (p != null) {
            System.out.print(p.s + " ");
            p = p.next;
        }
        System.out.println();
    }
}

/**
 * 循环队列
 */
class CircularQueue {
    private String[] items;
    private int capacity;
    private int head = 0;
    private int tail = 0;

    CircularQueue(int capacity) {
        this.capacity = capacity;
        items = new String[capacity];
    }

    public boolean enqeue(String s) {
        // 队满
        if((tail + 1) % capacity == head) {
            return false;
        }
        items[tail] = s;
        // 队尾下标 += 1，记得取余
        tail = (tail + 1) % capacity;
        return true;
    }

    public String dequeue() {
        // 队空
        if(head == tail) {
            return null;
        }
        String r = items[head];
        // 队头下标 += 1，记得取余
        head = (head + 1) % capacity;
        return r;
    }
}
