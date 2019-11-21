package site.yan.key;


import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.Key;
import java.util.Scanner;

/**
 * Create in 2019/11/21 15:52 by Zhao Xubin.
 */
public class Secrey {
    public static String secret(String text) {

        //讲获取的字符串转成字符数组
        char[] c = text.toCharArray();
        //使用for循环给字符数组加密
        for(int i=0;i<c.length;i++){
            c[i] = (char)(c[i]^20000);
        }
        return new String(c);
    }
}
