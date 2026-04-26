package com.example.assignment1.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Member_Table")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int memberID;
    private String memberFName;
    private String memberLName;
    private LocalDate memberJoinDate;

    public int getMemberID() { return memberID; }
    public void setMemberID(int memberID) { this.memberID = memberID; }

    public String getMemberFName() { return memberFName; }
    public void setMemberFName(String memberFName) { this.memberFName = memberFName; }

    public String getMemberLName() { return memberLName; }
    public void setMemberLName(String memberLName) { this.memberLName = memberLName; }

    public LocalDate getMemberJoinDate() { return memberJoinDate; }
    public void setMemberJoinDate(LocalDate memberJoinDate) { this.memberJoinDate = memberJoinDate; }
}