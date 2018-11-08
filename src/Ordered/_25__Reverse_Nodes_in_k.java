package Ordered;

import utils.ListNode;

public class _25__Reverse_Nodes_in_k {
    public ListNode reverseKGroup_Recrusive_Mine02(ListNode head, int k) {
        if(k == 1 || head == null)
            return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;

        while(needReverse(pre, k)) {
            // 初始化slow1, slow2, fast，分别指向已排序的链表段的首尾和下一个待排序的节点
            /*optimization 2*/
            // 实际上，slow1也可以省略，因为它一直是fast的下一个节点；带着也无妨
            ListNode slow1 = pre.next;
            ListNode slow2 = pre.next;
            ListNode fast = pre.next.next;
            // reverse k nodes
            for(int i = 1; i < k; i ++) {
                slow2.next = fast.next;
                fast.next = slow1;
                pre.next = fast;

                slow1 = fast;
                // error 1
                fast = slow2.next;
            }
            // 在pre之后的k个元素reverse之后，pre 向后移动k次；reverse之前不能动
            /*optimization 1*/
            /*for(int i = 0; i < k; i ++) {
                pre = pre.next;
            }*/
            // 实质上，pre指向的就是此时slow2的位置
            pre = slow2;
        }
        return dummy.next;
    }
    // k > 1
    private boolean needReverse(ListNode node, int k) {
        // if(node == null)
        //     return false;
        while(k-- > 0 && node.next != null) {
            node = node.next;
            System.out.println("--");
        }
        if(k == -1){
            return true;
        }
        return false;
    }

    public ListNode reverseKGroup_Iteration(ListNode head, int k) {
        if(k == 1 || head == null)
            return head;
        ListNode cur = head;
        for (int i = 0; i < k; i++) {
            if(cur == null) return head;
            cur = cur.next;
        }
        ListNode new_head = reverse(head, cur);
        head.next = reverseKGroup_Iteration(cur, k);
        return new_head;
    }

    private ListNode reverse(ListNode head, ListNode tail) {
        ListNode pre = tail;
        while(head != tail) {
            ListNode t = head.next;
            head.next = pre;
            pre = head;
            head = t;
        }
        return pre;
    }
}
