package com.kb.java.university.service;

import com.kb.java.university.dto.*;
import com.kb.java.university.entity.Grade;
import com.kb.java.university.exception.ObjectNotFoundException;
import com.kb.java.university.repository.GradeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GradeService {
    private final GradeRepository gradeRepository;
    private final StudentService studentService;
    private final TeacherService teacherService;

    public GradeService(GradeRepository gradeRepository, StudentService studentService, TeacherService teacherService) {
        this.gradeRepository = gradeRepository;
        this.studentService = studentService;
        this.teacherService = teacherService;
    }

    public GradeResponse findGrade(Long id) {
        Grade gradeById = getGradeById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Not found grade with given id: " + id));
        return new GradeResponse(gradeById.getGradeId(), gradeById.getGradeValue(), gradeById.getStudent(), gradeById.getTeacher());
    }

    public List<GradeResponse> findAllGrades() {
        List<Grade> allGrades = gradeRepository.findAll();
        return allGrades.stream()
                .map(grade -> new GradeResponse(grade.getGradeId(), grade.getGradeValue(), grade.getStudent(), grade.getTeacher()))
                .collect(Collectors.toList());
    }

    public GradeResponse createGrade(GradeRequest gradeRequest, Long studentId, Long teacherId){
        Grade grade = Grade.builder()
                .gradeValue(gradeRequest.getGradeValue())
                .student(studentService.getStudentById(studentId).orElseThrow(()-> new ObjectNotFoundException("Not found student with given id: " + studentId)))
                .teacher(teacherService.getTeacherById(teacherId).orElseThrow(()-> new ObjectNotFoundException("Not found teacher with given id: " + teacherId)))
                .build();
        gradeRepository.save(grade);
        return  new GradeResponse(grade.getGradeId(), grade.getGradeValue(), grade.getStudent(), grade.getTeacher());
    }

    public List<GradeCheckByStudentResponseDto> getGradesByStudent(Long id){
        List<Grade> gradesForStudent = gradeRepository.findAllByStudent(studentService.getStudentById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Not found student with given id: " + id)));
        return gradesForStudent.stream()
                .map(grade -> new GradeCheckByStudentResponseDto(grade.getGradeValue(),grade.getTeacher()))
                .collect(Collectors.toList());
    }

    public List<GradeCheckByTeacherResponseDto> getGradesByTeacher(Long id){
        List<Grade> gradesForTeacher = gradeRepository.findAllByTeacher(teacherService.getTeacherById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Not found teacher with given id: " + id)));
        return gradesForTeacher.stream()
                .map(grade -> new GradeCheckByTeacherResponseDto(grade.getGradeValue(),grade.getStudent()))
                .collect(Collectors.toList());
    }

    public void removeGrade(Long id) {
        getGradeById(id).
                ifPresent(grade -> gradeRepository.deleteById(id));
    }

    private Optional<Grade> getGradeById(Long id) {
        return gradeRepository.findById(id);
    }

}
