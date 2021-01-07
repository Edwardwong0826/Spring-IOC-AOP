package com.spring.beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Person {

    //@Value
    // like how in xml, we write in property tag inside value tag
    // 1. primitive values and string
    // 2. SpEL, #{}
    // 3. ${}, get config file value(in running environment variable value)
    @Value("Wong")
    private String name;
    @Value("#{20-2}")
    private int age;

    @Value("${person.nickName}")
    private String nickName;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
