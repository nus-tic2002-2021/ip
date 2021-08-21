package com.lockarhythm.responders.addlist;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.lockarhythm.responders.Response;
import com.lockarhythm.responders.QueryRespondable;

import com.lockarhythm.tasks.TaskList;

public class AddListResponder implements QueryRespondable {
    private TaskList list;

    public AddListResponder(TaskList list) {
        this.list = list;
    }

    public Response respondTo(String query) {
        if (query.equals("list")) {
            return new Response("Here are the tasks in your list:\n"+list.toString(), false);
        }
        // by default, adds the given query.
        list.add(query);
        return new Response("added: " + query, false);
    }
}
