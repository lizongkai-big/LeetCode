package TAG.LinkedList;

import utils.ListNode;

public class _82__Remove_Duplicates_from_Sorted_List_II {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode pre = dummy;
        ListNode cur = head;
        while(cur != null) {
            if(cur.next != null && cur.val == cur.next.val) {
                int v = cur.val;
                // 跳过重复的节点
                while(cur != null && cur.val == v) {
                    cur = cur.next;
                }
            }
            else {
                pre.next = cur;
                pre = pre.next;
                cur = cur.next;
            }
        }
        pre.next = null;
        return dummy.next;
    }
}
