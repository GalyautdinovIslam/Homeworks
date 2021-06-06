package FirstSemester;

public class SumCalc7WithWhile {
    public static void main(String[] args) {

        if (args.length == 1) {
            int n = Integer.parseInt(args[0]);

            if (n > 0) {
                double s = 0;

                while (n > 0) {
                    s = s - (Math.pow(-1, n)) / (Math.pow(2 * n - 1, 2));
                    n = n - 1;
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
