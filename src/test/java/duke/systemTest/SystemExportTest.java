package duke.systemTest;

import static duke.dukeutility.config.dukeIO.getDefaultTasksImportPathString;
import static duke.dukeutility.validator.TextCommandValidator.isParentDirectoryValid;
import static duke.testhelper.help.Builder.buildCommandInputStream;
import static duke.testhelper.help.Builder.buildString;
import static duke.testhelper.help.CodeUnderTest.OutputUnderTest.getExpectedOutputAddedDeadline;
import static duke.testhelper.help.CodeUnderTest.OutputUnderTest.getExpectedOutputAddedEvent;
import static duke.testhelper.help.CodeUnderTest.OutputUnderTest.getExpectedOutputAddedToDo;
import static duke.testhelper.help.CodeUnderTest.OutputUnderTest.getExpectedOutputBeginInputLoop;
import static duke.testhelper.help.CodeUnderTest.OutputUnderTest.getExpectedOutputCommandDeleted;
import static duke.testhelper.help.CodeUnderTest.OutputUnderTest.getExpectedOutputEntry;
import static duke.testhelper.help.CodeUnderTest.OutputUnderTest.getExpectedOutputExitInputLoop;
import static duke.testhelper.help.CodeUnderTest.OutputUnderTest.getExpectedOutputImportAttempt;
import static duke.testhelper.help.CodeUnderTest.OutputUnderTest.getExpectedOutputList;
import static duke.testhelper.help.CodeUnderTest.OutputUnderTest.getExpectedOutputReadPathNotFound;
import static duke.testhelper.help.CodeUnderTest.OutputUnderTest.getExpectedOutputTaskSetCompleted;
import static duke.testhelper.help.CodeUnderTest.OutputUnderTest.getExpectedOutputTerminate;
import static duke.testhelper.help.CodeUnderTest.ParserUnderTest.parseStringAsLocalDateTime;
import static duke.testhelper.help.CodeUnderTest.ParserUnderTest.stringToPath;
import static duke.testhelper.help.CodeUnderTest.PrettifyUnderTest.getExpectedTaskList;
import static duke.testhelper.help.CodeUnderTest.TextCommandUnderTest.DELIMITER_DEADLINE_DEADLINE;
import static duke.testhelper.help.CodeUnderTest.TextCommandUnderTest.DELIMITER_EVENT_FROM;
import static duke.testhelper.help.CodeUnderTest.TextCommandUnderTest.DELIMITER_EVENT_TO;
import static duke.testhelper.help.CodeUnderTest.TextCommandUnderTest.PROMPT_UNDER_TEST_ADD_DEADLINE;
import static duke.testhelper.help.CodeUnderTest.TextCommandUnderTest.PROMPT_UNDER_TEST_ADD_EVENT;
import static duke.testhelper.help.CodeUnderTest.TextCommandUnderTest.PROMPT_UNDER_TEST_ADD_TO_DO;
import static duke.testhelper.help.CodeUnderTest.TextCommandUnderTest.PROMPT_UNDER_TEST_DELETE_TASK;
import static duke.testhelper.help.CodeUnderTest.TextCommandUnderTest.PROMPT_UNDER_TEST_EXIT_LOOP;
import static duke.testhelper.help.CodeUnderTest.TextCommandUnderTest.PROMPT_UNDER_TEST_FIND;
import static duke.testhelper.help.CodeUnderTest.TextCommandUnderTest.PROMPT_UNDER_TEST_LIST;
import static duke.testhelper.help.CodeUnderTest.TextCommandUnderTest.PROMPT_UNDER_TEST_MARK_AS_DONE;
import static duke.testhelper.help.CodeUnderTest.TextCommandUnderTest.PROMPT_UNDER_TEST_SAVE;
import static duke.testhelper.help.CodeUnderTest.TextCommandUnderTest.generateTextCommandDeleteTaskByTaskId;
import static duke.testhelper.help.CodeUnderTest.TextCommandUnderTest.generateTextCommandExit;
import static duke.testhelper.help.CodeUnderTest.TextCommandUnderTest.generateTextCommandFindKeywordInDescription;
import static duke.testhelper.help.CodeUnderTest.TextCommandUnderTest.generateTextCommandLineAddDeadline;
import static duke.testhelper.help.CodeUnderTest.TextCommandUnderTest.generateTextCommandLineAddEvent;
import static duke.testhelper.help.CodeUnderTest.TextCommandUnderTest.generateTextCommandLineAddToDo;
import static duke.testhelper.help.CodeUnderTest.TextCommandUnderTest.generateTextCommandList;
import static duke.testhelper.help.CodeUnderTest.TextCommandUnderTest.generateTextCommandSave;
import static duke.testhelper.help.CodeUnderTest.TextCommandUnderTest.generateTextCommandSetCompleted;
import static duke.testhelper.help.config.dukeIOTestPath.resourceTestFolder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.fail;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Test;
import com.google.gson.JsonArray;
import duke.FileResourceManager;
import duke.Main;
import duke.TaskManager;
import duke.command.commandfactory.FileCommandFactory;
import duke.mock.mockTask.MockDeadline;
import duke.mock.mockTask.MockEvent;
import duke.mock.mockTask.MockTask;
import duke.mock.mockTask.MockToDo;
import duke.testhelper.TestStream;

public class SystemExportTest extends TestStream {

    /**
     * Execute add tasks commands and save as export1
     * import export1, save as export2
     * export1 == export2 ? pass
     */
    @Test
    public void IdempotentExport() {
        String thisTestSign = "saveEventsToJsonFile";
        String export1PathString = resourceTestFolder + "-added" + thisTestSign +
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-hh-mm-ss")) + ".json";
        String export2PathString = resourceTestFolder + "-load" + thisTestSign +
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-hh-mm-ss")) + ".json";
        FileResourceManager frm1 = new FileResourceManager(export1PathString, null);
        TaskManager tm1 = new TaskManager();
        //  sets of add tasks textCommands
        int countPerTaskType = 10;
        int expectedTotalTaskCount = 0;

        String[] addCommands = new String[countPerTaskType];
        for (int i = 0; i < countPerTaskType; i++) {

            String todoDesc = "tododesc" + i;

            String deadlineDesc = "deadlinedesc";
            String deadlineBy = "202002" + String.format("%2s", 19);

            String eventDesc = "eventdesc";
            String eventFrom = "20200202";
            String eventTo = "20210202";

            String addToDoCommand = generateTextCommandLineAddToDo(PROMPT_UNDER_TEST_ADD_TO_DO, todoDesc);
            expectedTotalTaskCount++;
            String addDeadlineCommand = generateTextCommandLineAddDeadline(PROMPT_UNDER_TEST_ADD_DEADLINE, deadlineDesc,
                DELIMITER_DEADLINE_DEADLINE, deadlineBy);
            expectedTotalTaskCount++;
            String addEventCommand =
                generateTextCommandLineAddEvent(PROMPT_UNDER_TEST_ADD_EVENT, eventDesc, DELIMITER_EVENT_FROM, eventFrom,
                    DELIMITER_EVENT_TO, eventTo);
            expectedTotalTaskCount++;

            addCommands[i] = addToDoCommand + addDeadlineCommand + addEventCommand;
        }

        String in0 = String.join("", addCommands);
        String in1 = generateTextCommandSetCompleted(PROMPT_UNDER_TEST_MARK_AS_DONE, 5);
        String in2 = (generateTextCommandSave(PROMPT_UNDER_TEST_SAVE));
        String in3 = (generateTextCommandExit(PROMPT_UNDER_TEST_EXIT_LOOP));
        System.setIn(buildCommandInputStream(in0, in1, in2, in3));
        try {
            Main.run(this.getPrintStream(), tm1, frm1);
            assertSame(tm1.getSize(), expectedTotalTaskCount, "expected amount " + expectedTotalTaskCount
                + ", actual " + tm1.getSize() + System.lineSeparator());
        } catch (Exception e) {
            fail(e.toString());
        }

        String secondIn0 = generateTextCommandSave(PROMPT_UNDER_TEST_SAVE);
        String secondIn1 = generateTextCommandSave(PROMPT_UNDER_TEST_EXIT_LOOP);

        System.setIn(buildCommandInputStream(secondIn0, secondIn1));
        FileResourceManager frm2 = new FileResourceManager(export2PathString, export1PathString);

        TaskManager tm2 = new TaskManager();
        try {
            Main.run(this.getPrintStream(), tm2, frm2);
        } catch (Exception e) {
            fail(e.toString());
        }
        assertEquals(30, tm2.getSize());
        try {
            JsonArray export1 = new FileCommandFactory().executeExtractTasksFromFile(frm1.getExportPath()).getJsonArg()
                .getAsJsonArray();
            JsonArray export2 = new FileCommandFactory().executeExtractTasksFromFile(frm2.getExportPath()).getJsonArg()
                .getAsJsonArray();
            assertNotNull(export1);
            assertNotNull(export2);
            assertEquals(30, export1.size());
            assertEquals(30, export2.size());
            assertEquals(export1, export2);
        } catch (Exception e) {
            fail("Failure during comparing exports. " + e + this.getOutput());
        }
    }


    public void writeToFile(String pathString, String... data) throws Exception {
        Path exportPath = stringToPath(pathString);
        try {
            if (exportPath == null) {
                throw new Exception("Export path validation failed.");
            }
            Files.createDirectories(exportPath.getParent());
            if (!isParentDirectoryValid(exportPath)) {
                throw new Exception("Export path validation failed.");
            }
        } catch (Exception e) {
            throw new IOException("Export path validation failed.");
        }
        Files.write(exportPath, buildString(data).getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE);
    }

    /**
     * This method generates 1) the input and 2) its expected output for system level test. No assertions required.
     *
     * @throws Exception Unable to generate files, may impact on test phase.
     */
    @Test
    public void GenerateExpectedTestFileInActualEnvironment() throws Exception {
        String importPathString = getDefaultTasksImportPathString();

        String loadInputPath =
            System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator +
                "resources" + File.separator + "linux-test" + File.separator + "input.txt";
        String expectedOutputPath =
            System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator +
                "resources" + File.separator + "linux-test" + File.separator + "expected.txt";

        String out0 = (getExpectedOutputEntry());

        String out1 = (getExpectedOutputImportAttempt(stringToPath(importPathString)));
        String out2 = (getExpectedOutputReadPathNotFound());

        String out3 = (getExpectedOutputBeginInputLoop());

        String todoDesc = "tododesc";

        String deadlineDesc = "deadlinedesc";
        String deadlineBy = "202002" + String.format("%2s", 19);

        String eventDesc = "eventdesc asdasdasd";
        String eventFrom = "20200202";
        String eventTo = "20210202";

        String in0 = generateTextCommandLineAddToDo(PROMPT_UNDER_TEST_ADD_TO_DO, todoDesc);
        String out4 = (getExpectedOutputAddedToDo(todoDesc));

        String in1 = generateTextCommandLineAddDeadline(PROMPT_UNDER_TEST_ADD_DEADLINE, deadlineDesc,
            DELIMITER_DEADLINE_DEADLINE, deadlineBy);
        String out5 = (getExpectedOutputAddedDeadline(deadlineDesc));

        String in2 =
            generateTextCommandLineAddEvent(PROMPT_UNDER_TEST_ADD_EVENT, eventDesc, DELIMITER_EVENT_FROM, eventFrom,
                DELIMITER_EVENT_TO, eventTo);
        String out6 = (getExpectedOutputAddedEvent(eventDesc));

        String in3 = generateTextCommandList(PROMPT_UNDER_TEST_LIST);

        MockTask[] allMockTasks = new MockTask[] {new MockToDo(todoDesc, 0, false),
            new MockDeadline(deadlineDesc, 1, false, parseStringAsLocalDateTime(deadlineBy)),
            new MockEvent(eventDesc, 2, false, parseStringAsLocalDateTime(eventFrom),
                parseStringAsLocalDateTime(eventTo))};
        String out7 = (getExpectedOutputList(getExpectedTaskList(allMockTasks)));

        String in4 = generateTextCommandDeleteTaskByTaskId(PROMPT_UNDER_TEST_DELETE_TASK, 0);
        String out8 = (getExpectedOutputCommandDeleted(0));

        String in5 = generateTextCommandList(PROMPT_UNDER_TEST_LIST);

        MockTask[] remainingMockTasks =
            new MockTask[] {new MockDeadline(deadlineDesc, 1, false, parseStringAsLocalDateTime(deadlineBy)),
                new MockEvent(eventDesc, 2, false, parseStringAsLocalDateTime(eventFrom),
                    parseStringAsLocalDateTime(eventTo))};
        String out9 = (getExpectedOutputList(getExpectedTaskList(remainingMockTasks)));

        String in6 = generateTextCommandSetCompleted(PROMPT_UNDER_TEST_MARK_AS_DONE, 2);
        String out10 = getExpectedOutputTaskSetCompleted(2);

        String keyword = "eventdesc";
        String in7 = generateTextCommandFindKeywordInDescription(PROMPT_UNDER_TEST_FIND, keyword);
        MockTask[] selectedMockTasks = new MockTask[] {
            new MockEvent(eventDesc, 2, true, parseStringAsLocalDateTime(eventFrom),
                parseStringAsLocalDateTime(eventTo))};
        String out11 = (getExpectedOutputList(getExpectedTaskList(selectedMockTasks)));

        String in8 = generateTextCommandExit(PROMPT_UNDER_TEST_EXIT_LOOP);
        String out12 = (getExpectedOutputExitInputLoop());

        String out13 = (getExpectedOutputTerminate());

        writeToFile(loadInputPath, in0, in1, in2, in3, in4, in5, in6, in7, in8);
        writeToFile(expectedOutputPath, out0, out1, out2, out3, out4, out5, out6, out7, out8, out9, out10, out11, out12,
            out13);
    }
}
