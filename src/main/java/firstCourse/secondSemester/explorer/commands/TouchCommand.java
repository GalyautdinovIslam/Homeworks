package firstCourse.secondSemester.explorer.commands;


import firstCourse.secondSemester.explorer.abstracts.AbstractCommand;
import firstCourse.secondSemester.explorer.entities.InputString;
import firstCourse.secondSemester.explorer.entities.UserPosition;
import firstCourse.secondSemester.explorer.service.ParametersProcessor;
import firstCourse.secondSemester.explorer.service.SuccessfulExecution;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class TouchCommand extends AbstractCommand {

    public TouchCommand() {
        super(createAvailableParameters());
    }

    @Override
    public void start(UserPosition position, InputString input) {
        ParametersProcessor.parametersSetting(input, getAvailableParameters());
        try {
            boolean check = new File(position.getPath().resolve(input.getPath())
                    .normalize().toAbsolutePath().toString()).createNewFile();
            if (!check)
                System.out.println("Attention: The file with this specified path has already existed.");
            else SuccessfulExecution.done();
        } catch (IOException ex) {
            System.out.println("Unknown situation. " +
                    "Please, contact with creators and report them what did you do.");
        }
        ParametersProcessor.parametersReset(getAvailableParameters());
    }

    private static Map<Character, Boolean> createAvailableParameters() {
        return new HashMap<>();
    }
}
