package com.lms.librarymanagementsystem.controller;

import com.lms.librarymanagementsystem.dto.BookRequest;
import com.lms.librarymanagementsystem.model.Book;
import com.lms.librarymanagementsystem.service.BookService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@CrossOrigin
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }
    @PostMapping
    public Book addBook(@Valid @RequestBody BookRequest request){
        Book book = new Book(
                request.title,
                request.author,
                request.isbn,
                request.totalCopies
        );
        return bookService.addBook(book);
    }

    @GetMapping
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }
}
