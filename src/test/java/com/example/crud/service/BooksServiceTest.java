package com.example.crud.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import com.example.crud.model.Books;
import com.example.crud.repository.BooksRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BooksServiceTest {

    @Mock
    private BooksRepository repository;

    @InjectMocks
    private BooksService service;

    @Test
    public void testFindAllBooks_Successful() {
        Books book1 = new Books(1, "Book1", "Author1", 100);
        Books book2 = new Books(2, "Book2", "Author2", 200);
        List<Books> bookList = Arrays.asList(book1, book2);

        when(repository.findAll()).thenReturn(bookList);

        List<Books> result = service.findAllBooks();

        assertEquals(2, result.size());
        assertEquals("Book1", result.get(0).getBookName());
        assertEquals("Book2", result.get(1).getBookName());

        verify(repository, times(1)).findAll();
    }

    @Test
    public void testAllBooks_Empty() {
        when(repository.findAll()).thenReturn(Collections.EMPTY_LIST);

        List<Books> result = service.findAllBooks();

        assertTrue(result.isEmpty());

        verify(repository, times(1)).findAll();
    }

    @Test
    public void createOrUpdate_Successful() {
        Books newBook = new Books(1L, "New Book", "New Author", 150);

        service.createOrUpdate(newBook);

        verify(repository, times(1)).save(newBook);
    }

    @Test
    public void findBookById_Successful() throws Exception {
        long bookId = 1;
        Books expectedBook = new Books(bookId, "Book1", "Author1", 100);

        when(repository.findBooksById(bookId)).thenReturn(expectedBook);

        Books result = service.findBookById(bookId);

        assertEquals(expectedBook, result);

        verify(repository, times(1)).findBooksById(bookId);
    }

    @Test
    public void testFindBookByIdNotFound() throws Exception {
        long nonExistentBookId = 100L;

        when(repository.findBooksById(nonExistentBookId)).thenReturn(null);

        Books result = service.findBookById(nonExistentBookId);

        assertNull(result);

        verify(repository, times(1)).findBooksById(nonExistentBookId);
    }

    @Test
    public void testFindBookByIdExceptionHandling() throws Exception {
        long bookId = 1L;
        when(repository.findBooksById(bookId)).thenThrow(new RuntimeException("Database connection error"));

        assertThrows(RuntimeException.class, () -> service.findBookById(bookId));

        verify(repository, times(1)).findBooksById(bookId);
    }


    @Test
    public void deleteById_Successful() throws Exception {
        service.deleteById(1L);
        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    public void deleteById_NonExistentBook() throws Exception {
        doNothing().when(repository).deleteById(anyLong());
        service.deleteById(99L);
        verify(repository, times(1)).deleteById(99L);
    }


}
