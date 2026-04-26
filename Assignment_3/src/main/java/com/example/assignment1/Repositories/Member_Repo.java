package com.example.assignment1.Repositories;

import com.example.assignment1.Models.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface Member_Repo extends JpaRepository<Member,Long> {
    List<Member> findByMemberFName(String memberFName);
    List<Member> findAll();

    @Query("SELECT m FROM Member m WHERE m.memberLName LIKE %:lastName%")
    List<Member> searchByLastName(String lastName);

    @Modifying
    @Transactional
    @Query("UPDATE Member m SET m.memberFName = :firstName WHERE m.memberID = :id")
    void updateMemberNameById(long id, String firstName);
}
