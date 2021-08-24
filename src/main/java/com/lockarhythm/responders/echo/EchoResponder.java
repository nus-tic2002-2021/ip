package com.lockarhythm.responders.echo;

import com.lockarhythm.responders.QueryRespondable;
import com.lockarhythm.responders.Response;

public class EchoResponder implements QueryRespondable {
  public Response respondTo(String query) {
    return new Response(query, false);
  }
}
