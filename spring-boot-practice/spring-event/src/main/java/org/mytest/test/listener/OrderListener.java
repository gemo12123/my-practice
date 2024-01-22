package org.mytest.test.listener;

import org.mytest.test.event.OrderAddEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {

    @Async
    @EventListener(OrderAddEvent.class)
    public void processOrderAddEvent(OrderAddEvent orderAddEvent) {
        System.out.println("检测到订单新增：" + orderAddEvent.getId());
    }

}
