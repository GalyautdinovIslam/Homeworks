package firstCourse.firstSemester.fromTasks;

public class SumCalc5 {
    public static void main(String[] args) {
        if (args.length == 2) {
            int n = Integer.parseInt(args[0]);

            if (n > 0) {
                float x = Float.parseFloat(args[1]);
                double k = 0;
                boolean f = false;
                for (int i = n; i > 0; i--) {
                    k = x / ((i + 1) + k);
                    if (k == (-1) * i) {
                        f = true;
                        break;
                    }
                }
                if (f) {
                    System.out.println("Please enter correct variable value.");
                } else {
                    k = 1 + k;
                    System.out.println("The sum is " + k + ".");
                }
            } else {
                System.out.println("The quantity of enclosures must be greater than 0.");
            }
        } else {
            System.out.println("Please enter the quantity of enclosures and variable value.");
        }
    }
}
