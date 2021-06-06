package FirstSemester.fromTasks;

import java.util.Scanner;

public class FactorialCalc {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        print("Enter a number:");
        int n = sc.nextInt();
        if (n < 0) {
            print("Number must be more than 0");
        } else {
            long result = 1;
            int i = 1;
            long time0 = System.nanoTime();
            while (i <= n) {
                result *= i;
                i++;
            }
            long time1 = System.nanoTime();
            long time = time1 - time0;
            print(n + "! = " + result + ".");
            print("Calculated in " + time + " nanoseconds with \"while\".");

            time0 = System.nanoTime();
            result = calcFact(n);
            time1 = System.nanoTime();
            time = time1 - time0;
            print(n + "! = " + result + ".");
            print("Calculated in " + time + " nanoseconds with recursion.");
        }
    }

    public static long calcFact(int n) {
        if (n == 1) {
            return 1;
        } else {
            return n * calcFact(n - 1);
        }
    }

    public static void print(String str) {
        System.out.println(str);
    }
}
