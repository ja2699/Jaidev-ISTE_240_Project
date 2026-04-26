package com.example.database_module.Service;

import Models.Course;
import Models.Student;
import Repositories.StudentDAO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class StudentService {

    @Autowired
    private StudentDAO studentDAO;

    @Autowired
    private Course courseDAO;

    public Student saveStudent(Student studentToSave){
        if(studentDAO.existsByEmail(studentToSave.getEmail()))
            throw new RuntimeException("This email already exists");

        if(studentToSave.getEmail() == null || studentToSave.getEmail().isEmpty())
            throw new IllegalArgumentException("Email cannot be empty");
    }
}
