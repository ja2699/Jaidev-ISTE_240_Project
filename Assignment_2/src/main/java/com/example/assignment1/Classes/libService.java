package com.example.assignment1.Classes;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import com.example.assignment1.model.Books;
import com.example.assignment1.model.Member;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;


@Service
public class libService {
    private List<Member> memberList = new ArrayList<>();
    private List<Books> bookList = new ArrayList<>();

        @PostConstruct
        public void addToList(){
            Books book1 = new Books();
            book1.setBookID(1);
            book1.setBookName("Harry Potter");
            book1.setBookAuthor("J.K. Rowling");
            bookList.add(book1);

            Books book2 = new Books();
            book2.setBookID(2);
            book2.setBookName("Famous Five");
            book2.setBookAuthor("Enid Blyton");
            bookList.add(book2);

            Books book3 = new Books();
            book3.setBookID(3);
            book3.setBookName("Percy Jackson");
            book3.setBookAuthor("Rick Riordan");
            bookList.add(book3);

            Member member1 = new Member();
            member1.setMemberID(1);
            member1.setMemberFName("Jaidev");
            member1.setMemberLName("Attawar");
            member1.setMemberJoinDate(LocalDate.of(2004, 11, 23));
            memberList.add(member1);

            Member member2 = new Member();
            member2.setMemberID(1);
            member2.setMemberFName("Jimmy");
            member2.setMemberLName("Banks");
            member2.setMemberJoinDate(LocalDate.of(1998, 9, 16));
            memberList.add(member2);

            Member member3 = new Member();
            member3.setMemberID(1);
            member3.setMemberFName("Ronald");
            member3.setMemberLName("McDonald");
            member3.setMemberJoinDate(LocalDate.of(2004, 11, 23));
            memberList.add(member3);
    }

    public List<Member> getMemberList() {
            return memberList;
    }
    public void addMember(Member member) { memberList.add(member); }

    public List<Books> getBookList() {
            return bookList;
    }
    public void addBook(Books book) { bookList.add(book); }
}
