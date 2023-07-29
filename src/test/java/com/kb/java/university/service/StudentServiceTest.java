package com.kb.java.university.service;

import com.kb.java.university.data.InitData;
import com.kb.java.university.dto.StudentRequest;
import com.kb.java.university.dto.StudentResponse;
import com.kb.java.university.entity.Student;
import com.kb.java.university.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.jdbc.Sql;

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

//@Test
//void shouldCreateStudent() {
//    //given
//    Student student = new Student();
//    when(studentRepositoryMock.save(student)).thenReturn(student);
//    //when
//    StudentResponse savedStudent = studentService.createStudent(new StudentRequest());
//    //then
////    verify(studentRepositoryMock, times(1)).save(student);
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
    void shouldGetAllStudents() {
        //given
        List<Student> studentToTest = InitData.studentsList();
        when(studentRepositoryMock.findAll()).thenReturn(studentToTest);
        //when
        List<StudentResponse> allStudents = studentService.findAllStudents();
        //then
        assertThat(allStudents).hasSize(4);
    }


}