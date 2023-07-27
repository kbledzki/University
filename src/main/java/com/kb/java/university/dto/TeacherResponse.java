package com.kb.java.university.dto;

import com.kb.java.university.entity.Grade;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class TeacherResponse {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private List<Grade> grades = new ArrayList<>();

    public TeacherResponse(Long id, String name, String lastName, String email) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
    }
}
