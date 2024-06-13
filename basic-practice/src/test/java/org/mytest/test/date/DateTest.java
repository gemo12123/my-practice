package org.mytest.test.date;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * java util Date使用
 */
public class DateTest {

    @Test
    public void test01() throws ParseException {
        Date date = new Date();
        System.out.println(date);

        // SimpleDateFormat线程不安全
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        System.out.println(sdf.format(date));
        Date parseDate = sdf.parse("2012-02-11 05:32:11 000");
        System.out.println(parseDate);

        // 获取时间戳
        System.out.println(date.getTime());
    }
}
