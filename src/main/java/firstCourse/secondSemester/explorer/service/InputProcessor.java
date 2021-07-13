package firstCourse.secondSemester.explorer.service;


import firstCourse.secondSemester.explorer.entities.InputString;

public class InputProcessor {

    public static String removeSpace(String str) {
        return str.replaceAll("^\\s*|\\s*$", "");
    }

    public static void unknownCommand(InputString input) {
        System.out.println("Attention: <" + input.getCommand() + "> command does not exist." +
                " Please correct the input or use the <help> command.");
    }

    public static void unknownPath() {
        System.out.println("Attention: The specified path does not exist or this path belongs to file.");
    }
}
