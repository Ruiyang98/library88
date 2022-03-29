package com.example.wj.controller;

import com.example.wj.pojo.Book;
import com.example.wj.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LibraryController {
    @Autowired
    BookService bookService;

    @GetMapping("/api/books")
    @CrossOrigin
    public List<Book> list() throws Exception {
        return bookService.list();
    }

    @PostMapping("/api/books")
    @CrossOrigin
    public Book addOrUpdate(@RequestBody Book book) throws Exception {
        bookService.addOrUpdate(book);
        return book;
    }

    @PostMapping("/api/delete")
    @CrossOrigin
    public void delete(@RequestBody Book book) throws Exception {
        bookService.deleteById(book.getId());
    }

    @GetMapping("/api/categories/{cid}/books")
    @CrossOrigin
    public List<Book> listByCategory(@PathVariable("cid") int cid) throws Exception {
        if(0 != cid) {
            return bookService.listByCategory(cid);
        } else {
            return list();
        }
    }

}
