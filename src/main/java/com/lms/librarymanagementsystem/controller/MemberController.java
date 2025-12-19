package com.lms.librarymanagementsystem.controller;

import com.lms.librarymanagementsystem.model.Member;
import com.lms.librarymanagementsystem.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
@CrossOrigin
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public Member addMember(@Valid @RequestBody Member member){
        return memberService.addMember(member);
    }

    @GetMapping
    public List<Member> getAllMembers(){
        return memberService.getAllMembers();
    }
}
