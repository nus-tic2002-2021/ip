package src.java.fileAccess;

import src.java.Message;
import src.java.task.TaskList;

import java.io.FileWriter;

public class FileAccess {

    public static void SaveProgressIntoFile(TaskList myList, String stringToBeWritten){
        String filepath = "src\\resources\\progress.txt";

        try {
            FileWriter fw = new FileWriter(filepath);
            fw.write(stringToBeWritten);
            fw.close();
            Message.msgSave();
        } catch (Exception e){
            Message.msgError(e);
        }
    }

    public static void LoadProgressFromFile(){

    }

    public static void DeleteFile(){

    }

    public static void SaveFileExample(){
        String filepath = "src\\resources\\myText.txt";
        try {
            FileWriter fw = new FileWriter(filepath);
            fw.write("helloWold");
            fw.close();
            Message.msgSave();
        } catch (Exception e){
            Message.msgError(e);
        }
    }
}
