package SwordOffer;

// DP
public class JumpFloorII {
    public int JumpFloorII(int target) {
        int[] f = new int[target+1];
        f[1] = 1;
        for (int i = 2; i <= target; i++) {
            // f[n] = f[1]+f[2]+f[3]...+f[n-1] + 1  等价于 f[n] = 2 * f[n-1]
            /*int sum = 0;
            for (int j = 1; j < i; j++) {
                sum += f[j];
            }
            f[i] = sum + 1;*/

            f[i] = f[i-1] * 2;
        }
        return f[target];
    }
}
