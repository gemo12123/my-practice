package org.mytest.annotation.annotation;

import org.mytest.annotation.selector.PrintLogConfigurationSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(PrintLogConfigurationSelector.class)
public @interface EnablePrint {
    String value() default "";
}
