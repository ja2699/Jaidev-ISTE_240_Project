package com.example.assignment1.Controllers;

import com.example.assignment1.Models.Librarian;
import com.example.assignment1.Services.Librarian_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/index/librarians")
public class Librarian_Controller {
    @Autowired
    private Librarian_Service librarian_Service;

    @GetMapping
    public List<Librarian> getAllLibrarians() {
        return librarian_Service.getAllLibrarians();
    }

    @GetMapping("/{id}")
    public Optional<Librarian> getLibrarianById(@PathVariable int id) {
        return librarian_Service.getLibrarianById(id);
    }

    @GetMapping("/search")
    public List<Librarian> searchLibrarians(@RequestParam(required = false) String name) {
        if (name != null) return librarian_Service.searchByName(name);
        return new ArrayList<>();
    }

    @PostMapping
    public Librarian addLibrarian(@RequestBody Librarian librarian) {
        return librarian_Service.saveLibrarian(librarian);
    }

    @PutMapping("/{id}")
    public Librarian updateLibrarian(@PathVariable int id, @RequestBody Librarian librarian) {
        librarian.setStaffID(id);
        return librarian_Service.saveLibrarian(librarian);
    }

    @DeleteMapping("/{id}")
    public void deleteLibrarian(@PathVariable int id) {
        librarian_Service.deleteLibrarian(id);
    }
}
