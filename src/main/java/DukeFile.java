import java.io.BufferedWriter;
import java.io.File;  // Import the File class
import java.io.FileWriter;
import java.io.IOException;  // Import the IOException class to handle errors

public class DukeFile {

    public void writeSaveFile() {
        String directoryName = "data";
        String fileName = "save.txt";
        String value = "this is testing";

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
            bw.write(value);
            bw.close();
        }
        catch (IOException e){
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public void readSaveFile() {

    }

}
