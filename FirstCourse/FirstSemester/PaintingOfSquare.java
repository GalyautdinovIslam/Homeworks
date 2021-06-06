package FirstSemester;

public class PaintingOfSquare {
    public static void main(String[] args) {
        if (args.length == 1) {
            int n = Integer.parseInt(args[0]);
            if (n > 0) {
                String s = "";
                for (int i = 0; i < n; i++) {
                    s += "* ";
                }
                for (int j = 0; j < n; j++) {
                    System.out.println(s);
                }
            } else {
                System.out.println("Please enter side of a square value.");
            }
        } else {
            System.out.println("Please enter side of a square value.");
        }
    }
}
