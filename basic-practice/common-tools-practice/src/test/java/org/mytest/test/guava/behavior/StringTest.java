package org.mytest.test.guava.behavior;

import com.google.common.base.CaseFormat;
import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import org.junit.Test;

import java.util.Arrays;

public class StringTest {
    /**
     * 字符串连接
     * Joiner是线程安全的
     */
    @Test
    public void test01() {
        String a = "123";
        String b = "456";
        String c = "789";
        String i = null;

        System.out.println(Joiner.on("; ")
                .skipNulls()
                .join(a, b, c, i));
        System.out.println(Joiner.on("; ")
                .useForNull("default")
                .join(a, b, c, i));
        System.out.println(String.join(";", a, b, c, i));
    }

    /**
     * 字符串切分
     * Splitter
     */
    @Test
    public void test02() {
        String str = "www  zhangsan;;lisi;wxw;;";
        // String.split会丢弃了尾部的分隔符，少一个空字符串
        System.out.println(Arrays.asList(str.split(";")));

        System.out.println(Splitter.on(";")
                .split(str));
        System.out.println(Splitter.on(";")
                // 移除结果字符串的前导空白和尾部空白
                .trimResults()
                // 从结果中自动忽略空字符串
                .omitEmptyStrings()
                .split(str));
        System.out.println(Splitter.on(";")
                // 移除结果字符串的指定前导和尾部
                .trimResults(CharMatcher.anyOf("w"))
                .split(str));
    }

    /**
     * CharMatcher
     * 注：CharMatcher只处理char类型代表的字符；它不能理解0x10000到0x10FFFF的Unicode 增补字符。
     * 这些逻辑字符以代理对[surrogate pairs]的形式编码进字符串，而CharMatcher只能将这种逻辑字符看待成两个独立的字符。
     */
    @Test
    public void test03() {
        // 将匹配CharMatcher的给定字符串替换为特定字符。
        System.out.println(CharMatcher.anyOf("连续的").collapseFrom("把每组连续的匹配字符替换为特定字符", 't'));
        System.out.println(CharMatcher.anyOf("eko").collapseFrom("bookkeepr", '-'));
        // 给定字符串是否匹配CharMatcher
        System.out.println(CharMatcher.anyOf("eko").matchesAllOf("obookoe"));
        System.out.println(CharMatcher.anyOf("eko").matchesAllOf("oeookoe"));
    }

    /**
     * CaseFormat:
     * LOWER_CAMEL，例如：lowerCamel
     * LOWER_HYPHEN，例如：lower-hyphen
     * LOWER_UNDERSCORE，例如：lower_underscore
     * UPPER_CAMEL，例如：UpperCamel
     * UPPER_UNDERSCORE，例如：UPPER_UNDERSCORE
     */
    @Test
    public void test04() {
        System.out.println(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL,
                "CONSTANT_NAME"));
    }
}
