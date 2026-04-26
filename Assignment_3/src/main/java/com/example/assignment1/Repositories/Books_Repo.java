package com.example.assignment1.Repositories;

import com.example.assignment1.Models.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface Books_Repo extends JpaRepository<Books, Long> {
    List<Books> findByBookName(String bookName);
    List<Books> findAll();

    @Query("SELECT b FROM Books b WHERE b.bookAuthor LIKE %:author%")
    List<Books> searchByAuthor(String author);

    @Modifying
    @Transactional
    @Query("UPDATE Books b SET b.bookName = :bookName WHERE b.bookID = :id")
    void updateBookNameById(long id, String bookName);
}
