package com.kb.java.university.dto;

import com.kb.java.university.entity.Student;

public class Response {
    public static StudentResponse studentResponse(Student student) {
        return new StudentResponse(student.getName(), student.getLastName(), student.getEmail());
    }
}
