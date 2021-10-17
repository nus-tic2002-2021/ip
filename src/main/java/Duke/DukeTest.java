package Duke;

import java.io.File;
import java.io.IOException;


public class DukeTest {
    public static void main(String[] args) throws IOException {
        System.out.println(System.getProperty("user.dir"));
        String fileName = "duke.txt";
        File file = new File(System.getProperty("user.dir") + "/src/main/java/Duke.Storage/"+fileName);
        System.out.println(file.exists());
    }

}
