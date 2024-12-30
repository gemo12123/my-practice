package org.mytest.test.jdk21.concurrency;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.StructuredTaskScope;

/**
 * StructConcurrency 结构化并发，孵化阶段
 */
public class StructConcurrency {
    public static void main(String[] args) {
        structConcurrency();
//        structConcurrencyOnFailure();
//        structConcurrencyOnSuccess();
    }

    /**
     * 结构化并发：StructuredTaskScope.ShutdownOnSuccess
     * 第一个任务成功关闭Scope
     */
    private static void structConcurrencyOnSuccess() {
        try (var sts = new StructuredTaskScope.ShutdownOnSuccess<Integer>()) {
            Callable<Integer> t1 = () -> {
                int sleepTime = 100 * new Random().nextInt(5);
                Thread.sleep(sleepTime);
                return sleepTime;
            };
            Callable<Integer> t2 = () -> {
                int sleepTime = 100 * new Random().nextInt(10, 15);
                Thread.sleep(sleepTime);
                return sleepTime;
            };
            StructuredTaskScope.Subtask<Integer> r1 = sts.fork(t1);
            StructuredTaskScope.Subtask<Integer> r2 = sts.fork(t2);

            sts.join();
            // 有一个返回即可获取result，但是get()可能因为未完成而报错
            System.out.println(sts.result());
            System.out.println(r1.state());
            System.out.println(r2.state());
//            System.out.println(r1.get());
//            System.out.println(r2.get());
        } catch (Exception e) {
            System.out.println("error is happened!");
        }
    }

    /**
     * 结构化并发：StructuredTaskScope.ShutdownOnFailure
     * 第一个任务失败关闭Scope
     */
    private static void structConcurrencyOnFailure() {
        try (var sts = new StructuredTaskScope.ShutdownOnFailure()) {
            Callable<Integer> t1 = () -> {
                int sleepTime = 100 * new Random().nextInt(10);
                Thread.sleep(sleepTime);
                return sleepTime;
            };
            Callable<Integer> t2 = () -> {
                int sleepTime = 100 * new Random().nextInt(10);
                Thread.sleep(sleepTime);
                throw new RuntimeException("task error!");
            };
            StructuredTaskScope.Subtask<Integer> r1 = sts.fork(t1);
            StructuredTaskScope.Subtask<Integer> r2 = sts.fork(t2);

            sts.join();
            System.out.println(r1.state());
            System.out.println(r2.state());
            sts.throwIfFailed();
            System.out.println(r1.get());
            System.out.println(r2.get());
        } catch (Exception e) {
            System.out.println("error is happened!" + e.getMessage());
        }
    }

    /**
     * 结构化并发：StructuredTaskScope
     */
    private static void structConcurrency() {
        try (var sts = new StructuredTaskScope<Integer>()) {
            Callable<Integer> t1 = () -> {
                int sleepTime = 100 * new Random().nextInt(10);
                Thread.sleep(sleepTime);
                return sleepTime;
            };
            Callable<Integer> t2 = () -> {
                int sleepTime = 100 * new Random().nextInt(10);
                Thread.sleep(sleepTime);
                throw new RuntimeException();
            };
            StructuredTaskScope.Subtask<Integer> r1 = sts.fork(t1);
            StructuredTaskScope.Subtask<Integer> r2 = sts.fork(t2);

            sts.join();
            // 失败任务未完成get()会抛出异常
            System.out.println(r1.get());
            System.out.println(r2.get());
            System.out.println("end....");
        } catch (Exception e) {
            System.out.println("error is happened!");
        }
    }
}
