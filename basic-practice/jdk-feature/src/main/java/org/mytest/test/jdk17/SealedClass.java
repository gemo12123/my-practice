package org.mytest.test.jdk17;

/**
 * 密封类。jdk15 提出，jdk17 转正
 * sealed：继续延续密封类特性，可以继续指定继承的类，并传递密封定义给子类
 * non-sealed：声明这个类为非密封类，可以被任意继承
 * final：不允许继承
 */
public class SealedClass {
    /**
     * sealed类，指定Dog可以继承
     */
    public static sealed class Animal permits Dog{}

    /**
     * Dog类，因为有permits，所以可以继承Animal，且声明sealed指定LabradorDog继承
     */
    public static sealed class Dog extends Animal permits LabradorDog{}

    /**
     * LabradorDog类，声明non-sealed
     */
    public static non-sealed class LabradorDog extends Dog{}

    /**
     * SmallLabradorDog,继承自LabradorDog，因为其声明为non-sealed所以可继承
     */
    public static class SmallLabradorDog extends LabradorDog{}

    /**
     * CorgiDog类，未被Dog permit声明，不可以继承
     */
    public static class CorgiDog
//            extends Dog
    {}

}
