package firstCourse.secondSemester.explorer.commands;

import firstCourse.secondSemester.explorer.abstracts.AbstractCommand;
import firstCourse.secondSemester.explorer.entities.InputString;
import firstCourse.secondSemester.explorer.entities.UserPosition;
import firstCourse.secondSemester.explorer.service.App;
import firstCourse.secondSemester.explorer.service.ParametersProcessor;
import firstCourse.secondSemester.explorer.service.SuccessfulExecution;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


public class RmCommand extends AbstractCommand {

    public RmCommand() {
        super(createAvailableParameters());
    }

    @Override
    public void start(UserPosition position, InputString input) {
        ParametersProcessor.parametersSetting(input, getAvailableParameters());
        remove(new File(position.getPath().resolve(input.getPath()).normalize().toAbsolutePath().toString()));
        checkDeletedPosition(new File(position.getPath().toString()));
        SuccessfulExecution.done();
        ParametersProcessor.parametersReset(getAvailableParameters());
    }


    private static void remove(File file) {
        if (file.isDirectory()) {
            for (File el : file.listFiles()) {
                remove(el);
            }
        }
        file.delete();
    }

    private static void checkDeletedPosition(File file) {
        boolean check = false;
        while (!file.exists()) {
            file = new File(file.getParent());
            check = true;
        }
        if (check) App.setNewPosition(file.toPath());
    }

    private static Map<Character, Boolean> createAvailableParameters() {
        return new HashMap<>();
    }
}
