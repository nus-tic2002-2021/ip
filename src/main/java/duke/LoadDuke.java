package duke;

import java.time.LocalDate;
import java.time.LocalTime;

import duke.action.ParseDateTime;
import duke.action.ParseProgress;
import duke.exception.UnableToLoadProcessException;
import duke.task.TaskList;
import duke.task.TaskPriority;
import duke.ui.Message;

/**
 * Duke class that executes when Duke is loading progress
 *
 * @author Kang Teng
 * @version 8.0
 * @since 2021-09-01
 */

public class LoadDuke {

    private TaskList myList;
    private String progress;
    private String[] sentences;

    /**
     * Constructor
     */
    public LoadDuke(TaskList myList, String progress) {
        this.myList = myList;
        this.progress = progress;
    }

    /**
     * Execute the load
     */
    public void run() throws UnableToLoadProcessException {
        try {
            splitStringByTask();
            createTaskFromSentence();
        } catch (Exception e) {
            Message.msgError(e);
            throw new UnableToLoadProcessException();
        }
    }

    /**
     * Calls to parser to split progress.txt into sentences
     * Each sentence represents a task in String
     */
    private void splitStringByTask() throws Exception {
        sentences = ParseProgress.splitProgressIntoSentence(progress);
    }

    /**
     * Loop through the sentences and read each sentence
     * Generate task from each sentence
     */
    private void createTaskFromSentence() throws Exception {
        for (int i = 0; i < sentences.length - 1; i++) {
            String[] phrases = ParseProgress.splitSentenceByBarSeparator(sentences[i]);
            addTaskFromPhrases(phrases);
        }
    }

    /**
     * Add task into taskList based on each sentence
     */
    private void addTaskFromPhrases(String[] phrases) throws Exception {

        String taskIndexInString = phrases[0];
        int taskIndex = Integer.parseInt(taskIndexInString);

        String taskTypeInString = phrases[1];

        String isDoneInString = phrases[2];

        String taskDetail = phrases[3];

        String taskPriorityInString = phrases[4];
        int taskPriorityInInt = Integer.parseInt(taskPriorityInString);
        TaskPriority taskPriority = TaskPriority.convertIntToPriority(taskPriorityInInt);

        String taskDateInString = "null";
        LocalDate taskDate;

        String taskStartTimeInString = "null";
        LocalTime taskTimeStart;

        String taskEndTimeInString = "null";
        LocalTime taskTimeEnd;

        int i = 5;
        while (true) {
            if (phrases[i].equals(";\r")) {
                break;
            }

            switch (i) {
            case 5:
                taskDateInString = phrases[5];
                break;
            case 6:
                taskStartTimeInString = phrases[6];
                break;
            case 7:
                taskEndTimeInString = phrases[7];
                break;
            default:
                break;
            }
            i++;
        }

        switch (taskTypeInString) {
        case "T":
            myList.addItemToDos(taskDetail);
            break;
        case "E":
            taskDate = ParseDateTime.toDate(taskDateInString);
            if (taskEndTimeInString.equals("null") && taskStartTimeInString.equals("null")) {
                myList.addItemEvent(taskDetail, taskDate);
            } else if (taskEndTimeInString.equals("null")) {
                taskTimeStart = ParseDateTime.toTime(taskStartTimeInString);
                myList.addItemEvent(taskDetail, taskDate, taskTimeStart);
            } else {
                taskTimeStart = ParseDateTime.toTime(taskStartTimeInString);
                taskTimeEnd = ParseDateTime.toTime(taskEndTimeInString);
                myList.addItemEvent(taskDetail, taskDate, taskTimeStart, taskTimeEnd);
            }
            break;
        case "D":
            taskDate = ParseDateTime.toDate(taskDateInString);
            if (taskStartTimeInString.equals("null")) {
                myList.addItemDeadline(taskDetail, taskDate);
            } else {
                taskTimeStart = ParseDateTime.toTime(taskStartTimeInString);
                myList.addItemDeadline(taskDetail, taskDate, taskTimeStart);
            }
            break;
        default:
            break;
        }

        if (isDoneInString.equals("1")) {
            myList.setTaskDone(taskIndex);
        }

        myList.setTaskPriority(taskIndex, taskPriority);
    }
}
