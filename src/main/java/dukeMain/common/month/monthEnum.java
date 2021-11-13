package dukeMain.common.month;

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

    monthEnum(int monthInt) {
        this.monthInt = monthInt;
    }

    public int getMonthInt() {
        return this.monthInt;
    }
}
