package set.up.util;

import static set.up.util.Color.BLUE;
import static set.up.util.Color.RESET;

public class Print {
    public static void print(Object obj) {
        print(BLUE, obj);
    }

    public static void print(String color, Object obj) {
        System.out.print(color + obj + RESET);
    }

    public static void println(Object obj) {
        println(BLUE, obj);
    }

    public static void println(String color, Object obj) {
        System.out.println(color + obj + RESET);
    }
}
