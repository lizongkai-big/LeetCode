package TAG.DP;

// https://www.nowcoder.com/practice/72a5a919508a4251859fb2cfb987a0e6?tpId=13&tqId=11163&tPage=1&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking
public class RectCover {
    public int RectCover(int target) {
        int mem[] = new int[target+1];
        if(target <= 2)
            return target;
        mem[0] = 0;
        mem[1] = 1;
        mem[2] = 2;
        for(int i = 3; i <= target; i++) {
            mem[i] = mem[i-1] + mem[i-2];
        }
        return mem[target];
    }
}
