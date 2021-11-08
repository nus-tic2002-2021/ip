package storage;

import data.Acronym;
import data.DeadLine;
import data.Event;
import data.Task;


import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskDecoder {
    public static final Pattern TASK_DATA_ARGS_FORMAT = // '/' forward slashes are reserved for delimiter prefixes
            Pattern.compile("((^[DE]) \\| ([0-1]) \\| (.*\\|) (.*))|((^[T]) \\| ([0-1]) \\| (.*))");


    public static void decodeTaskListToLoad(List<String> encodedTaskList, List<Task> task) throws IOException
    {
        for (String encodedTask : encodedTaskList){
            task.add(decodeTaskFromStringLineByLine(encodedTask));
        }

    }

    private static Task decodeTaskFromStringLineByLine(String encodedTask) {
        final Matcher matcher = TASK_DATA_ARGS_FORMAT.matcher(encodedTask);
        if (!matcher.matches()) {
            System.out.println("Data are not in the correct format. Unable to write to ArrayList From File");
        }
        Task newTask = null;
        String[] data = encodedTask.split("[|]");
        String acro = data[0].trim();
        String isDone = data[1].trim();
        String taskDesc = data[2].trim();
        // Can be put in a separate method
        if (acro.equals(Acronym.T.toString())) { // Todo Task
            newTask = new Task(taskDesc,Acronym.T);
            if (isDone.equals("1")) {
                newTask.setDone(true);
            }
        } else if (acro.equals(Acronym.D.toString())) { //Deadline Task
            String deadLineTimeStamp = data[3].trim();
            newTask = new DeadLine(taskDesc,Acronym.D,deadLineTimeStamp);
            if (isDone.equals("1")) {
                newTask.setDone(true);
            }
        } else if (acro.equals(Acronym.E.toString())) { //Event Task
            String eventTimeStamp = data[3].trim();
            newTask = new Event(taskDesc, Acronym.E,eventTimeStamp);
            if (isDone.equals("1")) {
                newTask.setDone(true);
            }
        }
        return newTask;
    }
}
