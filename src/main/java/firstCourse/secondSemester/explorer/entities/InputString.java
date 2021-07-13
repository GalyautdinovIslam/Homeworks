package firstCourse.secondSemester.explorer.entities;


import firstCourse.secondSemester.explorer.service.InputProcessor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputString {
    private final String command;
    private final String path;
    private final char[] parameters;

    public InputString(String str) {
        str = InputProcessor.removeSpace(str);
        String commandString = str.replaceAll("\\s.*$", "");
        str = InputProcessor.removeSpace(str.replaceFirst(commandString, ""));
        command = commandString.toLowerCase();
        String parameters = "";
        Pattern pattern = Pattern.compile("^\\*[^\\s]*");
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            parameters = str.substring(++start, end);
        }
        path = InputProcessor.removeSpace(str.replaceFirst("\\*" + parameters, ""));
        this.parameters = parameters.toLowerCase().toCharArray();
    }

    public String getCommand() {
        return command;
    }

    public String getPath() {
        return path;
    }

    public char[] getParameters() {
        return parameters;
    }
}
