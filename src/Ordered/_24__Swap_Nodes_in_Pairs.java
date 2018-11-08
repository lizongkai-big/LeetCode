package Ordered;

import utils.ListNode;

public class _24__Swap_Nodes_in_Pairs {
    public ListNode swapPairs_Iteration_Mine01(ListNode head) {
        if(head == null)
            return null;
        // dummy 节点
        ListNode res = new ListNode(0);
        res.next = head;
        ListNode slow = head;
        ListNode fast = head.next;
        ListNode front = res;
        while(fast != null) {
            slow.next = fast.next;
            fast.next = slow;
            front.next = fast;

            front = slow;
            if(slow.next != null && slow.next.next != null) {
                fast = slow.next.next;
                slow = slow.next;
            }
            else{
                slow = null;
                fast = null;
            }
        }
        return res.next;
    }

    public ListNode swapPairs_Iteration_Others(ListNode head) {
        // 对于空链表或只有一个节点的链表，无需swap
        if(head == null || head.next == null)
            return head;
        // dummy Node
        ListNode res = new ListNode(0);
        res.next = head;
        // 要有前节点
        ListNode front = res;
        // 如果链表的剩余部分为空或只有一个节点，无需swap
        while(front.next != null && front.next.next != null) {
            // 草纸上模拟下就晓得了
            ListNode slow = front.next;
            ListNode fast = front.next.next;

            slow.next = fast.next;
            fast.next = slow;
            front.next = fast;

            front = slow;
        }
        return res.next;
    }

    public ListNode swapPairs_Iteration(ListNode head) {
        // 对于空链表和只有一个节点的链表，不做交换直接返回
        if(head == null || head.next == null)
            return head;
        // n 表示第二个节点
        ListNode n = head.next;
        // 交换前两个节点，现在的头结点就成了n
        n.next = head;
        // 把第二个节点之后的链表当成一个新的链表
        head.next = swapPairs_Iteration(head.next.next);
        return n;
    }
}
