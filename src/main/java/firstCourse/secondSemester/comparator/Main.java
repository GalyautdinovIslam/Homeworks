package firstCourse.secondSemester.comparator;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Person person1 = new Person("Ivan", 21, true);
        Person person2 = new Person("Ivan", 25, true);
        Person person3 = new Person("Olga", 19, false);
        ArrayList<Person> personList = new ArrayList<>();
        personList.add(person2);
        personList.add(person1);
        personList.add(person3);

        Vehicle vehicle1 = new Vehicle("AUDI", 2006);
        Vehicle vehicle2 = new Vehicle("AUDI", 2004);
        Vehicle vehicle3 = new Vehicle("LADA", 2015);

        ArrayList<Vehicle> vehicleList = new ArrayList<>();
        vehicleList.add(vehicle1);
        vehicleList.add(vehicle3);
        vehicleList.add(vehicle2);


        CarOwner<Person, Vehicle> owner1 = new CarOwner<>(person1, vehicle1);

        System.out.println(owner1.getObj1().toString() + " " + owner1.getObj2().toString());

        System.out.println();

        System.out.println(person1.compareTo(person2));
        System.out.println(person1.compareTo(person3));

        System.out.println();

        VehicleModelComparator vmc = new VehicleModelComparator();
        VehicleYearComparator vyc = new VehicleYearComparator();
        System.out.println(vmc.compare(vehicle1, vehicle2));
        System.out.println(vmc.compare(vehicle1, vehicle3));
        System.out.println(vyc.compare(vehicle1, vehicle2));
        System.out.println(vyc.compare(vehicle1, vehicle3));

        System.out.println();

        for (Person x : personList) {
            System.out.println(x.toString());
        }
        for (int i = 0; i < personList.size(); i++) {
            for (int j = 0; j < personList.size() - i - 1; j++) {
                if (personList.get(j).compareTo(personList.get(j + 1)) > 0) {
                    Person tmp = personList.get(j + 1);
                    personList.set(j + 1, personList.get(j));
                    personList.set(j, tmp);
                }
            }
        }
        for (Person x : personList) {
            System.out.println(x.toString());
        }

        System.out.println();

        for (Vehicle x : vehicleList) {
            System.out.println(x.toString());
        }
        for (int i = 0; i < vehicleList.size(); i++) {
            for (int j = 0; j < vehicleList.size() - i - 1; j++) {
                if (vmc.compare(vehicleList.get(j), vehicleList.get(j + 1)) > 0) {
                    Vehicle tmp = vehicleList.get(j + 1);
                    vehicleList.set(j + 1, vehicleList.get(j));
                    vehicleList.set(j, tmp);
                }
            }
        }
        for (Vehicle x : vehicleList) {
            System.out.println(x.toString());
        }
        for (int i = 0; i < vehicleList.size(); i++) {
            for (int j = 0; j < vehicleList.size() - i - 1; j++) {
                if (vyc.compare(vehicleList.get(j), vehicleList.get(j + 1)) > 0) {
                    Vehicle tmp = vehicleList.get(j + 1);
                    vehicleList.set(j + 1, vehicleList.get(j));
                    vehicleList.set(j, tmp);
                }
            }
        }
        for (Vehicle x : vehicleList) {
            System.out.println(x.toString());
        }
    }
}
