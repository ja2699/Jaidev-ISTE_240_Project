package com.example.assignment3.Models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Borrowing_History_Table")
public class BorrowingHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "memberID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "bookID")
    private Books book;

    private LocalDate borrowDate;
    private LocalDate returnDate;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Member getMember() { return member; }
    public void setMember(Member member) { this.member = member; }

    public Books getBook() { return book; }
    public void setBook(Books book) { this.book = book; }

    public LocalDate getBorrowDate() { return borrowDate; }
    public void setBorrowDate(LocalDate borrowDate) { this.borrowDate = borrowDate; }

    public LocalDate getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }
}
