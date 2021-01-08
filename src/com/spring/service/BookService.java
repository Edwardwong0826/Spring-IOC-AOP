package com.spring.service;

import com.spring.repository.BookDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

// default add to IOC Container component, IOC container will use no args constructor create object,
// add initialize, assign value to fields operation
@Service
public class BookService {

    @Qualifier("bookDAO")
    @Autowired
    BookDAO bookDAO2;

    //@Autowired - bean pass to here also get from IOC container
    public BookService(BookDAO bookDAO2) {
        this.bookDAO2 = bookDAO2;
    }

    public void print(){
        System.out.println(bookDAO2);
    }

    @Override
    public String toString() {
        return "BookService{" +
                "bookDAO=" + bookDAO2 +
                '}';
    }
}
