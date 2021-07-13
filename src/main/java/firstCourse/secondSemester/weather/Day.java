package firstCourse.secondSemester.weather;

public class Day {
    private final String date;
    private final String weather;
    private final int temp;
    private final int min;
    private final int max;

    public Day(String date, String weather, int temp, int min, int max) {
        this.date = date;
        this.weather = weather;
        this.temp = temp;
        this.min = min;
        this.max = max;
    }

    public String getDate() {
        return date;
    }

    public String getWeather() {
        return weather;
    }

    public int getTemp() {
        return temp;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }
}
