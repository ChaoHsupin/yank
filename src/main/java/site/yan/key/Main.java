package site.yan.key;

/**
 * Create in 2019/11/21 14:16 by Zhao Xubin.
 */

import java.io.*;
import java.util.*;

/**
 * %cd%为当前目录，而%~dp0为脚本自身目录
 */
public class Main {

    public static String BASE_PATH;
    public static String SECRET_FILE;
    public static String SECRET_PIN;
    public static String CMD;

    /**
     * add
     * show
     */
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("命令不全");
            System.exit(0);
        }
        BASE_PATH = args[0];
        SECRET_FILE = BASE_PATH + "\\.key";
        SECRET_PIN = BASE_PATH + "\\.pin";
        checkHasPin();
        CMD = args[1].toUpperCase();
        Scanner scanner = new Scanner(System.in);
        if (CMD.equals("ADD")) {
            System.out.println("输入标题：");
            String title = scanner.next();
            System.out.println("输入用户名：");
            String username = scanner.next();
            System.out.println("输入密钥：");
            String password = scanner.next();
            System.out.println("备注：");
            String memo = scanner.next();
            String info = title + "\n" + username + "\n" + password + "\n" + memo + "\n";
            if (addToFile(title, info))
                System.out.println("保存成功");
            else
                System.out.println("保存异常");
        } else if (CMD.equals("SHOW")) {
            System.out.println("请输入PIN码（4位数字）");
            String pin = scanner.next();
            if (!pin.matches("\\d{4}")) {
                System.out.println("PIN码格式错误");
                System.exit(0);
            }
            if (!verify(pin)) {
                System.out.println("PIN码错误");
                System.exit(0);
            }
            System.out.println("输入该密钥模糊标题");
            String title = scanner.next();
            parse(title);

        } else {
            System.out.println("命令输入错误");
        }

    }

    public static boolean addToFile(String title, String info) {
        try {
            File file = new File(SECRET_FILE);
            if (!file.exists()) file.createNewFile();
            FileWriter fileWriter = new FileWriter(file, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println(Secrey.secret(title) + " " + Secrey.secret(info));
            fileWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * PIN码验证
     *
     * @return
     */
    public static boolean verify(String pin) {
        File file = new File(SECRET_PIN);
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);
            String re = reader.readLine();
            return (Secrey.secret(pin).equals(re));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String parse(String title) {

        Map<String, String> map = new HashMap<>();
        File file = new File(SECRET_FILE);
        try {
            if(!file.exists())file.createNewFile();
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String temp;
            while (Objects.nonNull(temp = bufferedReader.readLine())) {
                String[] line = temp.split(" ");
                map.put(Secrey.secret(line[0]), Secrey.secret(line[1]));
            }
            Set<String> set = map.keySet();
            Iterator iterator = set.iterator();
            boolean has=false;
            while (iterator.hasNext()) {
                String head = (String) iterator.next();
                if (contains(head, title))
                {
                    has=true;
                    String[] info=map.get(head).split("\\n");
                    System.out.println("--------------------");
                    System.out.println("   title:"+info[0]);
                    System.out.println("username:"+info[1]);
                    System.out.println("password:"+info[2]);
                    System.out.println("    memo:"+info[3]);
                    System.out.println("--------------------");
                }
            }
            if(!has)System.out.println("未找到相关密钥");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void checkHasPin() {
        File file = new File(SECRET_PIN);
        if (!file.exists()) {
            System.out.println("欢迎使用由言言技术中心研发yank密钥管理工具\n" +
                    "首次使用请输入初始4位PIN码：");
            String pin = new Scanner(System.in).next();
            if (!pin.matches("\\d{4}")) {
                System.out.println("PIN码格式错误");
                System.exit(0);
            }
            try {
                file.createNewFile();
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(Secrey.secret(pin));
                fileWriter.close();
                System.out.println("PIN码设置成功");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static boolean contains(String text, String regex) {
        if (text.startsWith(regex) || text.endsWith(regex)) return true;
        if (text.split(regex).length > 1) return true;
        return false;
    }
}
