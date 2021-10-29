package src.java.storage;

import src.java.ui.Message;
import src.java.task.TaskList;
import src.java.ui.Ui;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class FileAccess {
    private String filepath;
    private File progressFile;
    private Ui ui;

    public FileAccess() {
        filepath = "src\\resources\\progress.txt";
        progressFile = new File(filepath);
        ui = new Message();
    }

    public void SaveProgressIntoFile(TaskList myList, String stringToBeWritten) {

        try {
            FileWriter fw = new FileWriter(filepath);
            fw.write(stringToBeWritten);
            fw.close();
            ui.msgSave();
        } catch (Exception e) {
            ui.msgError(e);
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
            ui.msgError(e);
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
            ui.msgSave();
        } catch (Exception e) {
            ui.msgError(e);
        }
    }
}
