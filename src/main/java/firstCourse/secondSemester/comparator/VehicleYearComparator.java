package firstCourse.secondSemester.comparator;

import java.util.Comparator;

public class VehicleYearComparator implements Comparator<Vehicle> {

    @Override
    public int compare(Vehicle vehicle1, Vehicle vehicle2) {
        return vehicle1.year - vehicle2.year;
    }
}

