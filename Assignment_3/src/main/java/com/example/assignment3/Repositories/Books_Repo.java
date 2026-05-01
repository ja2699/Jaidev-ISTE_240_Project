package com.example.assignment3.Repositories;

import com.example.assignment3.Models.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface Books_Repo extends JpaRepository<Books, Long> {
    @Query("SELECT b FROM Books b WHERE b.bookName LIKE %:name%")
    List<Books> findByBookName(@Param("name") String name);
    List<Books> findAll();

    @Query("SELECT b FROM Books b WHERE b.bookAuthor LIKE %:author%")
    List<Books> searchByAuthor(String author);

    @Modifying
    @Transactional
    @Query("UPDATE Books b SET b.bookName = :bookName WHERE b.bookID = :id")
    void updateBookNameById(long id, String bookName);
}
