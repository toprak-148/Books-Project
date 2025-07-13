package com.tdogan.books.controller;

import com.tdogan.books.entity.Book;
import org.springframework.web.bind.annotation.*;

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
                        new Book("Title two","Author two", "Computer Science"),
                        new Book("Title three","Author three", "math"),
                        new Book("Title four","Author four", "math")
                )
        );
    }


    @GetMapping("/api/books")
    public List<Book> getBooks(@RequestParam(required = false) String category)
    {
        if(category == null)
            return books;
    /*
        List<Book> filteredBooks = new ArrayList<>();
        for(Book book:books)
        {
            if(book.getCategory().equalsIgnoreCase(category))
                filteredBooks.add(book);
        }
        return filteredBooks;
           ya da stream ile yapilabilir
     */
        return books.stream()
                .filter(book -> book.getCategory().equalsIgnoreCase(category))
                .toList();




    }


    /*
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
        return books.stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);



    }

   @PostMapping("/api/books")
   public void createBook(@RequestBody  Book newBook)
   {
       /*
       for(Book book:books)
           if(book.getTitle().equalsIgnoreCase(newBook.getTitle()))
               return ;

        */
       boolean isNewBook = books.stream()
                       .noneMatch(book -> book.getTitle().equalsIgnoreCase(newBook.getTitle()));
       if(isNewBook)
           books.add(newBook);


   }

   @PutMapping("/api/books/{title}")
    public void updateBook(@PathVariable String title,@RequestBody Book updateBook)
   {
       for(int i = 0 ; i < books.size();i++)
       {
           if(books.get(i).getTitle().equalsIgnoreCase(title))
           {
               books.set(i,updateBook);
               return;
           }
       }
   }

   @DeleteMapping("/api/books/{title}")
    public void deleteBook(@PathVariable String title)
   {
       books.removeIf(book -> book.getTitle().equalsIgnoreCase(title));
   }

}
