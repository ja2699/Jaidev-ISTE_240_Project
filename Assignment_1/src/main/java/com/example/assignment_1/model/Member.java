package com.example.assignment_1.model;

import org.springframework.stereotype.Component;

@Component
public class Member {
    public String memberFName;
    public String memberLName;
    public int memberID;
    public String memberJoinDate;

    public String getMemberFName() { return memberFName; }
    public void setMemberFName(String memberFName) { this.memberFName = memberFName; }

    public String getMemberLName() { return memberLName; }
    public void setMemberLName(String memberLName) { this.memberLName = memberLName; }

    public int getMemberID() { return memberID; }
    public void setMemberID(int memberID) { this.memberID = memberID; }

    public String getMemberJoinDate() { return memberJoinDate; }
    public void setMemberJoinDate(String memberJoinDate) { this.memberJoinDate = memberJoinDate; }
}
