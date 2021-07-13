package firstCourse.firstSemester.other;

import java.util.Scanner;

public class IntegralCalc {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        print("Enter lower limit:");
        double x1 = sc.nextDouble();
        print("Enter upper limit:");
        double x2 = sc.nextDouble();
        if (x2 > x1) {
            print("Enter a number of steps:");
            print("(Number must be even for Simpson's method)");
            int steps = sc.nextInt();
            calcIntegralTrap(x1, x2, steps);
            print("");
            calcIntegralSimpson(x1, x2, steps);
            print("");
            calcIntegralRectangle(x1, x2, steps);
        } else {
            print("Upper limit must be more than lower limit.");
        }
    }

    public static void print(String str) {
        System.out.println(str);
    }

    public static double f(double x) {
        return Math.exp(-x * x);
    }

    public static void calcIntegralTrap(double x1, double x2, int steps) {
        double square = 0.0;
        for (double i = x1; i < x2; i += (x2 - x1) / steps) {
            square += (f(i) + f(i + ((x2 - x1) / steps))) * (x2 - x1) / (2.0 * steps);
        }
        print("IntegralCalc approximately equals " + square + " (with trapezoids).");
    }

    public static void calcIntegralSimpson(double x1, double x2, int steps) {
        double square = 0;
        if (steps % 2 == 0) {
            double evensum = 0;
            double oddsum = 0;
            for (double i = x1 + (x2 - x1) / steps; i <= x2 - (x2 - x1) / steps; i += 2 * (x2 - x1) / steps) {
                oddsum += f(i);
            }
            for (double i = x1 + 2 * (x2 - x1) / steps; i < x2; i += 2 * (x2 - x1) / steps) {
                evensum += f(i);
            }
            square = (x2 - x1) / (3 * steps) * (f(x1) + f(x2) + 2 * evensum + 4 * oddsum);
            print("IntegralCalc approximately equals " + square + " (with Simpson's method).");
        }
        square = (x2 - x1) / 6 * (f(x1) + f(x2) + 4 * f((x1 + x2) / 2));
        print("IntegralCalc approximately equals " + square + " (with Simpson's method)(lesson).");
    }

    public static void calcIntegralRectangle(double x1, double x2, int steps) {
        double square = 0.0;
        for (double i = x1; i < x2; i += (x2 - x1) / steps) {
            square += f(i) * (x2 - x1) / steps;
        }
        print("IntegralCalc approximately equals " + square + " (with rectangles).");
    }
}
