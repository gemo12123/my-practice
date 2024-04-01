package org.mytest.test.guava.create;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedSet;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.util.Arrays;

/**
 * 不可变集合
 */
public class GuavaImmutableSetTest {

    /**
     * 创建
     */
    @Test
    public void test01() {
        // 默认是RegularImmutableSet
        ImmutableSet<String> color = ImmutableSet.of("red", "orange", "yellow", "green", "blue", "purple", "red");
        System.out.println(color);
        ImmutableSortedSet<String> names = ImmutableSortedSet.copyOf(Arrays.asList("zs", "ls"));
        System.out.println(names);
        ImmutableList<Integer> ages = ImmutableList.<Integer>builder()
                .add(15)
                .add(12)
                .add(15)
                .add(12)
                .build();
        System.out.println(ages);
    }

    /**
     * 不可变集合只是指元素不可增删，元素对象本身是可以改变的
     */
    @Test
    public void test02() {
        Student zs = new Student("zs", 10);
        Student ls = new Student("ls", 10);
        ImmutableSet<Student> students = ImmutableSet.of(zs, ls);
        System.out.println(students);
        ls.setAge(16);
        System.out.println(students);
        students.add(new Student("ww", 10));
    }

    @Data
    @AllArgsConstructor
    public static class Student {
        private String name;
        private int age;

    }
}
