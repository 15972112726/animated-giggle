package cn.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 * Created by dell on 2018/4/27.
 */
public class StringHelperUtil {


    /**
     * double转String
     *
     * @param d
     * @return
     */
    public static String doubleToString(double d) {
        String s = null;
        if (d % 1.0 == 0) {
            s = String.valueOf((long) 0);
            return s;
        } else {
            s = String.valueOf(d);
            return s;
        }
    }

    /**
     * 将object转为long类型
     *
     * @param obj
     * @return
     */
    public static Long objectToTimeLong(Object obj) {
        Long l = null;
        if (obj != null) {
            l = (Long) obj;
        }
        return l;
    }

    /**
     * 格式化价格的输出，目前控制强制保留两个小数
     *
     * @param number
     * @return
     */
    public static String formatPrice(double number) {
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);
        nf.setMinimumFractionDigits(2);
        nf.setGroupingUsed(false);
        return nf.format(number);
    }

    /**
     * 格式化量的输出，不显示小数
     *
     * @param amount
     * @return
     */
    public static String formatAmount(double amount) {
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(0);
        nf.setGroupingUsed(false);
        return nf.format(amount);
    }

    /**
     * double运算
     * @param amount
     * @param scale
     * @return
     */
    public static double div(double amount,int scale){
        BigDecimal b1 = new BigDecimal(Double.valueOf(amount));
        BigDecimal b2 = new BigDecimal(100);
        return b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 根据小数位数格式化数字，强制显示val位小数位
     *
     * @param number
     * @param val
     * @return
     */
    public static String formatNumber(double number, int val) {
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(val);
        nf.setMinimumFractionDigits(val);
        nf.setGroupingUsed(false);
        return nf.format(number);
    }

    /**
     * 根据设置格式化数字，val为最多的小数位数，末尾不自动补0
     *
     * @param number
     * @param val
     * @return
     */
    public static String formatNumberByMaxFract(double number, int val) {
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(val);
        nf.setGroupingUsed(false);
        return nf.format(number);
    }

    /**
     * 根据小数位数格式化Imix数字，至少显示val小数位
     *
     * @param number
     * @param val
     * @return
     */
    public static String formatImixNumber(double number, int val) {
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(val);
        nf.setMinimumFractionDigits(val);
        nf.setGroupingUsed(false);
        return nf.format(number);
    }

    /**
     * 获取UTF-8编码下字符的长度
     *
     * @param str
     * @return
     */
    public static int getStrLength(String str) {
        return getStrLength(str, "UTF-8");
    }

    /**
     * 根据字符集获取字符长度
     *
     * @param str
     * @param charSet
     * @return
     */
    private static int getStrLength(String str, String charSet) {
        int length = 0;
        try {
            length = str.getBytes(charSet).length;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return length;
    }

    /**
     * 根据最大长度以utf-8格式截取字符串
     *
     * @param str
     * @param maxSize
     * @return
     */
    public static String cutStrByUTF8(String str, int maxSize) {
        String utf8Str = "";
        try {
            byte[] tmp = str.getBytes("UTF-8");
            if (maxSize < tmp.length) {
                utf8Str = new String(tmp, 0, maxSize, "UTF-8");
            } else {
                utf8Str = new String(tmp, "UTF-8");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return utf8Str;
    }

    /**
     * 将字符串转换为byte数组
     *
     * @param str
     * @return
     */
    public static byte[] getByteInStr(String str) {
        if (StringHelperUtil.isEmpty(str))
            return null;
        try {
            return str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将数组转换为字符串
     *
     * @param b
     * @return
     */
    public static String getStrFormatByte(byte[] b) {
        if (null == b || b.length == 0)
            return "";
        try {
            return new String(b, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 判断字符串是否为空（不包含空格）
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str.trim()))
            return true;
        return false;
    }


}
