package duke.command.commandfactory;

import static duke.dukeutility.definition.CommandPromptsAndOptions.ADD_DEADLINE_DEADLINE_DELIMITER;
import static duke.dukeutility.definition.CommandPromptsAndOptions.ADD_EVENT_SCHEDULE_DELIMITER;
import static duke.dukeutility.definition.CommandPromptsAndOptions.PROMPT_ADD_DEADLINE;
import static duke.dukeutility.definition.CommandPromptsAndOptions.PROMPT_ADD_EVENT;
import static duke.dukeutility.definition.CommandPromptsAndOptions.PROMPT_ADD_TODO;
import static duke.dukeutility.definition.CommandPromptsAndOptions.PROMPT_DELETE_TASK;
import static duke.dukeutility.definition.CommandPromptsAndOptions.PROMPT_EXIT_LOOP;
import static duke.dukeutility.definition.CommandPromptsAndOptions.PROMPT_FIND_BY_KEYWORD_DESCRIPTION;
import static duke.dukeutility.definition.CommandPromptsAndOptions.PROMPT_UPDATE_DONE;
import static duke.dukeutility.parser.DateParser.parseStringAsLocalDateTime;
import static duke.dukeutility.validator.TextCommandValidator.isRequestAddDeadline;
import static duke.dukeutility.validator.TextCommandValidator.isRequestAddEvent;
import static duke.dukeutility.validator.TextCommandValidator.isRequestAddToDo;
import static duke.dukeutility.validator.TextCommandValidator.isRequestDeleteTask;
import static duke.dukeutility.validator.TextCommandValidator.isRequestExitLoop;
import static duke.dukeutility.validator.TextCommandValidator.isRequestFind;
import static duke.dukeutility.validator.TextCommandValidator.isRequestList;
import static duke.dukeutility.validator.TextCommandValidator.isRequestMarkTaskAsDone;
import static duke.dukeutility.validator.TextCommandValidator.isRequestSave;
import java.time.LocalDateTime;
import duke.FileResourceManager;
import duke.TaskManager;
import duke.command.Command;
import duke.command.errorcommand.CommandExecutionError;
import duke.command.errorcommand.CommandInvalidRequestParameters;
import duke.command.errorcommand.CommandInvalidTextCommandSyntax;
import duke.command.errorcommand.CommandTaskNotFound;
import duke.command.errorcommand.CommandUnknownRequest;
import duke.command.systemcommand.CommandExitLoop;
import duke.command.taskcommand.taskquery.CommandListAll;
import duke.command.taskcommand.taskquery.CommandListTasksWithKeyword;
import duke.command.taskcommand.taskadd.CommandAddNewDeadline;
import duke.command.taskcommand.taskadd.CommandAddNewEvent;
import duke.command.taskcommand.taskadd.CommandAddNewToDo;
import duke.command.taskcommand.taskupdate.CommandDeleteTask;
import duke.command.taskcommand.taskupdate.CommandMarkTaskAsDone;
import duke.dukeexception.DukeInvalidSyntaxException;


public class UiCommandFactory extends CommandFactory {
    protected Command executeCommandExitLoop() {
        return new CommandExitLoop(PROMPT_EXIT_LOOP);
    }

    public Command executeTextCommand(String text, TaskManager taskManager, FileResourceManager frm) {
        try {
            if (isRequestExitLoop(text)) {
                return this.executeCommandExitLoop();
            } else if (isRequestList(text)) {
                return new CommandListAll(taskManager);
            } else if (isRequestMarkTaskAsDone(text)) {
                return this.executeCommandMarkTaskAsDone(text, taskManager);
            } else if (isRequestAddToDo(text)) {
                return this.executeCommandAddToDo(text, taskManager);
            } else if (isRequestAddDeadline(text)) {
                return this.executeCommandAddDeadline(text, taskManager);
            } else if (isRequestAddEvent(text)) {
                return this.executeCommandAddEvent(text, taskManager);
            } else if (isRequestDeleteTask(text)) {
                return this.executeCommandDeleteTask(text, taskManager);
            } else if (isRequestSave(text)) {
                return frm.executeCommandSave(taskManager);
            } else if (isRequestFind(text)) {
                return this.executeCommandFindByKeywordInDescription(text, taskManager);
            } else {
                return new CommandUnknownRequest(text);
            }
        } catch (Exception e) {
            return new CommandExecutionError(e, "command execution @ cli");
        }
    }

    protected Command executeCommandAddToDo(String text, TaskManager taskManager) {
        int minDescLength = 0;
        String taskDescription = text.replaceFirst(PROMPT_ADD_TODO, "");
        if (taskDescription.length() <= minDescLength) {
            return new CommandInvalidRequestParameters("ToDo description is too short");
        }
        return new CommandAddNewToDo(taskManager, taskDescription);
    }

    protected Command executeCommandAddDeadline(String text, TaskManager taskManager) {

        String argLine;
        String[] argList;
        String taskDescription;
        LocalDateTime deadline;
        try {
            argLine = text.replaceFirst(PROMPT_ADD_DEADLINE, "");
            String addDeadlineStringDelimiter = ADD_DEADLINE_DEADLINE_DELIMITER;
            argList = argLine.split(addDeadlineStringDelimiter);
            int expectedArgsLength = 2;
            if (argList.length != expectedArgsLength) {
                throw new DukeInvalidSyntaxException(
                    "Expected " + expectedArgsLength + " arguments delimited by \"" + addDeadlineStringDelimiter +
                        "\"");
            }
            taskDescription = argList[0];
            deadline = parseStringAsLocalDateTime(argList[1]);
        } catch (DukeInvalidSyntaxException e) {
            return new CommandInvalidTextCommandSyntax(e.getMessage());
        } catch (Exception e) {
            return new CommandExecutionError(e, "Unknown Error");
        }
        return new CommandAddNewDeadline(taskManager, taskDescription, deadline);
    }

    protected Command executeCommandAddEvent(String text, TaskManager taskManager) {

        String argLine;
        String[] argList;
        String[] scheduleOptionList;
        String taskDescription;
        LocalDateTime from;
        LocalDateTime to;
        try {
            argLine = text.replaceFirst(PROMPT_ADD_EVENT, "");
            argList = argLine.split(ADD_EVENT_SCHEDULE_DELIMITER);
            if (argList.length != 2) {
                throw new Exception("Request line for adding event does not conform to syntax.");
            }
            taskDescription = argList[0];
            scheduleOptionList = argList[1].split("-");
            if (scheduleOptionList.length != 2) {
                throw new Exception("Request line for adding event does not conform to syntax.");
            }

            from = parseStringAsLocalDateTime(scheduleOptionList[0]);

            to = parseStringAsLocalDateTime(scheduleOptionList[1]);
        } catch (Exception e) {
            return new CommandInvalidRequestParameters(e.toString());
        }

        return new CommandAddNewEvent(taskManager, taskDescription, from, to);
    }


    protected Command executeCommandMarkTaskAsDone(String text, TaskManager taskManager) {
        String argLine;
        String[] argList;
        Integer taskId;
        try {
            argLine = text.replaceFirst(PROMPT_UPDATE_DONE, "");
            argList = argLine.split(" ");
            if (argList.length != 1) {
                throw new Exception("Invalid syntax.");
            }
            taskId = Integer.parseInt(argList[0]);
            if (!taskManager.containsTaskId(taskId)) {
                return new CommandTaskNotFound(argList[0]);
            }
        } catch (Exception e) {
            return new CommandInvalidRequestParameters(e.toString());
        }
        return new CommandMarkTaskAsDone(taskManager, taskId);
    }

    protected Command executeCommandDeleteTask(String text, TaskManager taskManager) {
        String argLine;
        String[] argList;
        Integer taskId;
        try {
            argLine = text.replaceFirst(PROMPT_DELETE_TASK, "");
            argList = argLine.split(" ");
            if (argList.length != 1) {
                throw new Exception("Invalid syntax.");
            }
            taskId = Integer.parseInt(argList[0]);
            if (!taskManager.containsTaskId(taskId)) {
                return new CommandTaskNotFound(argList[0]);
            }
        } catch (Exception e) {
            return new CommandInvalidRequestParameters(e.toString());
        }
        return new CommandDeleteTask(taskManager, taskId);
    }

    protected Command executeCommandFindByKeywordInDescription(String text, TaskManager taskManager) {
        String argLine;
        String[] argList;
        String keyword;
        try {
            argLine = text.replaceFirst(PROMPT_FIND_BY_KEYWORD_DESCRIPTION, "");
            argList = argLine.split(" ");
            if (argList.length != 1) {
                throw new Exception("Invalid syntax. Keyword should not have spacing.");
            }
            keyword = argList[0];
        } catch (Exception e) {
            return new CommandInvalidRequestParameters(e.toString());
        }
        return new CommandListTasksWithKeyword(taskManager, keyword);
    }
}