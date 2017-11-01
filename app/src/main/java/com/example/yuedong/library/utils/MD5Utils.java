package com.example.yuedong.library.utils;

import java.security.MessageDigest;

/**
 * Created by Administrator on 2017/10/13.
 */

public class MD5Utils {

    private static String rex(byte[] result) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    public static String MD5Encode(String password) {
        String charSet = "UTF-8";
        String resultString = null;
        try {
            resultString = new String(password);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charSet == null || "".equals(charSet)) {
                resultString = rex(md.digest(resultString
                        .getBytes()));
            } else {
                resultString = rex(md.digest(resultString
                        .getBytes(charSet)));
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return resultString;
    }


}
