/**
 * SPI: Service Provider Interface
 * SPI 是 JDK 内置的一种服务提供发现机制，能够让第三方为 Java 程序提供服务。其可以动态加载接口/抽象类对应的具体实现。
 * JDK SPI规范：
 *  1.SPI 定义的文件必须放在根目录的META-INF/services目录下，
 *  2.文件名为接口或抽象类的全限定名，
 *  3.文件内容为接口/抽象类的具体实现类的全限定名（按行分割）。
 *
 * @author gemo
 * @date 2024/3/30 18:50
 */
package org.mytest.test;