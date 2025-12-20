package com.lms.librarymanagementsystem.dto;
import java.time.LocalDate;

public class IssueRecordDTO {

    private String bookTitle;
    private String memberName;
    private LocalDate issueDate;
    private LocalDate returnDate;

    public IssueRecordDTO(String bookTitle, String memberName,
                          LocalDate issueDate, LocalDate returnDate) {
        this.bookTitle = bookTitle;
        this.memberName = memberName;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
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
