package com.tdogan.books.entity;

import org.springframework.boot.autoconfigure.domain.EntityScan;



public class Book {

    private long id;
    private String title;
    private String author;
    private String category;
    private int raiting;


    public Book(long id ,String title, String author, String category ,int raiting) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.id = id;
        this.raiting = raiting;
    }

    public long getId() {
        return id;
    }

    public void setRaiting(int raiting) {
        this.raiting = raiting;
    }

    public int getRaiting() {
        return raiting;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
