package duke.dukeutility.validator;


public class StringValidator {

    public static Boolean isSubstring(String sentence, String keyword) {

        String[] words = sentence.split("\\s+");
        for (String word : words) {
            if (word.equals(keyword)) {
                return true;
            }
        }
        return false;

    }
}
