package Duke.storage;

import Duke.exception.DukeException;
import Duke.task.*;
import Duke.ui.ReturnMessages;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;

public class Storage {
    protected boolean isExist;
    protected String fileName;
    protected File file;
    private static ReturnMessages returnMessage = new ReturnMessages();
    Scanner scanner;

    /**
     * Constructor for Storage
     *
     * @param fileName   the file saving the information of task list
     *
     */
    public Storage(String fileName) {
        this.fileName = fileName;
        this.file = new File(System.getProperty("user.dir") + "/src/main/java/Duke/storage/"+fileName); // create a File for the given file path
    }

    /**
     * load info from hard disk
     * @return task list from hard disk
     */
    public TaskList load() throws DukeException {
        TaskList taskList = new TaskList();
        if (file.exists()) {
            returnMessage.loadingFileFeedback();
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    // process the line
                    taskList.addTask(decodeStr2Task(line));
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
        returnMessage.loadSuccessFeedback();
        return taskList;
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
        returnMessage.createSuccessFeedback(file.getName());
    }

    /**
     * save task list to hard disk
     *
     * @param taskList - data to save
     *
     */
    public void save(TaskList taskList)  {

        FileWriter myWriter;
        try {
            myWriter = new FileWriter(file.getAbsoluteFile(), false);
            FileWriter finalMyWriter = myWriter;
            taskList.getTaskList().forEach((task) -> {
                try {
                    finalMyWriter.write(task.encodeTask() + "\n");
                } catch (IOException e) {
                    //
                    throw new DukeException(e.getMessage());
                }
            });
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new DukeException(e.getMessage());
        }


        returnMessage.saveSuccessFeedback();
    }

    /**
     * decode the str to its individual task format
     * @param str the full string per line
     * @return created task object
     */
    public Task decodeStr2Task(String str) {
        String[] line = str.split(" \\| ");
        Task task = null;
        switch (line[0]) {
            case "T":
                task = new ToDos(line[2], line[2]);

                break;
            case "D":
                String[] date = str.split("-", 3);
                LocalDate d = LocalDate.parse(line[3]);
                LocalTime t = LocalTime.parse(line[4]);
                task = new Deadline(line[2], LocalDateTime.of(d,t));
                break;
            case "E":
                task = new Events(line[2], LocalDate.parse(line[3]), LocalTime.parse(line[4]), LocalTime.parse(line[5]));
                break;
        }
        if (Task.DONE.equals(line[1])) {
            if (task != null) {
                task.setDone();
            }
        }
        return task;
    }
}
