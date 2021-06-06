package FirstSemester;

public class VallisMultiplication {
    public static void main(String[] args) {
        if (args.length == 1) {
            int n = Integer.parseInt(args[0]);
            if (n > 0) {
                float s = 1f;

                for (int i = 1; i <= n; i++) {
                    s = s * 4 * i * i / (4 * i * i - 1);
                }

                System.out.println("Pi approximately equals " + Math.round(2 * s * 100.0) / 100.0 + ".");

            } else {
                System.out.println("The quantity of multipliers must be greater than 0.");
            }

        } else {
            System.out.println("Please enter the quantity of multipliers.");
        }
    }
}
