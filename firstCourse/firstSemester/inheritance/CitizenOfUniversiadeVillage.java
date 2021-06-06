package firstSemester.inheritance;

public class CitizenOfUniversiadeVillage extends CitizenOfKazan {
    public String Name;
    public byte houseNumber;
    public int roomNumber;
    public boolean payment;
    public float points;

    public static boolean payForAccommodation(boolean payment) {
        return true;
    }

    public static void goToEvent(float points, float x) {
        points += x;
    }

    public static void cookMeal() {
        System.out.println("Meal was cooked");
    }
}