package com.example.assignment3.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "librarian_Table")
public class Librarian {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int staffID;

    private String librarianName;

    public int getStaffID() { return staffID; }
    public void setStaffID(int staffID) { this.staffID = staffID; }

    public String getLibrarianName() { return librarianName; }
    public void setLibrarianName(String librarianName) { this.librarianName = librarianName; }
}
