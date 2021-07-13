package secondSemester.lamodaParsing;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {
    private final static int FOR_BRAND = 1;
    private final static int FOR_TYPE = 3;

    private final static String PATH_FOR_HTML = "firstCourse/secondSemester/lamodaParsing/Lamoda.html";

    private static void matcherCostFound(List<String> listData, int i, Pattern forValue, ArrayList<String> dataName) {
        String forAdd = listData.get(i + FOR_BRAND).replaceAll("^\\s+", "") + " | " +
                listData.get(i + FOR_TYPE).replaceAll("^\\s+", "") + " | ";
        Matcher matcherValue = forValue.matcher(listData.get(i));
        while (matcherValue.find()) {
            int start = matcherValue.start();
            int end = matcherValue.end();
            String newStr = listData.get(i).substring(start, end).replaceAll("[^\\s\\d]", "");
            forAdd = forAdd.concat(newStr + " | ");
        }
        dataName.add(forAdd);
    }

    private static void matcherHrefFound(List<String> listData, int i, Pattern href, ArrayList<String> dataHref) {
        Matcher matchHref = href.matcher(listData.get(i));
        while (matchHref.find()) {
            int start = matchHref.start();
            int end = matchHref.end();
            dataHref.add(listData.get(i).substring(start, end).replaceAll("\"", ""));
        }
    }

    private static void resultConcat(ArrayList<String> dataName, ArrayList<String> dataHref) {
        for (int i = 0; i < dataName.size(); i++) {
            dataName.set(i, dataName.get(i).concat(dataHref.get(i)));
        }
    }

    private static void resultPrint(ArrayList<String> dataName) {
        for (String str : dataName) {
            System.out.println(str);
        }
    }

    public static void main(String[] args) {
        try {
            List<String> listData = Files.readAllLines(Paths.get(PATH_FOR_HTML));

            //Здесь на ламоде всегда хранится полная ссылка
            Pattern forHref = Pattern.compile("a href=\"(.+?)\" data-sku");

            //Ищет все цены на товар, выдают их в последовательности: без скидки, а далее скидки по порядку
            Pattern forCost = Pattern.compile("<span class=\"price\">");

            Pattern forValue = Pattern.compile(">[0-9\\s]+?</span>");
            Pattern href = Pattern.compile("https:(.)+/\"");
            ArrayList<String> dataName = new ArrayList<>();
            ArrayList<String> dataHref = new ArrayList<>();
            for (int i = 0; i < listData.size(); i++) {
                Matcher matcherCost = forCost.matcher(listData.get(i));
                Matcher matcherHref = forHref.matcher(listData.get(i));
                if (matcherCost.find()) {
                    matcherCostFound(listData, i, forValue, dataName);
                }
                if (matcherHref.find()) {
                    matcherHrefFound(listData, i, href, dataHref);
                }
            }
            resultConcat(dataName, dataHref);
            resultPrint(dataName);
        } catch (IOException ex) {
            System.out.println("caught");
        }
    }
}
