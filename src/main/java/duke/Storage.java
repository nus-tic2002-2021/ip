package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private static String filePath;
    private ArrayList <String> loadFile;
    public Storage(String filePath){
        this.filePath = filePath;
        loadFile = new ArrayList<>();
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
    public ArrayList<String> load(){
        try {
            readFile(filePath);
        } catch (FileNotFoundException e) {
            newFile(); //create new file
            System.out.println("File not found");
        }
        return loadFile;
    }

    private void writeToFile(ArrayList<String> saveFileList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for(String saveFile : saveFileList) {
            fw.write(saveFile + System.lineSeparator());
        }
        fw.close();
    }

    private void readFile(String filePath) throws FileNotFoundException  {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String read = s.nextLine();
            loadFile.add(read);
        }
    }

}
