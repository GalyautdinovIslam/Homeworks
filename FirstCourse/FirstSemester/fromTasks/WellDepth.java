package FirstSemester.fromTasks;

public class WellDepth {
    public static void main(String[] args) {

        if (args.length == 1) {
            //enter time like parameter
            float t = Float.parseFloat(args[0]);

            if (t > 0) {
                //gravity's const
                float g = 9.81f;

                //Sound's speed
                float v = 340f;

                //Discriminant
                float d = 4 * v * (v + 2 * g * t);

                //time of fall
                double tDown = (Math.sqrt(d) - 2 * v) / (2 * g);

                //Well's Depth
                double h = Math.round((g * tDown * tDown / 2) * 100.0) / 100.0;

                System.out.println("Well's depth is " + h + " m");

            } else {
                System.out.println("The entered stopwatch time is incorrect.");
                System.out.println("Please enter the stopwatch time (sec).");
            }

        } else {
            System.out.println("Please enter the stopwatch time (sec).");
        }
    }
}
