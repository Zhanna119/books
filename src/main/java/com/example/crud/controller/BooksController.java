package com.example.crud.controller;

import com.example.crud.model.Books;
import com.example.crud.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class BooksController {

    @Autowired
    private final  BooksService booksService;

    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    @GetMapping("/book")
    public List<Books> getAllBooks(){
        return booksService.findAllBooks();
    }

    @GetMapping("/book/{id}")
    public Books getBookById(@PathVariable("id") Long id) throws Exception {
        return booksService.findBookById(id);
    }

    @PostMapping("/books")
    public Long saveBooks (@RequestBody Books book) throws Exception{
        booksService.createOrUpdate(book);
        return book.getId();
    }

    @PutMapping("/books")
    public Books updateBook (@RequestBody Books book) throws Exception{

        booksService.createOrUpdate(book);
        return book;
    }

    @DeleteMapping("/books/{id}")
    public void deleteBooks(@PathVariable("id") Long id) throws Exception{
        booksService.deleteById(id);
    }
}