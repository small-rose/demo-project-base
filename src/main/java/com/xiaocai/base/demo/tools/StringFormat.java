package com.xiaocai.base.demo.tools;

/**
 * @author Xiaocai.Zhang
 */
public class StringFormat {

    public static String formatLeftS(String str, int minLength) {
        String format = "%-" + (minLength < 1 ? 1 : minLength) + "s";
        return String.format(format, str);
    }

    public static String format0Right(long num, int minLength) {
        String format = "%0" + (minLength < 1 ? 1 : minLength) + "d";
        return String.format(format, num);
    }

    public static String format0Right(double d, int minLength, int precision) {
        String format = "%0" + (minLength < 1 ? 1 : minLength) + "." + (precision < 0 ? 0 : precision) + "f";
        return String.format(format, d);
    }
}
