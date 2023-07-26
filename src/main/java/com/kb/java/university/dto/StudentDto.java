package com.kb.java.university.dto;

import com.kb.java.university.entity.Grade;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class StudentDto {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private List<Grade> grades = new ArrayList<>();
    public void addRow(Grade grade) {
        if (grades == null) {
            grades = new ArrayList<>();
        }
        grades.add(grade);
    }
    public void removeGrade(Long gradeId){
        grades.remove(gradeId);
    }
}
