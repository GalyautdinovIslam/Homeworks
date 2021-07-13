package firstCourse.secondSemester.explorer.service;

import firstCourse.secondSemester.explorer.abstracts.AbstractCommand;
import firstCourse.secondSemester.explorer.commands.*;
import firstCourse.secondSemester.explorer.entities.InputString;
import firstCourse.secondSemester.explorer.entities.UserPosition;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class App {
    private static Scanner sc;
    private static UserPosition position;
    private static Map<String, AbstractCommand> data;
    private static boolean toContinue;

    public static void main(String[] args) {
        init();
        start();
    }

    private static void init() {
        sc = new Scanner(System.in);
        position = new UserPosition(System.getProperty("user.dir"));
        toContinue = true;
        data = new HashMap<>();
        data.put("dir", new DirCommand());
        data.put("cd", new CdCommand());
        data.put("touch", new TouchCommand());
        data.put("mkdir", new MkdirCommand());
        data.put("tree", new TreeCommand());
        data.put("rm", new RmCommand());
        data.put("help", new HelpCommand());
        data.put("exit", new ExitCommand());
    }

    private static void start() {
        System.out.println("Welcome to the console file manager. " +
                "Use the <help> command to see the list of available commands and additional information.");
        while (toContinue) {
            System.out.print(position.getPath() + "$");
            InputString input = new InputString(sc.nextLine());
            if (data.containsKey(input.getCommand())) data.get(input.getCommand()).start(position, input);
            else InputProcessor.unknownCommand(input);
        }
    }

    public static void setNewPosition(Path newPosition) {
        position = new UserPosition(newPosition);
    }

    public static void exit() {
        toContinue = false;
    }
}
