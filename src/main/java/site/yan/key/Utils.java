package site.yan.key;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
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

    public static boolean contains(String text, final String regex) {
        if (text.startsWith(regex) || text.endsWith(regex)) return true;
        if (text.split(regex).length > 1) return true;
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        return false;
    }

    /**
     * 操控粘贴板
     * @param text
     */
    public static void setClipboardString(String text) {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable trans = new StringSelection(text);
        clipboard.setContents(trans, null);
    }
}
