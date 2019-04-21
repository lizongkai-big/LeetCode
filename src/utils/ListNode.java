package utils;

public class ListNode {
    public int val;
    public ListNode next;
    public ListNode(int x) {val = x;}

    public static ListNode buildListNode(int[] num) {
        ListNode dummy = new ListNode(-1);
        ListNode head = dummy;
        for (int i: num) {
            dummy.next = new ListNode(i);
            dummy = dummy.next;
        }
        return head.next;
    }

    public static void printListNode(ListNode head) {
        if(head == null) {
            System.out.println("This ListNode is NULL!");
            return;
        }
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static ListNode reverseIterative(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode tmp = cur;
            cur = cur.next;
            tmp.next = pre;
            pre = tmp;
        }
        return pre;
    }

    public static ListNode reverseRecursive(ListNode head) {
        return reverseListRecursive(head, null);
    }

    private static ListNode reverseListRecursive(ListNode cur, ListNode pre) {
        if(cur == null) return pre;
        ListNode tmp = cur;
        cur = cur.next;
        tmp.next = pre;
        pre = tmp;
        return reverseListRecursive(cur, pre);
    }

    public static void main(String[] args) {
        int[] num = new int[]{3,4,5,0,1,2};
        ListNode head = ListNode.buildListNode(num);
        ListNode.printListNode(head);
        head = ListNode.reverseIterative(head);
        ListNode.printListNode(head);
        head = ListNode.reverseRecursive(head);
        ListNode.printListNode(head);
    }
}
