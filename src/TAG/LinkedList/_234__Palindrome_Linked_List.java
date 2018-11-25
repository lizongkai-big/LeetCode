package TAG.LinkedList;

import utils.ListNode;

/**
 * 判断一个单链表是否为回文的
 */
public class _234__Palindrome_Linked_List {
    /**
     * https://github.com/andavid/leetcode-java/blob/master/note/234/README.md
     * 利用快慢指针，让slow指向链表的中点，并倒置前半个链表，最后比较中点两侧的链表是否相等
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        // zero node or only one node
        if(head == null || head.next == null) return true;
        ListNode pre = null; // pre初始化不能是head，不然在倒置时会有head.next = head，而head.next本身指向head，形成一个循环！
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null) {
            // 倒置结点，需要一个临时结点，仅靠slow，fast不能实现倒置
            ListNode tmp = slow;
            /*-------slow和fast的向后移动，要在倒置结点之前----------*/
            slow = slow.next;
            fast = fast.next.next;

            tmp.next = pre;
            pre = tmp;
        }
        /*让slow指向链表的后半部分的开始*/
        if(fast != null)
            slow = slow.next;
        while(slow != null) {
            if(slow.val != pre.val) {
                return false;
            }
            slow = slow.next;
            pre = pre.next;
        }
        // 恢复现场
        return true;
    }

    public void two_points_practice(ListNode head) {
        if(head == null || head.next == null) return;
        ListNode slow = head, fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        if(fast != null)
            slow = slow.next;
    }
}
