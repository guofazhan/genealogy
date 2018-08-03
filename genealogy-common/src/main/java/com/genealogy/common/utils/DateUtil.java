package com.genealogy.common.utils;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <一句话功能简述> <功能详细描述>
 * 
 * @author guofazhan
 * @version [版本号, 2015-12-9]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class DateUtil
{
    // 一天的毫秒数
    public static final long DAY_MILLI = 24 * 60 * 60 * 1000;
    
    /**
     * 要用到的DateFormat的定义
     */
    
    public static String DATE_FORMAT_EN_DATEONLY = "yyyy-MM-dd";
    
    public static String DATE_FORMAT_CN_DATEONLY = "yyyy年MM月dd日";
    
    public static String DATE_FORMAT_EN_DATETIME = "yyyy-MM-dd HH:mm";
    
    public static String DATE_FORMAT_CN_DATETIME = "yyyy年MM月dd日 HH:mm";
    
    public static String DATE_FORMAT_EN_DATESECS = "yyyy-MM-dd HH:mm:ss";
    
    public static String DATE_FORMAT_CN_DATESECS = "yyyy年MM月dd日 HH:mm:ss";
    
    public static String DATE_FORMAT_EN_DATEMSEL = "yyyy-MM-dd HH:mm:ss:SS";
    
    public static String DATE_FORMAT_CN_DATEMSEL = "yyyy年MM月dd日 HH:mm:ss:SS";
    
    public static final String PATTERN_TIME = "yyyyMMddHHmmss";
    
    /**
     * 格式：yyyy-MM-dd
     */
    private static SimpleDateFormat sdfEnDateOnly = new SimpleDateFormat(DateUtil.DATE_FORMAT_EN_DATEONLY);
    
    private static SimpleDateFormat sdfCnDateOnly = new SimpleDateFormat(DateUtil.DATE_FORMAT_CN_DATEONLY);
    
    private static SimpleDateFormat sdfEnDateTime = new SimpleDateFormat(DateUtil.DATE_FORMAT_EN_DATETIME);
    
    private static SimpleDateFormat sdfCnDateTime = new SimpleDateFormat(DateUtil.DATE_FORMAT_CN_DATETIME);
    
    private static SimpleDateFormat sdfEnDateSecs = new SimpleDateFormat(DateUtil.DATE_FORMAT_EN_DATESECS);
    
    private static SimpleDateFormat sdfCnDateSecs = new SimpleDateFormat(DateUtil.DATE_FORMAT_CN_DATESECS);
    
    private static SimpleDateFormat sdfEnDateMsel = new SimpleDateFormat(DateUtil.DATE_FORMAT_EN_DATEMSEL);
    
    private static SimpleDateFormat sdfCnDateMsel = new SimpleDateFormat(DateUtil.DATE_FORMAT_CN_DATEMSEL);
    
   
    public static int getDay() {
    	Calendar cal = Calendar.getInstance();  
    	cal.setTime(new Date()); 
    	return cal.get(Calendar.DAY_OF_WEEK); 
    }
    
	/**
	 * 描述:格式化日期方法
	 * **/
	public static String formatDate(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String time = formatter.format(date);
		
		return time;
	}
	
	
    //当前日期+几天后得到的日期
    public static Date nextDay(int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_YEAR, day);
        return cal.getTime();
    }
    
    public static String formatCurrentDateTime(String formatStr)
    {
        Date currentDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
        return formatter.format(currentDate);
    }
    
    public static String formatDateTime(Date date, String formatStr)
    {
        if (date == null)
        {
            return "";
        }
        SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
        return formatter.format(date);
    }


    
    /**
     * 获取系统当前年的数据字符串
     * 
     * @return String 类型，格式 yyyy
     */
    public static String getYear()
    {
        String strYear = "";
        Date currentDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        strYear = formatter.format(currentDate);
        return strYear;
    }

    
    public static String getMonth()
    {
        String strMonty = "";
        Date currentDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM");
        strMonty = formatter.format(currentDate);
        return strMonty;
    }
    
    /**
     * 获取系统当前年月的数据字符串
     * 
     * @return String 类型，格式 yyyy-MM
     */
    public static String getYearMonth()
    {
        String strYearMonth = "";
        Date currentDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
        strYearMonth = formatter.format(currentDate);
        return strYearMonth;
    }
    
    /**
     * 获取系统当前日期的前一个年月的数据字符串
     * 
     * @return String 类型，格式 yyyyMM
     */
    public static String getPreYearMonth()
    {
        String strYearMonth = "";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
        Calendar calendar = Calendar.getInstance();
        // 取得现在时间
        calendar.setTime(new Date());
        // 取得上一个时间
        calendar.set(Calendar.MONDAY, calendar.get(Calendar.MONDAY) - 1);
        strYearMonth = formatter.format(calendar.getTime());
        return strYearMonth;
    }
    
    /**
     * 获取系统当前年月日的数据字符串
     * 
     * @return String 类型，格式 yyyy-MM-dd
     */
    public static String getDate()
    {
        return DateUtil.getYearMonthDay();
    }
    
    /**
     * 获取系统当前时分秒的数据字符串
     * 
     * @return String 类型，格式 HH:mm:ss
     */
    public static String getTime()
    {
        String strCurrentTime = "";
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        strCurrentTime = formatter.format(currentTime);
        return strCurrentTime;
    }
    
    /**
     * 获取系统当前时分秒毫秒的数据字符串
     * 
     * @return String 类型，格式 HH:mm:ss:SS
     */
    public static String getTimes()
    {
        String strCurrentTime = "";
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss:SS");
        strCurrentTime = formatter.format(currentTime);
        return strCurrentTime;
    }
    
    /**
     * 获取系统当前年月日时分秒的数据字符串
     * 
     * @return String 类型，格式 yyyy-MM-dd HH:mm:ss
     */
    public static String getDateTime()
    {
        return DateUtil.getYearMonthDayHMS();
    }
    
    /**
     * 获取系统当前年月日的数据字符串
     * 
     * @return String 类型，格式 yyyy-MM-dd
     */
    public static String getYearMonthDay()
    {
        String strCurrentDate = "";
        Date currentDate = new Date();
        strCurrentDate = sdfEnDateOnly.format(currentDate);
        return strCurrentDate;
    }
    
    /**
     * 获取系统当前中文年月日的数据字符串
     * 
     * @return String 类型，格式 yyyy年MM月dd日
     */
    public static String getYearMonthDayCn()
    {
        String strYearMonth = "";
        Date currentDate = new Date();
        strYearMonth = sdfCnDateOnly.format(currentDate);
        return strYearMonth;
    }
    
    /**
     * 获取系统当前年月日时分的数据字符串
     * 
     * @return String 类型，格式 yyyy-MM-dd HH:mm
     */
    public static String getYearMonthDayHM()
    {
        String strYearMonth = "";
        Date currentDate = new Date();
        strYearMonth = sdfEnDateTime.format(currentDate);
        return strYearMonth;
    }
    
    /**
     * 获取系统当前中文年月日时分的数据字符串
     * 
     * @return String 类型，格式 yyyy年MM月dd日 HH:mm
     */
    public static String getYearMonthDayHMCn()
    {
        String strYearMonth = "";
        Date currentDate = new Date();
        strYearMonth = sdfCnDateTime.format(currentDate);
        return strYearMonth;
    }
    
    /**
     * 获取系统当前年月日时分秒的数据字符串
     * 
     * @return String 类型，格式 yyyy-MM-dd HH:mm:ss
     */
    public static String getYearMonthDayHMS()
    {
        String strYearMonth = "";
        Date currentDate = new Date();
        strYearMonth = sdfEnDateSecs.format(currentDate);
        return strYearMonth;
    }
    
    /**
     * 获取系统当前中文年月日时分秒的数据字符串
     * 
     * @return String 类型，格式 yyyy年MM月dd日 HH:mm:ss
     */
    public static String getYearMonthDayHMSCn()
    {
        String strYearMonth = "";
        Date currentDate = new Date();
        strYearMonth = sdfCnDateSecs.format(currentDate);
        return strYearMonth;
    }
    
    /**
     * 获取系统当前年月日时分秒毫秒的数据字符串
     * 
     * @return String 类型，格式 yyyy-MM-dd HH:mm:ss:SS
     */
    public static String getYearMonthDayHMSS()
    {
        String strYearMonth = "";
        Date currentDate = new Date();
        strYearMonth = sdfEnDateMsel.format(currentDate);
        return strYearMonth;
    }
    
    /**
     * 获取系统当前中文年月日时分秒毫秒的数据字符串
     * 
     * @return String 类型，格式 yyyy年MM月dd日 HH:mm:ss:SS
     */
    public static String getYearMonthDayHMSSCn()
    {
        String strYearMonth = "";
        Date currentDate = new Date();
        strYearMonth = sdfCnDateMsel.format(currentDate);
        return strYearMonth;
    }
    
    /**
     * 获取系统当前年月的数据字符串
     * 
     * @return String 类型，格式 yyyyMM
     */
    public static String getYM()
    {
        String strYMD = "";
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
        strYMD = formatter.format(currentTime);
        return strYMD;
    }
    
    /**
     * 获取系统当前年月日的数据字符串
     * 
     * @return String 类型，格式 yyyyMMdd
     */
    public static String getYMD()
    {
        String strYMD = "";
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        strYMD = formatter.format(currentTime);
        return strYMD;
    }
    
    public static String getH()
    {
        String strYMD = "";
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("HH");
        strYMD = formatter.format(currentTime);
        return strYMD;
    }
    
    /**
     * 获取系统当前时分秒的数据字符串
     * 
     * @return String 类型，格式 HHmmss
     */
    public static String getHMS()
    {
        String strHMS = "";
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("HHmmss");
        strHMS = formatter.format(currentTime);
        return strHMS;
    }
    
    /**
     * 获取系统当前年月日时分的数据字符串
     * 
     * @return String 类型，格式 yyyyMMddHHmm
     */
    public static String getYMDHM()
    {
        String strYMDHM = "";
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");
        strYMDHM = formatter.format(currentTime);
        return strYMDHM;
    }
    
    /**
     * 获取系统当前年月日时分秒的数据字符串
     * 
     * @return String 类型，格式 yyyyMMddHHmmss
     */
    public static String getYMDHMS()
    {
        String strYMDHMS = "";
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        strYMDHMS = formatter.format(currentTime);
        return strYMDHMS;
    }
    
    /**
     * 获取系统当前年月日时分秒毫秒的数据字符串
     * 
     * @return String 类型，格式 yyyyMMddHHmmssSS
     */
    public static String getYMDHMSS()
    {
        String strYMDHMSS = "";
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSS");
        strYMDHMSS = formatter.format(currentTime);
        return strYMDHMSS;
    }

    /**
     * 得到对比日期变化的目标日期 getDateChange("2007-08-10",1)="2007-08-11";
     * 
     * @param strDate 日期格式 "2000-08-10"
     * @param iQuantity 变化的数量 以天为单位
     * @return String 类型，格式 "2007-08-11"
     */
    public static String getDateChange(String strDate, int iQuantity)
    {
        String strTarget = "";
        int iYear = Integer.parseInt(strDate.substring(0, 4));
        int iMonth = Integer.parseInt(strDate.substring(5, 7));
        int iDay = Integer.parseInt(strDate.substring(8, 10));
        
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, iYear);
        cal.set(Calendar.MONTH, iMonth - 1);
        cal.set(Calendar.DAY_OF_MONTH, iDay);
        cal.add(Calendar.DATE, iQuantity);
        Date currentDate = cal.getTime();
        strTarget = sdfEnDateOnly.format(currentDate);
        return strTarget;
    }
    
    /**
     * 得到对比日期变化的目标日期包含时间 getDateTimeChange("2007-08-10 15:23:12",1)="2007-08-11 15:23:12";
     * 
     * @param strDateTimeTime 当前日期 格式 "2007-08-10 15:23:12"
     * @param iQuantity 变化的数量 以天为单位
     * @return String 类型，格式 "2007-08-11 15:23:12"
     */
    public static String getDateTimeChange(String strDateTimeTime, int iQuantity)
    {
        String strTarget = "";
        String strHHMMSS = strDateTimeTime.substring(10, 19);
        int iYear = Integer.parseInt(strDateTimeTime.substring(0, 4));
        int iMonth = Integer.parseInt(strDateTimeTime.substring(5, 7));
        int iDay = Integer.parseInt(strDateTimeTime.substring(8, 10));
        
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, iYear);
        cal.set(Calendar.MONTH, iMonth - 1);
        cal.set(Calendar.DAY_OF_MONTH, iDay);
        cal.add(Calendar.DATE, iQuantity);
        Date currentDate = cal.getTime();
        strTarget = sdfEnDateOnly.format(currentDate);
        strTarget = strTarget + strHHMMSS;
        return strTarget;
    }

    
    /**
     * Parse date and time like "yyyy-MM-dd HH:mm".
     */
    public static Date parseDateTime(String dt)
    {
        try
        {
            return new SimpleDateFormat(DATE_FORMAT_EN_DATETIME).parse(dt);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    public static Date parseDateTimes(String dt)
    {
        try
        {
            if ("".equals(dt) || null == dt)
            {
                return null;
            }
            return new SimpleDateFormat(DATE_FORMAT_EN_DATESECS).parse(dt);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    

    
    /**
     * 获取上月最后一天 严玉成 2013-11-12
     * 
     * @param date yyyy-MM
     * @return yyyy-MM-dd
     */
    public static String lastDayOfMonth(String date)
    {
        Date dat = null;
        try
        {
            dat = new SimpleDateFormat("yyyy-MM").parse(date);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(dat);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.roll(Calendar.DAY_OF_MONTH, -1);
        return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
    }
    
    /**
     * 获取上月 严玉成 2013-11-12
     */
    public static String preYearMonth(String date)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        
        Date dt = null;
        try
        {
            dt = sdf.parse(date);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(dt);
        rightNow.add(Calendar.YEAR, 0);
        rightNow.add(Calendar.MONTH, -1);
        Date dt1 = rightNow.getTime();
        return sdf.format(dt1);
    }
    
    /**
     * 日期转换 将yy-MM-dd 转换成 yyyy-mm-dd 严玉成 2013-11-21
     * 
     * @param str
     */
    public static String dateChange(String str)
    {
        String y = "yy-MM-dd";
        String x = "yyyy-MM-dd";
        SimpleDateFormat format1 = new SimpleDateFormat(y);
        SimpleDateFormat format = new SimpleDateFormat(x);
        try
        {
            format1.parse(str);// 这个是按照y的格式，将s变成一个Date对象
            return format.format(format1.parse(str));// 按照X的格式,输出Date对象的值
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
        
    }
    
    /**
     * 获取当月第一天
     * 
     * @author ycyan
     * @创建时间：2014-4-8 下午2:28:32
     */
    public static String getFirstDay()
    {
        // 获取前月的第一天
        Calendar cal_1 = Calendar.getInstance();// 获取当前日期
        cal_1.add(Calendar.MONTH, 0);
        cal_1.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
        
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String firstDay = format.format(cal_1.getTime());
        return firstDay;
        
    }
    
    /**
     * 获取当月最后一天
     * 
     * @author ycyan
     * @创建时间：2014-4-8 下午2:28:32
     */
    public static String getLastMonthDay()
    {
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String last = format.format(ca.getTime());
        return last;
    }
    
    /**
     * 获取某年第一天日期
     * 
     * @return Date
     */
    
    public static String getCurrYearFirst()
    {
        Calendar calendar = Calendar.getInstance();
        int cur_year = calendar.get(Calendar.YEAR);
        calendar.clear();
        calendar.set(Calendar.YEAR, cur_year);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String currYearFirst = format.format(calendar.getTime());
        return currYearFirst;
    }
    
    /**
     * 获取某年最后一天日期
     * 
     * @return Date
     */
    public static String getCurrYearLast()
    {
        Calendar calendar = Calendar.getInstance();
        int cur_year = calendar.get(Calendar.YEAR);
        calendar.clear();
        calendar.set(Calendar.YEAR, cur_year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String currYearlast = format.format(calendar.getTime());
        return currYearlast;
    }
    
    /**
     * 将yyyy-mm-dd 转换成 yyyy年mm月dd日
     */
    public static String numberToStringCN(String strNumber)
    {
        String strDateTime = "";
        strDateTime =
            strNumber.substring(0, 4) + "年" + strNumber.substring(5, 7) + "月" + strNumber.substring(8, 10) + "日";
        return strDateTime;
    }
    
    public static String formatDouble(double a)
    {
        DecimalFormat df = new DecimalFormat();
        String style = "###,##0.00";
        df.applyPattern(style);
        return df.format(a);
    }
    
    public static String formatString(String a)
    {
        Double b = Double.parseDouble(a);
        DecimalFormat df = new DecimalFormat();
        String style = "###,##0.00";
        df.applyPattern(style);
        return df.format(b);
    }
    
    /**
     * 格式化时间 将2010-01-04 18:35:20.0 转为2012年1月4日18:35
     * */
    public static String getFormatTime(String paramDateFormat, String resultDateFormat, String str_time)
    {
        String time = "";
        SimpleDateFormat sdf = new SimpleDateFormat(paramDateFormat);
        SimpleDateFormat sdf1 = new SimpleDateFormat(resultDateFormat);
        try
        {
            time = sdf1.format(sdf.parse(str_time));
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return time;
    }
    
	
	public static String getCurrentDateStr(){
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat(PATTERN_TIME);
		return format.format(date);
	}
    
}
