package firstSemester.fromTasks;

import java.util.Scanner;

public class PascalPrintln {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int middle = factorial(n) / (factorial(n / 2) * factorial(n - n / 2));
        int length = 1;
        int lengthk = 0;
        int k = 0;
        int deltaLength = 0;
        //System.out.println(middle);
        while (middle > 0) {
            length += 1;
            middle /= 10;
        }
        //System.out.println(length);
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j < (n - i); j++) {
                for (int z = 0; z < length; z++) {
                    System.out.print(" ");
                }
            }
            for (int j = 0; j < i + 1; j++) {
                k = factorial(i) / (factorial(j) * factorial(i - j));
                System.out.print(k);
                while (k > 0) {
                    lengthk += 1;
                    k /= 10;
                }
                deltaLength = length - lengthk;
                for (int z = 0; z < deltaLength; z++) {
                    System.out.print(" ");
                }
                lengthk = 0;
                for (int z = 0; z < length; z++) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public static int factorial(int x) {
        int y = 1;
        if (x == 0) {
            return 1;
        } else {
            for (int i = 1; i <= x; i++) {
                y *= i;
            }
            return y;
        }
    }
}
