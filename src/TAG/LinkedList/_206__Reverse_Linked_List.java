package TAG.LinkedList;

import utils.ListNode;

public class _206__Reverse_Linked_List {
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode node = head;
        while (node != null) {
            ListNode tmp = node;
            node = node.next;
            tmp.next = pre;
            pre = tmp;
        }
        return pre;
    }
}
