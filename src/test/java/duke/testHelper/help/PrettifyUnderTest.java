package duke.testHelper.help;


import duke.mock.mockTask.MockDeadline;
import duke.mock.mockTask.MockEvent;
import duke.mock.mockTask.MockTask;
import duke.mock.mockTask.MockToDo;


public class PrettifyUnderTest {

    private static String getTaskCharCodeUnderTest(MockTask task){
        if(task instanceof MockToDo){
            return "T";
        }
        if(task instanceof MockEvent){
            return "E";
        }
        if(task instanceof MockDeadline){
            return "D";
        }
        return " ";
    }
    private static String getTaskChronologyString(MockTask task){
        if(task instanceof MockToDo){
            return "-";
        }
        if(task instanceof MockEvent){
            return "From: " + ((MockEvent) task).getFromDateString() + ", To: " + ((MockEvent) task).getToDateString();
        }
        if(task instanceof MockDeadline){
            return "By: " + ((MockDeadline) task).getbyDateString() ;
        }
        return " ";
    }
    private static String fillCellUnderTest(String value, Integer lengthColMax) {
        int lengthValue = value.length();
        int lengthPad = lengthColMax - lengthValue;
        return String.format("%s%s", value, String.format("%" + (lengthPad > 0 ? lengthPad : "") + "s", " "));
    }

    public static String getPrettifyUnderTestList(MockTask... mockTasks) {

        int taskQty = mockTasks.length;
        StringBuilder generating = new StringBuilder();
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
        generating.append(System.lineSeparator());

        /* column width references */

        int lengthColId = headerId.length();
        int lengthColTaskType = headerTaskType.length();
        int lengthColDoneStatus = headerDoneStatus.length();
        int lengthColDesc = headerDescription.length();


        for (int i = 0; i < taskQty; i++) {
            MockTask task = mockTasks[i];
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

}