package com.kb.java.university.service;

import com.kb.java.university.dto.GradeRequest;
import com.kb.java.university.dto.GradeResponse;
import com.kb.java.university.entity.Grade;
import com.kb.java.university.repository.GradeRepository;
import org.springframework.stereotype.Service;

@Service
public class GradeService {
    private final GradeRepository gradeRepository;
    private final StudentService studentService;

    public GradeService(GradeRepository gradeRepository, StudentService studentService) {
        this.gradeRepository = gradeRepository;
        this.studentService = studentService;
    }
    public GradeResponse createGrade(GradeRequest gradeRequest){
        Grade grade = Grade.builder()
                .gradeValue(gradeRequest.getGradeValue())
                .student(gradeRequest.getStudent())
                .build();
        gradeRepository.save(grade);
        return  new GradeResponse(grade.getId(), grade.getGradeValue(), grade.getStudent());
    }

    public void setGradeToStudent(Long id, String lastName){
        studentService.findStudentByLastName(lastName);

    }

}
