package cn.root.util;

import org.apache.commons.codec.digest.DigestUtils;

public class PasswordDesensitization {

    public static String desensitization(String password, String id) {
        return DigestUtils.md5Hex(password + id.substring(0, 6));
    }
}
