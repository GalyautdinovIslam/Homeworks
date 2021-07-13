package firstCourse.secondSemester.comparator;

import java.util.Objects;

public class Vehicle {
    protected String model;
    protected byte doorsNumber;
    protected byte wheelsNumber;
    protected int year;
    protected static final byte DOORS_NUMBER_STANDARD = 4;
    protected static final byte WHEELS_NUMBER_STANDARD = 4;

    public Vehicle(String model, int year) {
        this(model, DOORS_NUMBER_STANDARD, WHEELS_NUMBER_STANDARD, year);
    }

    public Vehicle(String model, byte doorsNumber, byte wheelsNumber, int year) {
        this.model = model;
        this.doorsNumber = doorsNumber;
        this.wheelsNumber = wheelsNumber;
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle)) return false;
        Vehicle vehicle = (Vehicle) o;
        return this.doorsNumber == vehicle.doorsNumber &&
                this.wheelsNumber == vehicle.wheelsNumber &&
                this.year == vehicle.year &&
                Objects.equals(this.model, vehicle.model);
    }

    @Override
    public int hashCode() {
        return 31 * Objects.hash(model, doorsNumber, wheelsNumber, year);
    }

    @Override
    public String toString() {
        return "Vehicle: " + this.model + ", " + this.year + " year.";
    }

    public String getModel() {
        return this.model;
    }

    public int getYear() {
        return this.year;
    }
}
