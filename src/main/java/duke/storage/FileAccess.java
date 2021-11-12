package duke.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import duke.exception.UnableToLoadBuddhaException;
import duke.exception.UnableToLoadProcessException;
import duke.ui.Message;
import duke.ui.Ui;

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
     *
     * @param filepath
     */
    public FileAccess(String filepath) {
        this.filepath = filepath;
        progressFile = new File(this.filepath);
    }

    /**
     * Save progress of the current task
     *
     * @param stringToBeWritten String that needs to be outputed
     */
    public void saveProgressIntoFile(String stringToBeWritten) {

        assert !stringToBeWritten.isEmpty() : "stringToBeWritten should not be empty";

        try {
            FileWriter fw = new FileWriter(filepath);
            fw.write(stringToBeWritten);
            fw.close();
            Message.msgSave();
        } catch (Exception e) {
            Message.msgError(e);
        }
    }

    /**
     * Load existing progress of the task
     *
     * @return String that represents a list of task details
     */
    public static String loadProgressFromFile() throws UnableToLoadProcessException {
        String progress = "";
        String pathRoot = System.getProperty("user.dir");
        // e.g. pathRoot = D:\My Files\School Documents\Repository\Duke

        String pathRssFolder = "src" + File.separator + "resources";
        // pathRssFolder = src\resources

        String pathFileName = "progress.txt";
        // pathFileName = progress.txt

        String filePath = pathRoot + File.separator + pathRssFolder + File.separator + pathFileName;
        // e.g.
        // filePath = D:\My Files\School
        // Documents\Repository\Duke\src\main\resources\progress.txt

        try {
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            progress = sb.toString();
            br.close();
        } catch (Exception e) {
            throw new UnableToLoadProcessException();
        }
        return progress;
    }

    /**
     * Read Buddah.txt
     *
     * @return String that contains the content in Buddha.txt
     */
    public String readBuddhaText() throws UnableToLoadBuddhaException {
        String buddhaText = "";
        String pathRoot = System.getProperty("user.dir");
        String pathRssFolder = "src" + File.separator + "resources";
        String pathFileName = "buddha.txt";
        String filePath = pathRoot + File.separator + pathRssFolder + File.separator + pathFileName;

        try {
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            buddhaText = sb.toString();
            br.close();
        } catch (Exception e) {
            throw new UnableToLoadBuddhaException();
        }
        return buddhaText;
    }
}
