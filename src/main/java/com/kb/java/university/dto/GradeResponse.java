package com.kb.java.university.dto;

import com.kb.java.university.entity.Student;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class GradeResponse {
    private Long id;
    private double gradeValue;
    private Student student;
}
