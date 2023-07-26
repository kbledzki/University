package com.kb.java.university.service;

import com.kb.java.university.dto.StudentDto;
import com.kb.java.university.entity.Student;
import com.kb.java.university.exception.ObjectNotFoundException;
import com.kb.java.university.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    public StudentService(StudentRepository studentRepository, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }

    public StudentDto findById(Long id) {
        Student studentById = studentRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Not found student with given id: " + id));
        return modelMapper.map(studentById, StudentDto.class);
    }
    public List<StudentDto> findAllStudents() {
        List<Student> allStudents = studentRepository.findAll();
        return allStudents.stream()
                .map(student -> modelMapper.map(student, StudentDto.class))
                .collect(Collectors.toList());
    }
    public Student createStudent(Student student){
        return studentRepository.save(student);
    }
    public void editStudent (Long id, Student student){
        Student studentToEdit = studentRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Not found student with given id: " + id));

    }
}
