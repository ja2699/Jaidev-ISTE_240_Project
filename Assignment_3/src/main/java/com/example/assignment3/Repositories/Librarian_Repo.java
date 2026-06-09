package com.example.assignment3.Repositories;

import com.example.assignment3.Models.Librarian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface Librarian_Repo extends JpaRepository<Librarian,Long> {
    List<Librarian> findByLibrarianName(String librarianName);
    List<Librarian> findAll();

    @Query("SELECT l FROM Librarian l WHERE l.librarianName LIKE %:name%")
    List<Librarian> searchByName(String name);

    @Modifying
    @Transactional
    @Query("UPDATE Librarian l SET l.librarianName = :name WHERE l.staffID = :id")
    void updateLibrarianNameById(long id, String name);
}
