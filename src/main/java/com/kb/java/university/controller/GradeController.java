package com.kb.java.university.controller;

import com.kb.java.university.dto.GradeRequest;
import com.kb.java.university.dto.GradeResponse;
import com.kb.java.university.dto.StudentRequest;
import com.kb.java.university.dto.StudentResponse;
import com.kb.java.university.service.GradeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class GradeController {
    private final GradeService gradeService;
    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @PostMapping("/v1/grade/{studentId}")
    public ResponseEntity<GradeResponse> createGrade (@RequestBody GradeRequest grade,
                                                      @PathVariable Long studentId)
                                                     {
        return new ResponseEntity<>(gradeService.createGrade(grade,studentId), HttpStatus.CREATED);
    }
}
