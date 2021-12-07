package set.up.util;

import java.util.Scanner;

public class Input {
    public static final Scanner SCANNER_STRING = new Scanner(System.in);

    public static String getString() {
        return getString("");
    }

    public static String getString(String str) {
        Print.print(str);
        return SCANNER_STRING.nextLine();
    }
}
