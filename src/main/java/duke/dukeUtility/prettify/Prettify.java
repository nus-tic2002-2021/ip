package duke.dukeUtility.prettify;

import duke.TaskManager;
import duke.task.model.Task;

import java.util.ArrayList;

public class Prettify {

    private static char getTaskTypeSymbol(Task t) {
        return ' ';
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
        Task[] tl = taskMgr.getAllAsArray();

        StringBuilder generating = new StringBuilder();
        int taskQty = taskMgr.getSize();
        generating.append((taskQty + " task" + (taskQty > 1 ? "s" : "") + " in list" + System.lineSeparator()));
        /* header values */

        String headerId = String.format(".%-4s  ", "id#");
        String headerDoneStatus = "Done  ";
        String headerTaskType = "Type ";
        String headerDescription = "Task Description                 ";
        String headerChronology = "Chronology";

        /* append headers */

        generating.append(headerId);
        generating.append(headerDoneStatus);
        generating.append(headerTaskType);
        generating.append(headerDescription);
        generating.append(headerChronology);
        generating.append("\n");

        /* column width references */

        int lengthColId = headerId.length();
        int lengthColTaskType = headerTaskType.length();
        int lengthColDoneStatus = headerDoneStatus.length();
        int lengthColDesc = headerDescription.length();

        // body
        for (Task t : tl) {

            // fill column Id
            String idValue = String.format("%4d", 0).replace(" ", "0");
            String columnId = fillCell(idValue, lengthColId);

            // fill column Done Status
            String doneStatusValue = String.format("[%s]", false ? "X" : " ");
            String columnDoneStatus = fillCell(doneStatusValue, lengthColDoneStatus);
            // fill column Task Type Status
            String taskTypeSymbolValue = String.format("[%s]", getTaskTypeSymbol(t));
            String columnTaskType = fillCell(taskTypeSymbolValue, lengthColTaskType);
            // fill column Description
            String descValueLong = t.getTaskDescription();
            String descValue = descValueLong.substring(0, Math.min(descValueLong.length(), lengthColDesc - 1));
            String columnDescription = fillCell(descValue, lengthColDesc);
            // fill column Chronology
            String columnChronology = " ";
            generating.append(columnId);
            generating.append(columnDoneStatus);
            generating.append(columnTaskType);
            generating.append(columnDescription);
            generating.append(columnChronology);
            generating.append(System.lineSeparator());
        }
        String generated = generating.toString();
        return generated;
    }

}