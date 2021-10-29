import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    public static File CreateStorageFile() throws DukeException {
        File newFile = new File("data\\Storage.txt");
        try {
            newFile.getParentFile().mkdirs();
            newFile.createNewFile();
            System.out.println("New Storage.txt file created.");
            return newFile;
        } catch (IOException err) {
            throw new DukeException("Failed to create new Storage.txt file.");
        }
    }

    public static File OpenStorageFile() throws DukeException {
        File newFile = new File("data\\storage.txt");
        if (!newFile.exists()) {
            try {
                newFile = CreateStorageFile();
            } catch (DukeException e) {
                throw new DukeException("Duke will run without any prior stored task information");
            }
        }
        return newFile;
    }

    public static void ReadFileToArray(File storageFile) {
        try {
            Scanner s = new Scanner(storageFile);
            while (s.hasNext()) {
                try {
                    TaskList.addTaskToList(Parser.ParseStorageLine(s.nextLine()));
                } catch (DukeException e) {
                    e.printErrMsg();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File cannot be opened, " +
                    "Duke will not have any prior stored task information");
        }
    }

    public static void writeListToFile(File FileWrite) {
        try {
            FileWriter fw = new FileWriter(FileWrite, false);
            ArrayList<Task> list = TaskList.DukeList;
            for (int i = 0; i < list.size(); i++) {
                Task taskAtIndex = list.get(i);
                String typeCheck = taskAtIndex.getTaskType();
                String newLine = "";
                if (typeCheck.equals("T")) {
                    newLine = buildStorageLine(taskAtIndex);
                } else if (typeCheck.equals("D")) {
                    newLine = buildStorageLine(taskAtIndex);
                } else if (typeCheck.equals("E")) {
                    newLine = buildStorageLine(taskAtIndex);
                }
                fw.write(newLine + System.getProperty("line.separator"));
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Write to file failed");
        }
    }

    public static String buildStorageLine(Task targetTask) {
        String divider = " | ";
        String initial = targetTask.getTaskType();
        String newLine = initial + divider;
        if (targetTask.Completed) {
            newLine = newLine + "1" + divider;
        } else {
            newLine = newLine + "0" + divider;
        }
        newLine = newLine + targetTask.Description;

        if (initial.equals("D") || initial.equals("E"))
        newLine = newLine + divider + targetTask.getAdditionalInfo();
        return newLine;
    }
}
