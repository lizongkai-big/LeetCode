package Ordered;

import utils.ListNode;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class _23__Merge_k_Sorted_Lists {
    /**
     * 所有的链表比较出最小的那个，然后加入到结果集中，同时该链表指针后移
     * O(n * MAX(length))
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists_Mine(ListNode[] lists) {
        int len = lists.length;
        ListNode head = new ListNode(0);
        ListNode res = head;
        while (true) {
            int min = Integer.MAX_VALUE;
            int moveInx = -1;
            // && !allEmpty(lists)
            for (int i = 0; i < len; i++) {
                if (lists[i] == null)
                    continue;
                if (lists[i].val < min) {
                    min = lists[i].val;
                    moveInx = i;
                }
            }
            // lists is ALL EMPTY
            if (moveInx == -1)
                break;
            head.next = new ListNode(min);
            head = head.next;
            lists[moveInx] = lists[moveInx].next;
        }
        return res.next;
    }

    /**
     * 使用分治的方法，两两合并
     *
     * @param lists
     * @return
     */
    public static ListNode mergeKLists_MergerWithDivideConquer(ListNode[] lists) {
        int len = lists.length;
        while (len > 1) {
            // 步长
            int step = (len + 1) / 2;
            // 下标最大值
            int inx = len / 2;
            for (int i = 0; i < inx; i++) {
                lists[i] = merge(lists[i], lists[i + step]);
            }
            len = step;
        }
        return lists[0];
    }

    //This function is from Merge Two Sorted Lists.
    public static ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val) {
            l1.next = merge(l1.next, l2);
            return l1;
        } else {
            l2.next = merge(l1, l2.next);
            return l2;
        }
    }

    /**
     * 借助最小堆
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists_MinimumHeap(List<ListNode> lists) {
        if (lists == null || lists.size() == 0) return null;

        PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(lists.size(), new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                if (o1.val < o2.val)
                    return -1;
                else if (o1.val == o2.val)
                    return 0;
                else
                    return 1;
            }
        });

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        for (ListNode node : lists)
            if (node != null)
                queue.add(node);

        while (!queue.isEmpty()) {
            tail.next = queue.poll();
            tail = tail.next;

            if (tail.next != null)
                queue.add(tail.next);
        }
        return dummy.next;
    }
}
