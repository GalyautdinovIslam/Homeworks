package firstCourse.secondSemester.FPstreams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class EditedStream {
    private static boolean flag = false;

    private static final String path = "src/main/resources/firstCourse/secondSemester/FPstreams/test.txt";

    // Сначала убирает с чётным количеством символов, а потом четные строки
    public static long first() throws IOException {
        return Files.lines(Paths.get(path)).filter((g) -> g.length() % 2 == 1)
                .filter((g) -> {
                    if (!flag) return flag = true;
                    else return flag = false;
                })
                .count();
    }

    //Сначала убирает чётные строки, а потом с чётным кол-вом символов
    public static long second() throws IOException {
        return Files.lines(Paths.get(path)).filter((g) -> {
            if (!flag) return flag = true;
            else return flag = false;
        })
                .filter((g) -> g.length() % 2 == 1)
                .count();
    }

    public static void main(String[] args) {
        try {
            System.out.println(first() + ", " + second());
        } catch (IOException ex) {
            ex.getStackTrace();
        }
    }

}
