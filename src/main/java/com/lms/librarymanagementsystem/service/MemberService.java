package com.lms.librarymanagementsystem.service;

import com.lms.librarymanagementsystem.model.Member;
import com.lms.librarymanagementsystem.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member addMember(Member member){
        if(member.getEmail() != null && memberRepository.existsByEmail(member.getEmail())){
            throw new RuntimeException("Email already registered");
        }

        if(memberRepository.existsByPhone(member.getPhone())){
            throw new RuntimeException("Phone number already registered");
        }

        return memberRepository.save(member);
    }

    public List<Member> getAllMembers(){
        return memberRepository.findAll();
    }
}
