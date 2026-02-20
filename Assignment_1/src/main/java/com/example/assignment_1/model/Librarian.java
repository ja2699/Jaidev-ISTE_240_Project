package com.example.assignment_1.model;

import org.springframework.stereotype.Component;

@Component
public class Librarian {
    public String librarianName;
    public int staffID;

    public String getLibrarianName(){ return librarianName; }
    public void setLibrarianName(String librarianName){ this.librarianName = librarianName; }

    public int getStaffID(){ return staffID; }
    public void setStaffID(int staffID){ this.staffID = staffID; }
}
