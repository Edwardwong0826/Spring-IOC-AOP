package com.spring.beans;

import org.springframework.beans.factory.FactoryBean;
//create spring defined FactoryBean
public class ColorFactoryBean implements FactoryBean<Color> {

    // return color object, this object will add to IOC container
    @Override
    public Color getObject() throws Exception {
        System.out.println("ColorFactoryBean...getObject...");
        return new Color();
    }

    @Override
    public Class<?> getObjectType() {
        return Color.class;
    }

    // control then bean return is it singleton or prototype
    // if true, the bean will singleton,and store in IOC container one set
    // if false, multi instance bean, every request or get will create new bean
    @Override
    public boolean isSingleton() {
        return false;
    }
}
