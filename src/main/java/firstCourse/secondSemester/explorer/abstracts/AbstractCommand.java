package firstCourse.secondSemester.explorer.abstracts;

import firstCourse.secondSemester.explorer.entities.InputString;
import firstCourse.secondSemester.explorer.entities.UserPosition;

import java.util.Map;

public abstract class AbstractCommand {
    private Map<Character, Boolean> availableParameters;

    public AbstractCommand(Map<Character, Boolean> availableParameters) {
        this.availableParameters = availableParameters;
    }

    public abstract void start(UserPosition position, InputString input);

    public Map<Character, Boolean> getAvailableParameters() {
        return availableParameters;
    }
}
