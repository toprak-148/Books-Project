package com.tdogan.books.controller;

import com.tdogan.books.entity.Book;
import com.tdogan.books.request.BookRequest;
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
                        new Book(1,"Title one","Author one", "science",5),
                        new Book(2,"Title two","Author two", "Computer Science",5),
                        new Book(3,"Title three","Author three", "math",5),
                        new Book(4,"Title four","Author four", "math",5),
                        new Book(5,"Java Spring Master","Toprak Dogan","Computer Sceince",5),
                        new Book(6,"Java OOP Master","Akın Kaldıroğlu","Computer Sceince",5)


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
   public void createBook(@RequestBody BookRequest bookRequest)
   {
       /*
       for(Book book:books)
           if(book.getTitle().equalsIgnoreCase(newBook.getTitle()))
               return ;

        */

       long id;
       if(books.isEmpty())
           id = 1;
       else{
           id = books.get(books.size() - 1 ).getId() + 1;
       }

       Book book = convertBook(id,bookRequest);
       books.add(book);





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

   @PutMapping("/{id}")
   public void updateBook(@PathVariable long id , @RequestBody Book updateBook)
   {
       for(int i = 0 ; i < books.size(); i++)
       {
           if(books.get(i).getId() == id )
           {
               books.set(i,updateBook);
               return;
           }
       }
   }

   @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable long id ) {
       books.removeIf(book -> book.getId() == id);

   }
   private Book convertBook(long id , BookRequest bookRequest)
   {
       return new Book(
               id,
               bookRequest.getTitle(),
               bookRequest.getAuthor(),
               bookRequest.getCategory(),
               bookRequest.getRating()
       );
   }

}
