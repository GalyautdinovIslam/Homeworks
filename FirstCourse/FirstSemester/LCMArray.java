package FirstSemester;

import java.util.Scanner;

public class LCMArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the first number:");
        int n = sc.nextInt();
        System.out.println("Enter the second number:");
        int m = sc.nextInt();
        long lcm = 1;
        if (n > 0 && m > 0) {
            long[] first = new long[n + 1];
            long[] second = new long[m + 1];
            int i = 2;
            int min = Math.min(n, m);
            int max = Math.max(n, m);
            boolean check = false;
            if (n > m) {
                check = true;
            }
            while (i <= n) {
                if (n % i == 0) {
                    first[i] += 1;
                    n /= i;
                } else {
                    i += 1;
                }
            }
            int j = 2;
            while (j <= m) {
                if (m % j == 0) {
                    second[j] += 1;
                    m /= j;
                } else {
                    j += 1;
                }
            }
            for (int k = 2; k <= min; k++) {
                lcm *= Math.pow(k, Math.max(first[k], second[k]));
            }
            for (int k = min + 1; k <= max; k++) {
                if (check) {
                    lcm *= Math.pow(k, first[k]);
                } else {
                    lcm *= Math.pow(k, second[k]);
                }
            }
            System.out.println("LCM = " + lcm + ".");
        } else {
            System.out.println("The numbers must be more than 0.");
        }
    }
}
