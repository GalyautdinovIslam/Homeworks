package firstCourse.secondSemester.explorer.commands;

import firstCourse.secondSemester.explorer.abstracts.AbstractCommand;
import firstCourse.secondSemester.explorer.entities.InputString;
import firstCourse.secondSemester.explorer.entities.UserPosition;
import firstCourse.secondSemester.explorer.service.InputProcessor;
import firstCourse.secondSemester.explorer.service.ParametersProcessor;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class TreeCommand extends AbstractCommand {
    private static final int FIRST_DEPTH_SIZE = 0;
    private static final int FOR_DEPTH_SHOWING = 1;
    private static final int NEXT_DEPTH = 1;
    private static final String STANDARD_CURSOR = "->";
    private static final String FOR_PRINTF = "%" + STANDARD_CURSOR.length() + "s";

    public TreeCommand() {
        super(createAvailableParameters());
    }

    @Override
    public void start(UserPosition position, InputString input) {
        ParametersProcessor.parametersSetting(input, getAvailableParameters());
        if (getAvailableParameters().get('d'))
            outputDepth(new File(position.getPath().resolve(input.getPath()).normalize().toAbsolutePath().toString()));
        else output(new File(position.getPath().resolve(input.getPath()).normalize().toAbsolutePath().toString()));
        ParametersProcessor.parametersReset(getAvailableParameters());
    }

    private static void output(File inputFile) {
        if (inputFile.exists()) {
            if (inputFile.isDirectory()) System.out.println("[D]" + inputFile.getName());
            else System.out.print("[F]" + inputFile.getName());
            output(inputFile, FIRST_DEPTH_SIZE);
        } else {
            InputProcessor.unknownPath();
        }
    }

    private static void outputDepth(File inputFile) {
        if (inputFile.exists()) {
            if (inputFile.isDirectory()) System.out.println("[D]" + inputFile.getName());
            else System.out.print("[F]" + inputFile.getName());
            outputDepth(inputFile, FIRST_DEPTH_SIZE);
        } else {
            InputProcessor.unknownPath();
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
                    System.out.print(STANDARD_CURSOR + "[D]" + file.getName());
                    System.out.println();
                    output(file, depthCounter + NEXT_DEPTH);
                } else {
                    for (int i = 0; i < depthCounter; i++) {
                        System.out.printf(FOR_PRINTF, "");
                    }
                    System.out.print(STANDARD_CURSOR + "[F]" + file.getName());
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
                        System.out.print(STANDARD_CURSOR + "[D]" + file.getName() +
                                "(" + (depthCounter + FOR_DEPTH_SHOWING) + ")");
                        System.out.println();
                        outputDepth(file, depthCounter + NEXT_DEPTH);
                    } else {
                        for (int i = 0; i < depthCounter; i++) {
                            System.out.printf(FOR_PRINTF, "");
                        }
                        System.out.print(STANDARD_CURSOR + "[F]" + file.getName() +
                                "(" + (depthCounter + FOR_DEPTH_SHOWING) + ")");
                        System.out.println();
                    }
                }
            }
        }
    }

    private static Map<Character, Boolean> createAvailableParameters() {
        Map<Character, Boolean> result = new HashMap<>();
        result.put('d', false);
        return result;
    }
}
