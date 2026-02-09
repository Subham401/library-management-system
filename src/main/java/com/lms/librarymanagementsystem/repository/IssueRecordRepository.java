package com.lms.librarymanagementsystem.repository;

import com.lms.librarymanagementsystem.model.IssueRecords;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IssueRecordRepository extends JpaRepository<IssueRecords, Long> {

    boolean existsByBookIdAndMemberIdAndReturnDateIsNull(Long bookId, Long memberId);

    Optional<IssueRecords> findByBookIdAndMemberIdAndReturnDateIsNull(Long bookId, Long memberId);
}
