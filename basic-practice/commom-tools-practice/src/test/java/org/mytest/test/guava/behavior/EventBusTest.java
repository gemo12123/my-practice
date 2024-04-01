package org.mytest.test.guava.behavior;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.util.concurrent.Executors;

/**
 * 事件总线是一种发布/订阅模式的实现
 */
public class EventBusTest {
    @Test
    public void test01() {
//        EventBus eventBus = new EventBus();
        EventBus eventBus = new AsyncEventBus(Executors.newFixedThreadPool(3));
        // 注册订阅者
        eventBus.register(new MyListener());
        eventBus.register(new MyListenerBak());

        // 事件发布
        eventBus.post(new MyEvent("post"));
        System.out.println("-----------");
        eventBus.post("test");
    }

    @Data
    @AllArgsConstructor
    public static class MyEvent {
        String msg;
    }

    public static class MyListener {
        @Subscribe
        public void listen(MyEvent event) {
            System.out.println("listen msg:" + event.getMsg());
        }
    }

    public static class MyListenerBak {
        @Subscribe
        public void listen(String event) {
            System.out.println("listen string msg:" + event);
        }
    }
}
