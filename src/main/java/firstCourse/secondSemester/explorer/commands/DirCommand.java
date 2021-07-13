package firstCourse.secondSemester.explorer.commands;


import firstCourse.secondSemester.explorer.abstracts.AbstractCommand;
import firstCourse.secondSemester.explorer.entities.InputString;
import firstCourse.secondSemester.explorer.entities.UserPosition;
import firstCourse.secondSemester.explorer.service.InputProcessor;
import firstCourse.secondSemester.explorer.service.ParametersProcessor;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DirCommand extends AbstractCommand {
    private static final int BIT_IN_BYTE = 8;

    public DirCommand() {
        super(createAvailableParameters());
    }

    @Override
    public void start(UserPosition position, InputString input) {
        ParametersProcessor.parametersSetting(input, getAvailableParameters());
        File file = new File(position.getPath().resolve(input.getPath()).normalize().toAbsolutePath().toString());
        int maxLength = 0;
        if (file.exists() && file.isDirectory()) {
            for (File el : file.listFiles()) {
                if (maxLength < el.getName().length()) maxLength = el.getName().length();
            }
            for (File el : file.listFiles()) {
                if (el.isDirectory()) System.out.print("[D] ");
                else System.out.print("[F] ");
                System.out.printf("%" + -maxLength + "s", el.getName());
                if (getAvailableParameters().get('l')) {
                    System.out.print(" | " + new Date(el.lastModified()).toString());
                    if (el.isFile()) System.out.print(" | " + el.length() / BIT_IN_BYTE + " байт");
                }
                System.out.println();
            }
        } else InputProcessor.unknownPath();
        ParametersProcessor.parametersReset(getAvailableParameters());
    }


    private static Map<Character, Boolean> createAvailableParameters() {
        Map<Character, Boolean> result = new HashMap<>();
        result.put('l', false);
        return result;
    }
}
