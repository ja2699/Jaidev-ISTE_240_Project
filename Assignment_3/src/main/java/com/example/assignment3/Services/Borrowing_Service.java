package com.example.assignment3.Services;

import com.example.assignment3.Models.BorrowingHistory;
import com.example.assignment3.Models.Member;
import com.example.assignment3.Repositories.Borrowing_Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class Borrowing_Service {
    @Autowired
    Borrowing_Repo borrowing_Repo;

    public List<BorrowingHistory> getAllBorrowingHistory() { return borrowing_Repo.findAll(); }
    public Optional<BorrowingHistory> getBorrowingHistoryById(long id) { return borrowing_Repo.findById(id); }
    public List<BorrowingHistory> searchByMember(Member member) { return borrowing_Repo.findByMember(member); }
    public List<BorrowingHistory> searchByBorrowDate(LocalDate borrowDate) { return borrowing_Repo.searchByBorrowDate(borrowDate); }
    public BorrowingHistory saveBorrowingHistory(BorrowingHistory borrowingHistory) { return borrowing_Repo.save(borrowingHistory); }
    public void updateReturnDate(long id, LocalDate returnDate) { borrowing_Repo.updateReturnDateById(id, returnDate); }
    public void deleteBorrowingHistory(long id) { borrowing_Repo.deleteById(id); }
    public List<BorrowingHistory> searchByUsername(String username) {
        return borrowing_Repo.findByUsername(username);
    }
}
