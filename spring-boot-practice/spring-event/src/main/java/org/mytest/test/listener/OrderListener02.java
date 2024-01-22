package org.mytest.test.listener;

import org.mytest.test.event.OrderAddEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("prototype")
@Component
public class OrderListener02 implements ApplicationListener<OrderAddEvent> {


    @Override
    public void onApplicationEvent(OrderAddEvent event) {
        System.out.println("检测到订单新增2222222222：" + event.getId());
    }
}
