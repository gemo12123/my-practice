package org.mytest.test.jdk14;

public record RecordClass(String name, int age) {

    public static void main(String[] args) {
        RecordClass john = new RecordClass("John", 17);
        System.out.println(john);
        System.out.println(john.name);
        System.out.println(john.age);
        // john.name = "ls";
    }

}
