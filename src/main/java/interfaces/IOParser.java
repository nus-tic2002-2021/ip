package interfaces;

public interface IOParser<T, S> {
    T readInput(S sc) throws Exception;
}
