package com.example.yuedong.library.utils;

import android.text.TextUtils;

import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mayuedong on 2017/10/25.
 */

public class VaildUtils {

    private final static Pattern number_pattern = Pattern.compile("^[0-9]*$");

    /**
     * 验证是数字
     *
     * @param str 验证字符
     * @return boolean
     */
    public static boolean isNumber(String str) {
        return number_pattern.matcher(str).matches();
    }

    /**
     * 判断是否为空
     *
     * @param str 字符串
     * @return true 空 false 非空
     */
    public static Boolean isEmpty(String str) {
        if (null == str || str.trim().equals("")) {
            return true;
        }
        return false;
    }

    /**
     * 判断输入项是否为手机
     *
     * @param mobiles
     * @return
     */
    public static boolean isMobile(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[0-9])|(18[0-9])|(17[0-9])|(14[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /**
     * 判断两个值是否相等
     *
     * @param first
     * @param second
     * @return
     */
    public static boolean isEqual(String first, String second) {
        return first.equals(second);
    }

    /**
     * 功能：判断字符串是否为日期格式
     *
     * @param
     * @return
     */
    public static boolean isDate(String strDate) {
        String regxStr = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";
        Pattern pattern = Pattern.compile(regxStr);
        Matcher m = pattern.matcher(strDate);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 检查日期是否有效
     *
     * @param year  年
     * @param month 月
     * @param day   日
     * @return boolean
     */
    public static boolean getDateIsTrue(String year, String month, String day) {
        try {
            String data = year + month + day;
            SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMdd");
            simpledateformat.setLenient(false);
            simpledateformat.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 验证身份证号码是否正确
     *
     * @param IDCardNo 身份证号码
     * @return boolean
     */
    public static boolean isIDCard(String IDCardNo) {
        if (null == IDCardNo || TextUtils.isEmpty(IDCardNo)) return false;
        //记录错误信息
        String errmsg = "";
        String[] ValCodeArr = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};
        String[] Wi = {"7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7", "9", "10", "5", "8", "4", "2"};
        String Ai = "";

        //================ 身份证号码的长度 15位或18位 ================
        if (IDCardNo.length() != 15 && IDCardNo.length() != 18) {
            errmsg = "身份证号码长度应该为15位或18位!";
            return false;
        }

        //================ 数字 除最后以为都为数字 ================
        if (IDCardNo.length() == 18) {
            Ai = IDCardNo.substring(0, 17);
        } else if (IDCardNo.length() == 15) {
            Ai = IDCardNo.substring(0, 6) + "19" + IDCardNo.substring(6, 15);
        }
        if (isNumber(Ai) == false) {
            errmsg = "身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字";
            return false;
        }

        //================ 出生年月是否有效 ================
        //年份
        String strYear = Ai.substring(6, 10);
        //月份
        String strMonth = Ai.substring(10, 12);
        //日
        String strDay = Ai.substring(12, 14);
        if (getDateIsTrue(strYear, strMonth, strDay) == false) {
            errmsg = "身份证生日无效";
            return false;
        }
        GregorianCalendar gc = new GregorianCalendar();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150 || (gc.getTime().getTime() - s.parse(strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {
                errmsg = "身份证生日不在有效范围";
                return false;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            errmsg = "身份证生日不在有效范围";
            return false;
        } catch (java.text.ParseException e1) {
            e1.printStackTrace();
            errmsg = "身份证生日不在有效范围";
            return false;
        }
        if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {
            errmsg = "身份证月份无效";
            return false;
        }
        if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {
            errmsg = "身份证日期无效";

            return false;
        }

        //================ 地区码时候有效 ================
        Hashtable hashtable = getAreaCodeAll();
        if (hashtable.get(Ai.substring(0, 2)) == null) {
            errmsg = "身份证地区编码错误";
            return false;
        }

        //================ 判断最后一位的值 ================
        int TotalmulAiWi = 0;
        for (int i = 0; i < 17; i++) {
            TotalmulAiWi = TotalmulAiWi + Integer.parseInt(String.valueOf(Ai.charAt(i))) * Integer.parseInt(Wi[i]);
        }
        int modValue = TotalmulAiWi % 11;
        String strVerifyCode = ValCodeArr[modValue];
        Ai = Ai + strVerifyCode;
        if (IDCardNo.length() == 18) {
            if (Ai.equals(IDCardNo) == false) {
                errmsg = "身份证无效，不是合法的身份证号码";
                return false;
            }
        } else {
            return true;
        }
        return true;
    }

    /**
     * 获取身份证号所有区域编码设置
     *
     * @return Hashtable
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static Hashtable getAreaCodeAll() {
        Hashtable hashtable = new Hashtable();
        hashtable.put("11", "北京");
        hashtable.put("12", "天津");
        hashtable.put("13", "河北");
        hashtable.put("14", "山西");
        hashtable.put("15", "内蒙古");
        hashtable.put("21", "辽宁");
        hashtable.put("22", "吉林");
        hashtable.put("23", "黑龙江");
        hashtable.put("31", "上海");
        hashtable.put("32", "江苏");
        hashtable.put("33", "浙江");
        hashtable.put("34", "安徽");
        hashtable.put("35", "福建");
        hashtable.put("36", "江西");
        hashtable.put("37", "山东");
        hashtable.put("41", "河南");
        hashtable.put("42", "湖北");
        hashtable.put("43", "湖南");
        hashtable.put("44", "广东");
        hashtable.put("45", "广西");
        hashtable.put("46", "海南");
        hashtable.put("50", "重庆");
        hashtable.put("51", "四川");
        hashtable.put("52", "贵州");
        hashtable.put("53", "云南");
        hashtable.put("54", "西藏");
        hashtable.put("61", "陕西");
        hashtable.put("62", "甘肃");
        hashtable.put("63", "青海");
        hashtable.put("64", "宁夏");
        hashtable.put("65", "新疆");
        hashtable.put("71", "台湾");
        hashtable.put("81", "香港");
        hashtable.put("82", "澳门");
        hashtable.put("91", "国外");
        return hashtable;
    }

    /**
     * yyyy-MM-dd HH:mm:ss  yyyy年MM月dd日
     *
     * @param strTime
     * @param formatType
     * @return
     * @throws ParseException
     */
    public static long stringToLong(String strTime, String formatType)
            throws ParseException {
        Date date = stringToDate(strTime, formatType); // String类型转成date类型
        if (date == null) {
            return 0;
        } else {
            long currentTime = dateToLong(date); // date类型转成long类型
            return currentTime;
        }
    }

    public static Date stringToDate(String strTime, String formatType)
            throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = null;
        date = formatter.parse(strTime);
        return date;
    }

    public static long dateToLong(Date date) {
        return date.getTime();
    }

    /*
    验证车牌号
     */
    public static boolean isCarNo(String str) {
        if (str.isEmpty()) {
            return false;
        }
        String regex = "^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Za-z]{1}[A-Za-z]{1}[A-Za-z0-9]{4}[A-Za-z0-9挂学警港澳]{1}$";
        return str.matches(regex);
    }

    public static boolean isMatchFee(String text) {
        Pattern pattern = Pattern.compile("/^([1-9]\\d*|0)(\\.\\d{1,2})?$/");
        Matcher isNum = pattern.matcher(text);
        if (isNum.matches()) {
            return true;
        } else {
            return false;
        }
    }

    //验证中文 且限制长度
    public static boolean isChinese(String str) {
        if (str.isEmpty()) {
            return false;
        }
        //   ^[\u2E80-\u9FFF]+$
        if (str.matches("^[\u4e00-\u9fa5]{2,4}$") && str.length() < 100) {//Unicode编码中的汉字范围
            return true;
        }
        return false;
    }

    /**
     * 校验银行卡卡号
     */
    public static boolean isBankCard(String cardId) {
        char bit = getBankCardCheckCode(cardId.substring(0, cardId.length() - 1));
        if (bit == 'N') {
            return false;
        }
        return cardId.charAt(cardId.length() - 1) == bit;
    }

    public static char getBankCardCheckCode(String nonCheckCodeCardId) {
        if (TextUtils.isEmpty(nonCheckCodeCardId)
                || !nonCheckCodeCardId.matches("\\d+")
                || nonCheckCodeCardId.length() < 16
                || nonCheckCodeCardId.length() > 19) {
            //如果传的不是数据返回N
            return 'N';
        }
        char[] chs = nonCheckCodeCardId.trim().toCharArray();
        int luhmSum = 0;
        for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if (j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;
        }
        return (luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0');
    }

    /**
     * MD5加密
     *
     * @param password
     * @return
     */

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

    private static String rex(byte[] result) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
    //***********************************************************************
}
