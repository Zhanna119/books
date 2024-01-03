package com.example.crud.model;

import jakarta.persistence.*;

@Entity

public class Books {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String bookName;
    private String author;
    private int price;

    public Books(long id, String bookName, String author, int price) {
        this.id = id;
        this.bookName = bookName;
        this.author = author;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


}
