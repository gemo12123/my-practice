package org.mytest.test.commons.pool;

import org.apache.commons.pool2.impl.GenericKeyedObjectPool;
import org.apache.commons.pool2.impl.GenericKeyedObjectPoolConfig;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Test;

import java.time.Duration;

public class PoolTest {

    public void conf(){

        /**
         * maxTotal：对象池中最大使用数量，默认为8.
         * maxIdle：对象池中空闲对象的最大数量，默认为8.
         * minIdle：对象池中空闲对象的最小数量，默认为8.
         * lifo：当获取对象池中的空闲实例时，是否需要遵循后进先出的原则，默认为true。
         * blockWhenExhausted：当对象处于exhausted状态，即可用实例为空时，是否阻塞来获取实例的线程，默认为true。
         * fairness：当对象池处于exhausted状态，即可用实例为空时，大量线程在同时阻塞等待获取可用的实例，
         *      fairness配置来控制是否启用公平锁算法，即先到先得，默认为false，这一项的前提是blockWhenExhausted配置为true。
         * maxWaitMillis：最大阻塞时间，当对象池处于exhausted状态，即可用实例为空时，大量线程同时阻塞等待获取可用的实例，
         *      如果阻塞时间超过了maxWaitMillis将会抛出异常，当此值为负时，代表无限期阻塞直到可用，默认为-1；
         * testOnCreate：创建对象前是否校验（即调用工厂的validateObject()方法），如果校验失败，那么borrowObject()返回将失败，默认为false。
         * testOnBorrow：取用对象前是否校验，默认为false。
         * testOnReturn：返回对象池前是否检验，即调用工程的returnObject()，若检验失败会销毁对象而不是返回池中，默认为false。
         * testBetweenEvictionRunsMillis：驱逐周期，默认为-1代表不仅区局测试。
         * testWhileIdle：处于idle队列中即闲置的对象是否被驱逐器进行驱逐验证，当该对象上次运行时间距当前超过setTimeBetweenEvictionRunsMillis(long)设置的值，
         *      将会被驱逐验证，调用validateObject()方法，若验证成功，对象将会销毁，默认为false。
         */
        GenericObjectPoolConfig<Student> conf = new GenericObjectPoolConfig<>();
        // 对象总数
        conf.setMaxTotal(1);
        // 空闲对象数
        conf.setMaxIdle(1);
        //一定要关闭jmx，不然springboot启动会报已经注册了某个jmx的错误
        conf.setJmxEnabled(false);
    }
    @Test
    public void test01() {
        GenericObjectPoolConfig<Student> conf = new GenericObjectPoolConfig<>();
        // 对象总数
        conf.setMaxTotal(1);
        // 空闲对象数
        conf.setMaxIdle(1);
        //一定要关闭jmx，不然springboot启动会报已经注册了某个jmx的错误
        conf.setJmxEnabled(false);
        GenericObjectPool<Student> pool = new GenericObjectPool<>(new StudentPoolFactory(),conf);
        try {
            // borrow和return都会抛出异常，建议在finally中处理return
            Student student = pool.borrowObject();
            System.out.println(student + "......" + student.getName());
            student.setName("zs");
            pool.returnObject(student);

            // 如果指定时间借用不到对象，会抛出NoSuchElementException
            Student student2 = pool.borrowObject(Duration.ofMillis(100));
            System.out.println(student2 + "......" + student2.getName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            pool.close();
        }
    }

    @Test
    public void test02() {
        GenericKeyedObjectPoolConfig<Student> conf = new GenericKeyedObjectPoolConfig<>();
        // 不区分key
        conf.setMaxTotal(2);
        // 区分key
        conf.setMaxTotalPerKey(2);
        conf.setMaxIdlePerKey(2);
        GenericKeyedObjectPool<String, Student> pool = new GenericKeyedObjectPool<>(new StudentKeyedPoolFactory(), conf);
        try {
            Student zs = pool.borrowObject("zs");
            System.out.println(zs+"....."+zs.getName());
            pool.returnObject("zs", zs);
            Student ls = pool.borrowObject("ls");
            System.out.println(ls+"....."+ls.getName());
            Student zss = pool.borrowObject("zs");
            System.out.println(zss+"....."+zss.getName());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.close();
        }
    }
}
