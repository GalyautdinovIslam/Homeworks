package firstCourse.secondSemester.FPstreams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SortedStrByCharStream {

    private static final String path = "src/main/resources/firstCourse/secondSemester/FPstreams/test.txt";


    public static void main(String[] args) {
        try {
            Files.lines(Paths.get(path)).sorted((o1, o2) -> o1.length() - o2.length())
                    .forEach(System.out::println);
        } catch (IOException ex) {
            ex.getStackTrace();
        }
    }
}
