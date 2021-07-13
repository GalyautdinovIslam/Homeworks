package firstCourse.secondSemester.explorer.commands;

import firstCourse.secondSemester.explorer.abstracts.AbstractCommand;
import firstCourse.secondSemester.explorer.entities.InputString;
import firstCourse.secondSemester.explorer.entities.UserPosition;
import firstCourse.secondSemester.explorer.service.ParametersProcessor;
import firstCourse.secondSemester.explorer.service.SuccessfulExecution;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class MkdirCommand extends AbstractCommand {

    public MkdirCommand() {
        super(createAvailableParameters());
    }

    @Override
    public void start(UserPosition position, InputString input) {
        ParametersProcessor.parametersSetting(input, getAvailableParameters());
        File file = new File(position.getPath().resolve(input.getPath()).normalize().toAbsolutePath().toString());
        boolean check = file.exists();
        if (!check) {
            if (getAvailableParameters().get('p')) {
                check = file.mkdirs();
                if (!check) System.out.println("Attention: The directory was not created. " +
                        "The parent directories may have been successfully created while executing this command.");
                else SuccessfulExecution.done();
            } else {
                check = file.mkdir();
                if (!check) System.out.println("Attention: The directory was not created. " +
                        "Maybe parent directory does not exist. " +
                        "Please, use <p> parameter for creating all non-existent parent directories.");
                else SuccessfulExecution.done();
            }
        } else {
            if (file.isDirectory()) System.out.println("Attention: The directory has already existed.");
            else System.out.println("Attention: The specified path belongs to existing file.");
        }
        ParametersProcessor.parametersReset(getAvailableParameters());
    }

    private static Map<Character, Boolean> createAvailableParameters() {
        Map<Character, Boolean> result = new HashMap<>();
        result.put('p', false);
        return result;
    }
}
