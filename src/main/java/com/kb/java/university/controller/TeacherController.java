package com.kb.java.university.controller;

import com.kb.java.university.dto.StudentResponse;
import com.kb.java.university.dto.TeacherRequest;
import com.kb.java.university.dto.TeacherResponse;
import com.kb.java.university.service.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class TeacherController {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/v1/teachers")
    public ResponseEntity<List<TeacherResponse>> getTeachers() {
        return new ResponseEntity<>(teacherService.findAllTeachers(), HttpStatus.OK);
    }

    @GetMapping("/v1/teacher/{id}")
    public ResponseEntity<TeacherResponse> getTeacher (@PathVariable Long id){
        return new ResponseEntity<>(teacherService.findTeacher(id), HttpStatus.OK);
    }

    @PostMapping("/v1/teacher")
    public ResponseEntity<TeacherResponse> createStudent(@RequestBody TeacherRequest teacherRequest){
        return new ResponseEntity<>(teacherService.createTeacher(teacherRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/v1/teacher/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void removeStudent(@PathVariable Long id){
        teacherService.removeTeacher(id);
    }
}
