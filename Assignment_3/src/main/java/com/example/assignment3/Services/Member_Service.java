package com.example.assignment3.Services;

import com.example.assignment3.Models.Member;
import com.example.assignment3.Repositories.Member_Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class Member_Service {
    @Autowired
    Member_Repo member_repo;

    public List<Member> getAllMembers() { return member_repo.findAll(); }
    public Optional<Member> getMemberById(long id) { return member_repo.findById(id); }
    public List<Member> searchByFirstName(String firstName) { return member_repo.findByMemberFName(firstName); }
    public List<Member> searchByLastName(String lastName) { return member_repo.searchByLastName(lastName); }
    public Member saveMember(Member member) { return member_repo.save(member); }
    public void updateMemberName(long id, String firstName) { member_repo.updateMemberNameById(id, firstName); }
    public void deleteMember(long id) { member_repo.deleteById(id); }
}
