package Easy;

import utils.ListNode;

public class _2__Add_Two_Numbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode list = new ListNode(0);
        ListNode res = list;
        while(l1 != null || l2 != null || carry == 1) {
            if(l1 != null){
                carry += l1.val;
                l1 = l1.next;
            }
            if(l2 != null){
                carry += l2.val;
                l2 = l2.next;
            }
            ListNode node = new ListNode(carry % 10);
            carry /= 10;
            list.next = node;
            list = list.next;
        }
        return res.next;
    }
}
