package com.lms.librarymanagementsystem.controller;

import com.lms.librarymanagementsystem.model.IssueRecords;
import com.lms.librarymanagementsystem.service.IssueService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/issues")
@CrossOrigin
public class IssueController {
    private final IssueService issueService;

    public IssueController(IssueService issueService){
        this.issueService = issueService;
    }

    @PostMapping("/issue")
    public IssueRecords issueBook(@RequestParam Long bookId,
                                  @RequestParam Long memberId){
        return issueService.issueBook(bookId,memberId);
    }

    @PostMapping("/return")
    public IssueRecords returnBook(@RequestParam Long bookId,
                                   @RequestParam Long memberId){
        return issueService.returnBook(bookId,memberId);
    }
}
