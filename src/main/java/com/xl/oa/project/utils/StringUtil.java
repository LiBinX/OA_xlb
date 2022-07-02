package com.xl.oa.project.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class StringUtil {

    /**
     * 返回指定格式的日期字符串
     * @param date
     * @param formatter
     * @return
     */
    public static String getFormatterDate(Date date, String formatter){
        SimpleDateFormat sdf = new SimpleDateFormat(formatter);
        return sdf.format(date);
    }

    /**
     * 判断请求是否是ajax
     * @param request
     * @return
     */
    public static boolean isAjax(HttpServletRequest request){
        String header = request.getHeader("X-Requested-With");
        if("XMLHttpRequest".equals(header))return true;
        return false;
    }

    /**
     * 从流读取字符串
     * @param inputStream
     * @return
     */
    public static String getStringFromInputStream(InputStream inputStream){
        String string = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"GB2312"));
            String buf = null;
            try {
                while((buf = bufferedReader.readLine()) != null){
                    string += buf;
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }finally{
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return string;
    }

    /**
     * 根据日期获取当天是周几
     * @param date 日期
     * @return 周几
     */
    public static String dateToWeek(Date date) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String datetime = sdf.format(date);
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        try {
            date = sdf.parse(datetime);
            cal.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        return weekDays[w];
    }

    public static boolean isNow(Date date){
        //当前时间
        Date now = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        //获取字符串类型今天的日期
        String nowDay = sf.format(now);

        //对比的时间
        String day = sf.format(date);
        return day.equals(nowDay);
    }
}
