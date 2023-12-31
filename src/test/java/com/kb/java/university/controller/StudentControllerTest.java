package com.kb.java.university.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kb.java.university.dto.StudentResponse;
import com.kb.java.university.entity.Student;
import com.kb.java.university.service.StudentService;
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
class StudentControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private StudentService studentService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Sql({"/schema.sql"})
    @Sql({"/data.sql"})
    @Transactional
    void shouldReturnAllStudents() throws Exception {
        //given
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/students"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("Nametest1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lastName", Matchers.is("Lastnametest1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email", Matchers.is("email1@test.com")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", Matchers.is("Nametest2")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].email", Matchers.is("email2@test.com")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].email", Matchers.is("email3@test.com")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[3].name", Matchers.is("Nametest4")))
                .andReturn();
        //then
        assertThat(studentService.findAllStudents()).hasSize(4);
    }

    @Test
    @Sql({"/schema.sql"})
    @Sql({"/data.sql"})
    @Transactional
    void shouldReturnStudentByGivenId() throws Exception {
        //given
        Student student = Student.builder().studentId(134L).name("Nametest1").lastName("Lastnametest1").email("email1@test.com").build();
        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/student/134"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is(student.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", Matchers.is(student.getLastName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.is(student.getEmail())))
                .andReturn();
        //then
        Student studentToTest = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Student.class);
        assertThat(studentToTest).isNotNull();
    }

    @Test
    @Sql({"/schema.sql"})
    @Sql({"/data.sql"})
    @Transactional
    void shouldAddStudent() throws Exception {
        //given
        Student student = Student.builder().studentId(123L).name("name").lastName("lastName").email("name@email.com").build();
        String studentAsString = objectMapper.writeValueAsString(student);
        List<StudentResponse> studentsBeforeAdd = new ArrayList<>(studentService.findAllStudents());
        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/student")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(studentAsString))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();
        //then
        List<StudentResponse> studentsAfterAdd = new ArrayList<>(studentService.findAllStudents());
        assertThat(studentsBeforeAdd).hasSize(4);
        assertThat(studentsAfterAdd).hasSize(5);
        assertThat(studentsAfterAdd.get(4).getName()).isEqualTo(student.getName());
    }

    @Test
    @Sql({"/schema.sql"})
    @Sql({"/data.sql"})
    @Transactional
    void shouldRemoveStudentByGivenId() throws Exception {
        //given
        List<StudentResponse> studentsBeforeDelete = new ArrayList<>(studentService.findAllStudents());
        //when
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/student/134"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andReturn();
        //then
        List<StudentResponse> studentsAfterDelete = new ArrayList<>(studentService.findAllStudents());
        assertThat(studentsBeforeDelete).hasSize(4);
        assertThat(studentsAfterDelete).hasSize(3);
    }

    @Test
    @Sql({"/schema.sql"})
    @Sql({"/data.sql"})
    @Transactional
    void shouldEditStudentByGivenId() throws Exception {
        //given
        Student student = Student.builder().studentId(134L).name("Nametest1").lastName("Lastnametest1").email("email1@test.com").build();
        Student studentToEdit = Student.builder().studentId(134L).name("Lolek").lastName("Newlastname1").email("email1@test.com").build();
        String studentToEditAsString = objectMapper.writeValueAsString(studentToEdit);
        //when
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/student/134")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(studentToEditAsString))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andReturn();
        //then
        Student editedStudent = studentService.getStudentById(134L).get();
        assertThat(editedStudent.getName()).isNotEqualTo(student.getName());
        assertThat(editedStudent.getLastName()).isNotEqualTo(student.getLastName());
        assertThat(editedStudent.getName()).isEqualTo("Lolek");
        assertThat(editedStudent.getLastName()).isEqualTo("Newlastname1");
    }
}