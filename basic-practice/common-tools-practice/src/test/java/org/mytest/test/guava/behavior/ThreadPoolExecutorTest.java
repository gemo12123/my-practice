package org.mytest.test.guava.behavior;

import com.google.common.util.concurrent.*;
import org.junit.Test;

import java.util.concurrent.Executors;

/**
 * 线程池callback，也可以使用CompletableFuture
 *
 * @author gemo
 * @date 2025/12/15 16:29
 */
public class ThreadPoolExecutorTest {
    @Test
    public void test01() throws InterruptedException {
        ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));


        for (int i = 0; i < 10; i++) {
            int j = i;
            ListenableFuture<String> future = executorService.submit(() -> {
                if (j == 5) {
                    throw new RuntimeException("error:" + j);
                }
                return "user" + j;
            });

            Futures.addCallback(future, new FutureCallback<String>() {
                public void onSuccess(String user) {
                    System.out.println(user);
                }

                public void onFailure(Throwable t) {
                    System.out.println(t.getMessage());
                }
            }, executorService);
        }
        Thread.sleep(2000);
    }


}
