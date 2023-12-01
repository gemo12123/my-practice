package org.mytest.annotation.advice;

import org.aopalliance.aop.Advice;
import org.mytest.annotation.annotation.PrintLog;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.Set;

public class PrintLogAdvisor extends AbstractPointcutAdvisor {

    private String defaultValue;
    private Advice advice;
    private Pointcut pointcut;

    public PrintLogAdvisor(String defaultValue) {
        // 指定切面的注解
        LinkedHashSet<Class<? extends Annotation>> printLogAnnotationTypes = new LinkedHashSet<>();
        printLogAnnotationTypes.add(PrintLog.class);
        this.defaultValue = defaultValue;
        this.advice = buildAdvice();
        this.pointcut = buildPointcut(printLogAnnotationTypes);
    }

    private Pointcut buildPointcut(Set<Class<? extends Annotation>> printLogAnnotationTypes) {
        ComposablePointcut result = null;
        for (Class<? extends Annotation> printLogAnnotationType : printLogAnnotationTypes) {
            // 类切面
            AnnotationMatchingPointcut classPointcut = new AnnotationMatchingPointcut(printLogAnnotationType, true);
            // 方法切面
            AnnotationMatchingPointcut methodPointcut = new AnnotationMatchingPointcut(null, printLogAnnotationType, true);
            if (result == null) {
                result = new ComposablePointcut(classPointcut);
            } else {
                result.union(classPointcut);
            }
            result = result.union(methodPointcut);
        }
        return result != null ? result : Pointcut.TRUE;
    }

    private Advice buildAdvice() {
        AnnotationPrintLogExecutionInterceptor printLogExecutionInterceptor = new AnnotationPrintLogExecutionInterceptor();
        printLogExecutionInterceptor.setDefaultValue(defaultValue);
        return printLogExecutionInterceptor;
    }

    @Override
    public Pointcut getPointcut() {
        return pointcut;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

}
