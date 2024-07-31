package org.mytest.test.date;

import org.junit.Test;

import java.time.Instant;

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

    }
}
