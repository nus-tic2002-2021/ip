package interfaces;

public interface Promptable<T extends Printable> {
    public String start();
    public String add(T printable);
    public String list(Iterable<T> inputs);
    public String list(T[] inputs);
    public String exit();
}
