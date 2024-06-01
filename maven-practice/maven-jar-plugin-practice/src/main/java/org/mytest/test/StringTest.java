package org.mytest.test;

public class StringTest {
    public static void main(String[] args) {
        String s = "  ";
        System.out.println("isEmpty result:" + StringUtils.isEmpty(s));
        System.out.println("isBlank result:" + StringUtils.isBlank(s));

    }
}
