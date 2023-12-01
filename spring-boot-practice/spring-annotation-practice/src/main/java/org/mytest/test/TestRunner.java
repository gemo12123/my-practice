package org.mytest.test;

import org.mytest.annotation.annotation.PrintLog;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class TestRunner implements ApplicationRunner {
    @PrintLog("test")
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("this is runner");
    }
}
