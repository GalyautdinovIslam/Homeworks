package FirstSemester;

import java.util.Scanner;

public class LCM {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the first number:");
        long n = sc.nextLong();
        System.out.println("Enter the second number:");
        long m = sc.nextLong();
        if (n > 0 && m > 0) {
            long lcm = n * m;
            for (long i = Math.max(n, m); i < lcm; i++) {
                if (i % n == 0 && i % m == 0) {
                    lcm = i;
                }
            }
            System.out.println("LCM = " + lcm + ".");
        } else {
            System.out.println("The numbers must be more than 0.");
        }
    }
}
