package org.mytest.test.date;

import org.junit.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

/**
 * Duration & Period
 */
public class TimeRangeTest {

    /**
     * Duration：时间间隔
     * 以秒或纳秒度量， 不使用基于 Date 的结构
     * 可以是负值，表示在开始点之前发生的端点创建的。
     */
    @Test
    public void testDuration() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime localDateTime = LocalDateTime.of(2024, 1, 7, 2, 5, 53, 111);

        Duration between = Duration.between(localDateTime, now);
        System.out.println(between);
        System.out.println(between.toDays());
        System.out.println(between.toHours());
    }

    /**
     * Period：日期间隔
     * 基于年、月、日构建时间间隔
     */
    @Test
    public void testPeriod() {
        LocalDate now = LocalDate.now();
        LocalDate localDateTime = LocalDate.of(2024, 1, 7);

        Period between = Period.between(localDateTime, now);
        System.out.println(between);
        System.out.println(between.getYears());
        System.out.println(between.getMonths());
        System.out.println(between.getDays());
    }

    /**
     * ChronoUnit：一个单位的时间内测量一段时间
     */
    @Test
    public void testChronoUnit() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime localDateTime = LocalDateTime.of(2024, 1, 7, 2, 5, 53, 111);

        long between = ChronoUnit.DAYS.between(localDateTime, now);
        System.out.println(between);
    }
}
