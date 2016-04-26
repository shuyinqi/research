package com.shuyinqi.joda;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static org.junit.Assert.assertEquals;

/**
 * Created by jiayusun on 2016/4/22.
 */
public class JodaTimeTest {


    public void testJodaTime(){
        //final DateTime
        DateTime dateTime= new DateTime(2016,4,12,15,22,30);
        //得到1年1个月1天1小时之后的日期
        assertEquals(new DateTime(2017,5,13,16,22,30),dateTime.plusYears(1).plusMonths(1).plusDays(1).plusHours(1));
        //把小时改成指定的13点
        assertEquals(new DateTime(2016,4,12,13,22,30),dateTime.withHourOfDay(13));

        String dateStr="2016-04-12 15:22:30";
        //String转DateTime
        DateTime parsedDateTime = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime(dateStr);
        assertEquals(dateTime,parsedDateTime);
        //1.DateTime转String
        String strnew = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").print(dateTime);
        assertEquals(strnew,dateStr);
        //2.DateTime转String
        assertEquals(dateStr,dateTime.toString("yyyy-MM-dd HH:mm:ss"));
        //截取精确到天
        DateTime tillDay = dateTime.dayOfMonth().roundFloorCopy();
        assertEquals(new DateTime(2016,4,12,0,0,0),tillDay);


        DateTime dateTime2 = new DateTime(2014, 12, 2, 8, 12, 45);
        //指定时分秒为23:59:59
        assertEquals(new DateTime(2014, 12, 2, 23, 59, 59), dateTime2.withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59));
        //取一天中的最后一秒（秒的最大值）
        assertEquals(new DateTime(2014, 12, 2, 23, 59, 59), dateTime2.secondOfDay().withMaximumValue());
        //得到当天的00:00:00
        assertEquals(new DateTime(2014, 12, 2, 0, 0, 0), dateTime2.secondOfDay().withMinimumValue());
    }
    @Test
    public void testJodaTime2(){
        DateTime dateTime=new DateTime(2012, 12, 13, 18, 23,55);
        // 年,月,日,时,分,秒,毫秒
        DateTime dt3 = new DateTime(2011, 2, 13, 10, 30, 50, 333);
        //下面就是按照一点的格式输出时间
        String str2 = dateTime.toString("MM/dd/yyyy hh:mm:ss.SSSa");
        String str3 = dateTime.toString("dd-MM-yyyy HH:mm:ss");
        String str4 = dateTime.toString("EEEE dd MMMM, yyyy HH:mm:ssa");
        String str5 = dateTime.toString("MM/dd/yyyy HH:mm ZZZZ");
        String str6 = dateTime.toString("MM/dd/yyyy HH:mm Z");

        DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        //时间解析
        DateTime dateTime2 = DateTime.parse("2012-12-21 23:22:45", format);
        //时间格式化，输出==> 2012/12/21 23:22:45 Fri
        String string_u = dateTime2.toString("yyyy/MM/dd HH:mm:ss EE");
        System.out.println(string_u);

        //格式化带Locale，输出==> 2012年12月21日 23:22:45 星期五
        String string_c = dateTime2.toString("yyyy年MM月dd日 HH:mm:ss EE", Locale.CHINESE);
        System.out.println(string_c);

        DateTime dt1 = new DateTime();// 取得当前时间
        // 根据指定格式,将时间字符串转换成DateTime对象,这里的格式和上面的输出格式是一样的
        DateTime dt2 = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime("2012-12-26 03:27:39");

        //计算两个日期间隔的天数
        LocalDate start=new LocalDate(2012, 12,14);
        LocalDate end=new LocalDate(2013, 01, 15);
        int days = Days.daysBetween(start, end).getDays();

        //增加日期
        DateTime dateTime1 = DateTime.parse("2012-12-03");
        dateTime1 = dateTime1.plusDays(30);
        dateTime1 = dateTime1.plusHours(3);
        dateTime1 = dateTime1.plusMinutes(3);
        dateTime1 = dateTime1.plusMonths(2);
        dateTime1 = dateTime1.plusSeconds(4);
        dateTime1 = dateTime1.plusWeeks(5);
        dateTime1 = dateTime1.plusYears(3);
        // Joda-time 各种操作.....
        dateTime = dateTime.plusDays(1) // 增加天
                .plusYears(1)// 增加年
                .plusMonths(1)// 增加月
                .plusWeeks(1)// 增加星期
                .minusMillis(1)// 减分钟
                .minusHours(1)// 减小时
                .minusSeconds(1);// 减秒数

        //判断是否闰月
        DateTime dt4 = new DateTime();
        DateTime.Property month = dt4.monthOfYear();
        System.out.println("是否闰月:" + month.isLeap());
        //取得 3秒前的时间
        DateTime dt5 = dateTime1.secondOfMinute().addToCopy(-3);
        dateTime1.getSecondOfMinute();// 得到整分钟后，过的秒钟数
        dateTime1.getSecondOfDay();// 得到整天后，过的秒钟数
        dateTime1.secondOfMinute();// 得到分钟对象,例如做闰年判断等使用

        // DateTime与java.util.Date对象,当前系统TimeMillis转换
        DateTime dt6 = new DateTime(new Date());
        Date date = dateTime1.toDate();
        DateTime dt7 = new DateTime(System.currentTimeMillis());
        dateTime1.getMillis();

        Calendar calendar = Calendar.getInstance();
        dateTime = new DateTime(calendar);



    }
}
