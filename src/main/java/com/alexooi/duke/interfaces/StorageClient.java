package com.alexooi.duke.interfaces;

public interface StorageClient<T> {
    T load();
    void save(String input, boolean isAppend);
}
