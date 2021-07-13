package firstCourse.firstSemester.other;

import java.util.Scanner;

public class FunctionResultCheck {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        print("Enter x:");
        double x = sc.nextDouble();
        print("Enter y:");
        double y = sc.nextDouble();
        if (Math.abs(y - f(x)) <= 1e-6) {
            print("Right.");
        } else {
            print("Wrong.");
        }
    }

    public static void print(String str) {
        System.out.println(str);
    }

    public static double f(double x) {
        double y = (double) ((x - 3.0) * (x - 2.0) * (x + 1.0) * (x + 3.0));
        print("Result was rounded(1e-6).");
        return y;
    }
}
