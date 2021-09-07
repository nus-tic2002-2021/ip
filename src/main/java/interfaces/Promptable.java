package interfaces;

import classes.Task;

public interface Promptable<T extends Task> {
    public String start();
    public String add(T printable);
    public String done(T printable);
    public String list(Iterable<T> inputs);
    public String list(T[] inputs);
    public String exit();
}
