package TAG.BackTracking;

import java.util.ArrayList;
import java.util.List;

public class _93__Restore_IP_Addresses {
    public List<String> restoreIpAddresses(String s) {
        List<String> solutions = new ArrayList<String>();
        restoreIp(s, solutions, 0, "", 0);
        return solutions;
    }

    private void restoreIp(String ip, List<String> solutions, int idx, String restored, int count) {
        // 剪枝也有优先级；如果这两个判断条件交换一下，程序就会超时的
        if (count == 4) { // restored中有四个部分很快，但达到ip的长度很难
            if(idx == ip.length())
                solutions.add(restored);
            return;
        }

        for (int i=1; i<4; i++) {
            if (idx+i > ip.length()) break;
            String s = ip.substring(idx,idx+i);
            // ****对以0开头本身又不是0的ip段（如00,03,003）进行剪枝
            if ((s.startsWith("0") && s.length()>1) || (i==3 && Integer.parseInt(s) >= 256)) continue;
            restoreIp(ip, solutions, idx+i, restored+s+(count==3?"" : "."), count+1);
        }
    }


    public static void main(String[] args) {
        _93__Restore_IP_Addresses restore_ip_addresses = new _93__Restore_IP_Addresses();
        restore_ip_addresses.restoreIpAddresses("19216811");
    }
}
