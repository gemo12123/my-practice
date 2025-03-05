package org.mytest.test.jdk21.concurrency;

/**
 * VirtualThread 虚拟线程 Bug（JDK21）:
 * 当前的Virtual Thread在遇到需要被调度出去的事件时，由于JVM内部实现的原因无法从当前的Carrier Thread移除，
 * 而是选择一直占用当前的Carrier Thread。
 * 典型的虚拟线程pin问题主要由以下几个原因导致：
 * 1.遇到了类加载、初始化事件
 * 2.遇到了synchronized代码块。JEP491 解决了synchronized问题
 * 3.应用中调用了native c代码
 */
public class VirtualThreadBugs {
    public static void main(String[] args) throws InterruptedException {
//        syncBug();
        noSync();
    }

    /**
     * 阻塞平台线程
     *
     * @throws InterruptedException
     */
    private static void syncBug() throws InterruptedException {
        Object obj = new Object();
        for (int i = 0; i < 15; i++) {
            int j = i;
            Thread.ofVirtual()
                    .start(() -> {
                        System.out.println(Thread.currentThread() + "====" + j + " is running");
                        synchronized (obj) {
                            try {
                                Thread.sleep(3 * 1000);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });
        }
        Thread.sleep(15 * 1000);
        System.out.println("main end");
    }

    /**
     * 不阻塞平台线程
     *
     * @throws InterruptedException
     */
    private static void noSync() throws InterruptedException {
        Object obj = new Object();
        for (int i = 0; i < 15; i++) {
            int j = i;
            Thread.ofVirtual()
                    .start(() -> {
                        System.out.println(Thread.currentThread() + "====" + j + " is running");
                        try {
                            Thread.sleep(3 * 1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    });
        }
        Thread.sleep(15 * 1000);
        System.out.println("main end");
    }
}
