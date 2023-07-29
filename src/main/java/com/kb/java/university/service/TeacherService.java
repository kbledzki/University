package com.kb.java.university.service;

import com.kb.java.university.dto.StudentRequest;
import com.kb.java.university.dto.TeacherRequest;
import com.kb.java.university.dto.TeacherResponse;
import com.kb.java.university.entity.Student;
import com.kb.java.university.entity.Teacher;
import com.kb.java.university.exception.ObjectNotFoundException;
import com.kb.java.university.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public TeacherResponse findTeacher(Long id) {
        Teacher teacherById = getTeacherById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Not found teacher with given id: " + id));
        return new TeacherResponse(teacherById.getTeacherId(), teacherById.getName(), teacherById.getLastName(), teacherById.getEmail(), teacherById.getGrades());
    }
    public List<TeacherResponse> findAllTeachers() {
        List<Teacher> allTeachers = teacherRepository.findAll();
        return allTeachers.stream()
                .map(teacher -> new TeacherResponse(teacher.getTeacherId(), teacher.getName(), teacher.getLastName(), teacher.getEmail(), teacher.getGrades()))
                .collect(Collectors.toList());
    }
    public TeacherResponse createTeacher(TeacherRequest teacherRequest) {
        Teacher teacher = Teacher.builder()
                .name(teacherRequest.getName())
                .lastName(teacherRequest.getLastName())
                .email(teacherRequest.getEmail())
                .build();
        teacherRepository.save(teacher);
        return new TeacherResponse(teacher.getTeacherId(), teacher.getName(), teacher.getLastName(), teacher.getEmail());
    }

    public void editTeacher(Long id, TeacherRequest teacher) {
        Teacher teacherToEdit = teacherRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Not found teacher with given id: " + id));
        teacherToEdit.setEmail(teacher.getEmail());
        teacherToEdit.setName(teacher.getName());
        teacherToEdit.setLastName(teacher.getLastName());
        teacherRepository.save(teacherToEdit);
    }
    public void removeTeacher(Long id) {
        getTeacherById(id).
                ifPresent(teacher -> teacherRepository.deleteById(id));
    }
    public Optional<Teacher> getTeacherById(Long id) {
        return teacherRepository.findById(id);
    }

}
