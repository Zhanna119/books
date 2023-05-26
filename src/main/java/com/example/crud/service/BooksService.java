package com.example.crud.service;

import com.example.crud.model.Books;
import com.example.crud.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BooksService {
    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Books> findAllBooks(){
        return this.booksRepository.findAll();
    }
    public void createOrUpdate(Books book) {
        this.booksRepository.save(book);
    }

    public Books findBookById(Long id) throws Exception{
        return booksRepository.findBooksById(id);
    }

    public void deleteById(Long id) throws Exception{
        booksRepository.deleteById(id);
    }

}