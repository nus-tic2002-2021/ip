package com.lockarhythm.ui;

import com.lockarhythm.query.Result;
import java.util.Arrays;
import java.util.Scanner;

/**
 * TerminalUI is an <i>adapter</i> of UI <i>port</i>.
 */
public final class TerminalUI implements UI {
  private static Scanner in = new Scanner(System.in);

  /**
   * nextLine reads the next line from standard input.
   *
   * @return the next line as a string
   */
  public String nextLine() {
    return in.nextLine();
  }

  /**
   * hasNext returns true if another line is available in standard input.
   *
   * @return true if another line is available. False otherwise.
   */
  public boolean hasNext() {
    return in.hasNext();
  }

  /**
   * print formats the given strings and prints to standard output.
   *
   * @param strings the list of strings to be printed
   */
  public void print(String... strings) {
    System.out.println("\t____________________________________________________________");
    for (String s : strings) {
      s = Arrays.stream(s.split("\n")).map(x -> "\t" + x).reduce("", (x, y) -> x + y + "\n");
      System.out.println(s);
    }
    System.out.println("\t____________________________________________________________\n");
  }

  /**
   * print formats the given Result and prints to standard output. This is a helper function to handle the common operation of printing results from query interpreters.
   *
   * @param res the task Result to print.
   */
  public void print(Result res) {
    assert res != null;
    print(res.getText());
  }
}
