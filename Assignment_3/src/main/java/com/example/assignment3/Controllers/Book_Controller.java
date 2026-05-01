package com.example.assignment3.Controllers;

import com.example.assignment3.Models.Books;
import com.example.assignment3.Services.Book_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class Book_Controller {
    @Autowired
    private Book_Service bookService;

    @GetMapping
    public List<Books> getAllBooks() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Books> getBookById(@PathVariable long id) {
        return bookService.findById(id);
    }

    @GetMapping("/search")
    public List<Books> searchBooks(@RequestParam(required = false) String name,
                                   @RequestParam(required = false) String author) {
        if (name != null) return bookService.findByName(name);
        if (author != null) return bookService.findByAuthor(author);
        return new ArrayList<>();
    }

    @PostMapping
    public Books addBook(@RequestBody Books book) {
        return bookService.saveBook(book);
    }


    //update and delete
    @PutMapping("/{id}")
    public Books updateBook(@PathVariable int id, @RequestBody Books book) {
        book.setBookID(id);
        return bookService.saveBook(book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable int id) {
        bookService.deleteBook(id);
    }
}
