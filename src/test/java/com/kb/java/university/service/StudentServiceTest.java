package com.kb.java.university.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kb.java.university.data.InitData;
import com.kb.java.university.dto.StudentRequest;
import com.kb.java.university.dto.StudentResponse;
import com.kb.java.university.entity.Student;
import com.kb.java.university.exception.ObjectNotFoundException;
import com.kb.java.university.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.jdbc.Sql;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
class StudentServiceTest {
    @Mock
    private StudentRepository studentRepositoryMock;
    @InjectMocks
    private StudentService studentService;

//@Test
//void shouldCreateStudent() {
//    //given
//    Student student = InitData.studentsList().get(0);
//    StudentRequest studentRe = new StudentRequest();
//    StudentResponse studentResponse = new StudentResponse();
//
//    when(studentRepositoryMock.save(student)).thenReturn(studentResponse);
//    //when
//  studentService.createStudent( new ModelMapper().map(student,StudentRequest.class));
    //then
//    verify(studentRepositoryMock, times(1)).save(student);
//    assertThat(studentService.createStudent(studentRe)).isEqualTo("Success");

//    assertEquals(student, savedStudent);
//}
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
//    @Test
//    void shouldRemoveAStudentByGivenId() {
//        //given
//        List<Student> studentToTest = InitData.studentsList();
//        studentToTest.remove(3);
//        when(studentRepositoryMock.deleteById(anyLong())).thenReturn(studentToTest);
//        //when
//        studentService.removeStudent(1L);
//        List<StudentResponse> allStudentAfterRemove = studentService.findAllStudents();
//        //then
//        assertThat(allStudentAfterRemove).hasSize(3);
//    }


}