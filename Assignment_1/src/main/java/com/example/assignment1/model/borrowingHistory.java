package com.example.assignment_1.model;

import org.springframework.stereotype.Component;

@Component
public class borrowingHistory {
    public Member memberFName;
    public Member memberID;
    public Books bookName;
    public Books bookID;
    public String borrowDate;
    public String returnDate;

    public String getBorrowDate() { return borrowDate; }
    public void setBorrowDate(String borrowDate) { this.borrowDate = borrowDate; }

    public String getReturnDate() { return returnDate; }
    public void setReturnDate(String returnDate) { this.returnDate = returnDate; }
}
