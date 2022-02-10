package com.lockarhythm.ui;

import com.lockarhythm.query.Result;

/**
 * UI is a <i>port</i>. An example adapter is the TerminalUI.
 */
public interface UI {
  /**
   * nextLine returns the next line from the input.
   *
   * @return the next line as a string.
   */
  public String nextLine();

  /**
   * hasNext returns true if there are more line(s) to be consumed in the next call to nextLine. Returns false otherwise.
   *
   * @return true if a new line exists. False otherwise.
   */
  public boolean hasNext();

  /**
   * print should print the given strings to the output.
   *
   * @param strings a list of strings to be printed
   */
  public void print(String... strings);

  /**
   * print is a helper function to print Results to output.
   *
   * @param res a task result to print.
   */
  public void print(Result res);
}
