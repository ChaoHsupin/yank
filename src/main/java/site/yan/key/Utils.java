package site.yan.key;

import java.util.Scanner;

/**
 * Create in 2019/11/22 9:50 by Zhao Xubin.
 */
public class Utils {

    private static Scanner scanner = new Scanner(System.in);

    public static void tell(String text, boolean emotion, boolean stop) {
        System.out.println(text + " " + (emotion ? ":)" : ":("));
        if (stop) System.exit(0);
    }
    public static void tell(String text) {
        System.out.print(text);
    }

    public static String input() {
        String input = scanner.next();
        return input;
    }

    public static boolean contains(String text, String regex) {
        if (text.startsWith(regex) || text.endsWith(regex)) return true;
        if (text.split(regex).length > 1) return true;
        return false;
    }
}
