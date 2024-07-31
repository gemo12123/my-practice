package org.mytest.test.date;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

/**
 * 时区
 */
public class ZoneTest {
    @Test
    public void test01() {
        ZoneId defaultZoneId = ZoneId.systemDefault();
        System.out.println(defaultZoneId);
        for (String zoneId : ZoneId.getAvailableZoneIds()) {
            System.out.println(zoneId);
        }
    }

    @Test
    public void test02() {
        System.out.println(ZoneId.of("Asia/Shanghai"));
        System.out.println(ZoneId.of("UTC"));
        System.out.println(ZoneId.of("UTC+8"));
        System.out.println(ZoneId.of("UTC-8"));
        System.out.println(ZoneId.of("+8"));
        System.out.println(LocalDateTime.now(ZoneId.of("+8")));
    }

    @Test
    public void test03() {
        // LocalDateTime是不带时区信息的时区当前时间
        LocalDateTime now = LocalDateTime.now(ZoneId.of("UTC"));
        // 获取到的字符串可以通过ZoneId.of获取实例
        ZoneId zone = ZoneId.of("Asia/Aden");
        // 把本地时间加时区信息 转换成一个ZonedDateTime
        ZonedDateTime zdt = now.atZone(zone);
        System.out.println(zdt);
        ZoneOffset offset = zdt.getOffset();
        System.out.println(offset);
    }
}
