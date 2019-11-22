package site.yan.key;

import site.yan.key.cmds.Add;
import site.yan.key.cmds.CmdBase;
import site.yan.key.cmds.Show;

import static site.yan.key.Cmd.CMD;

/**
 * Create in 2019/11/22 10:08 by Zhao Xubin.
 */
public class Execute {
    public static void run() {
        CmdBase runnable = new Add();
        switch (CMD) {
            case "ADD":
                break;
            case "SHOW":
                runnable = new Show();
                break;
            default:
                Utils.tell("命令格式错误", false, true);
        }
        runnable.execute();
    }


}
