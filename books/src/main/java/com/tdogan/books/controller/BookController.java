package com.tdogan.books.controller;

import com.tdogan.books.entity.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {


    private final List<Book> books = new ArrayList<>();
    public BookController(){
        initializeBooks();
    }

    private void initializeBooks()
    {
        books.addAll(
                List.of(
                        new Book("Title one","Author one", "science"),
                        new Book("Title one","Author one", "science"),
                        new Book("Title one","Author one", "science"),
                        new Book("Title one","Author one", "science")
                )
        );
    }

    @GetMapping("/api/books")
    public List<Book> getBooks() { return this.books; }


    /*
    @GetMapping("/api/books/{title}")
    public Book getBookByTitle(@PathVariable String title)
    {}
        for(Book book : books)
        {
            if(book.getTitle().equalsIgnoreCase(title))
                return book;

        }
        return null;
        altında stream ile yazılmış aynı türden iş yapan fonksiyon mev.
    }

     */
    @GetMapping("/api/books/{title}")
    public Book getBookByTitle(@PathVariable String title)
    {
        books.stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);


    }



}
