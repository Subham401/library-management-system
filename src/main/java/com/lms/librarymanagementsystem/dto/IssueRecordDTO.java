package com.lms.librarymanagementsystem.dto;
import java.time.LocalDate;

public class IssueRecordDTO {

    private String bookTitle;
    private String memberName;
    private LocalDate issueDate;
    private LocalDate returnDate;
    private Double fine;

    public IssueRecordDTO(String bookTitle, String memberName,
                          LocalDate issueDate, LocalDate returnDate, Double fine) {
        this.bookTitle = bookTitle;
        this.memberName = memberName;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
        this.fine = fine;
    }

    public Double getFine() {
        return fine;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getMemberName() {
        return memberName;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }
}
