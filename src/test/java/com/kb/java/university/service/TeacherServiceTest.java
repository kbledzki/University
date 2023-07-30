package com.kb.java.university.service;

import com.kb.java.university.data.InitData;
import com.kb.java.university.dto.StudentResponse;
import com.kb.java.university.dto.TeacherResponse;
import com.kb.java.university.entity.Student;
import com.kb.java.university.entity.Teacher;
import com.kb.java.university.repository.TeacherRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TeacherServiceTest {
    @Mock
    private TeacherRepository teacherRepositoryMock;
    @InjectMocks
    private TeacherService teacherService;
    @Test
    void shouldFindAllTeachers() {
        //given
        List<Teacher> teachersToTest = InitData.teachersList();
        when(teacherRepositoryMock.findById(anyLong())).thenReturn(Optional.of(teachersToTest.get(0)));
        //when
        TeacherResponse teacherById = teacherService.findTeacher(1L);
        //then
        verify(teacherRepositoryMock, times(1)).findById(1L);
        assertEquals("TNtest1", teacherById.getName());
    }

    @Test
    void shouldFindTeacherByGivenLastName() {
        //given
        List<Teacher> teachersTotest = InitData.teachersList();
        when(teacherRepositoryMock.findByLastName(anyString())).thenReturn(Optional.of(teachersTotest.get(0)));
        //when
        TeacherResponse teacherByLastName = teacherService.findTeacherByLastName("TLtest1");
        //then
        verify(teacherRepositoryMock, times(1)).findByLastName("TLtest1");
        assertEquals("temail1@test.com", teacherByLastName.getEmail());
    }

    @Test
    void shouldGetAllTeachers() {
        //given
        List<Teacher> teacherToTest = InitData.teachersList();
        when(teacherRepositoryMock.findAll()).thenReturn(teacherToTest);
        //when
        List<TeacherResponse> allTeachers = teacherService.findAllTeachers();
        //then
        assertThat(allTeachers).hasSize(4);
    }

    @Test
    void shouldRemoveATeacherByGivenId() {
        //given
        Teacher teacher = new Teacher();
        Long id = 1L;
        when(teacherRepositoryMock.findById(id)).thenReturn(Optional.of(teacher));
        //when
        teacherService.removeTeacher(id);
        //then
        verify(teacherRepositoryMock).deleteById(id);
    }
}
