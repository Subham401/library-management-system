package com.lms.librarymanagementsystem.service;

import com.lms.librarymanagementsystem.dto.IssueRecordDTO;
import com.lms.librarymanagementsystem.exception.BadRequestException;
import com.lms.librarymanagementsystem.exception.ResourceNotFoundException;
import com.lms.librarymanagementsystem.model.Book;
import com.lms.librarymanagementsystem.model.IssueRecords;
import com.lms.librarymanagementsystem.model.Member;
import com.lms.librarymanagementsystem.repository.BookRepository;
import com.lms.librarymanagementsystem.repository.IssueRecordRepository;
import com.lms.librarymanagementsystem.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class IssueService {

    private BookRepository bookRepository;
    private MemberRepository memberRepository;
    private IssueRecordRepository issueRecordRepository;

    public IssueService(BookRepository bookRepository,
                        MemberRepository memberRepository,
                        IssueRecordRepository issueRecordRepository) {
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
        this.issueRecordRepository = issueRecordRepository;
    }

    @Transactional
    public IssueRecords issueBook(Long bookId, Long memberId){
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        if(book.getAvailableCopies() <= 0){
            throw new BadRequestException("Book is not available");
        }

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found"));

        book.setAvailableCopies(book.getAvailableCopies() - 1);

        IssueRecords record = new IssueRecords(book, member, LocalDate.now());

        bookRepository.save(book);
        return issueRecordRepository.save(record);
    }

    @Transactional
    public IssueRecords returnBook(Long bookId,Long memberId){
        IssueRecords record = issueRecordRepository
                .findByBookIdAndMemberIdAndReturnDateIsNull(bookId,memberId)
                .orElseThrow(() -> new RuntimeException("This book is not currently available"));

        Book book = record.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1 );

        record.setReturnDate(LocalDate.now());
        record.setFine(0.0);

        bookRepository.save(book);
        return issueRecordRepository.save(record);
    }

    public List<IssueRecordDTO> getAllIssuedBooks() {
        return issueRecordRepository.findAll()
                .stream()
                .map(record -> new IssueRecordDTO(
                        record.getBook().getTitle(),
                        record.getMember().getName(),
                        record.getIssueDate(),
                        record.getReturnDate()
                ))
                .toList();
    }

}
