package duke.dukeutility.prettify;

import static duke.dukeutility.helper.buildString;

import java.util.ArrayList;
import java.util.Arrays;

import duke.TaskManager;
import duke.task.model.Deadline;
import duke.task.model.Event;
import duke.task.model.Task;
import duke.task.model.ToDo;

public class Prettify {

    private static char getTaskTypeSymbol(Task t) {
        return t.getClass().getSimpleName().charAt(0);
    }

    private static String fillCell(String value, Integer lengthColMax) {
        int lengthValue = value.length();
        int lengthPad = lengthColMax - lengthValue;
        return String.format("%s%s", value,
            String.format("%" + (lengthPad > 0 ? lengthPad : "") + "s", (lengthPad > 0 ? " " : "")));
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

    public static String getExpectedStatisticsAll(ArrayList<Task> tasks) {
        // Tabulate
        int col = 0;
        int colDone = col++;
        int colNotDone = col++;
        int row = 0;
        int rowToDo = row++;
        int rowDeadline = row++;
        int rowEvent = row++;


        Integer[][] stats = new Integer[col][row];
        for (Integer[] ints : stats) {
            Arrays.fill(ints, 0);
        }
        for (Task t : tasks) {

            Integer thisRow = null;
            if (t instanceof ToDo) {
                thisRow = rowToDo;
            } else if (t instanceof Deadline) {
                thisRow = rowDeadline;
            } else if (t instanceof Event) {
                thisRow = rowEvent;
            }
            Integer thisCol = null;
            if (t.isDone()) {
                thisCol = colDone;
            } else {
                thisCol = colNotDone;
            }
            assert (thisCol != null);
            assert (thisRow != null);
            if (thisCol != null && thisRow != null) {
                stats[thisCol][thisRow]++;
            }
        }
        int rowHeader = row++;
        int colTaskType = col++;
        String[][] table = new String[col][row];
        for (String[] tableRow : table) {
            Arrays.fill(tableRow, "");
        }
        table[0][rowHeader] = "Task Type   ";
        table[0][rowToDo] = "To Do";
        table[0][rowDeadline] = "Deadline";
        table[0][rowEvent] = "Event";

        table[2][rowHeader] = "Incomplete   ";
        table[2][rowToDo] = stats[colNotDone][rowToDo].toString();
        table[2][rowDeadline] = stats[colNotDone][rowDeadline].toString();
        table[2][rowEvent] = stats[colNotDone][rowEvent].toString();

        table[1][rowHeader] = "Complete   ";
        table[1][rowToDo] = stats[colDone][rowToDo].toString();
        table[1][rowDeadline] = stats[colDone][rowDeadline].toString();
        table[1][rowEvent] = stats[colDone][rowEvent].toString();


        int[] colLength = new int[col];

        colLength[colTaskType] = getMaxLength(table[colTaskType]);
        colLength[colNotDone] = getMaxLength(table[colNotDone]);
        colLength[colDone] = getMaxLength(table[colDone]);


        for (int c = 0; c < col; c++) {
            for (int r = 0; r < row; r++) {
                table[c][r] = fillCell(table[c][r], colLength[c]);
            }
        }
        StringBuilder lines = new StringBuilder();

        lines.append(String.join("", getRowValue(table, rowHeader)) + System.lineSeparator());
        lines.append(String.join("", getRowValue(table, rowToDo)) + System.lineSeparator());
        lines.append(String.join("", getRowValue(table, rowDeadline)) + System.lineSeparator());
        lines.append(String.join("", getRowValue(table, rowEvent)) + System.lineSeparator());
        return lines.toString();
    }

    public static String getRowValue(String[][] table, int r) {
        StringBuilder result = new StringBuilder();
        for (int c = 0; c < table.length; c++) {
            result.append(table[c][r]);
        }
        return result.toString();
    }

    private static int getMaxLength(String[] strings) {
        int maxLength = 0;
        for (String s : strings) {
            maxLength = Math.max(maxLength, s.length());
        }
        return maxLength;

    }


}
