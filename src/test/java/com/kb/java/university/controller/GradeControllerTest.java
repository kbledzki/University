package com.kb.java.university.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kb.java.university.service.GradeService;
import jakarta.transaction.Transactional;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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
}
