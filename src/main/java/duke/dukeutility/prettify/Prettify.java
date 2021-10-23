package duke.dukeutility.prettify;

import static duke.dukeutility.helper.buildString;

import java.util.ArrayList;

import duke.TaskManager;
import duke.task.model.Task;

public class Prettify {

    private static char getTaskTypeSymbol(Task t) {
        return t.getClass().getSimpleName().charAt(0);
    }

    private static String fillCell(String value, Integer lengthColMax) {
        int lengthValue = value.length();
        int lengthPad = lengthColMax - lengthValue;
        return String.format("%s%s", value, String.format("%" + (lengthPad > 0 ? lengthPad : "") + "s", " "));
    }

    /**
     * Generates All Tasks as String
     * Column widths are inherited from header columns.
     *
     * @return String
     */
    public static String prettifyTaskMgr(TaskManager taskMgr) {
        ArrayList<Task> tl = taskMgr.getAllAsArray();
        return prettifyTasks(tl);

    }

    /**
     * Present an array of tasks.
     *
     * @param tl
     * @return
     */
    public static String prettifyTasks(ArrayList<Task> tl) {

        StringBuilder generating = new StringBuilder();
        // Title
        int taskQty = tl.size();
        generating.append((taskQty + " task" + (taskQty > 1 ? "s" : "") + " in list" + System.lineSeparator()));

        // Header Values

        String headerId = String.format(" %-4s  ", "id#");
        String headerDoneStatus = "Done  ";
        String headerTaskType = "Type ";
        String headerDescription = "Task Description                 ";
        String headerChronology = "Chronology";

        generating.append(buildString(headerId, headerDoneStatus, headerTaskType, headerDescription, headerChronology,
            System.lineSeparator()));

        /* column width references */

        int lengthColId = headerId.length();
        int lengthColTaskType = headerTaskType.length();
        int lengthColDoneStatus = headerDoneStatus.length();
        int lengthColDesc = headerDescription.length();

        // Body Values
        for (Task t : tl) {
            // fill column Id
            String idValue = String.format("%4d", t.getTaskId()).replace(" ", "0");
            String id = fillCell(idValue, lengthColId);
            // fill column Done Status
            String doneStatusValue = String.format("[%s]", t.isDone() ? "X" : " ");
            String doneStatus = fillCell(doneStatusValue, lengthColDoneStatus);
            // fill column Task Type Status
            String taskTypeSymbolValue = String.format("[%s]", getTaskTypeSymbol(t));
            String type = fillCell(taskTypeSymbolValue, lengthColTaskType);
            // fill column Description
            String descValueLong = t.getTaskDescription();
            String descValue = descValueLong.substring(0, Math.min(descValueLong.length(), lengthColDesc - 1));
            String desc = fillCell(descValue, lengthColDesc);
            // fill column Chronology
            String chronology = t.getChronologyString();

            String line = buildString(id, doneStatus, type, desc, chronology, System.lineSeparator());
            generating.append(line);
        }
        return generating.toString();
    }
}
