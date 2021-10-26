package Duke.storage;

import Duke.exception.DukeException;
import Duke.task.*;
import Duke.ui.ReturnMessages;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;

public class Storage_Unschedule {

    protected boolean isExist;
    protected String fileName;
    protected File file;
    private static ReturnMessages returnMessage = new ReturnMessages();
    Scanner scanner;


    /**
     * Constructor for Storage_Unscheduled
     *
     */
    public Storage_Unschedule() {
        //this.fileName = fileName;
        this.file = new File(System.getProperty("user.dir") + "/src/main/java/Duke/storage/task_time_records.txt"); // create a File for the given file path
        this.fileName = file.getName();
    }

    /**
     * load info from hard disk
     * @return task list from hard disk
     */
    public UnscheduledTaskList load() throws DukeException {
        UnscheduledTaskList unscheduledRecords = new UnscheduledTaskList();
        if (file.exists()) {
            //returnMessage.loadingFileFeedback();
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    // process the line
                    unscheduledRecords.addTask(decodeStr2Unscheduled(line));
                }
            } catch (IOException e) {
                //fail to parse
                throw new DukeException("file parse fail");
            }
        } else {
            try {
                createFile();
            } catch (IOException e) {
                //fail to create file
                throw new DukeException("create file fail");
            }
        }
        //returnMessage.loadSuccessFeedback();
        return unscheduledRecords;
    }


    /**
     * Creates File if not exist
     *
     * @exception  IOException throws a IO Exception if file exists
     *
     */
    public void createFile() throws IOException {
        try {
            boolean create = file.createNewFile();
        } catch (IOException ioe) {
            this.isExist = true;
            System.out.println("File exists");
        }
        this.isExist = true;
        returnMessage.createSuccessFeedback(file.getName());
    }


    /**
     * decode the str to unscheduled task records with description and duration
     * @param str the full string per line
     * @return created task object
     */
    public UnscheduledTask decodeStr2Unscheduled(String str) {
        String[] line = str.split(" \\| ");
        UnscheduledTask unscheduledRecord;
        System.out.println("line[0]: "+line[0].stripTrailing());
        System.out.println("line[1]: "+line[1].stripLeading());
        unscheduledRecord = new UnscheduledTask(line[0], Integer.parseInt(line[1]));
        return unscheduledRecord;
    }
}
