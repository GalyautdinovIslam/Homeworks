package firstCourse.secondSemester.weather;

import com.google.gson.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MainPageController {
    private int countCity;
    private int inPage;
    private boolean isNext;
    private ArrayList<Request> requests;

    @FXML
    private TextField TextFieldInput;

    @FXML
    private Button ButtonSearch;

    @FXML
    private Button ButtonNext;

    @FXML
    private Button ButtonPrevious;

    @FXML
    private Text TExtWLeft;

    @FXML
    private Text TextDateLeft;

    @FXML
    private Text TextMLeft;

    @FXML
    private Text TextTLeft;

    @FXML
    private Text TextWCenter;

    @FXML
    private Text TextDateCenter;

    @FXML
    private Text TextMCenter;

    @FXML
    private Text TextTCenter;

    @FXML
    private Text TextWRight;

    @FXML
    private Text TextDateRight;

    @FXML
    private Text TextMRight;

    @FXML
    private Text TextTRight;

    @FXML
    private Text TextCity;

    @FXML
    private Button ButtonRight;

    @FXML
    private Button ButtonLeft;

    @FXML
    public void onSearch() {
        String input = inputProcessing();
        if (!input.equals("")) {
            ArrayList<Integer> list = searchCity(input);
            ArrayList<Request> requests = new ArrayList<>();
            for (int i : list) {
                requests.add(searchWeather(i));
            }
            if (list.size() != 0) {
                refresh(list.size());
                setPosition(requests, inPage, 0);
            }
            this.requests = requests;
        }
    }

    @FXML
    public void onNext() {
        if (requests.size() > inPage) {
            isNext = false;
            setPosition(requests, ++inPage, 0);
        }
    }

    @FXML
    public void onPrevious() {
        if (inPage > 1) {
            isNext = false;
            setPosition(requests, --inPage, 0);
        }
    }

    @FXML
    public void onRight() {
        if (!isNext) {
            isNext = true;
            setPosition(requests, inPage, 3);
        }
    }

    @FXML
    public void onLeft() {
        if (isNext) {
            isNext = false;
            setPosition(requests, inPage, 0);
        }
    }

    private String inputProcessing() {
        String input = TextFieldInput.getCharacters().toString().trim();
        TextFieldInput.setText(input);
        input = input.replaceAll(" ", "%20");
        return input;
    }

    private ArrayList<Integer> searchCity(String input) {
        Gson gson = new Gson();
        try {
            URLConnection conn = new URL("https://www.metaweather.com/api/location/search/?query=" + input).openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

            JsonArray arrCity = gson.fromJson(reader, JsonArray.class);
            ArrayList<Integer> list = new ArrayList<>();
            for (JsonElement el : arrCity) {
                JsonObject obj = el.getAsJsonObject();
                list.add(obj.get("woeid").getAsInt());
            }
            reader.close();
            return list;
        } catch (MalformedURLException muex) {
            toExceptions(new Exception("Problem with URL. Try again."));
        } catch (IOException ioex) {
            toExceptions(new Exception("Problem with connecting to server. Check your internet connection or connect with server admin."));
        }
        return null;
    }

    private Request searchWeather(int i) {
        Gson gson = new Gson();
        try {
            URLConnection conn = new URL("https://www.metaweather.com/api/location/" + i).openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

            JsonObject data = gson.fromJson(reader, JsonObject.class);
            JsonPrimitive titleData = data.getAsJsonPrimitive("title");
            JsonArray weatherData = data.getAsJsonArray("consolidated_weather");
            String title = titleData.getAsString();
            ArrayList<Day> days = new ArrayList<>();
            for (JsonElement el : weatherData) {
                JsonObject obj = el.getAsJsonObject();
                days.add(new Day(obj.get("applicable_date").getAsString(), obj.get("weather_state_name").getAsString(), obj.get("the_temp").getAsInt(), obj.get("min_temp").getAsInt(), obj.get("max_temp").getAsInt()));
            }
            reader.close();
            return new Request(title, days);
        } catch (MalformedURLException muex) {
            toExceptions(new Exception("Problem with URL. Try again."));
        } catch (IOException ioex) {
            toExceptions(new Exception("Problem with connecting in processing. Check your internet connection."));
        }
        return null;
    }

    private void setPosition(ArrayList<Request> requests, int i, int j) {
        TextCity.setText(requests.get(i - 1).getTitle());

        TextDateLeft.setText(requests.get(i - 1).getDays().get(j).getDate());
        TextTLeft.setText("Temp: " + requests.get(i - 1).getDays().get(j).getTemp() + "°C");
        TExtWLeft.setText("Weather: " + requests.get(i - 1).getDays().get(j).getWeather());
        TextMLeft.setText("Max/Min: " + requests.get(i - 1).getDays().get(j).getMax() + "/" + requests.get(i - 1).getDays().get(j).getMin() + "°C");

        TextDateCenter.setText(requests.get(i - 1).getDays().get(1 + j).getDate());
        TextTCenter.setText("Temp: " + requests.get(i - 1).getDays().get(1 + j).getTemp() + "°C");
        TextWCenter.setText("Weather: " + requests.get(i - 1).getDays().get(1 + j).getWeather());
        TextMCenter.setText("Max/Min: " + requests.get(i - 1).getDays().get(1 + j).getMax() + "/" + requests.get(i - 1).getDays().get(1 + j).getMin() + "°C");

        TextDateRight.setText(requests.get(i - 1).getDays().get(2 + j).getDate());
        TextTRight.setText("Temp: " + requests.get(i - 1).getDays().get(2 + j).getTemp() + "°C");
        TextWRight.setText("Weather: " + requests.get(i - 1).getDays().get(2 + j).getWeather());
        TextMRight.setText("Max/Min: " + requests.get(i - 1).getDays().get(2 + j).getMax() + "/" + requests.get(i - 1).getDays().get(2 + j).getMin() + "°C");
    }

    private void toExceptions(Exception ex) {
        TextCity.setText(ex.getMessage());

        TextDateLeft.setText("Date");
        TextTLeft.setText("Temp: ");
        TExtWLeft.setText("Weather: ");
        TextMLeft.setText("Max/Min: ");

        TextDateCenter.setText("Date");
        TextTCenter.setText("Temp: ");
        TextWCenter.setText("Weather: ");
        TextMCenter.setText("Max/Min: ");

        TextDateRight.setText("Date");
        TextTRight.setText("Temp: ");
        TextWRight.setText("Weather: ");
        TextMRight.setText("Max/Min: ");
    }

    private void refresh(int i) {
        countCity = i;
        inPage = 1;
        isNext = false;
    }
}
