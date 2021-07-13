package firstCourse.secondSemester.treeCommand;

import java.io.File;
import java.nio.file.Paths;
import java.util.Scanner;

public class TreeCommand {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_GREEN = "\u001B[32m"; //Files
    private static final String ANSI_BLUE = "\u001B[34m"; //Folders

    private static final int FIRST_DEPTH_SIZE = 0;
    private static final int FOR_DEPTH_SHOWING = 1;
    private static final int NEXT_DEPTH = 1;
    private static final String STANDARD_CURSOR = "->";
    private static final String FOR_PRINTF = "%" + STANDARD_CURSOR.length() + "s";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean whileFlag = true;
        boolean depthShow = true;
        while (whileFlag) {
            System.out.println("Show depth at end of line? [y/n]");
            switch (sc.nextLine()) {
                case ("y"):
                    whileFlag = false;
                    break;
                case ("n"):
                    depthShow = whileFlag = false;
                    break;
            }
        }
        System.out.println("Enter a path:");
        System.out.println("[Attention: if you enter a relative path, the path is completed with your location.]");
        if (depthShow) outputDepth(Paths.get(sc.nextLine()).toFile());
        else output(Paths.get(sc.nextLine()).toFile());
    }

    public static void output(File inputFile) {
        if (inputFile.exists()) {
            if (inputFile.isDirectory()) System.out.println(ANSI_BLUE + inputFile.getName() + ANSI_RESET);
            else System.out.print(ANSI_GREEN + inputFile.getName() + ANSI_RESET);
            output(inputFile, FIRST_DEPTH_SIZE);
        } else {
            System.out.println("[Attention: invalid path.]");
        }
    }

    public static void outputDepth(File inputFile) {
        if (inputFile.exists()) {
            if (inputFile.isDirectory()) System.out.println(ANSI_BLUE + inputFile.getName() + ANSI_RESET);
            else System.out.print(ANSI_GREEN + inputFile.getName() + ANSI_RESET);
            outputDepth(inputFile, FIRST_DEPTH_SIZE);
        } else {
            System.out.println("[Attention: invalid path.]");
        }
    }

    private static void output(File inputFile, int depthCounter) {
        File[] array = inputFile.listFiles();
        if (array != null) {
            for (File file : array) {
                if (file.isDirectory()) {
                    for (int i = 0; i < depthCounter; i++) {
                        System.out.printf(FOR_PRINTF, "");
                    }
                    System.out.print(STANDARD_CURSOR + ANSI_BLUE + file.getName() + ANSI_RESET);
                    System.out.println();
                    output(file, depthCounter + NEXT_DEPTH);
                } else {
                    for (int i = 0; i < depthCounter; i++) {
                        System.out.printf(FOR_PRINTF, "");
                    }
                    System.out.print(STANDARD_CURSOR + ANSI_GREEN + file.getName() + ANSI_RESET);
                    System.out.println();
                }
            }
        }
    }

    private static void outputDepth(File inputFile, int depthCounter) {
        if (inputFile.isDirectory()) {
            File[] array = inputFile.listFiles();
            if (array != null) {
                for (File file : array) {
                    if (file.isDirectory()) {
                        for (int i = 0; i < depthCounter; i++) {
                            System.out.printf(FOR_PRINTF, "");
                        }
                        System.out.print(STANDARD_CURSOR + ANSI_BLUE + file.getName() + ANSI_RESET +
                                "(" + (depthCounter + FOR_DEPTH_SHOWING) + ")");
                        System.out.println();
                        outputDepth(file, depthCounter + NEXT_DEPTH);
                    } else {
                        for (int i = 0; i < depthCounter; i++) {
                            System.out.printf(FOR_PRINTF, "");
                        }
                        System.out.print(STANDARD_CURSOR + ANSI_GREEN + file.getName() + ANSI_RESET +
                                "(" + (depthCounter + FOR_DEPTH_SHOWING) + ")");
                        System.out.println();
                    }
                }
            }
        }
    }
}
