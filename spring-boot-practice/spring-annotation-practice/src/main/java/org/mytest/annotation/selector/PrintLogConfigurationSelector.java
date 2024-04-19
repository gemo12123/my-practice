package org.mytest.annotation.selector;

import org.mytest.annotation.configuration.PrintLogConfiguration;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class PrintLogConfigurationSelector implements ImportSelector {

    /**
     * ImportSelector 的实现类可以根据指定筛选条件（通常是一个或多个注解）决定导入哪个配置类
     *
     * @param importingClassMetadata
     * @return
     */
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{PrintLogConfiguration.class.getName()};
    }
}
