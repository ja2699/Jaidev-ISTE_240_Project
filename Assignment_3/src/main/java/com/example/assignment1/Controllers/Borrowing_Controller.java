package com.example.assignment1.Controllers;

import com.example.assignment1.Models.BorrowingHistory;
import com.example.assignment1.Services.Borrowing_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/index/borrowing")
public class Borrowing_Controller {
    @Autowired
    private Borrowing_Service borrowing_Service;

    @GetMapping
    public List<BorrowingHistory> getAllBorrowingHistory() {
        return borrowing_Service.getAllBorrowingHistory();
    }

    @GetMapping("/{id}")
    public Optional<BorrowingHistory> getBorrowingHistoryById(@PathVariable int id) {
        return borrowing_Service.getBorrowingHistoryById(id);
    }

    @GetMapping("/search")
    public List<BorrowingHistory> searchBorrowing(@RequestParam(required = false) String borrowDate) {
        if (borrowDate != null) return borrowing_Service.searchByBorrowDate(LocalDate.parse(borrowDate));  //displays list of books that have been borrowed on that date.
                                                                                                           // If no date, return empty list
        return new ArrayList<>();
    }


    //search borrowing history of specific user
    @GetMapping("/member/{username}")
    public List<BorrowingHistory> getBorrowingByUsername(@PathVariable String username) {
        return borrowing_Service.searchByUsername(username);
    }

    @PostMapping
    public BorrowingHistory addBorrowingHistory(@RequestBody BorrowingHistory borrowingHistory) {
        return borrowing_Service.saveBorrowingHistory(borrowingHistory);
    }

    //delete and update by id
    @PutMapping("/{id}")
    public BorrowingHistory updateBorrowingHistory(@PathVariable int id, @RequestBody BorrowingHistory borrowingHistory) {
        borrowingHistory.setId(id);
        return borrowing_Service.saveBorrowingHistory(borrowingHistory);
    }

    @DeleteMapping("/{id}")
    public void deleteBorrowingHistory(@PathVariable int id) {
        borrowing_Service.deleteBorrowingHistory(id);
    }
}
