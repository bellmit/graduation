package com.software.utils;

/**
 * created by 2010-7-2
 */
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期工具类
 */
@Slf4j
public class DateUtil {
    public final static String shortFormat = "yyyyMMdd";
    public final static String longFormat = "yyyyMMddHHmmss";
    public final static String webFormat = "yyyy-MM-dd";
    public final static String timeFormat = "HHmmss";
    public final static String monthFormat = "yyyyMM";
    public final static String chineseDtFormat = "yyyy年MM月dd日";
    public final static String chineseFullFormat = "yyyy年MM月dd日 HH时mm分";
    public final static String chineseLongFormat = "yyyy年MM月dd日 HH:mm:ss";
    public final static String chineseshortFormat = "yyyy年MM月";
    /**
     * 最高院使用日期格式YYYY-MM-DD
     */
    public final static String newFormat = "yyyy-MM-dd";
    public final static String zbFormat = "yyyy/MM/dd";
    public final static String tsfxFormat = "yyyy.MM.dd";
    public final static String noSecondFormat = "yyyy-MM-dd HH:mm";

    public final static String datetimeFormat = "yyyy-MM-dd HH:mm:ss";

    public final static String sqlFormat = "yyyy/MM/dd HH:mm:ss";
    public final static long ONE_DAY_MILL_SECONDS = 86400000;

    /**
     * 获得一个日期的去年的同一时间
     *
     * @param date
     *            日期
     * @return
     */
    public static Date getLastYear(Date date) {
        if (date == null) {
            return null;
        }

        int year = getYear(date);
        int month = getMonth(date);
        int day = getDayOfMonth(date);

        StringBuffer sb = new StringBuffer();
        sb.append(year - 1).append("-").append(month).append("-").append(day);
        return parse(sb.toString(), webFormat);
    }

    public static String getLastYearByDate(Date date) {
        // if (date == null) {
        // return null;
        // }
        //
        // int year = getYear(date);
        // int month = getMonth(date);
        // int day = getDayOfMonth(date);
        //
        // StringBuffer sb = new StringBuffer();
        // sb.append(year-1).append("-").append(month).append("-").append(day);
        // return sb.toString();
        return format(addYears(date, -1), webFormat);
    }

    // -----------------------------------------------------------------------

    /**
     * Adds to a date returning a new object. The original date object is
     * unchanged.
     *
     * @param date
     *            the date, not null
     * @param calendarField
     *            the calendar field to add to
     * @param amount
     *            the amount to add, may be negative
     * @return the new date object with the amount added
     * @throws IllegalArgumentException
     *             if the date is null
     */
    public static Date add(Date date, int calendarField, int amount) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(calendarField, amount);
        return c.getTime();
    }

    public static Date addYears(Date date, int amount) {
        return add(date, Calendar.YEAR, amount);
    }

    public static Date addMonths(Date date, int amount) {
        return add(date, Calendar.MONTH, amount);
    }

    public static Date addWeeks(Date date, int amount) {
        return add(date, Calendar.WEEK_OF_YEAR, amount);
    }

    public static Date addDays(Date date, int amount) {
        return add(date, Calendar.DAY_OF_MONTH, amount);
    }

    public static Date addHours(Date date, int amount) {
        return add(date, Calendar.HOUR_OF_DAY, amount);
    }

    /**
     * 标准化输出
     *
     * @param date
     *            日期对象
     * @param format
     *            日期输出格式
     * @return 按照指定格式输出的字符串
     */
    public static String format(Date date, String format) {
        if (date == null) {
            return null;
        }

        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 获得中文格式日期
     * @param date
     * @return
     */
    public static String getChineseDate(Date date){
        String res = "";
        int year = getYear(date);
        int month = getMonth(date);
        int day = getDayOfMonth(date);

        String[] sl = {"〇","一","二","三","四","五","六","七","八","九","十"};
        String year_c = sl[year/1000]+sl[year/100%10]+sl[year/10%10]+sl[year%10];
        String month_c = "";
        if(month<=10)
            month_c = sl[month];
        else
            month_c = sl[10]+sl[month%10];

        String day_c = "";
        if(day<=10)
            day_c = sl[day];
        else if(day>10 && day<20)
            day_c = sl[10]+sl[day%10];
        else if(day==20 || day==30)
            day_c = sl[day/10]+sl[10];
        else if(day>20 && day<30 || day>30)
            day_c = sl[day/10]+sl[10]+sl[day%10];

        res = year_c+"年"+month_c+"月"+day_c+"日";
        return res;
    }

    /**
     * 获得一个时间的年份数
     *
     * @param date
     * @return
     */
    public static int getYear(Date date) {
        if (date == null) {
            return -1;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    /**
     * 获得一个时间的月份数
     *
     * @param date
     * @return
     */
    public static int getMonth(Date date) {
        if (date == null) {
            return -1;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH) + 1;
    }

    /**
     * 获得一个时间在一个月中的天数
     *
     * @param date
     * @return
     */
    public static int getDayOfMonth(Date date) {
        if (date == null) {
            return -1;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 标准化输出
     *
     * @param s_date
     * @param format
     * @return
     */
    public static Date parse(String s_date, String format) {
        if (s_date == null)
            return null;
        try {
            return new SimpleDateFormat(format).parse(s_date);
        } catch (ParseException e) {

            log.error("Wrong parse", e);
        }
        return null;
    }

    /**
     * 取得两个日期的间隔天数
     *
     * @param one
     * @param two
     *
     * @return 间隔天数
     */
    public static long getDiffDays(Date one, Date two) {
        Calendar sysDate = new GregorianCalendar();

        sysDate.setTime(one);

        Calendar failDate = new GregorianCalendar();

        failDate.setTime(two);
        return (sysDate.getTimeInMillis() - failDate.getTimeInMillis())
                / (24 * 60 * 60 * 1000);
    }

    /**
     * 获得当天的开始时间
     *
     * @return
     */
    public static Date today() {
        return parse(format(new Date(), webFormat), webFormat);
    }

    /**
     * 比较两个日期的先后关系
     *
     * @param one
     *            日期1
     * @param two
     *            日期2
     * @return 0 ： 表示相等 1 ： one大于two -1 ： two大于one
     */
    public static int compareDate(Date one, Date two) {
        // 合法性判断
        if (one == null && two != null)
            return -1;
        else if (one != null && two == null)
            return 1;
        else if (one == null && two == null)
            return 0;
        else {
            if (one.getTime() > two.getTime())
                return 1;
            if (one.getTime() == two.getTime())
                return 0;
            return -1;
        }
    }

    /**
     * @param date
     * @return
     */
    public static String formatToFullChinese(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String sdate = sdf.format(date);
        String newsdate[] = new String[8];
        // 全部转化为汉字
        for (int i = 0; i < sdate.length(); i++) {
            int k = Integer.parseInt(Character.toString(sdate.charAt(i)));
            switch (k) {
                case 0:
                    newsdate[i] = "〇";
                    break;
                case 1:
                    newsdate[i] = "一";
                    break;
                case 2:
                    newsdate[i] = "二";
                    break;
                case 3:
                    newsdate[i] = "三";
                    break;
                case 4:
                    newsdate[i] = "四";
                    break;
                case 5:
                    newsdate[i] = "五";
                    break;
                case 6:
                    newsdate[i] = "六";
                    break;
                case 7:
                    newsdate[i] = "七";
                    break;
                case 8:
                    newsdate[i] = "八";
                    break;
                case 9:
                    newsdate[i] = "九";
                    break;
            }
        }
        // 加入年月日
        List<String> s1 = new ArrayList<String>();
        for (int i = 0; i < 8; i++) {
            if (i < 4) {
                s1.add(newsdate[i]);
            } else if (i == 4) {
                s1.add("年");
                s1.add(newsdate[i]);
            } else if (i == 5) {
                s1.add(newsdate[i]);
            } else if (i == 6) {
                s1.add("月");
                s1.add(newsdate[i]);
            } else if (i == 7) {
                s1.add(newsdate[i]);
                s1.add("日");
            }

        }

        String newstr = "";
        for (String s : s1) {
            newstr += s;
        }
        /*
         * 截取月份、日期
         */
        int i = newstr.indexOf("年");
        int j = newstr.indexOf("月");
        String month = newstr.substring(i + 1, j);
        String day = newstr.substring(j + 1, newstr.length() - 1);
        /*
         * 处理月份
         */
        String str1 = month.substring(0, 1);
        String str2 = month.substring(1);
        String newmonth = "";
        if ("〇".equals(str1)) {
            newmonth = str2;
        } else if ("一".equals(str1) && "〇".equals(str2)) {
            newmonth = "十";
        } else if ("一".equals(str1) && !"〇".equals(str2)) {
            newmonth = "十" + str2;
        }

        /*
         * 处理日期
         */
        String st1 = day.substring(0, 1);
        String st2 = day.substring(1);
        String newday = "";
        if ("〇".equals(st1)) {
            newday = st2;
        } else if ("一".equals(st1) && "〇".equals(st2)) {
            newday = "十";
        } else if ("一".equals(st1) && !"〇".equals(st2)) {
            newday = "十" + st2;
        } else if ("二".equals(st1) && "〇".equals(st2)) {
            newday = st1 + "十";
        } else if ("二".equals(st1) && !"〇".equals(st2)) {
            newday = st1 + "十" + st2;
        } else if ("三".equals(st1) && "〇".equals(st2)) {
            newday = st1 + "十";
        } else if ("三".equals(st1) && !"〇".equals(st2)) {
            newday = st1 + "十" + st2;
        }
        String newstring = newstr.substring(0, i) + "年" + newmonth + "月"
                + newday + "日";
        return newstring;
    }

}
