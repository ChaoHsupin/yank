package site.yan.key;

/**
 * Create in 2019/11/21 14:16 by Zhao Xubin.
 */
public class Yank {

    /**
     * 命令启动入口
     * @param args
     */
    public static void main(String[] args) {
        // 解析命令
        Cmd.praseCmd(args);
        // 执行命令
        Execute.run();
    }
}
