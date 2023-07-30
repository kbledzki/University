package com.kb.java.university.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = Teacher.TABLE_NAME)
public class Teacher {
    public static final String TABLE_NAME = "teacher";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teacherId;
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
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Grade> grades = new ArrayList<>();
}

