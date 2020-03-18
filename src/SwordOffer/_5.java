package SwordOffer;

import java.util.Stack;

public class _5 {
    public int minNumberInRotateArray(int [] array) {
        int len = array.length;
        if(len == 0) return 0;
        int left = 0, right = len-1;
        int aimInx = 0;
        while(array[left] > array[right]) {
            int mid = (left + right) / 2;
            if(mid - 1 < left && array[mid] < array[mid - 1]) {
                aimInx = mid;
                break;
            }
            if(array[left] < array[mid]) {
                left = mid + 1;
            }
            if(array[left] > array[mid]) {
                right = mid - 1;
            }
        }
        return array[aimInx];
    }
}
