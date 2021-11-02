package duke.storage;

import duke.task.TaskList;
import duke.ui.Message;
import duke.ui.Ui;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * Handles file handling related functions
 *
 * @author Kang Teng
 * @version 8.0
 * @since 2021-09-01
 */

public class FileAccess {
    private String filepath;
    private File progressFile;
    private Ui ui;

    /**
     * Constructor
     */
    public FileAccess() {
        filepath = "src\\resources\\progress.txt";
        progressFile = new File(filepath);
        ui = new Message();
    }

    /**
     * Save progress of the current task
     *
     * @param myList            TaskList that needs to be output
     * @param stringToBeWritten String that needs to be outputed
     */
    public void saveProgressIntoFile(TaskList myList, String stringToBeWritten) {

        try {
            FileWriter fw = new FileWriter(filepath);
            fw.write(stringToBeWritten);
            fw.close();
            ui.msgSave();
        } catch (Exception e) {
            ui.msgError(e);
        }
    }

    /**
     * Load existing progress of the task
     *
     * @return String that represents a list of task details
     */
    public String loadProgressFromFile() {
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

    /**
     * Delete an existing progress file
     * <p>
     * todo Method to be completed
     */
    public void deleteProgressFile() {

    }

    /**
     * Example of Save file
     */
    public void saveFileExample() {
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
