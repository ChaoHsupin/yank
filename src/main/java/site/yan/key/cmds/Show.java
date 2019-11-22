package site.yan.key.cmds;

import site.yan.key.Secrey;
import site.yan.key.Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static site.yan.key.Cmd.SECRET_FILE;
import static site.yan.key.Cmd.arg;
import static site.yan.key.Secrey.secretVerify;
import static site.yan.key.Utils.contains;

/**
 * Create in 2019/11/22 10:16 by Zhao Xubin.
 */
public class Show implements Run {
    @Override
    public void execute() {
        secretVerify();
        if(!arg.equals("#"))
            parse(arg);
        Utils.tell("输入该密钥模糊标题:");
        String title = Utils.input();
        parse(title);
    }

    private static void parse(String regex) {

        if ("*".equals(regex)) regex = ".";

        Map<String, String> map = new HashMap<>();
        File file = new File(SECRET_FILE);
        try {
            if (!file.exists()) file.createNewFile();
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String temp;
            while (Objects.nonNull(temp = bufferedReader.readLine())) {
                String[] line = temp.split(" ");
                map.put(Secrey.secret(line[0]), Secrey.secret(line[1]));
            }
            Set<String> set = map.keySet();
            Iterator iterator = set.iterator();
            boolean has = false;
            while (iterator.hasNext()) {
                String head = (String) iterator.next();
                if (contains(head, regex)) {
                    has = true;
                    String[] info = map.get(head).split("\\n");
                    System.out.println("--------------------");
                    System.out.println("   title:" + info[0]);
                    System.out.println("username:" + info[1]);
                    System.out.println("password:" + info[2]);
                    System.out.println("    memo:" + info[3]);
                    System.out.println("--------------------");
                }
            }
            if (!has) System.out.println("未找到相关密钥 :|");

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            System.exit(0);
        }
    }
}
