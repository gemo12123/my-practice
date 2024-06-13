package org.mytest.test.date;

import org.junit.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * LocalDate & LocalDateTime 使用
 */
public class NewDataTest {
    @Test
    public void testLocalDate(){
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy年MM月dd");
        System.out.println(dtf.format(localDate));

        LocalDate parseDate = LocalDate.parse("2023年05月01", dtf);
        System.out.println(parseDate);
    }

    @Test
    public void testLocalDateTime(){
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy年MM月dd HH:mm:ss SSS");
        System.out.println(dtf.format(localDateTime));

        LocalDateTime parseDateTime = LocalDateTime.parse("2023年05月01 14:52:36 001", dtf);
        System.out.println(parseDateTime);

        // 获取时间戳
        Instant instant = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant();
        long timestamp = instant.toEpochMilli();
        System.out.println(timestamp);
        System.out.println(System.currentTimeMillis());
    }
}
