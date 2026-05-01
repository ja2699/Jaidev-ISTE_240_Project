package com.example.assignment3.Controllers;

import com.example.assignment3.Models.Member;
import com.example.assignment3.Services.Member_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/members")
public class Member_Controller {

    @Autowired
    private Member_Service memberService;

    @GetMapping
    public List<Member> getAllMembers() { return memberService.getAllMembers(); }

    @GetMapping("/{id}")
    public Optional<Member> getMemberById(@PathVariable int id) { return memberService.getMemberById(id); }

    @GetMapping("/search")
    public List<Member> searchMembers(@RequestParam(required = false) String firstName,
                                      @RequestParam(required = false) String lastName) {
        if (firstName != null) return memberService.searchByFirstName(firstName);
        if (lastName != null) return memberService.searchByLastName(lastName);
        return new ArrayList<>();
    }

    @PostMapping
    public Member addMember(@RequestBody Member member) { return memberService.saveMember(member); }

    @PutMapping("/{id}")
    public Member updateMember(@PathVariable int id, @RequestBody Member member) {
        member.setMemberID(id);
        return memberService.saveMember(member);
    }

    @DeleteMapping("/{id}")
    public void deleteMember(@PathVariable int id) { memberService.deleteMember(id); }
}