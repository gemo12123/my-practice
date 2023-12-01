package org.mytest.annotation.selector;

import org.mytest.annotation.configuration.PrintLogConfiguration;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class PrintLogConfigurationSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{PrintLogConfiguration.class.getName()};
    }
}
