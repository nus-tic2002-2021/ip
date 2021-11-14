package Storage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Storage class handles storing and retrieving of Duke Task List from a txt file.
 */

public class Storage {

    public Storage() {

    }

    public void writeSaveFile(String stringToSave) {
        String directoryName = "data";
        String fileName = "save.txt";

        File directory = new File(directoryName);
        if (!directory.exists()){
            System.out.println("folder not found. creating folder");
            directory.mkdir();
            // If you require it to make the entire directory path including parents,
            // use directory.mkdirs(); here instead.
        }

        File file = new File(directoryName + "/" + fileName);
        try{
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(stringToSave);
            bw.close();
        }
        catch (IOException e){
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public List<String> readSaveFile() {

        List<String> content = new ArrayList<String>();

        String directoryName = "data";
        String fileName = "save.txt";

        File directory = new File(directoryName);
        File file = new File(directoryName + "/" + fileName);

        if (!directory.exists() || !file.exists()){
            System.out.println("file not found");
            return null;
        } else {
            try{
                content = Files.readAllLines(Paths.get(directoryName + "/" + fileName));
            } catch (Exception ex) {

            }
        }

        return content;
    }

}
