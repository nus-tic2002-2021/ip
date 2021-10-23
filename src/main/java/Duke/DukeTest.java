package Duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class DukeTest {
    /*public static void main(String[] args) throws IOException {
        System.out.println(System.getProperty("user.dir"));
        String fileName = "duke.txt";
        File file = new File(System.getProperty("user.dir") + "/src/main/java/Duke.Storage/"+fileName);
        System.out.println(file.exists());
    }
    */
    public static void main(String[] args) {
        System.out.println(LocalDate.parse("20210909", DateTimeFormatter.BASIC_ISO_DATE));
    }

}
