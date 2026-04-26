package com.example.database_module.Controllers;

import Models.Student;
import com.example.database_module.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    // functions to save in a daybase

    @PostMapping("/save")
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        try{
            Student createdStudent = studentService.saveStudent(student);
            return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>((HttpHeaders)  null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents(){
        List<Student> students = this.studentService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id){
        Optionsl<Student> student = studentService.getStudentById(id);

    }
}
