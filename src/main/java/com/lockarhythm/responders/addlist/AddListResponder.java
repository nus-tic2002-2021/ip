package com.lockarhythm.responders.addlist;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.lockarhythm.responders.Response;
import com.lockarhythm.responders.QueryRespondable;

import com.lockarhythm.tasks.TaskList;
import com.lockarhythm.tasks.Task;

public class AddListResponder implements QueryRespondable {
    private static String doneCommandPrefix = "done ";
    private TaskList list;
    Pattern pattern = Pattern.compile(doneCommandPrefix+"(\\d+)");

    public AddListResponder() {
        list = new TaskList();
    }

    private boolean isDoneCommand(Matcher matcher) {
        return matcher.find();
    }

    private Response handleDoneCommand(Matcher matcher) {
        int i = Integer.parseInt(matcher.group(1));
        if (i > 0 && i <= list.size()) {
            Task task = list.markAsDone(i - 1);
            return new Response("Nice! I've marked this task as done:\n\t" + task.toString(), false);
        }
        return new Response(String.format("Item %d is not on the list. I cannot mark it as done!", i), false);
    }

    public Response respondTo(String query) {
        if (query.equals("list")) {
            return new Response("Here are the tasks in your list:\n"+list.toString(), false);
        }
        Matcher matcher = pattern.matcher(query);
        if (isDoneCommand(matcher)) {
            return handleDoneCommand(matcher);
        }
        // by default, adds the given query.
        list.add(query);
        return new Response("added: " + query, false);
    }
}
