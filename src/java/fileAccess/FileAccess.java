package src.java.fileAccess;

import src.java.Message;
import src.java.task.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class FileAccess {
    String filepath;
    File progressFile;

    public FileAccess() {
        filepath = "src\\resources\\progress.txt";
        progressFile = new File(filepath);
    }

    public void SaveProgressIntoFile(TaskList myList, String stringToBeWritten) {

        try {
            FileWriter fw = new FileWriter(filepath);
            fw.write(stringToBeWritten);
            fw.close();
            Message.msgSave();
        } catch (Exception e) {
            Message.msgError(e);
        }
    }

    public String LoadProgressFromFile() {
        StringBuilder sb = new StringBuilder();

        try {
            Scanner scanner = new Scanner(progressFile);
            while (scanner.hasNext()) {
                sb.append(scanner.nextLine());
            }

        } catch (Exception e) {
            Message.msgError(e);
        }

        return sb.toString();
    }

    public void DeleteProgressFile() {

    }

    public void SaveFileExample() {
        String filepath = "src\\resources\\myText.txt";
        try {
            FileWriter fw = new FileWriter(filepath);
            fw.write("helloWold");
            fw.close();
            Message.msgSave();
        } catch (Exception e) {
            Message.msgError(e);
        }
    }
}
