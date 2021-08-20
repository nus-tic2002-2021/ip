package com.lockarhythm.responders.echo;

import com.lockarhythm.responders.Response;
import com.lockarhythm.responders.QueryRespondable;

public class EchoResponder implements QueryRespondable {
    public Response respondTo(String query) {
        return new Response(query, false);
    }
}
