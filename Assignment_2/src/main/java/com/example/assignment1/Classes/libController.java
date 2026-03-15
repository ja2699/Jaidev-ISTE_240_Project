package com.example.assignment1.Classes;

import com.example.assignment1.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@org.springframework.stereotype.Controller
public class libController {
    @Autowired
    private libService libService;


    @GetMapping("/index")
    public String index() {
        return "index";
    }


    @GetMapping("/books")
    public String getBooks(Model model) {
        model.addAttribute("books", libService.getBookList());
        return "books";
    }


    @GetMapping("/member/add")
    public String memberForm(Model model) {
        model.addAttribute("member", new Member());
        return "member_form";
    }


    @PostMapping("/member/add")
    public String addMember(@ModelAttribute Member member, Model model) {
        libService.addMember(member);
        model.addAttribute("memberName", member.getMemberFName());
        return "redirect:/add/success/" + member.getMemberFName();
    }


    @GetMapping("/add/success/{memberName}")
    public String successPage(@PathVariable String memberName, Model model) {
        model.addAttribute("entityName", memberName);
        return "success";
    }
}

