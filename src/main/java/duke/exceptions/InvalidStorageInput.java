package duke.exceptions;

/**
 * represent exceptions related to data in the txt file.
 */
public class InvalidStorageInput extends Exception {
    public InvalidStorageInput(String message) {
        super(message);
    }
}
