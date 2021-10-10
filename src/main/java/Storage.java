import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private static String filePath;

    public Storage(String filePath){
        this.filePath = filePath;
    }

    public void newFile(){
        try {
            File f = new File(filePath);
            f.getParentFile().mkdirs();
            if (f.createNewFile())
                System.out.println("File created");
            else
                System.out.println("Loading existing file");
        }
        catch (Exception e) {
            System.err.println(e);
        }

    }
    public void saveFile(ArrayList<String> saveFileList){
        try {
            writeToFile(saveFileList);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    private void writeToFile(ArrayList<String> saveFileList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for(String saveFile : saveFileList) {
            fw.write(saveFile + System.lineSeparator());
        }
        fw.close();
    }

}
