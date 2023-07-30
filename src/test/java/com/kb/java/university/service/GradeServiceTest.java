package com.kb.java.university.service;

import com.kb.java.university.data.InitData;
import com.kb.java.university.dto.GradeCheckByStudentResponseDto;
import com.kb.java.university.dto.GradeResponse;
import com.kb.java.university.dto.StudentResponse;
import com.kb.java.university.entity.Grade;
import com.kb.java.university.entity.Student;
import com.kb.java.university.repository.GradeRepository;
import com.kb.java.university.repository.StudentRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GradeServiceTest {
    @Mock
    private GradeRepository gradeRepositoryMock;
    @InjectMocks
    private GradeService gradeService;

    @Test
    void shouldFindGradeByGivenId() {
        //given
        List<Grade> gradeToTest = InitData.gradesList();
        when(gradeRepositoryMock.findById(anyLong())).thenReturn(Optional.of(gradeToTest.get(0)));
        //when
        GradeResponse gradeById = gradeService.findGrade(1L);
        //then
        verify(gradeRepositoryMock, times(1)).findById(1L);
        assertEquals(5.0, gradeById.getGradeValue());
    }

    @Test
    void shouldGetAllGrades() {
        //given
        List<Grade> gradesToTest = InitData.gradesList();
        when(gradeRepositoryMock.findAll()).thenReturn(gradesToTest);
        //when
        List<GradeResponse> allGrades = gradeService.findAllGrades();
        //then
        assertThat(allGrades).hasSize(8);
    }

    @Test
    void shouldRemoveAGradeByGivenId() {
        //given
        Grade grade = new Grade();
        Long id = 1L;
        when(gradeRepositoryMock.findById(id)).thenReturn(Optional.of(grade));
        //when
        gradeService.removeGrade(id);
        //then
        verify(gradeRepositoryMock).deleteById(id);
    }
}
