package site.yan.key.securtiy;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Create in 2019/11/22 11:25 by Zhao Xubin.
 */
public class MD5Core {
    public static String getMD5(String plainText) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte[] digest = md.digest();
            int i;
            StringBuilder sb = new StringBuilder();
            for (int offset = 0; offset < digest.length; offset++) {
                i = digest[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    sb.append(0);
                sb.append(Integer.toHexString(i));
            }
            return sb.toString().substring(0, 24);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(getMD5("1234"));
    }
}

