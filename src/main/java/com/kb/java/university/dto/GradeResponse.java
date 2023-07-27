package com.kb.java.university.dto;

import com.kb.java.university.entity.Student;
import com.kb.java.university.entity.Teacher;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class GradeResponse {
    private Long id;
    private double gradeValue;
    private Student student;
    private Teacher teacher;
}
