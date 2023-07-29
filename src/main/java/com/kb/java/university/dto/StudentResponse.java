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
public class StudentResponse {
    private Long studentId;
    private String name;
    private String lastName;
    private String email;
    private List<Grade> grades = new ArrayList<>();

    public StudentResponse(String name, String lastName, String email) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
    }

    public StudentResponse(Long studentId, String name, String lastName, String email) {
        this.studentId = studentId;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
    }

    public StudentResponse(String name, String lastName, String email, List<Grade> grades) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.grades = grades;
    }

    public void addGrade(Grade grade) {
        if (grades == null) {
            grades = new ArrayList<>();
        }
        grades.add(grade);
    }
    public void removeGrade(Long gradeId){
        grades.remove(gradeId);
    }
}
