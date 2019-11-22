package site.yan.key.securtiy;


import site.yan.key.Utils;

import java.io.*;
import java.util.Scanner;

import static site.yan.key.Cmd.SECRET_FILE;
import static site.yan.key.Cmd.SECRET_PIN;
import static site.yan.key.securtiy.MD5Core.getMD5;

/**
 * Create in 2019/11/21 15:52 by Zhao Xubin.
 */
public class Secrey {

    public static void checkHasPin() {
        File file = new File(SECRET_PIN);
        if (!file.exists()) {
            System.out.print("欢迎使用由言言技术中心研发yank密钥管理工具\n" +
                    "首次使用请设置4位PIN码：");
            String pin = new Scanner(System.in).next();
            if (!pin.matches("\\d{4}")) {
                System.out.println("PIN码格式错误 ：(");
                System.exit(0);
            }
            try {
                file.createNewFile();
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(getMD5(pin));
                fileWriter.close();
                System.out.println("PIN码设置成功 :)");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void secretVerify() {
        Utils.tell("请输入PIN码（4位数字）:");
        String pin = Utils.input();
        if (!pin.matches("\\d{4}"))
            Utils.tell("PIN码格式错误", false, true);
        if (!verifyPin(pin)) {
            Utils.tell("PIN码错误", false, true);
        }
    }

    private static boolean verifyPin(String pin) {
        File file = new File(SECRET_PIN);
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);
            String read = reader.readLine();
            String md5 = getMD5(pin);
            AesCore.setAesKey(md5);
            return (md5.equals(read));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean addToFile(String title, String info) {
        try {
            File file = new File(SECRET_FILE);
            if (!file.exists()) file.createNewFile();
            FileWriter fileWriter = new FileWriter(file, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println(Secrey.secret(title, true) + " " + Secrey.secret(info, true));
            fileWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String secret(String text, boolean encode) {
        return encode ? AesCore.encrypt(text) : AesCore.decrypt(text);
    }
}
