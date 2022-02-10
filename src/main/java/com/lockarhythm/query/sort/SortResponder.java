package com.lockarhythm.query.sort;

import com.lockarhythm.query.RegexQueryInterpreter;
import com.lockarhythm.query.Result;
import com.lockarhythm.tasks.TaskList;

public class SortResponder extends RegexQueryInterpreter {
  private static final boolean IS_ASCENDING_DEFAULT = true;

  private TaskList list;

  public SortResponder(TaskList list) {
    this.list = list;
  }

  protected String commandRegex() {
    return "^sort by (date|done)( desc| asc)?$";
  }

  public Result onMatch(String[] groups) {
    String sortKey = groups[1];
    boolean isAscending = parseIsAscending(groups[2]);

    TaskList sortedList = sortBy(sortKey, isAscending);

    return new Result(
        String.format(
            "Ok! I have sorted the tasks by %s in %s order!\n%s",
            sortKey, isAscending ? "ascending" : "descending", sortedList));
  }

  private boolean parseIsAscending(String value) {
    if (value == null) {
      return IS_ASCENDING_DEFAULT;
    }
    String s = value.trim();
    if (s.equals("desc")) {
      return false;
    } else if (s.equals("asc")) {
      return true;
    } else {
      return IS_ASCENDING_DEFAULT;
    }
  }

  /** Sorts by the `sortKey` in the order given by `isAscending`. */
  private TaskList sortBy(String sortKey, boolean isAscending) {
    switch (sortKey) {
      case "done":
        return list.sortByDone(isAscending);
      case "date":
        return list.sortByTaskDate(isAscending);
      default:
        assert false : "sortKey is not a valid value. Check command regex.";
    }
    ;
    return list.sortByTaskDate(isAscending);
  }
}
