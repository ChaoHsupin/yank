package site.yan.key.cmds;

import site.yan.key.Utils;
import site.yan.key.securtiy.Secrey;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static site.yan.key.Cmd.SECRET_FILE;
import static site.yan.key.Cmd.arg;
import static site.yan.key.Utils.contains;
import static site.yan.key.securtiy.Secrey.secretVerify;

/**
 * Create in 2019/11/22 10:16 by Zhao Xubin.
 */
public class Show implements CmdBase {
    @Override
    public void execute() {
        secretVerify();
        if (!arg.equals("#"))
            parse(arg);
        Utils.tell("输入该密钥模糊标题:");
        String title = Utils.input();
        parse(title);
    }

    private static void parse(String regex) {

        if (".".equals(regex)) regex = "";

        Map<String, String> map = new HashMap<>();
        File file = new File(SECRET_FILE);
        try {
            if (!file.exists()) file.createNewFile();
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String temp;
            while (Objects.nonNull(temp = bufferedReader.readLine())) {
                String[] line = temp.split(" ");
                map.put(Secrey.secret(line[0], false), Secrey.secret(line[1], false));
            }
            Set<String> set = map.keySet();
            List<String> list = new ArrayList<>(set);
            List<String> passwordList = new ArrayList<>();
            int count = 0;
            for (String head : list) {
                if (contains(head, regex)) {
                    String[] info = map.get(head).split("\\n");
                    passwordList.add(info[2]);
                    System.out.println();
                    System.out.println(++count);
                    System.out.println("------------------------");
                    System.out.println("   title:" + info[0]);
                    System.out.println("username:" + info[1]);
                    System.out.println("password:" + info[2]);
                    System.out.println("    memo:" + info[3]);
                    System.out.println("------------------------");
                }
            }
            System.out.println();
            if (list.size() == 0) System.out.println("未找到相关密钥 :|");
            else if (list.size() == 1) {
                Utils.setClipboardString(passwordList.get(0));
                Utils.tell("密钥已拷贝到粘贴板", true, true);
            } else {
                Utils.tell("请输入序号，密钥将被拷贝到粘贴板：");
                String order = Utils.input();
                if (!order.matches("\\d+")) {
                    Utils.tell("编号错误", false, true);
                } else {
                    int orderNum = Integer.valueOf(order);
                    if (orderNum > passwordList.size()) {
                        Utils.tell("编号超出范围", false, true);
                    } else {
                        Utils.setClipboardString(passwordList.get(orderNum - 1));

                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }
}
