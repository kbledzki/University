package com.kb.java.university.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = Student.TABLE_NAME)
public class Student {
    public static final String TABLE_NAME = "student";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    private String email;
    @OneToMany (mappedBy = "student", cascade = CascadeType.ALL)
    private List<Grade> grades = new ArrayList<>();
}


