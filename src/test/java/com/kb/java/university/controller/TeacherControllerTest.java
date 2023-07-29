package com.kb.java.university.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kb.java.university.dto.TeacherResponse;
import com.kb.java.university.entity.Teacher;
import com.kb.java.university.service.TeacherService;
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
public class TeacherControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Sql({"/schema.sql"})
    @Sql({"/data.sql"})
    @Transactional
    void shouldReturnAllTeachers() throws Exception {
        //given
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/teachers"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("TeacherNametest1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lastName", Matchers.is("TeacherLastnametest1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email", Matchers.is("Teacheremail1@test.com")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", Matchers.is("TeacherNametest2")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].email", Matchers.is("Teacheremail2@test.com")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].email", Matchers.is("Teacheremail3@test.com")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[3].name", Matchers.is("TeacherNametest4")))
                .andReturn();
        //then
        assertThat(teacherService.findAllTeachers()).hasSize(4);
    }
    @Test
    @Sql({"/schema.sql"})
    @Sql({"/data.sql"})
    @Transactional
    void shouldReturnTeacherByGivenId() throws Exception {
        //given
        Teacher teacher = Teacher.builder().teacherId(111L).name("TeacherNametest1").lastName("TeacherLastnametest1").email("Teacheremail1@test.com").build();
        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/teacher/111"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is(teacher.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", Matchers.is(teacher.getLastName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.is(teacher.getEmail())))
                .andReturn();
        //then
        Teacher teacherToTest = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Teacher.class);
        assertThat(teacherToTest).isNotNull();
    }
    @Test
    @Sql({"/schema.sql"})
    @Sql({"/data.sql"})
    @Transactional
    void shouldAddTeacher() throws Exception {
        //given
        Teacher teacher = Teacher.builder().teacherId(135L).name("name").lastName("lastName").email("name@email.com").build();
        String teacherAsString = objectMapper.writeValueAsString(teacher);
        List<TeacherResponse> teachersBeforeAdd = new ArrayList<>(teacherService.findAllTeachers());
        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/teacher")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(teacherAsString))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();
        //then
        List<TeacherResponse> teachersAfterAdd = new ArrayList<>(teacherService.findAllTeachers());
        assertThat(teachersBeforeAdd).hasSize(4);
        assertThat(teachersAfterAdd).hasSize(5);
        assertThat(teachersAfterAdd.get(4).getName()).isEqualTo(teacher.getName());
    }
    @Test
    @Sql({"/schema.sql"})
    @Sql({"/data.sql"})
    @Transactional
    void shouldRemoveTeacherByGivenId() throws Exception {
        //given
        List<TeacherResponse> teachersBeforeDelete = new ArrayList<>(teacherService.findAllTeachers());
        //when
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/teacher/111"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andReturn();
        //then
        List<TeacherResponse> teachersAfterDelete = new ArrayList<>(teacherService.findAllTeachers());
        assertThat(teachersBeforeDelete).hasSize(4);
        assertThat(teachersAfterDelete).hasSize(3);
    }
    @Test
    @Sql({"/schema.sql"})
    @Sql({"/data.sql"})
    @Transactional
    void shouldEditTeacherByGivenId() throws Exception {
        //given
        Teacher teacher = Teacher.builder().teacherId(111L).name("TeacherNametest1").lastName("TeacherLastnametest1").email("Teacheremail1@test.com").build();
        Teacher teacherToEdit = Teacher.builder().teacherId(111L).name("TeacherRoman").lastName("TeacherNewlastname1").email("Teacheremail21@test.com").build();
        String teacherToEditAsString = objectMapper.writeValueAsString(teacherToEdit);
        //when
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/teacher/111")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(teacherToEditAsString))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andReturn();
        //then
        Teacher editedTeacher = teacherService.getTeacherById(111L).get();
        assertThat(editedTeacher.getName()).isNotEqualTo(teacher.getName());
        assertThat(editedTeacher.getLastName()).isNotEqualTo(teacher.getLastName());
        assertThat(editedTeacher.getName()).isEqualTo("TeacherRoman");
        assertThat(editedTeacher.getLastName()).isEqualTo("TeacherNewlastname1");
    }
}
