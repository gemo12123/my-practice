package org.mytest.test.date;

import org.junit.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * 时间戳
 */
public class TimestampTest {
    @Test
    public void test01() {
        System.out.println(System.currentTimeMillis());
        System.out.println(Instant.now().toEpochMilli());
    }

    @Test
    public void testInstant() {
        Instant now = Instant.now();
        System.out.println(now);
        System.out.println(now.toEpochMilli());

        // 毫秒转Instant
        long timeMillis = System.currentTimeMillis();
        System.out.println(Instant.ofEpochMilli(timeMillis));
        System.out.println(Instant.ofEpochSecond(timeMillis / 1000));

        // LocalDateTime 转 Instant
        System.out.println(LocalDateTime.now().toInstant(ZoneOffset.of("+8")));

    }
}
