package firstCourse.secondSemester.explorer.commands;

import firstCourse.secondSemester.explorer.abstracts.AbstractCommand;
import firstCourse.secondSemester.explorer.entities.InputString;
import firstCourse.secondSemester.explorer.entities.UserPosition;
import firstCourse.secondSemester.explorer.service.App;
import firstCourse.secondSemester.explorer.service.InputProcessor;
import firstCourse.secondSemester.explorer.service.ParametersProcessor;
import firstCourse.secondSemester.explorer.service.SuccessfulExecution;

import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;


public class CdCommand extends AbstractCommand {

    public CdCommand() {
        super(createAvailableParameters());
    }

    @Override
    public void start(UserPosition position, InputString input) {
        ParametersProcessor.parametersSetting(input, getAvailableParameters());
        Path newPosition = position.getPath().resolve(input.getPath()).normalize().toAbsolutePath();
        if (new File(newPosition.toString()).exists()) {
            App.setNewPosition(newPosition);
            SuccessfulExecution.done();
        } else InputProcessor.unknownPath();
        ParametersProcessor.parametersReset(getAvailableParameters());
    }

    private static Map<Character, Boolean> createAvailableParameters() {
        return new HashMap<>();
    }
}
