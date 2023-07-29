package com.kb.java.university.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kb.java.university.dto.GradeResponse;
import com.kb.java.university.dto.StudentResponse;
import com.kb.java.university.entity.Grade;
import com.kb.java.university.entity.Student;
import com.kb.java.university.service.GradeService;
import jakarta.transaction.Transactional;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
public class GradeControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private GradeService gradeService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Sql({"/schema.sql"})
    @Sql({"/data.sql"})
    @Transactional
    void shouldReturnAllGrades() throws Exception {
        //given
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/grades"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].gradeValue", Matchers.is(4.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].gradeValue", Matchers.is(5.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].gradeValue", Matchers.is(5.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[7].gradeValue", Matchers.is(4.0)))
                .andReturn();
        //then
        assertThat(gradeService.findAllGrades()).hasSize(8);
    }

    @Test
    @Sql({"/schema.sql"})
    @Sql({"/data.sql"})
    @Transactional
    void shouldReturnAllGradesForStudentByGivenStudentId() throws Exception {
        //given
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/grade/student?id=134"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].gradeValue", Matchers.is(4.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].gradeValue", Matchers.is(5.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].gradeValue", Matchers.is(5.0)))
                .andReturn();
        //then
        assertThat(gradeService.getGradesByStudent(134L)).hasSize(4);
    }

    @Test
    @Sql({"/schema.sql"})
    @Sql({"/data.sql"})
    @Transactional
    void shouldReturnAllGradesForTeacherByGivenTeacherId() throws Exception {
        //given
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/grade/teacher?id=111"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].gradeValue", Matchers.is(4.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].gradeValue", Matchers.is(3.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].gradeValue", Matchers.is(4.0)))
                .andReturn();
        //then
        assertThat(gradeService.getGradesByTeacher(111L)).hasSize(3);
    }

    @Test
    @Sql({"/schema.sql"})
    @Sql({"/data.sql"})
    @Transactional
    void shouldReturnGradeByGivenId() throws Exception {
        //given
        Grade grade = Grade.builder().gradeId(156L).gradeValue(4.0).build();
        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/grade/156"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.gradeValue", Matchers.is(grade.getGradeValue())))
                .andReturn();
        //then
        Grade gradeToTest = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Grade.class);
        assertThat(gradeToTest).isNotNull();
    }

    @Test
    @Sql({"/schema.sql"})
    @Sql({"/data.sql"})
    @Transactional
    void shouldAddGrade() throws Exception {
        //given
        Grade grade = Grade.builder().gradeId(1546L).gradeValue(4.0).build();
        String gradeAsString = objectMapper.writeValueAsString(grade);
        List<GradeResponse> gradesBeforeAdd = new ArrayList<>(gradeService.findAllGrades());
        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/grade?teacherId=111&studentId=134")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(gradeAsString))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();
        //then
        List<GradeResponse> gradesAfterAdd = new ArrayList<>(gradeService.findAllGrades());
        assertThat(gradesBeforeAdd).hasSize(8);
        assertThat(gradesAfterAdd).hasSize(9);
        assertThat(gradesAfterAdd.get(4).getGradeValue()).isEqualTo(4.0);
    }

    @Test
    @Sql({"/schema.sql"})
    @Sql({"/data.sql"})
    @Transactional
    void shouldRemoveGradeByGivenId() throws Exception {
        //given
        List<GradeResponse> gradeBeforeDelete = new ArrayList<>(gradeService.findAllGrades());
        //when
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/grade/156"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andReturn();
        //then
        List<GradeResponse> gradeAfterDelete = new ArrayList<>(gradeService.findAllGrades());
        assertThat(gradeBeforeDelete).hasSize(8);
        assertThat(gradeAfterDelete).hasSize(7);
    }
}
