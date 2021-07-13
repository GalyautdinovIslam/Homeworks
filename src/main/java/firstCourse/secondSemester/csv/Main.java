package firstCourse.secondSemester.csv;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int forI = Integer.parseInt(sc.nextLine()); // строк
        int forJ = Integer.parseInt(sc.nextLine()); // столбцов

        String[][] arr = new String[forI][forJ];
        for (int i = 0; i < forI; i++) {
            for (int j = 0; j < forJ; j++) {
                arr[i][j] = sc.nextLine();
            }
        }

        create(arr);
        String[][] arr2 = read("src/main/resources/firstCourse/secondSemester/csv/data.csv");

        for (String[] strings : arr2) {
            for (int j = 0; j < arr2[0].length; j++) {
                System.out.print(strings[j] + " ");
            }
            System.out.println();
        }
    }

    public static void create(String[][] arr) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("src/main/resources/firstCourse/secondSemester/csv/data.csv"))) {
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[0].length; j++) {
                    dos.writeBytes(arr[i][j]);
                    if (j < arr[0].length - 1) dos.writeBytes(",");
                }
                if (i < arr.length - 1) dos.writeBytes("\n");
            }
        } catch (IOException ex) {
            ex.getStackTrace();
        }
    }

    public static String[][] read(String path) {
        try {
            List<String> strings = Files.readAllLines(Paths.get(path));
            int forI = strings.size();
            int forJ = 0;
            int index = -2;
            while (index != -1) {
                forJ++;
                index = strings.get(0).indexOf(',', index + 1);
            }

            String[][] arr = new String[forI][forJ];
            for (int i = 0; i < forI; i++) {
                String[] tmp = strings.get(i).split(",");
                System.arraycopy(tmp, 0, arr[i], 0, forJ);
            }
            return arr;
        } catch (IOException ex) {
            ex.getStackTrace();
        }
        return new String[1][1];
    }
}
