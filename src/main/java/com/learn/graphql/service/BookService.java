package com.learn.graphql.service;

import com.learn.graphql.Entity.Book;
import com.learn.graphql.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> getBooksByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    public Book getBookById(int id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + id));
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(int id, Book updatedBook) {
        Book existingBook = getBookById(id);
        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setPrice(updatedBook.getPrice());
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setPublisher(updatedBook.getPublisher());
        return bookRepository.save(existingBook);
    }

    public void deleteBook(int id) {
        Book book = getBookById(id);
        bookRepository.delete(book);
    }
}
