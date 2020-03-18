package SwordOffer;

import java.util.ArrayList;
import java.util.List;
import utils.ListNode;

public class _3 {
    ArrayList<Integer> res = new ArrayList<>();
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        if(listNode != null) {
            printListFromTailToHead(listNode.next);
            res.add(listNode.val);
        }
        return res;
    }

}
