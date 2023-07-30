package com.kb.java.university.service;

import com.kb.java.university.data.InitData;
import com.kb.java.university.dto.StudentRequest;
import com.kb.java.university.dto.StudentResponse;
import com.kb.java.university.entity.Student;
import com.kb.java.university.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
class StudentServiceTest {
    @Mock
    private StudentRepository studentRepositoryMock;
    @InjectMocks
    private StudentService studentService;

    @Test
    void shouldFindStudentByGivenId() {
        //given
        List<Student> studentToTest = InitData.studentsList();
        when(studentRepositoryMock.findById(anyLong())).thenReturn(Optional.of(studentToTest.get(0)));
        //when
        StudentResponse studentById = studentService.findStudent(1L);
        //then
        verify(studentRepositoryMock, times(1)).findById(1L);
        assertEquals("Ntest1", studentById.getName());
    }

    @Test
    void shouldFindStudentByGivenLastName() {
        //given
        List<Student> studentToTest = InitData.studentsList();
        when(studentRepositoryMock.findByLastName(anyString())).thenReturn(Optional.of(studentToTest.get(0)));
        //when
        StudentResponse studentByLastname = studentService.findStudentByLastName("Ltest1");
        //then
        verify(studentRepositoryMock, times(1)).findByLastName("Ltest1");
        assertEquals("email1@test.com", studentByLastname.getEmail());
    }

    @Test
    void shouldGetAllStudents() {
        //given
        List<Student> studentToTest = InitData.studentsList();
        when(studentRepositoryMock.findAll()).thenReturn(studentToTest);
        //when
        List<StudentResponse> allStudents = studentService.findAllStudents();
        //then
        assertThat(allStudents).hasSize(4);
    }

    @Test
    void shouldRemoveAStudentByGivenId() {
        //given
        Student student = new Student();
        Long id = 1L;
        when(studentRepositoryMock.findById(id)).thenReturn(Optional.of(student));
        //when
        studentService.removeStudent(id);
        //then
        verify(studentRepositoryMock).deleteById(id);
    }
   }