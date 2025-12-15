package org.mytest.test.guava.create;

import com.google.common.util.concurrent.RateLimiter;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * RateLimiter 令牌桶限流
 *
 * @author gemo
 * @date 2025/12/15 14:08
 */
public class GuavaRateLimiterTest {

    @Test
    public void test01() throws InterruptedException {
        // RateLimiter.create(5)：SmoothBursty模式，每秒产生5个令牌，即200ms产生一个令牌
        // RateLimiter.create(10, 30, TimeUnit.SECONDS)：SmoothWarmingUp模式，启动后 30 秒内逐渐提升到 10 QPS，防止系统冷启动被瞬间打爆
        // 注意：二者刚启动时都支持借未来，只是刚启动时的速率不同
        RateLimiter limiter = RateLimiter.create(5);

        // acquire 阻塞式，返回值是等待了多久（秒）
        // tryAcquire 非阻塞式，返回值是是否成功获取令牌
        double waitTime = limiter.acquire(50);
        System.out.println("等待时间：" + waitTime);

        /**
         * RateLimiter 限制的是速率，不是并发
         * acquire:获取指定数量的令牌，会阻塞，直到拿到令牌
         * tryAcquire:在不超过指定时间内是否可以拿到指定数量的令牌，如果能则最多等待指定时间，否则立即返回
         */
//        double waitTime2 = limiter.acquire(50);
//        System.out.println("等待时间：" + waitTime2);

        Thread.sleep(8 * 1000);
        long lastTime = System.currentTimeMillis();
        boolean rs = limiter.tryAcquire(50, 2, TimeUnit.SECONDS);
        System.out.println(rs + "时间间隔：" + (System.currentTimeMillis() - lastTime));

    }

    @Test
    public void test02() throws InterruptedException {
        // 每秒产生5个令牌，即200ms产生一个令牌
        RateLimiter limiter = RateLimiter.create(5);

        long lastTime = System.currentTimeMillis();
        // 注意：此数量可以可以超过令牌数量，但是需要按照200ms产生一个令牌归还后，后续才能获取成功
        while (true) {
            long currentTime = System.currentTimeMillis();
            System.out.println("时间间隔：" + (currentTime - lastTime));
            lastTime = currentTime;
            if (limiter.tryAcquire(30, 2, TimeUnit.SECONDS)) {
                System.out.println("ok");
            } else {
                System.out.println("error");
            }
            Thread.sleep(1000);
        }
    }

    @Test
    public void test03() throws InterruptedException {
        // SmoothBursty模式，无sleep等待2s（前一个占用了1秒，自己需要1秒），有则等待1s（自己需要1秒）
        RateLimiter limiter = RateLimiter.create(5);
        // SmoothWarmingUp模式，等待5s
//        RateLimiter limiter = RateLimiter.create(5, 10, TimeUnit.SECONDS);

//        Thread.sleep(10 * 1000);

        double waitTime = limiter.acquire(10);
        System.out.println("等待时间：" + waitTime);
        double waitTime2 = limiter.acquire(10);
        System.out.println("等待时间：" + waitTime2);
    }
}
