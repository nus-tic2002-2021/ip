package duke.testhelper.help.codeundertest;

import java.util.ArrayList;
import java.util.Arrays;

import duke.mock.mocktask.MockDeadline;
import duke.mock.mocktask.MockEvent;
import duke.mock.mocktask.MockTask;
import duke.mock.mocktask.MockToDo;
import duke.task.model.Deadline;
import duke.task.model.Event;
import duke.task.model.Task;
import duke.task.model.ToDo;

public class PrettifyUnderTest {

    private static String getTaskCharCodeUnderTest(MockTask task) {
        if (task instanceof MockToDo) {
            return "T";
        }
        if (task instanceof MockEvent) {
            return "E";
        }
        if (task instanceof MockDeadline) {
            return "D";
        }
        return " ";
    }

    private static String getTaskChronologyString(MockTask task) {
        if (task instanceof MockToDo) {
            return "-";
        }
        if (task instanceof MockEvent) {
            return "From: " + ((MockEvent) task).getFromDateString() + ", To: " + ((MockEvent) task).getToDateString();
        }
        if (task instanceof MockDeadline) {
            return "By: " + ((MockDeadline) task).getbyDateString();
        }
        return " ";
    }

    private static String fillCellUnderTest(String value, Integer lengthColMax) {
        int lengthValue = value.length();
        int lengthPad = lengthColMax - lengthValue;
        return String.format("%s%s", value,
            String.format("%" + (lengthPad > 0 ? lengthPad : "") + "s", (lengthPad > 0 ? " " : "")));
    }

    public static String getExpectedTaskList(MockTask... mockTasks) {

        int taskQty = mockTasks.length;
        StringBuilder generating = new StringBuilder();
        generating.append((taskQty + " task" + (taskQty > 1 ? "s" : "") + " in list" + System.lineSeparator()));
        /* header values */

        String headerId = String.format(" %-4s  ", "id#");
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
        generating.append(System.lineSeparator());

        /* column width references */

        int lengthColId = headerId.length();
        int lengthColTaskType = headerTaskType.length();
        int lengthColDoneStatus = headerDoneStatus.length();
        int lengthColDesc = headerDescription.length();

        for (MockTask task : mockTasks) {
            // fill column Id
            String idValue = String.format("%4d", task.getId()).replace(" ", "0");
            String columnId = fillCellUnderTest(idValue, lengthColId);

            // fill column Done Status
            String doneStatusValue = String.format("[%s]", task.getDone() ? "X" : " ");
            String columnDoneStatus = fillCellUnderTest(doneStatusValue, lengthColDoneStatus);
            // fill column Task Type Status
            String taskTypeSymbolValue = String.format("[%s]", getTaskCharCodeUnderTest(task));
            String columnTaskType = fillCellUnderTest(taskTypeSymbolValue, lengthColTaskType);
            // fill column Description
            String descValueLong = task.getDesc();
            String descValue = descValueLong.substring(0, Math.min(descValueLong.length(), lengthColDesc - 1));
            String columnDescription = fillCellUnderTest(descValue, lengthColDesc);
            // fill column Chronology
            String columnChronology = getTaskChronologyString(task);

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
                table[c][r] = fillCellUnderTest(table[c][r], colLength[c]);
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
