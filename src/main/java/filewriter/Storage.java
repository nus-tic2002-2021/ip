package filewriter;

import datetime.DateTime;
import exception.DukeException;
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.Todo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class of storage.
 * Stores and reads from text file.
 */
public class Storage {
    String filePath;
    FileWriter fw;
    ArrayList<Task> taskList;
    boolean isAppend = true;

    /**
     * Constructor of Storage object.
     * @param filePath filepath to text file to be read from and edited.
     * @throws DukeException When CreateWriter throws DukeException.
     */
    public Storage(String filePath) throws DukeException {
        this.filePath = filePath;
    }

    /**
     * Creates FileWriter member fw.
     * @throws DukeException when invalid filepath.
     */
    public void write(TaskList schedule) throws DukeException {
        try {
            File text = new File(filePath);
            text.getParentFile().mkdirs();
            fw = new FileWriter(text, false);
            fw.write(schedule.toText());
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Error creating file writer");
        }
    }

    /**
     * Interprets each line in txt file and add corresponding Task to TaskList.
     * @param line every line in the txt file represents a task.
     * @return Task object with type, task name, date and timing as described in String.
     * @throws DukeException Throws when Task is not stored in the correct format in txt file.
     */
    private Task read(String line) throws DukeException {
        Task output;
        switch (line.charAt(line.indexOf("[") + 1)) {
        case 'T':
            output = new Todo(line.substring(7, line.length()));
            updateStatus(output, line);
            return output;
        case 'D':
            try {
                int divider = line.indexOf("(by:");
                String input = line.substring(7, divider);
                input += "/by " + DateTime.readDeadLine(line.substring(divider + 5, line.length() - 1)).toString();
                output = new Deadline(input);
                updateStatus(output, line);
                return output;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                throw new DukeException("\n Deadline task not stored properly.");
            }
        default:
            try {
                int divider = line.indexOf("(at:");
                String input = line.substring(7, divider);
                input += "/at " + DateTime.readEventTime(line.substring(divider + 5, line.length() - 1)).toString();
                output = new Event(input);
                updateStatus(output, line);
                return output;
            } catch (Exception e) {
                throw new DukeException("\n Event task not stored properly.");
            }
        }
    }

    private void updateStatus(Task task, String line) {
        if (line.substring(4,5).equals("+")) {
            task.markAsDone();
        }
    }

    /**
     * Reads txt file and updates TaskList accordingly.
     * @return Storage object with updated Task taskList used in construction of TaskList object.
     * @throws DukeException when read throws DukeException.
     */
    public Storage load() throws DukeException {
        try {
            taskList = new ArrayList<>();
            File text = new File(filePath);
            text.createNewFile();
            text.getParentFile().mkdirs();
            BufferedReader reader = new BufferedReader(new FileReader(text));
            Stream<String> stream
                    = reader.lines();
            taskList = new ArrayList(stream.filter(line -> !line.equals(""))
                    .map(line -> {
                        try {
                            return read(line);
                        } catch (DukeException e) {
                            throw new RuntimeException();
                        }
                    }).collect(Collectors.toList()));
            return this;
        } catch (FileNotFoundException e) {
            throw new DukeException("File not found! \n ");
        } catch (IOException e) {
            return null;
        } catch (Exception e) {
            throw new DukeException("Unexpected Error: " + e.getMessage());
        }
    }

    /**
     * Calls during construction method of TaskList.
     * @return ArrayList of Task
     */
    public ArrayList<Task> getSchedule() {
        return taskList;
    }
}