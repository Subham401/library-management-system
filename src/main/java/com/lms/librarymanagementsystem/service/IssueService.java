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
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
        try{
            Book book = bookRepository.findById(bookId)
                    .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
            if (book.getAvailableCopies() <= 0) {
                throw new BadRequestException("Book is not available");
            }

            Member member = memberRepository.findById(memberId)
                    .orElseThrow(() -> new ResourceNotFoundException("Member not found"));

            boolean alreadyIssued = issueRecordRepository
                    .existsByBookIdAndMemberIdAndReturnDateIsNull(bookId, memberId);

            if (alreadyIssued) {
                throw new RuntimeException("You have already issued this book. Please return it first.");
            }

            book.setAvailableCopies(book.getAvailableCopies() - 1);

            IssueRecords record = new IssueRecords(book, member, LocalDate.now());

            record.setDueDate(LocalDate.now().plusDays(15));
            record.setFine(0.0);

            bookRepository.save(book);
            return issueRecordRepository.save(record);
        }catch (ObjectOptimisticLockingFailureException e){
            throw new BadRequestException("Someone else issued this book at the same time. Try again.");
        }
    }

    @Transactional
    public IssueRecords returnBook(Long bookId,Long memberId){
        try{
            IssueRecords record = issueRecordRepository
                    .findByBookIdAndMemberIdAndReturnDateIsNull(bookId, memberId)
                    .orElseThrow(() -> new RuntimeException("This book is not currently available"));

            Book book = record.getBook();
            book.setAvailableCopies(book.getAvailableCopies() + 1);

            record.setReturnDate(LocalDate.now());
            if (record.getDueDate().isBefore(LocalDate.now())) {
                long daysLate = ChronoUnit.DAYS.between(record.getDueDate(), LocalDate.now());
                record.setFine(daysLate * 10.0);
            } else {
                record.setFine(0.0);
            }

            bookRepository.save(book);
            return issueRecordRepository.save(record);
        }catch (ObjectOptimisticLockingFailureException e) {
            throw new RuntimeException(
                    "Another user is returning this book at the same time. Please try again."
            );
        }
    }

    public List<IssueRecordDTO> getAllIssuedBooks() {
        return issueRecordRepository.findAll()
                .stream()
                .map(record -> new IssueRecordDTO(
                        record.getBook().getTitle(),
                        record.getMember().getName(),
                        record.getIssueDate(),
                        record.getReturnDate(),
                        record.getFine()
                ))
                .toList();
    }

}
