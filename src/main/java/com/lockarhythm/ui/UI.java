package com.lockarhythm.ui;

import com.lockarhythm.query.Result;

public interface UI {
  public String nextLine();

  public boolean hasNext();

  public void print(String... strings);

  public void print(Result res);
}
