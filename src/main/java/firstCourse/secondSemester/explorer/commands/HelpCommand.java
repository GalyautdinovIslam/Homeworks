package firstCourse.secondSemester.explorer.commands;


import firstCourse.secondSemester.explorer.abstracts.AbstractCommand;
import firstCourse.secondSemester.explorer.entities.InputString;
import firstCourse.secondSemester.explorer.entities.UserPosition;
import firstCourse.secondSemester.explorer.service.ParametersProcessor;

import java.util.HashMap;
import java.util.Map;

public class HelpCommand extends AbstractCommand {
    private final static String forParameters = "---> ";

    public HelpCommand() {
        super(createAvailableParameters());
    }

    @Override
    public void start(UserPosition position, InputString input) {
        ParametersProcessor.parametersSetting(input, getAvailableParameters());
        System.out.println("A file manager assistant is here.");
        System.out.println("Below are the available commands with descriptions and available parameters.");
        System.out.println();
        System.out.println("<dir> — display a list of files and directories.");
        System.out.println(forParameters + "<l> - display background information about files and directories.");
        System.out.println();
        System.out.println("<cd> — change the current position of the user.");
        System.out.println();
        System.out.println("<touch> — create a new empty file.");
        System.out.println();
        System.out.println("<mkdir> — create a new directories.");
        System.out.println(forParameters + "<p> - create all non-existent parent directories too.");
        System.out.println();
        System.out.println("<tree> — display a list of files, directories recursively for all directories.");
        System.out.println(forParameters + "<d> - display depth at end of line.");
        System.out.println();
        System.out.println("<rm> — remove a file or directory.");
        System.out.println();
        System.out.println("<help> — display the tips about commands.");
        System.out.println();
        System.out.println("<exit> — terminate this program.");
        System.out.println();
        System.out.println("The input should look like this:");
        System.out.println("<command> *<parameters> <path>");
        ParametersProcessor.parametersReset(getAvailableParameters());
    }

    private static Map<Character, Boolean> createAvailableParameters() {
        return new HashMap<>();
    }
}
