package com.learn.graphql.controller;

import com.learn.graphql.Entity.Book;
import com.learn.graphql.service.BookService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @QueryMapping
    public List<Book> getBooks() {
        return bookService.getAllBooks();
    }

    @QueryMapping
    public List<Book> getBooksByAuthor(@Argument String author) {
        return bookService.getBooksByAuthor(author);
    }

    @QueryMapping
    public Book getBookById(@Argument String id) {
        return bookService.getBookById(Integer.parseInt(id));
    }

    @MutationMapping
    public Book saveBook(
            @Argument String title,
            @Argument double price,
            @Argument String author,
            @Argument String publisher) {
        return bookService.saveBook(new Book(0, title, price, author, publisher));
    }

    @MutationMapping
    public Book updateBook(
            @Argument String id,
            @Argument String title,
            @Argument double price,
            @Argument String author,
            @Argument String publisher) {
        Book updatedBook = new Book(0, title, price, author, publisher);
        return bookService.updateBook(Integer.parseInt(id), updatedBook);
    }

    @MutationMapping
    public boolean deleteBook(@Argument String id) {
        bookService.deleteBook(Integer.parseInt(id));
        return true;
    }
}
