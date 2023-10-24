package com.kb.java.university.service;

import com.kb.java.university.dto.StudentRequest;
import com.kb.java.university.dto.StudentResponse;
import com.kb.java.university.entity.Student;
import com.kb.java.university.exception.ObjectNotFoundException;
import com.kb.java.university.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public StudentResponse findStudent(Long id) {
        Student studentById = getStudentById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Not found student with given id: " + id));
        return new StudentResponse(studentById.getStudentId(), studentById.getName(), studentById.getLastName(), studentById.getEmail(), studentById.getGrades());
    }

    public StudentResponse findStudentByLastName(String lastName) {
        Student studentByName = studentRepository.findByLastName(lastName)
                .orElseThrow(() -> new ObjectNotFoundException("Not found student with given last name: " + lastName));
        return new StudentResponse(studentByName.getStudentId(), studentByName.getName(), studentByName.getLastName(), studentByName.getEmail(), studentByName.getGrades());
    }

    public List<StudentResponse> findAllStudents() {
        List<Student> allStudents = studentRepository.findAll();
        return allStudents.stream()
                .map(student -> new StudentResponse(student.getStudentId(), student.getName(), student.getLastName(), student.getEmail(), student.getGrades()))
                .collect(Collectors.toList());
    }

    public StudentResponse createStudent(StudentRequest studentRequest) {
        Student student = Student.builder()
                .name(studentRequest.getName())
                .lastName(studentRequest.getLastName())
                .email(studentRequest.getEmail())
                .build();
        studentRepository.save(student);
        return new StudentResponse(student.getStudentId(), student.getName(), student.getLastName(), student.getEmail());
    }

    public void editStudent(Long id, StudentRequest student) {
        Student studentToEdit = studentRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Not found student with given id: " + id));
        studentToEdit.setEmail(student.getEmail());
        studentToEdit.setName(student.getName());
        studentToEdit.setLastName(student.getLastName());
        studentRepository.save(studentToEdit);
    }

    public void removeStudent(Long id) {
        getStudentById(id)
                .ifPresentOrElse(student -> studentRepository.deleteById(id),
                        () -> new ObjectNotFoundException("Not found student with given id: " + id));
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }
}
