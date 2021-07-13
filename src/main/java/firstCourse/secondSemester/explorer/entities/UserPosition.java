package firstCourse.secondSemester.explorer.entities;

import java.nio.file.Path;
import java.nio.file.Paths;

public class UserPosition {
    private final Path path;

    public UserPosition(Path path) {
        this.path = path;
    }

    public UserPosition(String path) {
        this(Paths.get(path));
    }

    public Path getPath() {
        return path;
    }

}
