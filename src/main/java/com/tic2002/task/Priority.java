package com.tic2002.task;

public class Priority extends Task {
    private enum validPriority {LOW, MEDIUM, HIGH, OVERDUE; }

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
            return "[" + validPriority.LOW + "]";
        case 2:
            return "[" + validPriority.MEDIUM + "]";
        case 3:
            return "[" + validPriority.HIGH + "]";
        case 4:
            return "[" + validPriority.OVERDUE + "]";
        }
        return "";
    }
}
