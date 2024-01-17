package org.mytest.state;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.config.EnableStateMachine;

@EnableStateMachine
@SpringBootApplication
public class StateMachineApplication {
    public static void main(String[] args) {
        SpringApplication.run(StateMachineApplication.class, args);
    }
}
