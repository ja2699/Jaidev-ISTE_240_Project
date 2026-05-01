package com.example.assignment3.Services;

import com.example.assignment3.Models.Books;
import com.example.assignment3.Repositories.Books_Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class Book_Service {
    @Autowired
    private Books_Repo books_repo;

    public List<Books> findAll(){ return books_repo.findAll(); }
    public Optional<Books> findById(Long id){ return books_repo.findById(id); }
    public List<Books> findByName(String name){ return books_repo.findByBookName(name); }
    public List<Books> findByAuthor(String author){ return books_repo.searchByAuthor(author); }
    public Books saveBook(Books book) { return books_repo.save(book); }
    public void updateBookName(long id, String bookName) { books_repo.updateBookNameById(id, bookName); }
    public void deleteBook(long id) { books_repo.deleteById(id); }
}
