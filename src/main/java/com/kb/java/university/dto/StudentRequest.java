package com.kb.java.university.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode

public class StudentRequest {
    private String name;
    private String lastName;
    private String email;
}
