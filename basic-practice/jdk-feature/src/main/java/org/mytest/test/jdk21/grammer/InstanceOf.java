package org.mytest.test.jdk21.grammer;

/**
 * InstanceOf 增强
 */
public class InstanceOf {

    public static void main(String[] args) {
        instanceOf(new InstanceOfA());
        instanceOf(new InstanceOfB());
        instanceOf(new Shape("circle", 10));
    }

    public static void instanceOf(Object o) {
        if (o instanceof InstanceOfA a) {
            a.a();
        }
        if (o instanceof InstanceOfB b) {
            b.b();
        }
        if (o instanceof Shape(String type, long unit)) {
            System.out.println("Shape type is " + type + ", unit is " + unit);
        }
    }

    public static record Shape(String type, long unit) {
    }


    public static class InstanceOfA {
        String name;
        String age;

        public void a() {
            System.out.println("I am InstanceOfA");
        }
    }

    public static class InstanceOfB {
        String country;
        String address;


        public void b() {
            System.out.println("I am InstanceOfB");
        }
    }
}
