package duke.exceptions;

/**
 * Represent exceptions occur when writing/reading from the storage file.
 */
public class FileProcessError extends Exception{
    public FileProcessError(String message) {
        super(message);
    }
}
