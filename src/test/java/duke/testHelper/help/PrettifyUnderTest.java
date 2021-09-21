package duke.testHelper.help;


import java.util.List;

public class PrettifyUnderTest {
    private static String fillCellUnderTest(String value, Integer lengthColMax) {
        int lengthValue = value.length();
        int lengthPad = lengthColMax - lengthValue;
        return String.format("%s%s", value, String.format("%" + (lengthPad > 0 ? lengthPad : "") + "s", " "));
    }

    public static String getPrettifyUnderTestList(Integer taskQty, List<Integer> taskIds, List<Boolean> taskDoneStatuses, List<String> taskTypes, List<String> taskDescriptions, List<String> taskChronologies) {


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

            // fill column Id
            String idValue = String.format("%4d", taskIds.get(i)).replace(" ", "0");
            String columnId = fillCellUnderTest(idValue, lengthColId);

            // fill column Done Status
            String doneStatusValue = String.format("[%s]", taskDoneStatuses.get(i) ? " " : " ");
            String columnDoneStatus = fillCellUnderTest(doneStatusValue, lengthColDoneStatus);
            // fill column Task Type Status
            String taskTypeSymbolValue = String.format("[%s]", taskTypes.get(i));
            String columnTaskType = fillCellUnderTest(taskTypeSymbolValue, lengthColTaskType);
            // fill column Description
            String descValueLong = taskDescriptions.get(i);
            String descValue = descValueLong.substring(0, Math.min(descValueLong.length(), lengthColDesc - 1));
            String columnDescription = fillCellUnderTest(descValue, lengthColDesc);
            // fill column Chronology
            String columnChronology = taskChronologies.get(i);


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