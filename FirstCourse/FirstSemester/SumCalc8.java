package FirstSemester;

public class SumCalc8 {
    public static void main(String[] args) {
        if (args.length == 2) {
            int n = Integer.parseInt(args[0]);
            if (n > 0) {
                float x = Float.parseFloat(args[1]);
                double r = 0;
                for (int i = n; i > 0; i--) {
                    r = Math.cos(r + Math.toRadians(x));
                }
                System.out.println(r);
            } else {
                System.out.println("Please enter correct number of attachments and x value");
            }
        } else {
            System.out.println("Please enter correct number of attachments and x value");
        }
    }
}