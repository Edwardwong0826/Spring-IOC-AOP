package com.spring.service;

import com.spring.repository.BookDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Qualifier("bookDAO")
    @Autowired
    BookDAO bookDAO2;

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
