package FirstSemester.fromTasks;

public class CircleParameters {
    public static void main(String[] args) {
        if (args.length == 1) {

            float r = Float.parseFloat(args[0]);
            if (r > 0) {

                double c = Math.round(2 * Math.PI * r * 100.0) / 100.0;

                double s = Math.round(Math.PI * r * r * 100.0) / 100.0;

                System.out.println("Circumference is " + c + ". Area of a circle is " + s + ".");

            } else {
                System.out.println("The entered value for the radius is incorrect.");
                System.out.println("Please enter a value for the radius of the circle.");
            }

        } else {
            System.out.println("Please enter a value for the radius of the circle.");
        }
    }
}
