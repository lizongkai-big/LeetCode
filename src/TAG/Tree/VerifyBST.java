package TAG.Tree;

import java.util.Arrays;

public class VerifyBST {
    public boolean VerifySquenceOfBST(int [] sequence) {
        int len = sequence.length;
        if (len == 0) return false;
        if (len <= 2) return true;
        int pivot = sequence[len - 1];
        int big = len;
        for (int i = 0; i < len - 1; i++) {
            if (sequence[i] > pivot) {
                big = i;
                break;
            }
        }
        // 只有左子树
        if (big == len) return true;
        int i = big;
        for (; i < len - 1; i++) {
            if (sequence[i] < pivot) {
                return false;
            }
        }
        // 只有右子树
        if (i == len - 1) return true;

        // 既有左子树，又有右子树：[0, big) pivot [big,len-1)
        return VerifySquenceOfBST(Arrays.copyOfRange(sequence, 0, big))
                &&
                VerifySquenceOfBST(Arrays.copyOfRange(sequence, big, sequence.length - 1));
    }
}
