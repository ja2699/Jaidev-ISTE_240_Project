package com.example.assignment1.Repositories;

import com.example.assignment1.Models.BorrowingHistory;
import com.example.assignment1.Models.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

public interface Borrowing_Repo extends JpaRepository<BorrowingHistory, Long> {

    List<BorrowingHistory> findByMember(Member member);
    List<BorrowingHistory> findAll();

    @Query("SELECT b FROM BorrowingHistory b WHERE b.borrowDate = :borrowDate")
    List<BorrowingHistory> searchByBorrowDate(LocalDate borrowDate);

    @Query("SELECT b FROM BorrowingHistory b WHERE CONCAT(b.member.memberFName, b.member.memberLName) = :username")
    List<BorrowingHistory> findByUsername(String username);

    @Modifying
    @Transactional
    @Query("UPDATE BorrowingHistory b SET b.returnDate = :returnDate WHERE b.id = :id")
    void updateReturnDateById(long id, LocalDate returnDate);
}