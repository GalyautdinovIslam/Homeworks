package firstCourse.secondSemester.weather;

import java.util.ArrayList;

public class Request {
    private final String title;
    private final ArrayList<Day> days;

    public Request(String title, ArrayList<Day> days) {
        this.title = title;
        this.days = days;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<Day> getDays() {
        return days;
    }
}
