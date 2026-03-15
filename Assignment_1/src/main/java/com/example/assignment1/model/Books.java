package com.example.assignment_1.model;

import org.springframework.stereotype.Component;

@Component
public class Books {
    public String bookName;
    public String bookAuthor;
    public int bookID;

    public int getBookID() { return bookID; }
    public void setBookID(int bookID) { this.bookID = bookID; }

    public String getBookName() { return bookName; }
    public void setBookName(String bookName) { this.bookName = bookName; }

    public String getBookAuthor() { return bookAuthor; }
    public void setBookAuthor(String bookAuthor) { this.bookAuthor = bookAuthor; }
}
