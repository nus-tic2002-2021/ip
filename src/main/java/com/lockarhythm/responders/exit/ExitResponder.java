package com.lockarhythm.responders.exit;

import com.lockarhythm.responders.Response;
import com.lockarhythm.responders.QueryRespondable;

public class ExitResponder implements QueryRespondable {
    public Response respondTo(String query) {
        if (query.equals("bye")) {
            return new Response("Bye. Hope to see you again soon!", true);
        }
        return null;
    }
}
