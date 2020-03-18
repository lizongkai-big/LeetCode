package SwordOffer;

public class _1 {
    // 二维数组array中，每一行都从左到右递增排序，
    // 每一列都从上到下递增排序
    public boolean Find(int target, int [][] array) {
        if(array == null)
            return false;
        int row = array.length;
        int col = array[0].length;
        if(col == 0)
            return false;
        int i = 0, j = col - 1;
        boolean found = false;
        while(i < row && j >= 0) {
            if(array[i][j] == target) {
                found = true;
                break;
            }
            else if(array[i][j] < target) {
                i ++;
            }
            else {
                j --;
            }
        }
        return found;
    }
}
