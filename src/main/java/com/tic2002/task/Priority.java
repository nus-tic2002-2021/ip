package com.tic2002.task;

public class Priority extends Task {
    private enum validPriority {NONE(0), LOW(1), MEDIUM(2), HIGH(3);
        private int value;
        validPriority(int value) {
            this.value = value;
        }
    }

    public static boolean isValidPriority(int input) {
        for (validPriority p : validPriority.values()) {
            if (p.value == input) {
                return true;
            }
        }
        return false;
    }
    public static String printPriority(int value) {
        switch(value){
        case 1:
            return "[LOW]";
        case 2:
            return "[MED]";
        case 3:
            return "[HIGH]";
        }
        return "";
    }
}
