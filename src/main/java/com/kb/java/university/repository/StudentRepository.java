package com.kb.java.university.repository;

import com.kb.java.university.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository  extends JpaRepository<Student, Long> {
    Optional<Student> findByLastName(String lastName);

}
