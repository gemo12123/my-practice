package org.mytest.test.event;

import lombok.Data;
import org.springframework.context.ApplicationEvent;

@Data
public class OrderAddEvent extends ApplicationEvent {
    private String id;

    public OrderAddEvent(Object source, String id) {
        super(source);
        this.id = id;
    }
}
