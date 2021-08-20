package com.lockarhythm.responders;

public class Response {
  private String text;
  private boolean shouldExit;

  public Response(String text, boolean shouldExit) {
    this.text = text;
    this.shouldExit = shouldExit;
  }

  public String getText() {
    return text;
  }

  public boolean shouldExit() {
    return shouldExit;
  }
}
