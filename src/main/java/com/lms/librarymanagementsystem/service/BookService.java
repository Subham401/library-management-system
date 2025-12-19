package com.lms.librarymanagementsystem.service;

import com.lms.librarymanagementsystem.exception.BadRequestException;
import com.lms.librarymanagementsystem.model.Book;
import com.lms.librarymanagementsystem.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public Book addBook(Book book){
        if(bookRepository.existsByIsbn(book.getIsbn())){
            throw new BadRequestException("Book with this ISBN already exists");
        }
        book.setAvailableCopies(book.getTotalCopies());
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }
}
