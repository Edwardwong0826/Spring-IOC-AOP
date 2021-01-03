package com.spring.conditional;


import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

// self define logic to return needed classes bean to IOC container
public class MyImportSelector implements ImportSelector {
    /**
     *
     * AnnotationMetadata : can get all @import, @Conditional, @Configuration value metadata and information
     * return String[] is the needed class beans that want to import to IOC container
     */
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {


        return new String[]{"com.spring.beans.Blue","com.spring.beans.Yellow"};
    }
}
