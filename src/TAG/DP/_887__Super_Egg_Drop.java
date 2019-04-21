package TAG.DP;

public class _887__Super_Egg_Drop {
    public void superEggDrop(int k, int n) {
        int[][] mem = new int[k+1][n+1];
        for (int i = 1; i < n+1; i++) {
            mem[1][i] = i;
        }
        for (int i = 2; i < k+1; i++) {
            for (int j = 1; j < n+1; j++) {
                for (int l = 2; l < j; l++) {
                    // 最坏情况下的最优解？
                    mem[i][j] = Math.min(mem[i][j], 1 + Math.max(mem[i-1][l-1], mem[i][j-l]));
                }
            }

        }
    }
}
