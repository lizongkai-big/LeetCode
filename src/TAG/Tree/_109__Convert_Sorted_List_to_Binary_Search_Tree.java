package TAG.Tree;


import utils.TreeNode;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {val = x;}
}

/**
 * 利用了快慢指针
 *
 */
public class _109__Convert_Sorted_List_to_Binary_Search_Tree {
    public TreeNode sortedListToBST(ListNode head) {
        if(head == null) return null;
        return toBST(head, null);
    }

    public TreeNode toBST(ListNode head, ListNode tail) {
        if(head == tail){
            return null;
        }
        // fast 为了探测边界
        ListNode fast = head;
        // slow 才是真正需要的中间位置
        ListNode slow = head;
        while(fast != tail && fast.next != tail) {
            fast = fast.next.next;
            slow = slow.next;
        }
        TreeNode node = new TreeNode(slow.val);
        node.left = toBST(head, slow);
        node.right = toBST(slow.next, tail);
        return node;
    }
}
