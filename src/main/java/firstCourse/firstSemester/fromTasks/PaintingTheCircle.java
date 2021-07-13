package firstCourse.firstSemester.fromTasks;

public class PaintingTheCircle {
    public static void main(String[] args) {
        if (args.length == 1) {
            int d = Integer.parseInt(args[0]);
            for (int y = 0; y <= d; y++) {
                for (int x = 0; x <= d; x++) {
                    if ((x - 0.5 - d / 2d) * (x - 0.5 - d / 2d) + (y - 0.5 - d / 2d) * (y - 0.5 - d / 2d) <= d * d / 4d) {
                        System.out.print("* ");
                    } else {
                        System.out.print("  ");
                    }
                }
                System.out.println();
            }
        } else {
            System.out.println("Please enter value of diameter");
        }
    }
}
