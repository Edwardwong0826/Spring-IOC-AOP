package com.spring.AOP;

public class UserDAOImpl implements UserDAO{
    @Override
    public int add(int a, int b) {
        System.out.println("run method add");
        return a+b;
    }

    @Override
    public String update(String id) {
        System.out.println("run method update");
        return id;
    }
}
