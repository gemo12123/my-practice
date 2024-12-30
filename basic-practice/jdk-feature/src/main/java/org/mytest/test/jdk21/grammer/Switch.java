package org.mytest.test.jdk21.grammer;

import java.util.ArrayList;

/**
 * Switch 增强
 * jdk12 引入了类似 lambda 语法条件匹配成功后的执行块，不需要多写 break，
 * jdk13 提供了 yield 来在 block 中返回值,
 * jdk14 转正。
 * jdk21 增强了 switch 的类型模式匹配
 */
public class Switch {
    public static void main(String[] args) {
        System.out.println(format(10));
        System.out.println(format(20L));
        System.out.println("switch test");
        System.out.println(format(new ArrayList<>()));
        System.out.println(format(new Shape("box", 16)));
        System.out.println(format(new InstanceOf.Shape("circle", 314)));
    }

    public static String format(Object o) {
        return switch (o) {
            case Integer i -> "int i: " + i;
            case Double d -> "double d: " + d;
            case Float f -> "float f: " + f;
            case Long l -> "long l: " + l;
            case String s -> "String s: " + s;
            case Shape(String type, long unit) -> "switch shape type: " + type + " unit: " + unit;
            case InstanceOf.Shape shape -> shape.toString();
            default -> "unknown type:" + o.getClass().getName();
        };
    }

    public static record Shape(String type, long unit) {
    }
}
