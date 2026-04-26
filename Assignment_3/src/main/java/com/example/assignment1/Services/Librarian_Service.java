package com.example.assignment1.Services;

import com.example.assignment1.Models.Librarian;
import com.example.assignment1.Repositories.Librarian_Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class Librarian_Service {
    @Autowired
    private Librarian_Repo librarian_repo;

    public List<Librarian> getAllLibrarians() { return librarian_repo.findAll(); }
    public Optional<Librarian> getLibrarianById(long id) { return librarian_repo.findById(id); }
    public List<Librarian> searchByName(String name) { return librarian_repo.searchByName(name); }
    public Librarian saveLibrarian(Librarian librarian) { return librarian_repo.save(librarian); }
    public void updateLibrarianName(long id, String name) { librarian_repo.updateLibrarianNameById(id, name); }
    public void deleteLibrarian(long id) { librarian_repo.deleteById(id); }
}
