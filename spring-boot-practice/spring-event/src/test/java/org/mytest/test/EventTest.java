package org.mytest.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mytest.test.event.OrderAddEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EventTest {
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void test01(){
        System.out.println("start.........");
        applicationContext.publishEvent(new OrderAddEvent(this,"==id=="));
        System.out.println("end.........");
        applicationContext.publishEvent(new OrderAddEvent(this,"==id=="));
    }
}
