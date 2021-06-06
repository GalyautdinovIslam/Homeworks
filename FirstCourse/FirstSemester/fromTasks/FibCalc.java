package FirstSemester.fromTasks;

import java.util.Scanner;

public class FibCalc {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        print("Enter a number:");
        int n = sc.nextInt();
        if (n < 1) {
            print("The number must be more than 1.");
        } else {
            long time0 = System.nanoTime();
            int first = 1;
            int second = 1;
            int sum = 1;
            int tmp;
            int i = 3;
            while (i <= n) {
                sum = first + second;
                tmp = second;
                second = sum;
                first = tmp;
                i++;
            }
            long time1 = System.nanoTime();
            long time = time1 - time0;
            print("The " + n + "th element is " + sum + ".");
            print("Calculated in " + time + " nanoseconds with \"while\".");

            time0 = System.nanoTime();
            int[] array = new int[n];
            for (int j = 0; j < n; j++) {
                array[j] = 0;
            }
            calcFib(n, array);
            sum = array[n - 1];
            time1 = System.nanoTime();
            time = time1 - time0;
            print("The " + n + "th element is " + sum + ".");
            print("Calculated in " + time + " nanoseconds with recursion.");
        }
    }

    public static void print(String str) {
        System.out.println(str);
    }

    public static int calcFib(int n, int[] array) {
        if (n == 1 || n == 2) {
            return 1;
        }
        if (array[n - 1] != 0) {
            return array[n - 1];
        } else {
            return array[n - 1] = calcFib(n - 1, array) + calcFib(n - 2, array);
        }

    }
}
