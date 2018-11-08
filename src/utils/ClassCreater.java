package utils;

import java.util.Scanner;

public class ClassCreater {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()) {
            String s = scanner.nextLine();
            s = " " + s;
            System.out.println(s.replaceAll("\\.|\\s+", "_"));
        }
    }
}
