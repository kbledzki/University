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

    @GetMapping("/v1/grade/student")
    public ResponseEntity<List<GradeCheckByStudentResponseDto>> getGradesForStudent(@RequestParam Long id) {
        return new ResponseEntity<>(gradeService.getGradesByStudent(id), HttpStatus.OK);
    }
    @GetMapping("/v1/grade/teacher")
    public ResponseEntity<List<GradeCheckByTeacherResponseDto>> getGradesForTeacher(@RequestParam Long id) {
        return new ResponseEntity<>(gradeService.getGradesByTeacher(id), HttpStatus.OK);
    }

    @GetMapping("/v1/grades")
    public ResponseEntity<List<GradeResponse>> getGrades() {
        return new ResponseEntity<>(gradeService.findAllGrades(), HttpStatus.OK);
    }

    @GetMapping("/v1/grade/{id}")
    public ResponseEntity<GradeResponse> getGrade (@PathVariable Long id){
        return new ResponseEntity<>(gradeService.findGrade(id), HttpStatus.OK);
    }

    @PostMapping("/v1/grade")
    public ResponseEntity<GradeResponse> createGrade (@RequestBody GradeRequest grade,
                                                      @RequestParam Long studentId,
                                                      @RequestParam Long teacherId)
                                                     {
        return new ResponseEntity<>(gradeService.createGrade(grade,studentId,teacherId), HttpStatus.CREATED);
    }

    @DeleteMapping("/v1/grade/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeGrade(@PathVariable Long id){
        gradeService.removeGrade(id);
    }
}
