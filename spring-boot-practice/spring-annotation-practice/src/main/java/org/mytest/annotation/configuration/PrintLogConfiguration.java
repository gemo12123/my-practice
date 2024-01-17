package org.mytest.annotation.configuration;

import org.mytest.annotation.annotation.EnablePrint;
import org.mytest.annotation.processor.PrintLogBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportAware;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

public class PrintLogConfiguration implements ImportAware {
    protected AnnotationAttributes enablePrint;

    /**
     * 获取@EnablePrint注解元数据
     *
     * @param importMetadata
     */
    @Override
    public void setImportMetadata(AnnotationMetadata importMetadata) {
        this.enablePrint = AnnotationAttributes.fromMap(
                importMetadata.getAnnotationAttributes(EnablePrint.class.getName(), false));
    }

    @Bean
    public PrintLogBeanPostProcessor printLogBeanProcessor() {
        // 将@EnablePrint注解中的信息带入BeanPostProcessor
        String defaultValue = this.enablePrint.getString("value");
        return new PrintLogBeanPostProcessor(defaultValue);
    }
}
