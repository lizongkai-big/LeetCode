package TAG;

import utils.ListNode;
import utils.TreeNode;

import java.util.*;
import java.util.stream.Collectors;

public class tmp {
    public int i, j;
    public tmp(int i, int j){
        this.i = i;
        this.j = j;
    }
    public int minInRotatedArray(int[] array) {
        int lo = 0, hi = array.length;
        int mid = lo;
        while (array[lo] >= array[hi]) {
            if (hi - lo == 1) {
                mid = hi;
                break;
            }
            mid = lo + ((hi - lo) >> 1);
            if (array[mid] == array[hi] && array[mid] == array[lo]) {
                mid = getMin(array, lo, hi);
                break;
            }
            if (array[lo] < array[mid]) {
                lo = mid;
            }
            if (array[mid] < array[hi]) {
                hi = mid;
            }
        }
        return array[mid];
    }

    public int getMin(int[] array, int lo, int hi) {
        int res = array[lo];
        for (int i = lo + 1; i <= hi; i++) {
            if (res > array[i]) res = array[i];
        }
        return res;
    }

    public ListNode detectCycle(ListNode head) {
        ListNode s = head, f = head;
        ListNode meet = null, newHead = head;
        while (f != null && f.next != null) {
            s = s.next;
            f = f.next.next;
            if (s == f) {
                meet = s;
                break;
            }
        }
        // 没有环
        if (meet == null) return null;
        while (meet != newHead) {
            meet = meet.next;
            newHead = newHead.next;
        }
        return meet;
    }



    public void getNexts(int[] array) {
        if (array == null || array.length == 0) return;
        int k = -1;
        int[] next = new int[array.length];
        next[0] = -1;
        for (int i = 1; i < array.length - 1; i++) {
            while (k > -1 && array[k + 1] != array[i])
                k = next[k];
            if (array[k + 1] == array[i])
                k++;
            array[i] = k;
        }
    }


    public void countingSort(int[] nums) {
        int len = nums.length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            if(min > nums[i]) min = nums[i];
            if(max < nums[i]) max = nums[i];
        }
        int cLen = max - min + 1;
        int[] c = new int[cLen];
        for (int i = 0; i < len; i++) {
            c[nums[i]-min] ++;
        }
        for (int i = 1; i < c.length; i++) {
            c[i] += c[i-1];
        }
        int[] res = new int[len];
        for (int i = len-1; i >= 0; i--) {
            res[c[nums[i]-min]-1] = nums[i];
            c[nums[i]-min] --;
        }
        System.out.println(Arrays.stream(res).boxed().collect(Collectors.toList()));
    }

    public void findFirstBig(int[] nums) {
        int len = nums.length;
        for (int k = 0; k < len; k++) {
            int res = -1;
            for (int l = k-1; l >= 0; l--) {
                if(nums[l] > nums[k]) {
                    res = nums[l];
                    break;
                }
            }
            System.out.print(res + " ");
        }
    }

    public void findFirstBig_Stack(int[] nums) {
        int len = nums.length;
        // 存放下标
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[len];
        Arrays.fill(res, -1);
        for (int k = 0; k < len/2; k++) {
            swap(nums, k, len-k-1);
        }
        for (int k = 0; k < len; k++) {
            while (true) {
                if (stack.isEmpty() || nums[k] <= nums[stack.peek()]) {
                    stack.push(k);
                    break;
                }
                else {
                    res[stack.pop()] = nums[k];
                }
            }
        }
        for (int k = 0; k < len/2; k++) {
            swap(res, k, len-k-1);
        }
        System.out.println(Arrays.stream(res).boxed().collect(Collectors.toList()));
    }


    public void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    public static void main(String[] args) {
        tmp t = new tmp(1,1);
        int[] num = new int[]{-1, 1, -1, -3, -4, 5, 4, 1, 0, -5, 1, 2};
        // System.out.println(t.minInRotatedArray(new int[]{3,4,5,0,1,2}));
        // System.out.println(t.minInRotatedArray(new int[]{0,1,2,3,4,5}));
        // System.out.println(t.minInRotatedArray(new int[]{1,1,1,0,1,1}));
        // System.out.println(t.minInRotatedArray(new int[]{1,0,1,1,1,1}));
        // t.quick_sort_recursive(num);
        // t.quick_sort_iterative(num);
        // System.out.println(t.IsPopOrder(new int[]{1}, new int[]{4}));
        // t.knapsack(new int[]{2,2,4,6,3}, new int[]{3,4,8,9,6}, 5, 9);
        t.findFirstBig(num);
        System.out.println();
        t.findFirstBig_Stack(num);
    }
}