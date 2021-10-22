package duke.dukeUtility.parser;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathParser extends Parser {


    public static Path stringToPath(String pathString) {
        try {
            return Paths.get(pathString);
        } catch (Exception e) {
            return null;
        }
    }
}
