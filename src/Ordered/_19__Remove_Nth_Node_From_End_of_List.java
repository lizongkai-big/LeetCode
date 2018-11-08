package Ordered;

import utils.ListNode;

public class _19__Remove_Nth_Node_From_End_of_List {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode front = head, end = head;
        // 让head和end 拉开n个距离
        while(n-- > 0) {
            end = end.next;
        }
        // 说明删除的是第一个
        if(end == null) {
            head = head.next;
            return head;
        }
        // 让end往后走，直到让front指向被删元素的前一个位置
        while(end.next != null) {
            front = front.next;
            end = end.next;
        }
        ListNode tmp = front.next;
        front.next = tmp.next;
        return head;
    }
}
