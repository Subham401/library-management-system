package com.lms.librarymanagementsystem.repository;

import com.lms.librarymanagementsystem.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
}
