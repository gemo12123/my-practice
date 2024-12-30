package org.mytest.test.jdk11;

import java.util.stream.Collectors;

/**
 * String 增强
 */
public class StringEnhance {
    public static void main(String[] args) {
        jdk11();
        jdk12();
    }

    private static void jdk11() {
        //判断字符串是否为空
        System.out.println(" ".isBlank());
        //去除字符串首尾空格
        System.out.println(" Java ".strip());
        //去除字符串首部空格
        System.out.println(" Java ".stripLeading());
        //去除字符串尾部空格
        System.out.println(" Java ".stripTrailing());
        //重复字符串多少次
        System.out.println("Java".repeat(3));
        //返回由行终止符分隔的字符串集合。
        System.out.println("A\nB\nC".lines().count());
        System.out.println("A\nB\nC".lines().collect(Collectors.toList()));
    }

    private static void jdk12() {
        // 缩进 4 格
        String text = "Hello World!";
        text = text.indent(4);
        System.out.println(text);
        text = text.indent(-10);
        System.out.println(text);
        // 转变指定字符串
        String result = "foo".transform(input -> input + " bar");
        System.out.println(result); // foo bar
    }
}
