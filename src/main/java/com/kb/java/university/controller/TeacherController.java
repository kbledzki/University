package com.kb.java.university.controller;

import com.kb.java.university.dto.TeacherRequest;
import com.kb.java.university.dto.TeacherResponse;
import com.kb.java.university.service.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class TeacherController {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }


    @PostMapping("/v1/teacher")
    public ResponseEntity<TeacherResponse> createStudent(@RequestBody TeacherRequest teacherRequest){
        return new ResponseEntity<>(teacherService.createTeacher(teacherRequest), HttpStatus.CREATED);
    }
}
