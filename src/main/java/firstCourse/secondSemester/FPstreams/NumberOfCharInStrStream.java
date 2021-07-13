package firstCourse.secondSemester.FPstreams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class NumberOfCharInStrStream {

    private static final String path = "src/main/resources/firstCourse/secondSemester/FPstreams/test.txt";


    public static void main(String[] args) {
        try {
            Files.lines(Paths.get(path)).forEach((g) -> System.out.print(g.length() + ", "));
        } catch (IOException ex) {
            ex.getStackTrace();
        }
    }
}
