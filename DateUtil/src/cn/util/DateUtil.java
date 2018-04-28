package cn.util;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.*;
//import java.util.regex.Matcher;



/**
 * Created by dell on 2018/4/27.
 */
public class DateUtil {
    public static final String dashFormat = new String("yyyy-MM-dd");
    public static final String slashFormat = new String("yyyy/MM/dd");
    public static final String ymdFormat = new String("yyyyMMdd");
    public static final String timeFormat = new String("HH:mm:ss");
    public static final String timeFormat1 = new String("HH:mm:ss.SSS");
    public static final String dateTimeFormat = new String("yyyy-MM-dd HH:mm:ss");
    public static final String dateTimeFormat1 = new String("yyyy-MM-dd HH:mm:ss.SSS");
    public static final String dateTimeFormat2 = new String("yyyyMMdd-HH:mm:ss");
    public static final String dateTimeFormat3 = new String("yyyyMMdd-HH:mm:ss.SSS");
    public static final String dateTimeFormat4 = new String("yyyyMMdd HH:mm:ss");
    public static final String dateTimeFormat5 = new String("yyyyMMdd HH:mm:ss.SSS");

    public static final long M_PER_DAY = 1000*60*60*24;

    /**
     * 根据给定的时间格式对时间类型格式化
     *
     * @param date  日期
     * @param formatString  日期表达式
     * @return
     */
    public static String getDateString(Date date,String formatString){
        try {
            return (date!=null)?new SimpleDateFormat(formatString).format(date):"";
        }catch (Throwable e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将“yyyy-MM-dd HH：mm:ss”格式的字符串转换为日期格式
     * @param dStr
     * @return
     */
    public static Date getDate(String dStr){
        return (StringHelperUtil.isEmpty(dStr))?null:getDate(dStr,dashFormat);
    }

    /**
     * 根据字符串，字符串格式转换为日期
     * @param dStr
     * @param formatString
     * @return
     */
    public static Date getDate(String dStr,String formatString){
        SimpleDateFormat format = new SimpleDateFormat(formatString);
        format.setLenient(false);
        Date date = null;
        try {
            date=format.parse(dStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 日期时间拼接
     * @param date
     * @param time
     * @return
     */
    public static Date getStringDate(String date,String time){
        if (date!=null&&time!=null)
            return getDate(date+"-"+time,dateTimeFormat2);
        if (date==null&&time!=null)
            return getDate(time,timeFormat);
        if (date!=null&&time==null)
            return getDate(date,ymdFormat);
        return null;
    }

    /**
     * 获取给定日期当日的00：00：00时间戳，即去除日期中含有的时间数据
     * @param date
     * @return
     */
    public static Date getDateIgnoreTime(Date date){
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        gregorianCalendar.set(Calendar.HOUR,0);
        gregorianCalendar.set(Calendar.HOUR_OF_DAY,0);
        gregorianCalendar.set(Calendar.MINUTE,0);
        gregorianCalendar.set(Calendar.SECOND,0);
        gregorianCalendar.set(Calendar.MILLISECOND,0);
        return gregorianCalendar.getTime();
    }

    /**
     * 根据天数偏移量计算日期
     * @param date
     * @param days
     * @return
     */
    public static Date getDateAfter(Date date,int days){
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(GregorianCalendar.DATE,days);
        return calendar.getTime();
    }

    /**
     * 根据年月日偏移量计算最终日期
     * @param date
     * @param year
     * @param month
     * @param days
     * @return
     */
    public static Date getDateAfter(Date date,int year,int month,int days){
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(GregorianCalendar.YEAR,year);
        calendar.add(GregorianCalendar.MONTH,month);
        calendar.add(GregorianCalendar.DATE,days);
        return calendar.getTime();
    }

    /**
     * 计算两个日期间间隔的天数
     * @param startDate
     * @param endDate
     * @return
     */
    public static Long computDateInterval(Date startDate,Date endDate){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        long startTime = calendar.getTimeInMillis();

        calendar.setTime(endDate);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        long endTime = calendar.getTimeInMillis();

        return (Long) ((endTime-startTime)/M_PER_DAY);
    }

    /**
     * 获取日期中的年份数值
     * @param date
     * @return
     */
    public static int getYearForDate(Date date){
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar.get(GregorianCalendar.YEAR);
    }

    /**
     * 获取日期中月份数值calendar从0计数，所以需加一，才为实际所称月
     * @param date
     * @return
     */
    public static int getMonthForDate(Date date){
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar.get(GregorianCalendar.MONTH)+1;
    }

    /**
     * 获取日期中的天数值
     * @param date
     * @return
     */
    public static int getDayForDate(Date date){
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar.get(GregorianCalendar.DATE);
    }

    /**
     * 校验日期格式是否正确
     * @param str
     * @param formatString
     * @return
     */
    public static boolean checkDateValidity(String str,String formatString){
        SimpleDateFormat sdf = new SimpleDateFormat(formatString);
        sdf.setLenient(false);
        try {
            sdf.parse(str);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * 判断给定日期是否是该月的最后一天
     * @param date
     * @return
     */
    public static boolean isLastDayOfMonth(Date date){
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return (calendar.get(Calendar.DAY_OF_MONTH)==calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
    }

    /**
     * 将时间格式HH:mm:ss拼接在日期中
     * @param date
     * @param time
     * @return
     */
    public static Date getDateTime(Date date,String time){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        sdf.setLenient(false);
        try {
            return sdf.parse(DateUtil.getDateString(date,DateUtil.ymdFormat)+" "+time);
        } catch (ParseException e) {
            return date;
        }
    }

    /**
     * 将时间格式HH:mm:ss拼接在日期中，返回时间类型
     * @param date
     * @param time
     * @return
     */
    public static Timestamp getDateTimeRthTime(Date date,String time){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Timestamp timestamp =null;
        sdf.setLenient(false);
        try {
            String dateStr = sdf.format(date);
            timestamp =Timestamp.valueOf(dateStr+" "+time);
        }catch (Exception e){

        }
        return timestamp;
    }

    /**
     * 校验某日是否在日期区间中
     * @param compareDate
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isBetween(Date compareDate,Date date1,Date date2){
        if (date2.before(date1)){
            Date date = date1;
            date1=date2;
            date2=date;
        }
        return !(compareDate.before(date1)||compareDate.after(date2));
    }

    /**
     * 获取当前日期
     * @return
     */
    public static Date getCurrenDate(){
        return new Date();
    }

    /**
     * 获取当前时间
     * @return
     */
    public static Timestamp getCurrenTime(){
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * 日期比较函数
     * @param date1
     * @param date2
     * @return  比较两个日期的先后，date1>date2放回1，date1==date2放回0，date1<date2放回-1，date1,date2数据错误返回-2
     *
     */
    public static int compareDate(Date date1,Date date2){
        if (date1==null||date2==null)
            return -2;
        long temp = java.util.TimeZone.getDefault().getRawOffset();
        long result = (date2.getTime()+temp)/M_PER_DAY-(date1.getTime()+temp)/M_PER_DAY;
        if (result>0){
            return -1;
        }else if (result<0){
            return 1;
        }else {
            return 0;
        }
    }

    /**
     * 校验时间字符是否符合逻辑HH：mm格式
     * @param str
     * @return
     */
    public static boolean checkTimeHHnnPattern(String str){

        Pattern p=Pattern.compile("^([0-1]\\d|2[0-3]):[0-5]\\d$");
        Matcher m= p.matcher(str);
        return m.matches();
    }

    /**
     * 获取当前月份的第一天
     * @return
     */
    public static Date getCurrentMonthFiretDate(){
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(Calendar.DATE,1);
        return calendar.getTime();
    }

    /**
     * 取得当前月份的最后一天
     * @return
     */
    public static Date getCurrentMonthLastDate(){
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.add(Calendar.MONTH,1);
        calendar.set(Calendar.DATE,1);
        calendar.add(Calendar.DATE,-1);
        return calendar.getTime();
    }

    /**
     * 移除日期中非数字字符
     * @param time
     * @return
     */
    public static String replaceNotNum(String time){
        return time.replaceAll("[- :/]","");
    }
}