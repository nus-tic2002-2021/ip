package Duke.DukeLogic;

import Duke.Parser.FileLineParser;
import Duke.Models.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage class contains the methods related to the storage file.
 */
public class Storage {

    /**
     * Creates a new file and directory.
     * @return the new File object.
     * @throws DukeException
     */
    public static File createStorageFile() throws DukeException {
        File newFile = new File("data\\Duke.DukeLogic.Storage.txt");
        try {
            newFile.getParentFile().mkdirs();
            newFile.createNewFile();
            System.out.println("New Duke.DukeLogic.Storage.txt file created.");
            return newFile;
        } catch (IOException err) {
            throw new DukeException("Failed to create new Duke.DukeLogic.Storage.txt file.");
        }
    }

    /**
     * Opens and returns storage file.
     * If a storage file does not exist, create one and return that.
     * @return an opened File object.
     * @throws DukeException
     */
    public static File openStorageFile() throws DukeException {
        File newFile = new File("data\\storage.txt");
        if (!newFile.exists()) {
            try {
                newFile = createStorageFile();
            } catch (DukeException e) {
                throw new DukeException("Duke.Duke will run without any prior stored task information");
            }
        }
        return newFile;
    }

    /**
     * Takes a file and parse the contents into task objects
     * The parsed task objects will then be added to Duke's task list.
     * @param storageFile
     */
    public static void readFileToArray(File storageFile) {
        try {
            Scanner s = new Scanner(storageFile);
            while (s.hasNext()) {
                try {
                    Task taskFromLine = FileLineParser.ParseStorageLine(s.nextLine());
                    TaskList.addTaskToList(taskFromLine);
                } catch (DukeException e) {
                    e.printErrMsg();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File cannot be opened, " +
                    "Duke will not have any prior stored task information");
        }
    }

    /**
     * Retrieves information of all the tasks inside Duke task list
     * and converts each into a string to store inside storage file.
     * @param FileWrite
     */
    public static void writeListToFile(File FileWrite) {
        try {
            FileWriter fw = new FileWriter(FileWrite, false);
            ArrayList<Task> list = TaskList.DukeList;
            for (int i = 0; i < list.size(); i++) {
                Task taskAtIndex = list.get(i);
                String taskType = taskAtIndex.getTaskType();
                String newLine = "";
                if (taskType.equals("T")) {
                    newLine = buildStorageLine(taskAtIndex);
                } else if (taskType.equals("D")) {
                    newLine = buildStorageLine(taskAtIndex);
                } else if (taskType.equals("E")) {
                    newLine = buildStorageLine(taskAtIndex);
                }
                fw.write(newLine + System.getProperty("line.separator"));
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Write to file failed");
        }
    }

    /**
     * Takes in a task object using it to build
     * and return a string with the format of lines in storage file.
     * @param targetTask
     * @return a String with task information in storage line format.
     */
    public static String buildStorageLine(Task targetTask) {
        String divider = " | ";
        String initial = targetTask.getTaskType();
        String newLine = initial + divider;
        if (targetTask.isCompleted()) {
            newLine = newLine + "1" + divider;
        } else {
            newLine = newLine + "0" + divider;
        }
        newLine = newLine + targetTask.getDescription();

        if (initial.equals("D") || initial.equals("E")) {
            newLine = newLine + divider + targetTask.getAdditionalInfo();
        }

        newLine = newLine + divider + targetTask.getPriority();

        return newLine;
    }
}
