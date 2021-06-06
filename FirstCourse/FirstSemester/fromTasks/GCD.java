package FirstSemester.fromTasks;

import java.util.Scanner;

public class GCD {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the first number:");
        long n = sc.nextLong();
        System.out.println("Enter the second number:");
        long m = sc.nextLong();
        long gcd = 1;
        long t1 = System.currentTimeMillis();
        for (long i = Math.min(n, m); i > 0; i--) {
            if (n % i == 0 && m % i == 0) {
                gcd = i;
                break;
            }
        }
        System.out.println("Brute force: GCD = " + gcd + ".");
        long a = System.currentTimeMillis() - t1;

        t1 = System.currentTimeMillis();
        while (m != n) {
            if (m > n) {
                m -= n;
            } else {
                n -= m;
            }
        }
        System.out.println("Euclid's Algorithm: GCD = " + n + ".");
        long b = System.currentTimeMillis() - t1;
        if (b != a) {
            if (b > a) {
                System.out.println("Brute force faster than Euclid's Algorithm by " + (b - a) + ("ms."));
            } else {
                System.out.println("Euclid's Algorithm faster than Brute force by " + (a - b) + ("ms."));
            }
        } else {
            System.out.println("Euclid's Algorithm and Brutus Force took the same amount of time.");
        }
    }
}
