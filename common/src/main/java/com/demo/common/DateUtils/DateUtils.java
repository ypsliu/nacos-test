package com.demo.common.DateUtils;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtils {
    private static final String PATTERN_DAY = "yyyy-MM-dd";

    private static final String PATTERN_HOUR = "yyyy-MM-dd HH";

    // 得到上一年的时间
    public static String getLastYear(String str) {
        try{
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            c.setTime(format.parse(str));
            c.add(Calendar.YEAR, -1);
            Date y = c.getTime();
            String year = format.format(y);
            return year;
        }catch (Exception e){
            return null;
        }
    }

    // 得到上个月的时间
    public static String getLastMonth(String str) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(sdf.parse(str));
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
            String date = sdf.format(calendar.getTime());
            return date;
        } catch (ParseException e) {
            return null;
        }
    }


    // 得到前一天的时间
    public static String getNextDay(String str) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(sdf.parse(str));
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            String date = sdf.format(calendar.getTime());
            return date;
        } catch (ParseException e) {
            return null;
        }
    }

    // 得到上一个小时的时间
    public static String getOneHoursAgoTime(String str) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(sdf.parse(str));
            calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) - 1);
            String date = sdf.format(calendar.getTime());
            return date;
        } catch (ParseException e) {
            return null;
        }
    }

    // 得到前十分钟的时间
    public static String getOneHoursAgominute(String str) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(sdf.parse(str));
            calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) - 10);
            String date = sdf.format(calendar.getTime());
            return date;
        } catch (ParseException e) {
            return null;
        }
    }

    //判断是否为本月最后一天
    public static boolean booleantime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, (calendar.get(Calendar.DATE) + 1));
        if (calendar.get(Calendar.DAY_OF_MONTH) == 1) {
            return true;
        }
        return false;
    }

    /**
     * 判断该日期是否是该月的第一天
     *
     * @param date 需要判断的日期
     * @return
     */
    public static boolean isFirstDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        //System.out.println(calendar.get(Calendar.MONTH));
        return calendar.get(Calendar.DAY_OF_MONTH) == 1;
    }

    public static String getDateStr(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    public static Date parseDate(String str) throws ParseException {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.parse(str);
    }

    /**
     * @MethodName addDays
     * @Author wu.xiao.jian
     * @Description 增加天数
     * @Param [date, num]
     * @Return java.util.Date
     * @Date 2018/11/6 17:01
     */

    public static Date addDays(Date date, int num) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, num);
        return cal.getTime();
    }

    /**
     * 日期格式化
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String getDateStr(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String datestr = sdf.format(date);// format  为格式化方法
        return datestr;
    }

    public static Date parseDate(String str, String pattern) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(str);
        } catch (Exception e) {
            return null;
        }
    }

    public static int calcuteMonth(String start, String end) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Calendar bef = Calendar.getInstance();
        Calendar aft = Calendar.getInstance();
        try {
            bef.setTime(sdf.parse(start));
            aft.setTime(sdf.parse(end));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int result = aft.get(Calendar.MONTH) - bef.get(Calendar.MONTH);
        int month = (aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR)) * 12;
       /* System.out.println(month);
        System.out.println(result);
        System.out.println(month + result);*/
        return month + result;
    }

    /**
     * date2比date1多的天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDays(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 != year2)   //同一年
        {
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0)    //闰年
                {
                    timeDistance += 366;
                } else    //不是闰年
                {
                    timeDistance += 365;
                }
            }

            return timeDistance + (day2 - day1);
        } else    //不同年
        {
            //	System.out.println("判断day2 - day1 : " + (day2-day1));
            return day2 - day1;
        }
    }

    public static int getDaysOfMonth(String date, String formate) {
        SimpleDateFormat sdf = new SimpleDateFormat(formate);
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(sdf.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public static void main(String[] args) throws Exception {

        //System.out.println("取小时--"+getOneHoursAgoTime("2012-12-12 12:12:12"));
        //System.out.println("取天--"+getNextDay("2012-12-12 12:12:12"));
        //System.out.println("取月--"+getLastMonth("2012-12-12 12:12:12"));
        //System.out.println(isFirstDayOfMonth((parseDate("2018-12-01 00:00:00"))));
        //calcuteMonth("2018-11", "2017-12");

        Date begin = new Date(2021,6,15,9,30,52);
        Date end = new Date(2021,6,16,0,0,0);
        System.out.println(getBetweenDaysAll(begin,end));

//        System.out.println(getBetweenMonths(parseDate("2021-10-31","yyyy-MM-dd"),parseDate("2021-11-01","yyyy-MM-dd")));
//        new Thread(() -> System.out.println("??")).start();

//        List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");

//        features.parallelStream().forEach(System.out::println);

//        forEach(n -> System.out.println(n));

//        features.forEach(System.out::println);

//        features.stream().forEach(n -> n.concat("hhhh"));
//
//        features.forEach(System.out::println);
//
//        List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
//
//        IntSummaryStatistics stats = primes.stream().mapToInt((x) -> x).summaryStatistics();




    }

    public static SimpleDateFormat getFormat(String pattern) {
        return new SimpleDateFormat(pattern);
    }

    public static Date formatDate(Date source, String pattern) {
        try {
            if (null == source) {
                throw new RuntimeException("source is null");
            }
            return getFormat(pattern).parse(getFormat(pattern).format(source));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return source;

    }

    public static Date addDay(Date source, Integer day) {
        if (null == source) {
            throw new RuntimeException("source is null");
        }
        Calendar instance = Calendar.getInstance();
        instance.setTime(source);
        instance.add(Calendar.DAY_OF_MONTH, day);
        return instance.getTime();
    }

    public static Date addHour(Date source, Integer hour) {
        if (null == source) {
            throw new RuntimeException("source is null");
        }
        Calendar instance = Calendar.getInstance();
        instance.setTime(source);
        instance.add(Calendar.HOUR, hour);
        return instance.getTime();
    }

    public static Date addMonth(Date source, Integer month) {
        if (null == source) {
            throw new RuntimeException("source is null");
        }
        Calendar instance = Calendar.getInstance();
        instance.setTime(source);
        instance.add(Calendar.MONTH, month);
        return instance.getTime();

    }

    public static Date addYear(Date source, Integer year) {
        if (null == source) {
            throw new RuntimeException("source is null");
        }
        Calendar instance = Calendar.getInstance();
        instance.setTime(source);
        instance.add(Calendar.YEAR, year);
        return instance.getTime();

    }

    public static Date firstDayOfMonth(Date source) {
        if (null == source) {
            throw new RuntimeException("source is null");
        }
        Calendar instance = Calendar.getInstance();
        instance.setTime(source);
        instance.set(Calendar.DAY_OF_MONTH, instance.getActualMinimum(Calendar.DAY_OF_MONTH));
        return instance.getTime();
    }

    public static Date lastDayOfMonth(Date source) {
        if (null == source) {
            throw new RuntimeException("source is null");
        }
        Calendar instance = Calendar.getInstance();
        instance.setTime(source);
        instance.set(Calendar.DAY_OF_MONTH, instance.getActualMaximum(Calendar.DAY_OF_MONTH));
        return instance.getTime();
    }

    public static String formatHourStr(Date source) {
        return formatStr(source, PATTERN_HOUR);
    }

    public static String formatStr(Date source) {
        return formatStr(source, PATTERN_DAY);
    }


    public static String formatStr(Date source, String pattern) {
        if (null == source) {
            throw new RuntimeException("source is null");
        }
        return getFormat(pattern).format(source);
    }

    public static Date firstDayOfYear(Date source) {
        if (null == source) {
            throw new RuntimeException("source is null");
        }
        Calendar instance = Calendar.getInstance();
        instance.setTime(source);
        instance.set(Calendar.DAY_OF_YEAR, instance.getActualMinimum(Calendar.DAY_OF_YEAR));
        return instance.getTime();
    }

    public static Date lastDayOfYear(Date source) {
        if (null == source) {
            throw new RuntimeException("source is null");
        }
        Calendar instance = Calendar.getInstance();
        instance.setTime(source);
        instance.set(Calendar.DAY_OF_YEAR, instance.getActualMaximum(Calendar.DAY_OF_YEAR));
        return instance.getTime();
    }

    /**
     * @description : 获取两天之内的所有日期列表(只包含左边) DAYs yyyy-MM-dd 00:00:00
     * @author : chenz G006759
     * @date : Created at 2021/3/25 15:57
     */
    public static List<Date> getBetweenDays(Date beginTime, Date endTime) {
        List<Date> list = new ArrayList<>();
        if(beginTime == null || endTime == null){
            return list;
        }
        if(beginTime.compareTo(endTime) >=0){
            return list;
        }
        endTime = formatDate(endTime, "yyyy-MM-dd");
        Calendar instance = Calendar.getInstance();
        instance.setTime(beginTime);
        while (true){
            //开始循环时间
            Date temp = formatDate(instance.getTime(), "yyyy-MM-dd");
            if(temp.compareTo(endTime) == 0){
                break;
            }
            list.add(temp);
            //日期加1
            instance.add(Calendar.DAY_OF_MONTH, 1);
        }
        return list;
    }

    /**
     * @description : 获取两天之内的所有日期列表(两边都包含) DAYs yyyy-MM-dd 00:00:00
     * @author : chenz G006759
     * @date : Created at 2021/3/25 15:57
     */
    public static List<Date> getBetweenDaysAll(Date beginTime, Date endTime) {
        List<Date> list = new ArrayList<>();
        if(beginTime == null || endTime == null){
            return list;
        }
        endTime = formatDate(endTime, "yyyy-MM-dd");
        Calendar instance = Calendar.getInstance();
        instance.setTime(beginTime);
        while (true){
            //开始循环时间
            Date temp = formatDate(instance.getTime(), "yyyy-MM-dd");
            if(temp.compareTo(endTime) > 0){
                break;
            }
            list.add(temp);
            //日期加1
            instance.add(Calendar.DAY_OF_MONTH, 1);
        }
        return list;
    }

    /**
     * @description : 获取两天之内的所有月份列表(两边都包含) MONTHs yyyy-MM-01 00:00:00
     * @author : chenz G006759
     * @date : Created at 2021/3/25 15:57
     */
    public static List<Date> getBetweenMonths(Date beginTime, Date endTime) {
        List<Date> list = new ArrayList<>();
        if(beginTime == null || endTime == null){
            return list;
        }
        endTime = formatDate(endTime, "yyyy-MM-01");
        Calendar instance = Calendar.getInstance();
        instance.setTime(beginTime);
        while (true){
            //开始循环时间
            Date temp = formatDate(instance.getTime(), "yyyy-MM-01");
            if(temp.compareTo(endTime) > 0){
                break;
            }
            list.add(temp);
            //月份加1
            instance.add(Calendar.MONTH, 1);
        }
        return list;
    }

    /**
     * @description : 获取两天之内的所有年列表(两边都包含) years yyyy
     * @author : chenz G006759
     * @date : Created at 2021/3/25 15:57
     */
    public static List<String> getBetweenYears(Date beginTime, Date endTime) {
        List<String> list = new ArrayList<>();
        if(beginTime == null || endTime == null){
            return list;
        }
        if(beginTime.compareTo(endTime) >=0){
            return list;
        }
        endTime = formatDate(endTime, "yyyy");
        Calendar instance = Calendar.getInstance();
        instance.setTime(beginTime);
        while (true){
            //开始循环时间
            Date temp = formatDate(instance.getTime(), "yyyy");
            if(temp.compareTo(endTime) > 0){
                break;
            }
            list.add(formatStr(temp,"yyyy"));
            //月份加1
            instance.add(Calendar.YEAR, 1);
        }
        return list;
    }

}
