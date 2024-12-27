package org.mytest.test.jdk21.concurrency;

import java.util.Random;
import java.util.concurrent.*;

public class VirtualThread {
    public static void main(String[] args) {
//        vThreadSuspend();
//        vThreadPool();
    }

    /**
     * 测试虚拟线程池
     *
     * @throws InterruptedException
     * @throws ExecutionException
     */
    private static void vThreadPool() throws InterruptedException, ExecutionException {
        ExecutorService virtualThreadPool = Executors.newVirtualThreadPerTaskExecutor();
        CompletionService<Integer> ecs = new ExecutorCompletionService<>(virtualThreadPool);
        for (int i = 0; i < 10; i++) {
            int j = i;
            Callable<Integer> unstarted = () -> {
                int sleepTime = 100 * new Random().nextInt(10);
                System.out.println(j + " is sleeping with " + sleepTime);
                Thread.sleep(sleepTime);
                return sleepTime;
            };
            ecs.submit(unstarted);
        }
        System.out.println("submit end.....");

        for (int i = 0; i < 10; i++) {
            System.out.println(ecs.take().get());
        }
        System.out.println("end.....");
    }

    /**
     * 测试虚拟线程挂起
     *
     * @throws InterruptedException
     */
    private static void vThreadSuspend() throws InterruptedException {
        System.setProperty("jdk.virtualThreadScheduler.parallelism", "1");

        for (int i = 0; i < 10; i++) {
            int j = i;
            Thread.startVirtualThread(() -> {
                System.out.println(j + "is working");
                while (true) {
                    try {
                        // 控制阻塞虚拟线程挂起
                        Thread.sleep(10);
                    } catch (Exception e) {
                    }
                }
            });
            // 避免print时阻塞导致现象不明显
            Thread.sleep(10);
        }
        Thread.sleep(2000);
    }
}
