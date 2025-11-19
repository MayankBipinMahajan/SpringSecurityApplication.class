package com.mayank.SpringSecurity.controller;

import com.mayank.SpringSecurity.Entity.Student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class StudentController {

    List<Student> students = new ArrayList<>(Arrays.asList(
            new Student("Alice", 1001, "Computer"),
            new Student("Bob", 1002, "Mechanical"),
            new Student("Charlie", 1003, "Civil")
    ));

    @GetMapping("")
    public List getStudent(HttpServletRequest request){
        System.out.println(request.getSession().getId());
        return students;
    }

    @GetMapping("token")
    public CsrfToken getCSRF(HttpServletRequest request){
            System.out.println(request.getSession().getId());
            return (CsrfToken) request.getAttribute("_csrf");
    }

    @PostMapping("add")
    public Student addStudent(@RequestBody Student student){
        students.add(student);
        return student;
    }

}
