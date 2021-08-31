package com.lockarhythm.query;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class RegexQueryInterpreter implements QueryInterpreter {
  private Pattern pattern;

  public RegexQueryInterpreter() {
    pattern = Pattern.compile(commandRegex());
  }

  public Result interpret(String query) {
    Matcher matcher = pattern.matcher(query);
    if (matcher.find()) {
      return onMatch(toGroupStrings(matcher));
    }
    return null;
  }

  private String[] toGroupStrings(Matcher matcher) {
    String[] groups = new String[matcher.groupCount()+1];
    for (int i = 0; i <= matcher.groupCount(); i++) {
      groups[i] = matcher.group(i);
    }
    return groups;
  }

  // onMatch is called when regexp pattern matches. The regexp capturing groups is passed as arguments.
  public abstract Result onMatch(String[] groups);

  protected abstract String commandRegex();
}
