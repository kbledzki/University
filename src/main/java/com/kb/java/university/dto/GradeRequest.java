package com.kb.java.university.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.kb.java.university.entity.Student;
import com.kb.java.university.entity.Teacher;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class GradeRequest {
    private double gradeValue;

    private Student student;

    private Teacher teacher;
}
