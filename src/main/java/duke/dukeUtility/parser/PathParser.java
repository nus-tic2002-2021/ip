package duke.dukeUtility.parser;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathParser extends Parser{


    public static Path stringToPath(String path){
        try {
            return Paths.get(path);
        } catch (Exception e) {
            return null;
        }
    }
}
