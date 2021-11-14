package dukeMain.common.month;
/**
 * Represents all the months in both the full form and short form in this enum class.
 * A constructor is included in this class to allow easier conversion of the months
 * into Integer. By mapping all the months to a integer value ranging from 1 to 12.
 * e.g <code>JANUARY(1)</code>
 */
public enum monthEnum {
    JANUARY(1), FEBRUARY(2), MARCH(3),
    APRIL(4),MAY(5),JUNE(6),
    JULY(7), AUGUST(8), SEPTEMBER(9),
    OCTOBER(10),  NOVEMBER(11), DECEMBER(12),
    JAN(1),FEB(2),MAR(3),
    APR(4),JUN(6),
    JUL(7),AUG(8),SEP(9),
    OCT(10),NOV(11),DEC(12);

    private final int monthInt;

    /** Constructor monthEnum.
     * To map enum of month to an integer value
     *
     * @params int monthInt
     * */

    monthEnum(int monthInt) {
        this.monthInt = monthInt;
    }
    /** Return the integer form of the month based on the enum indicated.
     *
     * @return int monthInt
     * */
    public int getMonthInt() {
        return this.monthInt;
    }
}
