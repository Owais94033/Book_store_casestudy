package com.BookManager.demo.service.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.awt.print.Book;

@FeignClient(name = "book")
public interface BookClient {

    @GetMapping("/books/{id}")
    Book getBookById(@PathVariable("id") Long id);
}
