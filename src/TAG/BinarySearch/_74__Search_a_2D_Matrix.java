package TAG.BinarySearch;

public class _74__Search_a_2D_Matrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int m = matrix.length, n = matrix[0].length;
        int lo = 0, hi = m-1;
        int row = 0, col = 0;
        // 最后一个小于等于target的元素
        while (lo <= hi) {
            int mid = lo + ((hi-lo) >> 1);
            if(matrix[mid][0] <= target) {
                if(matrix[mid][0] == target) return true;
                if(matrix[mid+1][0] > target) row = target;
                lo = mid + 1;
            }
            else {
                hi = mid - 1;
            }
        }
        lo = 0;
        hi = n - 1;
        while (lo < hi) {
            int mid = lo + ((hi-lo) >> 1);
            if(matrix[row][mid] <= target) {
                if(matrix[row][mid] == target) return true;
                if(matrix[row][mid] > target) ;
                lo = mid + 1;
            }
            else {
                hi = mid - 1;
            }
        }
        return false;
    }
}
