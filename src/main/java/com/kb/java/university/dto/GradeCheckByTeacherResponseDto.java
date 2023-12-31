package com.kb.java.university.dto;

import com.kb.java.university.entity.Student;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class GradeCheckByTeacherResponseDto {
    private double gradeValue;
    private Student student;
}
