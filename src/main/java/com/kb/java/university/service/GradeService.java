package com.kb.java.university.service;

import com.kb.java.university.dto.GradeRequest;
import com.kb.java.university.dto.GradeResponse;
import com.kb.java.university.dto.StudentRequest;
import com.kb.java.university.dto.StudentResponse;
import com.kb.java.university.entity.Grade;
import com.kb.java.university.entity.Student;
import com.kb.java.university.exception.ObjectNotFoundException;
import com.kb.java.university.repository.GradeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GradeService {
    private final GradeRepository gradeRepository;
    private final StudentService studentService;


    public GradeService(GradeRepository gradeRepository, StudentService studentService) {
        this.gradeRepository = gradeRepository;
        this.studentService = studentService;
    }

    public GradeResponse findGrade(Long id) {
        Grade gradeById = getGradeById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Not found student with given id: " + id));
        return new GradeResponse(gradeById.getId(), gradeById.getGradeValue(), gradeById.getStudent());
    }

    public GradeResponse createGrade(GradeRequest gradeRequest, Long id){
        Grade grade = Grade.builder()
                .gradeValue(gradeRequest.getGradeValue())
                .student(studentService.getStudentById(id).orElseThrow(()-> new ObjectNotFoundException("Not found student with given id: " + id)))
                .build();
        gradeRepository.save(grade);
        return  new GradeResponse(grade.getId(), grade.getGradeValue(), grade.getStudent());
    }

    public void setGradeToStudent(GradeResponse grade, String lastName){
        GradeResponse gradeToSet = findGrade(grade.getId());
        studentService.findStudentByLastName(lastName);
    }

    private Optional<Grade> getGradeById(Long id) {
        return gradeRepository.findById(id);
    }

}
