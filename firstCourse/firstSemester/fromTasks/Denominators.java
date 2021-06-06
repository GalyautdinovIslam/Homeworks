package firstSemester.fromTasks;

import java.util.Scanner;

public class Denominators {
    public static void main(String[] args) {
        System.out.println("Please enter a number:");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 1; i < Math.sqrt(n); i++) {
            if (n % i == 0) {
                System.out.println(i + ", " + (n / i));
            }
        }
        if (n % Math.sqrt(n) == 0) {
            System.out.println((int) (Math.sqrt(n)));
        }
    }
}
