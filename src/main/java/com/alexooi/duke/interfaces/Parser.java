package com.alexooi.duke.interfaces;

public interface Parser<S, T> {
    T parseInput(S input) throws Exception;
    boolean isValidInput(S input);
}