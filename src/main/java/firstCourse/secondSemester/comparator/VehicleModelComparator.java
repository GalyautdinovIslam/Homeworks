package firstCourse.secondSemester.comparator;

import java.util.Comparator;

public class VehicleModelComparator implements Comparator<Vehicle> {

    @Override
    public int compare(Vehicle vehicle1, Vehicle vehicle2) {
        return vehicle1.model.compareTo(vehicle2.model);
    }
}
