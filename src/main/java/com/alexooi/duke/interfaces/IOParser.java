package com.alexooi.duke.interfaces;

import com.alexooi.duke.exceptions.InvalidCommandFormatException;

public interface IOParser<T, S> {
    T readInput(S sc, Iterable<T> it) throws Exception, InvalidCommandFormatException;
}
