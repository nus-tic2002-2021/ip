package com.alexooi.duke.interfaces;

public interface IOParser<T, S> {
    T readInput(S sc, Iterable<T> it) throws Exception;
}
