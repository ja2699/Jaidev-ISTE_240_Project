package com.example.assignment3.Controllers;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ViewController implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("forward:/index.html");
        registry.addViewController("/index/books").setViewName("forward:/index/books.html");
        registry.addViewController("/index/members").setViewName("forward:/index/members.html");
        registry.addViewController("/index/borrowing").setViewName("forward:/index/borrowing.html");
        registry.addViewController("/index/librarian").setViewName("forward:/index/librarian.html");
        registry.addViewController("/index/books/admin").setViewName("forward:/admin/admin_books.html");
        registry.addViewController("/index/members/admin").setViewName("forward:/admin/admin_members.html");
        registry.addViewController("/index/borrowing/admin").setViewName("forward:/admin/admin_borrowing.html");
        registry.addViewController("/index/librarian/admin").setViewName("forward:/admin/admin_librarian.html");
    }
}
