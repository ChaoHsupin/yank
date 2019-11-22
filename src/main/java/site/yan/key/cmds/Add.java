package site.yan.key.cmds;

import site.yan.key.Utils;

import static site.yan.key.securtiy.Secrey.*;

/**
 * Create in 2019/11/22 10:14 by Zhao Xubin.
 */
public class Add implements CmdBase {
    @Override
    public void execute() {
        secretVerify();
        Utils.tell("输入标题：");
        String title = Utils.input();
        Utils.tell("输入用户名：");
        String username = Utils.input();
        Utils.tell("输入密钥：");
        String password = Utils.input();
        Utils.tell("备注：");
        String memo = Utils.input();
        String info = title + "\n" + username + "\n" + password + "\n" + memo + "\n";
        if (addToFile(title, info))
            Utils.tell("保存成功", true, true);
        else
            Utils.tell("保存异常", false, true);
    }
}
