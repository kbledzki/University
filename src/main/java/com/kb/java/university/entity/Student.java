package com.kb.java.university.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = Student.TABLE_NAME)
public class Student {
    public static final String TABLE_NAME = "student";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;
    @NotNull
    @NotEmpty
    @Length(min = 2, max = 30)
    private String name;
    @NotNull
    @NotEmpty
    @Length(min = 2, max = 30)
    private String lastName;
    @NotNull
    @NotEmpty
    @Email
    private String email;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Grade> grades = new ArrayList<>();
}


