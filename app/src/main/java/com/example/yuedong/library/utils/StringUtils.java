package com.example.yuedong.library.utils;

/**
 * Created by mayuedong on 2017/12/5.
 */

public class StringUtils {
    /**
     * 非空判断
     *
     * @param s
     * @return
     */
    public static boolean isEmpty(String s) {
        if (null == s || s.equals("") || s.equals("null")) return true;
        return false;
    }
}
