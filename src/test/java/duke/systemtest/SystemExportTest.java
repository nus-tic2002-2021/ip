package duke.systemtest;

import static duke.dukeutility.config.DukeIo.getDefaultTasksImportPathString;
import static duke.dukeutility.validator.TextCommandValidator.isParentDirectoryValid;
import static duke.testhelper.help.Builder.buildCommandInputStream;
import static duke.testhelper.help.Builder.buildString;
import static duke.testhelper.help.codeundertest.OutputUnderTest.getExpectedOutputAddedDeadline;
import static duke.testhelper.help.codeundertest.OutputUnderTest.getExpectedOutputAddedEvent;
import static duke.testhelper.help.codeundertest.OutputUnderTest.getExpectedOutputAddedToDo;
import static duke.testhelper.help.codeundertest.OutputUnderTest.getExpectedOutputBeginInputLoop;
import static duke.testhelper.help.codeundertest.OutputUnderTest.getExpectedOutputCommandDeleted;
import static duke.testhelper.help.codeundertest.OutputUnderTest.getExpectedOutputEntry;
import static duke.testhelper.help.codeundertest.OutputUnderTest.getExpectedOutputExitInputLoop;
import static duke.testhelper.help.codeundertest.OutputUnderTest.getExpectedOutputImportAttempt;
import static duke.testhelper.help.codeundertest.OutputUnderTest.getExpectedOutputList;
import static duke.testhelper.help.codeundertest.OutputUnderTest.getExpectedOutputListTasksWithKeywordDescription;
import static duke.testhelper.help.codeundertest.OutputUnderTest.getExpectedOutputReadPathNotFound;
import static duke.testhelper.help.codeundertest.OutputUnderTest.getExpectedOutputTaskSetCompleted;
import static duke.testhelper.help.codeundertest.OutputUnderTest.getExpectedOutputTerminate;
import static duke.testhelper.help.codeundertest.ParserUnderTest.parseStringAsLocalDateTime;
import static duke.testhelper.help.codeundertest.ParserUnderTest.stringToPath;
import static duke.testhelper.help.codeundertest.PrettifyUnderTest.getExpectedTaskList;
import static duke.testhelper.help.codeundertest.TextCommandUnderTest.DELIMITER_DEADLINE_DEADLINE;
import static duke.testhelper.help.codeundertest.TextCommandUnderTest.DELIMITER_EVENT_FROM;
import static duke.testhelper.help.codeundertest.TextCommandUnderTest.DELIMITER_EVENT_TO;
import static duke.testhelper.help.codeundertest.TextCommandUnderTest.PROMPT_UNDER_TEST_ADD_DEADLINE;
import static duke.testhelper.help.codeundertest.TextCommandUnderTest.PROMPT_UNDER_TEST_ADD_EVENT;
import static duke.testhelper.help.codeundertest.TextCommandUnderTest.PROMPT_UNDER_TEST_ADD_TO_DO;
import static duke.testhelper.help.codeundertest.TextCommandUnderTest.PROMPT_UNDER_TEST_DELETE_TASK;
import static duke.testhelper.help.codeundertest.TextCommandUnderTest.PROMPT_UNDER_TEST_EXIT_LOOP;
import static duke.testhelper.help.codeundertest.TextCommandUnderTest.PROMPT_UNDER_TEST_FIND;
import static duke.testhelper.help.codeundertest.TextCommandUnderTest.PROMPT_UNDER_TEST_SAVE;
import static duke.testhelper.help.codeundertest.TextCommandUnderTest.generateTextCommandDeleteTaskByTaskId;
import static duke.testhelper.help.codeundertest.TextCommandUnderTest.generateTextCommandExit;
import static duke.testhelper.help.codeundertest.TextCommandUnderTest.generateTextCommandFindKeywordInDescription;
import static duke.testhelper.help.codeundertest.TextCommandUnderTest.generateTextCommandLineAddDeadline;
import static duke.testhelper.help.codeundertest.TextCommandUnderTest.generateTextCommandLineAddEvent;
import static duke.testhelper.help.codeundertest.TextCommandUnderTest.generateTextCommandLineAddToDo;
import static duke.testhelper.help.codeundertest.TextCommandUnderTest.generateTextCommandList;
import static duke.testhelper.help.codeundertest.TextCommandUnderTest.generateTextCommandSave;
import static duke.testhelper.help.codeundertest.TextCommandUnderTest.generateTextCommandSetCompleted;
import static duke.testhelper.help.config.DukeIoTestPath.getResourceTestFolder;
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
import duke.mock.mocktask.MockDeadline;
import duke.mock.mocktask.MockEvent;
import duke.mock.mocktask.MockTask;
import duke.mock.mocktask.MockToDo;
import duke.testhelper.TestStream;

public class SystemExportTest extends TestStream {

    /**
     * Execute add tasks commands and save as export1
     * import export1, save as export2
     * export1 == export2 ? pass
     */
    @Test
    public void idempotentExport() {
        String thisTestSign = "saveEventsToJsonTestFile";
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-hh-mm-ss"));
        String export1PathString = getResourceTestFolder() + "-added" + thisTestSign + date + ".json";
        String export2PathString = getResourceTestFolder() + "-load" + thisTestSign + date + ".json";
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
        String in1 = generateTextCommandSetCompleted(5);
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


    private void writeToFile(String pathString, String... data) throws Exception {
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
    public void generateExpectedTestFileInActualEnvironment() throws Exception {
        String importPathString = getDefaultTasksImportPathString();

        String loadInputPath =
            String.join(File.separator, new String[] {"src", "test", "resources", "linux-test", "input.txt"});
        String expectedOutputPath =
            String.join(File.separator, new String[] {"src", "test", "resources", "linux-test", "expected.txt"});

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
        String out4 = (getExpectedOutputAddedToDo(todoDesc, 0));

        String in1 = generateTextCommandLineAddDeadline(PROMPT_UNDER_TEST_ADD_DEADLINE, deadlineDesc,
            DELIMITER_DEADLINE_DEADLINE, deadlineBy);
        String out5 = (getExpectedOutputAddedDeadline(deadlineDesc, 1));

        String in2 =
            generateTextCommandLineAddEvent(PROMPT_UNDER_TEST_ADD_EVENT, eventDesc, DELIMITER_EVENT_FROM, eventFrom,
                DELIMITER_EVENT_TO, eventTo);
        String out6 = (getExpectedOutputAddedEvent(eventDesc, 2));

        String in3 = generateTextCommandList();

        MockTask[] allMockTasks = new MockTask[] {new MockToDo(todoDesc, 0, false),
            new MockDeadline(deadlineDesc, 1, false, parseStringAsLocalDateTime(deadlineBy)),
            new MockEvent(eventDesc, 2, false, parseStringAsLocalDateTime(eventFrom),
                parseStringAsLocalDateTime(eventTo))};
        String out7 = (getExpectedOutputList(getExpectedTaskList(allMockTasks)));

        String in4 = generateTextCommandDeleteTaskByTaskId(PROMPT_UNDER_TEST_DELETE_TASK, 0);
        String out8 = (getExpectedOutputCommandDeleted(0));

        String in5 = generateTextCommandList();

        MockTask[] remainingMockTasks =
            new MockTask[] {new MockDeadline(deadlineDesc, 1, false, parseStringAsLocalDateTime(deadlineBy)),
                new MockEvent(eventDesc, 2, false, parseStringAsLocalDateTime(eventFrom),
                    parseStringAsLocalDateTime(eventTo))};
        String out9 = (getExpectedOutputList(getExpectedTaskList(remainingMockTasks)));

        String in6 = generateTextCommandSetCompleted(2);
        String out10 = getExpectedOutputTaskSetCompleted(2);

        String keyword = "eventdesc";
        String in7 = generateTextCommandFindKeywordInDescription(PROMPT_UNDER_TEST_FIND, keyword);
        MockTask[] selectedMockTasks = new MockTask[] {
            new MockEvent(eventDesc, 2, true, parseStringAsLocalDateTime(eventFrom),
                parseStringAsLocalDateTime(eventTo))};
        String out11 = (getExpectedOutputListTasksWithKeywordDescription(getExpectedTaskList(selectedMockTasks), keyword));

        String in8 = generateTextCommandExit(PROMPT_UNDER_TEST_EXIT_LOOP);
        String out12 = (getExpectedOutputExitInputLoop());

        String out13 = (getExpectedOutputTerminate());

        writeToFile(loadInputPath, in0, in1, in2, in3, in4, in5, in6, in7, in8);
        writeToFile(expectedOutputPath, out0, out1, out2, out3, out4, out5, out6, out7, out8, out9, out10, out11, out12,
            out13);
    }
}
