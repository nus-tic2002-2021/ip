package com.tic2002.task;

public class Priority extends Task {
    private enum validPriority {NONE(0), LOW(1), MEDIUM(2), HIGH(3), OVERDUE(4);
        private int value;
        validPriority(int value) {
            this.value = value;
        }
    }

    /**
     * Check if user input is within 1 to 3
     * @param input user input
     * @return true and false
     */
    public static boolean isValidPriority(int input) {
        if(input >= 0 && input <=3){
            return true;
        }
        return false;
    }

    public static String printPriority(int value) {
        switch(value) {
        case 1:
            return "[LOW]";
        case 2:
            return "[MED]";
        case 3:
            return "[HIGH]";
        case 4:
            return "[OVERDUE]";
        }
        return "";
    }
}
