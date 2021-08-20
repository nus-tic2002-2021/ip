package com.lockarhythm.responders.addlist;

import com.lockarhythm.responders.Response;
import com.lockarhythm.responders.QueryRespondable;

import java.util.ArrayList;

public class AddListResponder implements QueryRespondable {
    private ArrayList<String> list;

    public AddListResponder() {
        list = new ArrayList<String>();
    }

    public Response respondTo(String query) {
        if (query.equals("list")) {
            StringBuilder s = new StringBuilder();
            int i = 1;
            for (i = 0; i < list.size(); i++) {
                s.append(i+1);
                s.append(". ");
                s.append(list.get(i));
                s.append("\n");
            }
            return new Response(s.toString(), false);
        }
        // by default, adds the given query.
        list.add(query);
        return new Response("added: " + query, false);
    }
}
