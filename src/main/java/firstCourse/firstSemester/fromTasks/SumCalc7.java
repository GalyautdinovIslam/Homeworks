package firstCourse.firstSemester.fromTasks;

public class SumCalc7 {
    public static void main(String[] args) {
        if (args.length == 1) {
            int n = Integer.parseInt(args[0]);

            if (n > 0) {
                double s = 0;

                for (int i = 1; i < n + 1; i++) {
                    s -= (Math.pow(-1, i)) / (Math.pow(2 * i - 1, 2));
                }

                System.out.println(s);
            } else {
                System.out.println("Please enter the number of terms.");
            }

        } else {
            System.out.println("Please enter the number of terms");
        }
    }
}
