package com.demo.common.DateUtils;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;

public class DateUtils {

    /**
     * DateTimeFormatter 代替 SimpleDateFormat，官方给出的解释：simple beautiful strong immutable thread-safe
     */

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static ThreadLocal<DateFormat>  DATE_FORMAT= new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    private static Date parse(String date, ThreadLocal<DateFormat> df){
        try {
            return df.get().parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            df.remove();
        }
        return null;
    }

    public static void main(String[] args) {
        for(int i=0;i<10;i++){
            new Thread(
                    ()->{
                        System.out.println(Thread.currentThread().getName()+""+DateUtils.parse("2022-03-17 00:00:00",DateUtils.DATE_FORMAT));
                    }
            ).start();
        }
        for(int i=0;i<10;i++){
            new Thread(
                    ()->{
                        System.out.println(Thread.currentThread().getName()+""+DateUtils.parse("2021-03-17 00:00:00",DateUtils.DATE_FORMAT));
                    }
            ).start();
        }
        for(int i=0;i<10;i++){
            new Thread(
                    ()->{
                        System.out.println(Thread.currentThread().getName()+""+DateUtils.parse("2020-03-17 00:00:00",DateUtils.DATE_FORMAT));
                    }
            ).start();
        }

    }
}
