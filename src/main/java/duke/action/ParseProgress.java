package duke.action;

/**
 * Handle progress related string
 * <p>
 * Splitting of string (describes the progress) loaded from progress file into arrays
 * String manipulation
 *
 * @author Kang Teng
 * @version 8.0
 * @since 2021-09-01
 */

public class ParseProgress {

    /**
     * Convert date from String to LocalDate
     *
     * @param progress String that is loaded from progress.txt
     * @return String[] String array that has been split based on \n
     */
    public static String[] splitProgressIntoSentence(String progress) {
        return progress.split("\\n", -1);
    }

    /**
     * Convert date from String to LocalDate
     *
     * @param sentence String that contains the task information
     * @return String[] String array that has been split based on |
     */
    public static String[] splitSentenceByBarSeparator(String sentence) {
        return sentence.split("\\|", -1);
    }
}
