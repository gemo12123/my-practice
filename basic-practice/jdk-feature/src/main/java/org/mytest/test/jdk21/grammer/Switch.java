package org.mytest.test.jdk21.grammer;

import java.util.ArrayList;

public class Switch {
    public static void main(String[] args) {
        System.out.println(format(10));
        System.out.println(format(20L));
        System.out.println("switch test");
        System.out.println(format(new ArrayList<>()));
    }

    public static String format(Object o) {
        return switch (o) {
            case Integer i -> "int i: " + i;
            case Double d -> "double d: " + d;
            case Float f -> "float f: " + f;
            case Long l -> "long l: " + l;
            case String s -> "String s: " + s;
            default -> "unknown type:" + o.getClass().getName();
        };
    }
}
