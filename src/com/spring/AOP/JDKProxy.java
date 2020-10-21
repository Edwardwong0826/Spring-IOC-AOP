package com.spring.AOP;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Array;
import java.util.Arrays;

public class JDKProxy {

    public static void main(String[] args) {

        Class[] interfaces = {UserDAO.class};
        UserDAOImpl userDao = new UserDAOImpl();
        UserDAO dao = (UserDAO)Proxy.newProxyInstance(JDKProxy.class.getClassLoader(), interfaces, new UserDAOProxy(userDao));
        System.out.println(dao.add(1,2));
    }
}

//instantiate proxy object
class UserDAOProxy implements InvocationHandler{

    // pass in the class object that want to become proxy object to here
    private Object obj;
    public UserDAOProxy(Object obj){
        this.obj = obj;
    }

    // enhance logic write in here
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // before method
        System.out.println("Before method invocation "+method.getName()+":pass in parameter"+ Arrays.toString(args));

        Object res = method.invoke(obj, args);

        // after method
        System.out.println("After method invocation" + obj);
        return res;
    }
}