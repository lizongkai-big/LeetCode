package TAG.BackTracking;

public class EightQueens {
    final int SIZE = 8;
    int[] result = new int[SIZE];

    public void cal8queens(int row) {
        if(row == SIZE) {
            printQueens(result);
            return;
        }
        for (int column = 0; column < SIZE; column++) {
            if(isOK(row, column)) {
                result[row] = column;
                cal8queens(row+1);
            }
        }
    }

    public boolean isOK(int r, int c) {
        int leftup = c - 1, rightup = c + 1;
        for (int i = r - 1; i >= 0; i--) {
            if(result[i] == c) return false;
            if(leftup >= 0) {
                if(result[i] == leftup) return false;
            }
            if(rightup < 8) {
                if(result[i] == rightup) return false;
            }
            leftup--;
            rightup++;
        }
        return true;
    }

    private void printQueens(int[] result) {
        for (int row = 0; row < SIZE; row++) {
            for (int column = 0; column < SIZE; column++) {
                if(result[row] == column)
                    System.out.print("Q");
                else
                    System.out.print("* ");
            }
            System.out.println();
        }
    }
}
