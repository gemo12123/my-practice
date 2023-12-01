package org.mytest.annotation.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.mytest.annotation.annotation.PrintLog;
import org.springframework.aop.support.AopUtils;
import org.springframework.core.BridgeMethodResolver;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AnnotationPrintLogExecutionInterceptor implements MethodInterceptor {

    private String defaultValue;

    private Map<Method, String> values = new ConcurrentHashMap<>(16);

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        // 找到类
        Class<?> targetClass = AopUtils.getTargetClass(invocation.getThis());
        // 找到方法
        Method specificMethod = ClassUtils.getMostSpecificMethod(invocation.getMethod(), targetClass);
        // 桥接模式处理
        Method userDeclaredMethod = BridgeMethodResolver.findBridgedMethod(specificMethod);

        String value = getPrintValue(userDeclaredMethod);
        if (isEmptyValue(value)) {
            System.out.println(userDeclaredMethod.getDeclaringClass()
                    + "." + userDeclaredMethod.getName()
                    + "(" + Arrays.toString(invocation.getArguments()) + ")");
        } else {
            System.out.println(value);
        }
        return invocation.proceed();
    }

    private String getPrintValue(Method method) {
        String value = values.get(method);
        if (value != null) {
            return value;
        }
        // 尝试从方法或类上获取@PrintLog注解
        PrintLog annotation = AnnotatedElementUtils.findMergedAnnotation(method, PrintLog.class);
        if (annotation == null) {
            annotation = AnnotatedElementUtils.findMergedAnnotation(method.getDeclaringClass(), PrintLog.class);
        }

        if (annotation != null) {
            value = annotation.value();
            if (isEmptyValue(value)) {
                value = defaultValue;
            }
            values.put(method, value);
        }

        return value;
    }

    private boolean isEmptyValue(String value) {
        return value == null || value.length() == 0;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
}
