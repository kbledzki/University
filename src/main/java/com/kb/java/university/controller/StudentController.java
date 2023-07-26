package com.kb.java.university.controller;

import com.kb.java.university.dto.StudentRequest;
import com.kb.java.university.dto.StudentResponse;
import com.kb.java.university.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping("/v1/students")
    public ResponseEntity<List<StudentResponse>> getStudents() {
        return new ResponseEntity<>(studentService.findAllStudents(), HttpStatus.OK);
    }
    @GetMapping("/v1/students/{id}")
    public StudentResponse getStudent (@PathVariable Long id){
        return studentService.findById(id);
    }

    @PostMapping("/v1/students")
    public ResponseEntity<StudentResponse> createStudent(@RequestBody StudentRequest student){
        return new ResponseEntity<>(studentService.createStudent(student), HttpStatus.CREATED);
    }




}
