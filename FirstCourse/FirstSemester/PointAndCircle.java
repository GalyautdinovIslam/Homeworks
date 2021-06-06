package FirstSemester;

public class PointAndCircle {
    public static void main(String[] args) {
        if (args.length == 5) {
            float x0 = Float.parseFloat(args[0]);
            float y0 = Float.parseFloat(args[1]);
            float r = Float.parseFloat(args[2]);
            float x = Float.parseFloat(args[3]);
            float y = Float.parseFloat(args[4]);
            if (r > 0) {
                if ((x - x0) * (x - x0) + (y - y0) * (y - y0) == r * r) {
                    System.out.println("Point is on the circle.");
                } else if ((x - x0) * (x - x0) + (y - y0) * (y - y0) < r * r) {
                    System.out.println("Point is in the circle.");
                } else {
                    System.out.println("Point is outside the circle.");
                }
            } else {
                System.out.println("Radius must be greater than 0.");
            }
        } else {
            System.out.println("Please enter x, y circle center's coordinates; circle's radius and x, y point's coordinates.");
        }
    }
}
