package com.kb.java.university.service;

import com.kb.java.university.dto.GradeCheckByStudentResponseDto;
import com.kb.java.university.dto.GradeRequest;
import com.kb.java.university.dto.GradeResponse;
import com.kb.java.university.dto.StudentResponse;
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
        return new GradeResponse(gradeById.getId(), gradeById.getGradeValue(), gradeById.getStudent(), gradeById.getTeacher());
    }

    public GradeResponse createGrade(GradeRequest gradeRequest, Long studentId, Long teacherId){
        Grade grade = Grade.builder()
                .gradeValue(gradeRequest.getGradeValue())
                .student(studentService.getStudentById(studentId).orElseThrow(()-> new ObjectNotFoundException("Not found student with given id: " + studentId)))
                .teacher(teacherService.getTeacherById(teacherId).orElseThrow(()-> new ObjectNotFoundException("Not found teacher with given id: " + teacherId)))
                .build();
        gradeRepository.save(grade);
        return  new GradeResponse(grade.getId(), grade.getGradeValue(), grade.getStudent(), grade.getTeacher());
    }
    public List<GradeCheckByStudentResponseDto> getGradesByStudent(Long id){
        List<Grade> gradesForStudent = gradeRepository.findAllByStudent(studentService.getStudentById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Not found student with given id: " + id)));
        return gradesForStudent.stream()
                .map(g -> new GradeCheckByStudentResponseDto(g.getGradeValue(),g.getTeacher()))
                .collect(Collectors.toList());
    }

    private Optional<Grade> getGradeById(Long id) {
        return gradeRepository.findById(id);
    }

}
