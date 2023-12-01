package org.mytest.annotation.processor;

import org.mytest.annotation.advice.PrintLogAdvisor;
import org.springframework.aop.framework.autoproxy.AbstractBeanFactoryAwareAdvisingPostProcessor;
import org.springframework.beans.factory.BeanFactory;

public class PrintLogBeanPostProcessor extends AbstractBeanFactoryAwareAdvisingPostProcessor {

    private String defaultValue;

    public PrintLogBeanPostProcessor(String defaultValue) {
        this.defaultValue = defaultValue;
        setBeforeExistingAdvisors(true);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        super.setBeanFactory(beanFactory);

        // 此时可以向Advisor中放入BeanFactory
        PrintLogAdvisor printLogAdvisor = new PrintLogAdvisor(defaultValue);
        this.advisor = printLogAdvisor;
    }
}
