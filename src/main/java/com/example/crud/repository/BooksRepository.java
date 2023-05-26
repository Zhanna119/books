package com.example.crud.repository;

import com.example.crud.model.Books;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface BooksRepository extends CrudRepository<Books, Long>
{
    List<Books> findAll();
    Books findBooksById(Long id);
}
