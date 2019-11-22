package site.yan.key;

import site.yan.key.securtiy.Secrey;

/**
 * Create in 2019/11/22 9:31 by Zhao Xubin.
 */
public class Cmd {

    public static String BASE_PATH;
    public static String SECRET_FILE;
    public static String SECRET_PIN;
    public static String CMD;
    public static String arg;

    public static void praseCmd(String[] args){
        if (args.length < 2)
            Utils.tell("命令不全\n",false,true);
        BASE_PATH = args[0];
        SECRET_FILE = BASE_PATH + "\\.key";
        SECRET_PIN = BASE_PATH + "\\.pin";
        Secrey.checkHasPin();
        CMD = args[1].toUpperCase();
        if(args.length==2){
            arg="#";
        }else if(args.length==3){
            arg=args[2];
        }else {
            Utils.tell("命令格式错误\n",false,true);
        }

    }

}
