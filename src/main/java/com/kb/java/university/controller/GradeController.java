package com.kb.java.university.controller;

import com.kb.java.university.dto.*;
import com.kb.java.university.service.GradeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class GradeController {
    private final GradeService gradeService;
    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }


    @GetMapping("/v1/grade/student/{id}")
    public ResponseEntity<List<GradeCheckByStudentResponseDto>> getGradesForStudent(@PathVariable Long id) {
        return new ResponseEntity<>(gradeService.getGradesByStudent(id), HttpStatus.OK);
    }

    @PostMapping("/v1/grade/{teacherId}/{studentId}")
    public ResponseEntity<GradeResponse> createGrade (@RequestBody GradeRequest grade,
                                                      @PathVariable Long studentId,
                                                      @PathVariable Long teacherId)
                                                     {
        return new ResponseEntity<>(gradeService.createGrade(grade,studentId,teacherId), HttpStatus.CREATED);
    }
}
