package firstCourse.secondSemester.explorer.commands;

import firstCourse.secondSemester.explorer.abstracts.AbstractCommand;
import firstCourse.secondSemester.explorer.entities.InputString;
import firstCourse.secondSemester.explorer.entities.UserPosition;
import firstCourse.secondSemester.explorer.service.App;
import firstCourse.secondSemester.explorer.service.ParametersProcessor;

import java.util.HashMap;
import java.util.Map;

public class ExitCommand extends AbstractCommand {

    public ExitCommand() {
        super(createAvailableParameters());
    }

    @Override
    public void start(UserPosition position, InputString input) {
        ParametersProcessor.parametersSetting(input, getAvailableParameters());
        System.out.println("Thanks for using my software. Good luck.");
        System.out.println("Created by @groshpaw");
        System.out.println("2021");
        App.exit();
    }

    private static Map<Character, Boolean> createAvailableParameters() {
        return new HashMap<>();
    }
}
