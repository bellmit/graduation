package com.software.utils;



import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

//日期工具类
public class DateUtils {

    public static String DEFAULT_FORMAT="yyyy-MM-dd";
    public static String FORMAT2="yyyy-MM-dd HH:mm:ss";

    //获取某年份的最后一天
    public static Date getYearLast(int year){
        Calendar calendar=Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR,year);
        calendar.roll(Calendar.DAY_OF_YEAR,-1);
        Date curYearLast=calendar.getTime();
        return curYearLast;
    }
    //获取某年份的第一天
    public static  Date getYearFirst(int year){
        Calendar calendar=Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR,year);
        Date curYearFirst=calendar.getTime();
        return curYearFirst;
    }
    //获取当你年份的第一天
    public static Date getCurYearFirst(){
        Calendar cur=Calendar.getInstance();
        int curYear=cur.get(Calendar.YEAR);
        return getYearFirst(curYear);
    }
    //获取当前年份的最后一天
    public static Date getCurYearLast(){
        Calendar cur=Calendar.getInstance();
        int curYear=cur.get(Calendar.YEAR);
        return getYearLast(curYear);
    }
    //格式化日期
    public static String formatDate(Date date){
        SimpleDateFormat f=new SimpleDateFormat(DEFAULT_FORMAT);
        String sDate=f.format(date);
        return sDate;
    }
    //精确化时间格式
    public static String formatDateBySecond(Date date){
        SimpleDateFormat f=new SimpleDateFormat(FORMAT2);
        String sDate=f.format(date);
        return sDate;
    }
    //得到当前年份
    public static  int getCurYear(){
        Calendar calendar=Calendar.getInstance();
        int curYear=calendar.get(Calendar.YEAR);
        return curYear;
    }
    //得到当前月份
    public static  int getCurMonth(){
        Calendar calendar=Calendar.getInstance();
        int curMonth=calendar.get(Calendar.MONTH);
        return curMonth;
    }
    //某个日期的year
    public static int getYear(Date date){
        Calendar bef=Calendar.getInstance();
        bef.setTime(date);
        return bef.get(Calendar.YEAR);
    }
    //计算两个日期之间相差的年龄
    public static  int getDiffYear(Date b,Date a){
        Calendar bef=Calendar.getInstance();
        Calendar aft=Calendar.getInstance();
        //String larq=DateUtils.formatDate(bgrxxModel.getLarq());
        //String csrq=DateUtils.formatDate(bgrxxModel.getCsnyr());
        bef.setTime(b);
        aft.setTime(a);
        int year=aft.get(Calendar.YEAR)-bef.get(Calendar.YEAR);
        return year;
    }
    //获取此刻的时间
    public static Date getIntime(){
        Calendar calendar=Calendar.getInstance();
        Date date=calendar.getTime();
        return date;
    }
    //获取当天零点的时间
    public static Date getZeroIntime(){
        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        Date date=calendar.getTime();
        return date;
    }
}
