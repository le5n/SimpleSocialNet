package security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public interface StringEncryptUtil {

    static String encrypt(String s) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        messageDigest.reset();

        byte[] bs = messageDigest.digest(s.getBytes());

        StringBuilder stringBuilder = new StringBuilder();

        //hex encode the digest
        for (byte b : bs) {
            String hexVal = Integer.toHexString(0xFF & b);
            if (hexVal.length() == 1)
                stringBuilder.append("0");
            stringBuilder.append(hexVal);
        }

        return stringBuilder.toString();
    }
}
