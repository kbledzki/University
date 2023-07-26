package com.kb.java.university.controller;

import com.kb.java.university.dto.StudentDto;
import com.kb.java.university.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping("/v1/students")
    @ResponseStatus(HttpStatus.OK)
    public List<StudentDto> getStudents() {
        return studentService.findAllStudents();
    }


}
