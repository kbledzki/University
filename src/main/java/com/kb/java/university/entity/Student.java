package com.kb.java.university.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
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
    @JsonManagedReference
    private List<Grade> grades = new ArrayList<>();
}


