package com.kb.java.university.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = Grade.TABLE_NAME)
public class Grade {
    public static final String TABLE_NAME = "grade";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double gradeValue;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
}
