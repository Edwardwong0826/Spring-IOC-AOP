package com.spring.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Color {


    private Car car;

    public Car getCar() {
        return car;
    }

    @Autowired
    public void setCar(Car car) {
        this.car = car;
    }
}
